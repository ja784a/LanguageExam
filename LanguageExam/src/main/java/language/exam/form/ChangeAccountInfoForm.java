package language.exam.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeAccountInfoForm {
	private Integer id;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
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