package language.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import language.exam.form.RegisterAccountForm;

@Controller
public class AccountController {
	
	@GetMapping("/register-account")
	public String getRegisterAccont(@ModelAttribute RegisterAccountForm form) {
		return "account/register-account";
	}
}