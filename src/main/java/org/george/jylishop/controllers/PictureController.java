package org.george.jylishop.controllers;

import org.apache.commons.io.IOUtils;
import org.george.jylishop.db.PictureService;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yulya on 16.07.2016.
 */
@Controller
public class PictureController {

    @RequestMapping(method = RequestMethod.GET, value = "/resources/{path}/{filename:.+}")
    @ResponseBody
    public void getPictureFile(@PathVariable String filename,
                               @PathVariable String path,
                               HttpServletResponse response) {

        String pic = PictureService.ROOT + path + "/" + filename;
        String notFound = PictureService.ROOT + "/notfound/notfound.jpg";
        File file = new File(pic);
        if (!file.isFile()) {
            file = new File(notFound);
        }
        reponsedWithFile(file, response);
    }

    private void reponsedWithFile(File file, HttpServletResponse response) {

        try {
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {

        }
    }
}
