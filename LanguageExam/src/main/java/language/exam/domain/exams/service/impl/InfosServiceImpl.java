package language.exam.domain.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Infos;
import language.exam.domain.exams.service.InfosService;
import language.exam.repository.InfosMapper;

@Service
public class InfosServiceImpl implements InfosService {
	
	@Autowired
	private InfosMapper mapper;
	
	public List<Infos> getAllInfos() {
		return mapper.selectAllInfos();
	}
	
	public Infos getInfo(Integer id) {
		return mapper.selectInfo(id);
	}
	
	public void addInfo(Infos info) {
		mapper.insertInfo(info);
	}
	
	public boolean isExistingId(Integer id) {
		if (mapper.countInfos(id) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updateInfo(Infos info) {
		mapper.updateInfo(info);
	}
	
	public void deleteInfo(Integer id) {
		mapper.deleteInfo(id);
	}
}