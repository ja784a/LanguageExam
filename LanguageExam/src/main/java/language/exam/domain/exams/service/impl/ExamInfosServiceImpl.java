package language.exam.domain.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.repository.ExamInfosMapper;

@Service
public class ExamInfosServiceImpl implements ExamInfosService {
	@Autowired
	private ExamInfosMapper mapper;
	
	public List<ExamInfos> getExamInfosForUsers(Integer subjectId, Integer gradeId, Integer placeId) {
		return mapper.selectExamInfosForUsers(subjectId, gradeId, placeId);
	}
	
	public ExamInfos getExamInfo(Integer examId) {
		return mapper.selectExamInfo(examId);
	}
	
	public boolean isValidExamId(Integer examId) {
		if (mapper.countExamInfos(examId) == 1) {
			return true;
		} else {
			return false;
		}
	}
}