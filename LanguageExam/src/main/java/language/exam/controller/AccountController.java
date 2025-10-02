package language.exam.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import language.exam.domain.exams.model.Accounts;
import language.exam.domain.exams.service.AccountsService;
import language.exam.form.ChangeAccountInfoForm;
import language.exam.form.ChangePasswordForm;
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
	
	@GetMapping("/account-info") 
	public String getAccountInfo(@AuthenticationPrincipal(expression = "username") String mail, Model model) {
		Accounts account = accountsService.getAccount(mail);
		
		model.addAttribute("account", account);
		
		return "account/account-info";
		
	}
	
	@GetMapping("/change-account-info")
	public String getChangeAccountInfo(@ModelAttribute ChangeAccountInfoForm accountInfoForm, @AuthenticationPrincipal(expression = "username") String mail) {
		Accounts account = accountsService.getAccount(mail);
		accountInfoForm.setName(account.getName());
		accountInfoForm.setMail(account.getMail());
		accountInfoForm.setPref(account.getPref());
		accountInfoForm.setCity(account.getCity());
		accountInfoForm.setTown(account.getTown());
		accountInfoForm.setBuilding(account.getBuilding());
		
		return "account/change-account-info";
	}
	
	@PostMapping("/change-account-info")
	public String postChangeAccountInfo(@Validated ChangeAccountInfoForm accountInfoForm, BindingResult result, @AuthenticationPrincipal(expression = "id") Integer id, String mail) {
		accountInfoForm.setId(id);
		if (result.hasErrors()) {
			return getChangeAccountInfo(accountInfoForm, mail);
		} else {
			Accounts account = modelMapper.map(accountInfoForm, Accounts.class);
			accountsService.updateAccount(account);
		}
		
		return "redirect:/account-info";
	}
	
	
	@GetMapping("/change-password")
	public String getChangePassword(@ModelAttribute ChangePasswordForm passwordForm) {
		return "account/change-password";
	}
	
	@PostMapping("/change-password")
	public String postChangePassword(@Validated ChangePasswordForm passwordForm, BindingResult result, @AuthenticationPrincipal(expression = "id") Integer id) {
		if (result.hasErrors()) {
			return getChangePassword(passwordForm);
		} else {
			Accounts account = new Accounts();
			account.setPass(passwordForm.getPass());
			account.setId(id);
			accountsService.updatePassword(account);
			
			return "redirect:/change-account-info";
		}
	}
}