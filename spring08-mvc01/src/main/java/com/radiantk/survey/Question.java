package com.radiantk.survey;

import java.util.Collections;
import java.util.List;

// 개별 설문항목 데이터를 담기 위한 데이터
public class Question {

	private String title;
	private List<String> options;
	
	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public Question(String title) {
		// Collections.emptyList()는 EMPTY_LIST와 달리 상수가 아닌 빈 객체를 반환
		// List 타입을 반환하는 EMPTY_LIST와 달리, List<T>를 반환하기 때문에 타입 안정성을 보장
		this(title,  Collections.<String>emptyList());
	}
	
	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}

	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
