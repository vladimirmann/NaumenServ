package Creation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public
    String provideUploadInfo() {
        return "upload";
    }


    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (request.isUserInRole("admin")||request.isUserInRole("moder")) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("src\\main\\resources\\static\\public\\files\\" + file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + file.getOriginalFilename()  + " в " + file.getOriginalFilename()  + "!";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + file.getOriginalFilename()  + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + file.getOriginalFilename()  + " потому что у вас нет доступа";
        }
    }

}