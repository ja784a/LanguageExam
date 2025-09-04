package language.exam.domain.exams.service;

import language.exam.domain.exams.model.Fees;

public interface FeesService {
	public Fees getFee(Integer subjectId, Integer gradeid);
}