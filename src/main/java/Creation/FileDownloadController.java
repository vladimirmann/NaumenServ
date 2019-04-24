package Creation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.castor.mapping.AbstractMappingLoaderFactory.LOG;

@Controller
public class FileDownloadController {

    @Autowired
    private HttpServletRequest request;
    @RequestMapping(value = "/files/{file_name:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        // Прежде всего стоит проверить, если необходимо, авторизован ли пользователь и имеет достаточно прав на скачивание файла. Если нет, то выбрасываем здесь Exception
        if (request.isUserInRole("admin")||request.isUserInRole("moder")||request.isUserInRole("user")) {
            //Авторизованные пользователи смогут скачать файл
            Path file = Paths.get(("src\\main\\resources\\static\\public\\files\\"), fileName);
            if (Files.exists(file)) {
                response.setHeader("Content-disposition", "attachment;filename=" + fileName);
                response.setContentType("application/vnd.ms-excel");

                try {
                    Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException e) {
                    LOG.info("Error writing file to output stream. Filename was '{}'" + fileName, e);
                    throw new RuntimeException("IOError writing file to output stream");
                }
            }
        }
    }
}
