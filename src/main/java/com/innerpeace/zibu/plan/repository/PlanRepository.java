package com.innerpeace.zibu.plan.repository;

import com.innerpeace.zibu.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
