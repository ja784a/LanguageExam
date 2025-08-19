package language.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Places;

@Mapper
public interface PlacesMapper {
	
	public List<Places> selectAllPlaces();
	
	public Places selectPlace(Integer id);
}