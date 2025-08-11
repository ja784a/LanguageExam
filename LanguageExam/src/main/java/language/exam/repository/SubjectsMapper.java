package language.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Subjects;

@Mapper
public interface SubjectsMapper {
	
	public List<Subjects> selectAllSubjects();
	
	public Subjects selectSubject(Integer id);
	
}