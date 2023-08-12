package com.bibimbap.DailyPlanServer.domain.category.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByMemberId(Long memberId);
    Optional<Category> findByCategoryCode(String categoryCode);
}
