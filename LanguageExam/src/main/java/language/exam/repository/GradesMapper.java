package language.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Grades;

@Mapper
public interface GradesMapper {
	
	public List<Grades> selectAllGrades();
	
	public Grades selectGrade(Integer id);
	
	public int countGrades(Integer gradeId);
	
	public List<Integer> selectIds();
	
	public List<Grades> selectExamDates(Integer subjectId, List<Integer> gradeIds, List<Integer> placeIds);
}