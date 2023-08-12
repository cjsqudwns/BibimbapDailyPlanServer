package com.bibimbap.DailyPlanServer.domain.toDo.service;

import com.bibimbap.DailyPlanServer.domain.category.entity.Category;
import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryCode;
import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryRepository;
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

import java.util.Optional;

import static com.bibimbap.DailyPlanServer.domain.category.entity.CategoryCode.DAILY;
import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final DailyPlanRepository dailyPlanRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveToDo(Long dailyPlanId, ToDoRequestDto toDoRequestDto){
        DailyPlan dailyPlanEntity = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 Id에 해당하는 데일리 플랜이 없습니다 : " + dailyPlanId));
        Optional<CategoryCode> isCategoryCode = CategoryCode.findByTitle(toDoRequestDto.getCategoryCode());
        if(!isCategoryCode.isPresent()){
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND, toDoRequestDto.getCategoryCode() + "는 존재하지 않는 카테고리 코드입니다");
        }
        //Optional<Category> category = categoryRepository.findByCategoryCode(toDoRequestDto.getCategoryCode());
        Category categoryEntity = categoryRepository.save(Category.builder()
                .member(dailyPlanEntity.getMember())
                .categoryCode(DAILY)
                .build());

        return toDoRepository.save(ToDo.builder()
                .category(categoryEntity)
                .dailyPlan(dailyPlanEntity)
                .title(toDoRequestDto.getTitle())
                .alarmStartTime(toDoRequestDto.getAlarmStartTime())
                .alarmEndTime(toDoRequestDto.getAlarmEndTime())
                .build()).getId();
    }
    @Transactional
    public boolean updateDToDo(Long todoId, ToDoUpdateDto toDoUpdateDto){
        ToDo todo = toDoRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND,"해당 Id에 해당하는 투두리스트가 없습니다 : "+ todoId));
        todo.update(toDoUpdateDto);
        return true;
    }
    @Transactional(readOnly = true)
    public ToDoResponseDto findToDoById(Long todoId){
        return toDoRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException(TODOLIST_NOT_FOUND,"해당 Id에 해당하는 투두리스트가 없습니다 : "+ todoId)).toToDoResponseDto();

    }
    @Transactional
    public boolean deleteToDo(Long todoId){
        Optional<ToDo> byId = toDoRepository.findById(todoId);
        if(!byId.isPresent())
            throw new EntityNotFoundException(TODOLIST_NOT_FOUND,"해당 Id에 해당하는 투두리스트가 없습니다 : "+ todoId);
        toDoRepository.deleteById(todoId);
        return true;
    }

//    @Transactional
//    public boolean completeToDo(Long todoId){
//
//    }

}
