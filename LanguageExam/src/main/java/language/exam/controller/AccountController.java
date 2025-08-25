package language.exam.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import language.exam.domain.exams.model.Accounts;
import language.exam.domain.exams.service.AccountsService;
import language.exam.form.RegisterAccountForm;

@Controller
public class AccountController {
	@Autowired
	private AccountsService accountsService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/register-account")
	public String getRegisterAccount(@ModelAttribute RegisterAccountForm form) {
		return "account/register-account";
	}
	
	@PostMapping("/register-account")
	public String postRegisterAccount(@ModelAttribute @Validated RegisterAccountForm form, BindingResult result) {
		if (result.hasErrors()) {
			return getRegisterAccount(form);
		} else {
			Accounts account = modelMapper.map(form, Accounts.class);
			
			accountsService.addAccount(account);
			
			return "redirect:/login";
		}
	}
}