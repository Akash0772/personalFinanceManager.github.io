package com.akash.app1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akash.app1.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	
}
