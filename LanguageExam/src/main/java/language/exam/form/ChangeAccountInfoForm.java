package language.exam.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import language.exam.annotation.NotRegisteredEmailExceptLoginUser;
import lombok.Data;

@Data
public class ChangeAccountInfoForm {
	private Integer id;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotRegisteredEmailExceptLoginUser
	@NotBlank
	@Email
	@Size(max = 256)
	private String mail;
	
	@NotBlank
	@Size(max = 50)
	private String pref;
	
	@NotBlank
	@Size(max = 50)
	private String city;
	
	@NotBlank
	@Size(max = 50)
	private String town;
	
	@Size(max = 50)
	private String building;
}