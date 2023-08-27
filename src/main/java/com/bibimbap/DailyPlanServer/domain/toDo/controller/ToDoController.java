package com.bibimbap.DailyPlanServer.domain.toDo.controller;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoRequestDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoResponseDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoUpdateDto;
import com.bibimbap.DailyPlanServer.domain.toDo.service.ToDoService;
import com.bibimbap.DailyPlanServer.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bibimbap.DailyPlanServer.global.result.ResultCode.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("/{dailyPlanId}") //ToDo 추가하기
    public ResponseEntity<ResultResponse> saveToDo(@PathVariable Long dailyPlanId, @RequestBody ToDoRequestDto toDoRequestDto){
        Long toDoId = toDoService.saveToDo(dailyPlanId, toDoRequestDto);
        return ResponseEntity.ok(ResultResponse.of(SAVE_TODO_SUCCESS, toDoId));
    }
    @PutMapping("/{toDoId}") //ToDo 수정하기
    public ResponseEntity<ResultResponse> updateToDo(@PathVariable Long toDoId, @RequestBody ToDoRequestDto toDoRequestDto){
        boolean isUpdate = toDoService.updateToDo(toDoId, toDoRequestDto);
        return ResponseEntity.ok(ResultResponse.of(UPDATE_TODO_SUCCESS, isUpdate));
    }
    @GetMapping("/{toDoId}") //ToDo 가져오기
    public ResponseEntity<ResultResponse> getToDoById(@PathVariable Long toDoId){
        ToDoResponseDto toDoResponseDto = toDoService.findToDoById(toDoId);
        return ResponseEntity.ok(ResultResponse.of(GET_TODO_SUCCESS, toDoResponseDto));
    }
    @GetMapping("/{dailyPlanId}/list") //ToDo 다 가져오기
    public ResponseEntity<ResultResponse> getAllToDoByDailyPlanId(@PathVariable Long dailyPlanId){
        List<ToDoResponseDto> toDoResponseDtos = toDoService.findAllByDailyPlanId(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(GET_TODO_ALL_SUCCESS, toDoResponseDtos));
    }

    @DeleteMapping("/{toDoId}") //ToDo 삭제하기
    public ResponseEntity<ResultResponse> deleteToDo(@PathVariable Long toDoId){
        boolean isDelete = toDoService.deleteToDo(toDoId);
        return ResponseEntity.ok(ResultResponse.of(DELETE_TODO_SUCCESS, isDelete));
    }

    @PostMapping("/complete/{toDoId}")
    public ResponseEntity<ResultResponse> completeToDo(@PathVariable Long toDoId){
        boolean isComplete = toDoService.completeToDoById(toDoId);
        return ResponseEntity.ok(ResultResponse.of(COMPLETE_TODO, isComplete));
    }

    @PostMapping("/fail/{toDoId}")
    public ResponseEntity<ResultResponse> failToDo(@PathVariable Long toDoId){
        boolean isFail = toDoService.failToDoById(toDoId);
        return ResponseEntity.ok(ResultResponse.of(FAIL_TODO, isFail));
    }
}

