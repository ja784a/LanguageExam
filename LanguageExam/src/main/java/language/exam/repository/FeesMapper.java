package language.exam.repository;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Fees;

@Mapper
public interface FeesMapper {
	public Fees selectFee(Integer subjectId, Integer gradeId);
}