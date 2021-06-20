package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.TestDTO;
import com.example.demo.entity.Test;
import com.example.demo.entity.User;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final UserRepository userRepository;
	private final TestRepository testRepository;
	private final BCryptPasswordEncoder pe;
	
	// ����Ʈ ��ü ����
	public List<TestDTO> getList(int curPage, int pageSz){
		System.out.println("pageSz : " + pageSz);
		List<Test> list = testRepository
								.findByTestId(1L, PageRequest.of(curPage, pageSz))
								.getContent();
		System.out.println("here.");
		return list.stream()
				.map(test -> {
					return TestDTO.builder()
							.testCd(test.getTestCd())
							.val(test.getVal())
							.build();
				})
				.collect(Collectors.toList());
	}
	
	// ��ü ������ �� ����
	public int getPageSize(int pageSz) {
		return ((int)testRepository.count() - 1) / pageSz + 1;
	}
	
	public String register(RegisterDTO registerDTO) {
		userRepository.save(
					User.builder()
						.id(registerDTO.getId())
						.password(pe.encode(registerDTO.getPassword()))
						.build()
				);
		
		return "success";
	}
}
