package language.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import language.exam.domain.exams.model.Infos;
import language.exam.domain.exams.service.InfosService;

@Controller
public class InfosController {
	
	@Autowired
	private InfosService infosService;
	
	@GetMapping("/info-details/{id}")
	public String getInfosDetails(@PathVariable Integer id, Model model) {
		Infos info = infosService.getInfo(id);
		
		model.addAttribute("info", info);
		
		return "infos/info-details";
	}
}