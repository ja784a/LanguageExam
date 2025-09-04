package language.exam.domain.exams.service;

import language.exam.domain.exams.model.Accounts;

public interface AccountsService {
	public void addAccount(Accounts account);
	
	public boolean isNotRegisteredMail(String mail);
	
	public Accounts getAccount(String mail);
	
	public void updateAccount(Accounts account);
}