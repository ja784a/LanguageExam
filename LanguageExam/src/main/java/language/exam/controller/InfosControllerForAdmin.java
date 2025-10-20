package language.exam.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import language.exam.domain.exams.model.Infos;
import language.exam.domain.exams.service.InfosService;
import language.exam.form.AddEditInfoForm;

@Controller
public class InfosControllerForAdmin {
	@Autowired
	private InfosService infosService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/admin-infos-for-admin")
	public String getAddInfos(Model model) {
		List<Infos> infos = infosService.getAllInfos();
		model.addAttribute("infos", infos);
		
		return "admin-infos/admin-infos";
	}
	
	@GetMapping("/add-info-for-admin")
	public String getAddInfo(@ModelAttribute AddEditInfoForm addInfoForm) {
		return "admin-infos/add-info";
	}
	
	@PostMapping("/add-info-for-admin")
	public String postAddEditInfo(@Validated AddEditInfoForm addEditInfoForm, BindingResult result) {
		if (result.hasErrors()) {
			return getAddInfo(addEditInfoForm);
		} else {
			Infos info = modelMapper.map(addEditInfoForm, Infos.class);
			infosService.addInfo(info);
			
			return "redirect:/admin-infos-for-admin";
		}
	}
	
	@GetMapping("/edit-info-for-admin/{id}")
	public String getEditInfo(@ModelAttribute AddEditInfoForm addEditInfoForm, @PathVariable("id") Integer id, Model model) {
		Infos info = infosService.getInfo(id);
		
		if (info == null) {
			return "error/error";
		} else {
			addEditInfoForm.setTitle(info.getTitle());
			addEditInfoForm.setContent(info.getContent());
			addEditInfoForm.setId(info.getId());
			model.addAttribute("id", info.getId());
			
			return "admin-infos/edit-info";
		}
	}
	
	@PostMapping("/edit-info-for-admin/{id}")
	public String postEditInfo(@Validated AddEditInfoForm addEditInfoForm, BindingResult result, @PathVariable("id") Integer id, Model model) {
		if (result.hasErrors()) {
			return getEditInfo(addEditInfoForm, id, model);
		} else {
			Infos info = modelMapper.map(addEditInfoForm, Infos.class);
			infosService.updateInfo(info);
			
			return "redirect:/admin-infos-for-admin";
		}
	}
	
	@GetMapping("/delete-info-for-admin/{id}")
	public String getDeleteInfo(@PathVariable("id") Integer id) {
		infosService.deleteInfo(id);
		
		return "redirect:/admin-infos-for-admin";
	}
	
}