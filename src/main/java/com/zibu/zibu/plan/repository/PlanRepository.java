package com.zibu.zibu.plan.repository;

import com.zibu.zibu.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
