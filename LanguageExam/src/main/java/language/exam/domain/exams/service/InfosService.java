package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.Infos;

public interface InfosService {
	public List<Infos> getAllInfos();
	
	public Infos getInfo(Integer id);
	
	public void addInfo(Infos info);
	
	public boolean isExistingId(Integer id);
	
	public void updateInfo(Infos info);
	
	public void deleteInfo(Integer id);
}