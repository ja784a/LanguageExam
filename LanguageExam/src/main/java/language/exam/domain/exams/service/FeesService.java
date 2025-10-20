package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.Fees;

public interface FeesService {
	public Fees getFee(Integer subjectId, Integer gradeid);
	
	public List<Fees> getFeesWithSubjectId(Integer subjectId);
	
	public void updateFees(List<Fees> feesList);
}