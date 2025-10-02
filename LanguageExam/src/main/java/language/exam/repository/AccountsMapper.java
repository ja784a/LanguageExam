package language.exam.repository;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Accounts;

@Mapper
public interface AccountsMapper {
	public void insertAccount(Accounts account);
	
	public int countAccounts(String mail);
	
	public Accounts selectAccount(String mail);
	
	public void updateAccount(Accounts account);
	
	public int countAccountsExceptLoginUser(String mail, Integer id);
	
	public void updatePassword(Accounts account);
}