package language.exam.form;

import jakarta.validation.GroupSequence;

@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class, ValidGroup4.class} )
public interface GroupOrder {
	
}