package language.exam.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordForm {
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
}