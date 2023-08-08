package com.bibimbap.DailyPlanServer.domain.toDoList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}
