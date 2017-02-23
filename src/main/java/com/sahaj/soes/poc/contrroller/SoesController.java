package com.sahaj.soes.poc.contrroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sukhan on 12/24/2016.
 */
public interface SoesController {
    @RequestMapping(value = "/")
    String home();

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    ModelAndView uploadFile(@RequestParam("inputFile") MultipartFile file);
}
