package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
	public Page<Test> findByTestId(Long testId, Pageable pageable);
}
