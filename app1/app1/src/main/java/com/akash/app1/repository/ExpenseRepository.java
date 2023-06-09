package com.akash.app1.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.app1.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
}
