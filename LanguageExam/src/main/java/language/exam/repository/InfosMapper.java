package language.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Infos;

@Mapper
public interface InfosMapper {
	public List<Infos> selectAllInfos();
	
	public Infos selectInfo(Integer id);
}