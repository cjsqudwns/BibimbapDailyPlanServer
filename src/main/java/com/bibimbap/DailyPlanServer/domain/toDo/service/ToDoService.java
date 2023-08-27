package com.bibimbap.DailyPlanServer.domain.toDo.service;

import com.bibimbap.DailyPlanServer.domain.category.entity.Category;
import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryCode;
import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryRepository;
import com.bibimbap.DailyPlanServer.domain.category.service.CategoryService;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlanRepository;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoRequestDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoResponseDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoUpdateDto;
import com.bibimbap.DailyPlanServer.domain.toDo.entity.ToDo;
import com.bibimbap.DailyPlanServer.domain.toDo.entity.ToDoRepository;
import com.bibimbap.DailyPlanServer.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bibimbap.DailyPlanServer.domain.category.entity.CategoryCode.DAILY;
import static com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.ToDoStatus.COMPLETE;
import static com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.ToDoStatus.FAIL;
import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final DailyPlanRepository dailyPlanRepository;
    private final CategoryService categoryService;

    @Transactional
    public Long saveToDo(Long dailyPlanId, ToDoRequestDto toDoRequestDto){
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 Id에 해당하는 데일리 플랜이 없습니다 : " + dailyPlanId));
        ToDo toDo = toDoRequestDto.toEntity();
        Category category = categoryService.save(toDoRequestDto.getCategoryCode(), dailyPlan, dailyPlan.getMember());

        toDo.setDailyPlan(dailyPlan);
        toDo.setCategory(category);

        return toDoRepository.save(toDo).getId();
    }
    @Transactional
    public boolean updateToDo(Long toDoId, ToDoRequestDto toDoRequestDto){
        ToDo toDo = toDoRepository.findById(toDoId)
                .map(entity -> entity.update(toDoRequestDto))
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND,"해당 Id에 해당하는 투두리스트가 없습니다 : "+ toDoId));

        Category category = toDo.getCategory();
        String afterCategoryCode = toDoRequestDto.getCategoryCode();
        System.out.println("afterCategoryCode = " + afterCategoryCode);
        System.out.println("category = " + category.getCategoryCode());

        if(!category.getCategoryCode().getCode().equals(afterCategoryCode))
            category = categoryService.update(category, afterCategoryCode, toDo.getDailyPlan());
        toDo.setCategory(category);
        return true;
    }
    @Transactional(readOnly = true)
    public ToDoResponseDto findToDoById(Long todoId){
        ToDo toDo = toDoRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND,"해당 Id에 해당하는 투두리스트가 없습니다 : "+ todoId));
        return ToDoResponseDto.builder()
                .entity(toDo)
                .build();
    }

    public List<ToDoResponseDto> findAllByDailyPlanId(Long dailyPlanId){
       DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
               .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id의 데일리 플랜이 없습니다 : " + dailyPlanId));
       if(dailyPlan.getToDos().size() == 0)
           throw new EntityNotFoundException(DAILYPLAN_NOT_HAVE_TODOLIST, "해당 Id의 데일리 플랜에 작성된 투두리스트가 없습니다 : " + dailyPlanId);
       return dailyPlan.getToDos().stream()
               .map(ToDoResponseDto::new)
               .collect(Collectors.toList());
    }
    @Transactional
    public boolean deleteToDo(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));
        toDoRepository.delete(toDo);
        return true;
    }

    @Transactional
    public boolean completeToDoById(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));
        toDo.setToDoStatus(COMPLETE);
        toDo.getCategory().completeToDo();
        return true;
    }
    @Transactional
    public boolean failToDoById(Long toDoId){
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND, "해당 Id에 해당하는 투두리스트가 없습니다 : " + toDoId));
        toDo.setToDoStatus(FAIL);
        toDo.getCategory().cancelCompleteToDo();
        return true;
    }
}
