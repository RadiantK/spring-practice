package com.radiantk.survey;

import java.util.List;

// 설문 항목에 대한 답변 및 응답자 정보
public class AnsweredData {
	// String타입의 list(컬렉션)
	private List<String> responses;
	// 중첩 프로퍼티:respondent은 Respondent타입이며 age, location프로퍼티를 갖음
	private Respondent respondent; 
	
	public List<String> getResponses() {
		return responses;
	}

	public void setResponses(List<String> response) {
		this.responses = response;
	}

	public Respondent getRespondent() {
		return respondent;
	}

	public void setRespondent(Respondent respondent) {
		this.respondent = respondent;
	}

}
