package language.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.ExamInfos;

@Mapper
public interface ExamInfosMapper {
	public List<ExamInfos> selectExamInfosForUsers(Integer subjectId, Integer gradeId, Integer placeId);
	
	public ExamInfos selectExamInfo(Integer examId);
	
	public int countExamInfos(Integer examId);
}