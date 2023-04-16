package com.akash.app1.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.app1.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
