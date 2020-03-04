//package life.yuanma.community.controller;
//
//import life.yuanma.community.dto.FileDTO;
//import life.yuanma.community.provider.UcloudProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import java.io.IOException;
//
//@Controller
//public class FileController {
//
//    @Autowired
//    private UcloudProvider ucloudProvider;
//
//    @RequestMapping("/file/upload")
//    @ResponseBody
//      public FileDTO upload(HttpRequest request){
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartRequest.getFile("");
//        try {
//            ucloudProvider.upload(file.getInputStream(),file.getContentType(),file.getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileDTO fileDTO = new FileDTO();
//          fileDTO.setSuccess(1);
//          fileDTO.setUrl("/images/loading.gif");
//          return fileDTO;
//      }
//}
