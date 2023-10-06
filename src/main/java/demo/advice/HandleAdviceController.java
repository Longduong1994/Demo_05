package demo.advice;

import demo.exception.RegisterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleAdviceController {
    @ExceptionHandler(RegisterException.class)
    public String registerFail(RegisterException registerException) {
        return registerException.getMessage();
    }
}
