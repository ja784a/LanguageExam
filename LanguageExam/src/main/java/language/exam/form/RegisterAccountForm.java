package language.exam.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import language.exam.annotation.NotRegisteredEmail;
import lombok.Data;

@Data
public class RegisterAccountForm {
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotRegisteredEmail
	@NotBlank
	@Email
	@Size(max = 256)
	private String mail;
	
	@NotBlank
	@Size(max = 12)
	private String pass;
	private String passCheck;
	
	@AssertTrue
	public boolean isPasswordValid() {
		if (pass == null || pass.isEmpty()) {
			return true;
		} else {
			return pass.equals(passCheck);
		}
	}
	
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