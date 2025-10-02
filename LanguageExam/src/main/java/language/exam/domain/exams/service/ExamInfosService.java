package language.exam.domain.exams.service;

import java.util.Date;
import java.util.List;

import language.exam.domain.exams.model.ExamInfos;

public interface ExamInfosService {
	public List<ExamInfos> getExamInfosForUsers(Integer subjectId, Integer gradeId, Integer placeId);
	
	public ExamInfos getExamInfo(Integer examId);
	
	public boolean isValidExamId(Integer examId);
	
	public List<ExamInfos> getExamDates(Integer subjectId, Integer gradeId, Integer placeId);
	
	public List<ExamInfos> getExamInfosForAdmin(int size, int offset);
	
	public int countExamInfosForAdmin();
	
	public void addExamInfo(ExamInfos examInfo);

	public boolean isNotDuplicatedExam(Integer subjectId, Integer gradeId, Integer placeId, Date examDate);
	
	public void updateExamInfo(ExamInfos examInfo);
	
	public void updateCancel(ExamInfos examInfo);
}