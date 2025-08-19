package language.exam.domain.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Places;
import language.exam.domain.exams.service.PlacesService;
import language.exam.repository.PlacesMapper;

@Service
public class PlacesServiceImpl implements PlacesService {
	
	@Autowired
	private PlacesMapper mapper;
	
	public List<Places> getAllPlaces() {
		return mapper.selectAllPlaces();
	}
	
	public Places getPlace(Integer id) {
		return mapper.selectPlace(id);
	}
}