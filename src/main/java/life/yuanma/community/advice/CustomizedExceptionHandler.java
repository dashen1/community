package life.yuanma.community.advice;


import com.alibaba.fastjson.JSON;
import life.yuanma.community.dto.ResultDTO;
import life.yuanma.community.exception.CustomizedErrorCode;
import life.yuanma.community.exception.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizedExceptionHandler {
    //什么意思
//    @ResponseBody
//    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
    //ResponseBody 可将modelview转换
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, HttpServletResponse response,Throwable ex, Model model)  {
//        HttpStatus status = getStatus(request);
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json
            ResultDTO resultDTO;
            if(ex instanceof CustomizedException){
                resultDTO =  ResultDTO.errorResouce((CustomizedException)ex);
            }else {
                resultDTO =  ResultDTO.errorResouce(CustomizedErrorCode.SERVER_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else {
            //错误页面跳转
            if(ex instanceof CustomizedException){
                model.addAttribute("message",ex.getMessage());
            }else {
                model.addAttribute("message",CustomizedErrorCode.SERVER_ERROR.getMessage());
            }
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}
