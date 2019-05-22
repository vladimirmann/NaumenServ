package Creation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@Controller
public class FileViewController {
    @Autowired
    private HttpServletRequest request;
    @RequestMapping(value="/", method= RequestMethod.GET)

    public
    String provideUploadInfo(Model model) {
        if (request.isUserInRole("admin") || request.isUserInRole("moder") || request.isUserInRole("user")) {
            File folder = new File("src\\main\\resources\\static\\public\\files\\");
            model.addAttribute("files", folder.listFiles());
            return "home";
        }
        return "home";
    }

}