package language.exam.domain.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Fees;
import language.exam.domain.exams.service.FeesService;
import language.exam.repository.FeesMapper;

@Service
public class FeesServiceImpl implements FeesService {
	
	@Autowired
	private FeesMapper mapper;
	
	public Fees getFee(Integer subjectId, Integer gradeId) {
		return mapper.selectFee(subjectId, gradeId);
	}
	
	public List<Fees> getFeesWithSubjectId(Integer subjectId) {
		return mapper.selectFeesWithSubjectId(subjectId);
	}
	
	public void updateFees(List<Fees> feesList) {
		mapper.updateFees(feesList);
	}
}