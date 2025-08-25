package language.exam.domain.exams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Accounts;
import language.exam.domain.exams.service.AccountsService;
import language.exam.repository.AccountsMapper;

@Service
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	private AccountsMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void addAccount(Accounts account) {
		account.setPass(encoder.encode(account.getPass()));
		mapper.insertAccount(account);
	}
	
	public boolean isNotRegisteredMail(String mail) {
		if (mapper.countAccounts(mail) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Accounts getAccount(String mail) {
		return mapper.selectAccount(mail);
	}
}