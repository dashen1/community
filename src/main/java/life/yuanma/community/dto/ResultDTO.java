package life.yuanma.community.dto;

import life.yuanma.community.exception.CustomizedErrorCode;
import life.yuanma.community.exception.CustomizedException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;
//使用泛型 可以传不同参数
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorResouce(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorResouce(CustomizedErrorCode customizedErrorCode) {
        return errorResouce(customizedErrorCode.getCode(),customizedErrorCode.getMessage());
    }

    public static ResultDTO loginSuccess(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("回复成功！");
        return resultDTO;
    }

    public static ResultDTO errorResouce(CustomizedException ex) {
        return errorResouce(ex.getCode(),ex.getMessage());
    }
    public static <T> ResultDTO errorResouce(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！");
        resultDTO.setData(t);
        return resultDTO;
    }
}
