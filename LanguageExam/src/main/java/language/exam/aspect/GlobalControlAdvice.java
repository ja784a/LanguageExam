package language.exam.aspect;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControlAdvice {
	
	//例外処理(共通)
	@ExceptionHandler(Exception.class) 
	public String exceptionHandler(Exception e, Model model) {
		e.printStackTrace();
		
		return "error/error";
	}
	
}