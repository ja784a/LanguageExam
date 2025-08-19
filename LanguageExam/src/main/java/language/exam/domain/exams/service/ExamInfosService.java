package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.ExamInfos;

public interface ExamInfosService {
	public List<ExamInfos> getExamInfosForUsers();
}