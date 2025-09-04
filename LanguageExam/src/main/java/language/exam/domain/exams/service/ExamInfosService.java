package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.ExamInfos;

public interface ExamInfosService {
	public List<ExamInfos> getExamInfosForUsers(Integer subjectId, Integer gradeId, Integer placeId);
	
	public ExamInfos getExamInfo(Integer examId);
	
	public boolean isValidExamId(Integer examId);
}