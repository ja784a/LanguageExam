package language.exam.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Fees;

@Mapper
public interface FeesMapper {
	public Fees selectFee(Integer subjectId, Integer gradeId);
	
	public List<Fees> selectFeesWithSubjectId(Integer subjectId);
	
	public void updateFees(List<Fees> feesList);
}