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
	
	public List<ExamInfos> getExamInfosForUsers() {
		return mapper.selectExamInfosForUsers();
	}
}