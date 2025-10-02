package language.exam.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.ExamInfos;

@Mapper
public interface ExamInfosMapper {
	public List<ExamInfos> selectExamInfosForUsers(Integer subjectId, Integer gradeId, Integer placeId);
	
	public ExamInfos selectExamInfo(Integer examId);
	
	public int countExamInfos(Integer examId);
	
	public List<ExamInfos> selectExamDates(Integer subjectId, Integer gradeId, Integer placeId);
	
	public List<ExamInfos> selectExamInfosForAdmin(int size, int offset);
	
	public int countExamInfosForAdmin();
	
	public void insertExamInfo(ExamInfos examInfo);
	
	public int countExamInfosForNotDuplicatedExam(Integer subjectId, Integer gradeId, Integer placeId, Date examDate);
	
	public void updateExamInfo(ExamInfos examInfo);
	
	public void updateCancel(ExamInfos examInfo);
}