package life.yuanma.community.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomizedException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizedException(CustomizedErrorCodeInterface errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

//    public CustomizedException(String message) {
//        this.message = message;
//    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
