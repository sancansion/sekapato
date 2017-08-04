package validator;

import javax.validation.Payload;

public @interface MailFormValidator {
    
    public String message() default "{MailFormValidator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}