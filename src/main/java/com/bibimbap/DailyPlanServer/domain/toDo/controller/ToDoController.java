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
    @PutMapping("/{todoId}") //ToDo 수정하기
    public ResponseEntity<ResultResponse> updateDToDo(@PathVariable Long todoId, @RequestBody ToDoUpdateDto toDoUpdateDto){
        return ResponseEntity.ok(ResultResponse.of(UPDATE_TODO_SUCCESS, toDoService.updateDToDo(todoId, toDoUpdateDto)));
    }
    @GetMapping("/{todoId}") //ToDo 가져오기
    public ResponseEntity<ResultResponse> findToDoById(@PathVariable Long todoId){
        ToDoResponseDto toDoResponseDto = toDoService.findToDoById(todoId);
        return ResponseEntity.ok(ResultResponse.of(GET_TODO_SUCCESS, toDoResponseDto));
    }

    @DeleteMapping("/{todoId}") //ToDo 삭제하기
    public ResponseEntity<ResultResponse> deleteToDo(@PathVariable Long todoId){
        return ResponseEntity.ok(ResultResponse.of(DELETE_TODO_SUCCESS, toDoService.deleteToDo(todoId)));
    }

    @GetMapping("/{dailyplanId}/list") //해당 날짜 ToDo 모두 가져오기
    public ResponseEntity<ResultResponse> findAllToDoById(@PathVariable Long dailyplanId){
        return ResponseEntity.ok(ResultResponse.of(GET_TODO_ALL_SUCCESS, toDoService.deleteToDo(dailyplanId)));
    }

    @PostMapping("/complete/{todoId}")
    public ResponseEntity<ResultResponse> completeToDo(@PathVariable Long todoId){
        return ResponseEntity.ok(ResultResponse.of(COMPLETE_TODO, toDoService.deleteToDo(todoId)));
    }

    @PostMapping("/fail/{todoId}")
    public ResponseEntity<ResultResponse> failToDo(@PathVariable Long todoId){
        return ResponseEntity.ok(ResultResponse.of(FAIL_TODO, toDoService.deleteToDo(todoId)));
    }
}

