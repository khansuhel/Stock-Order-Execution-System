package com.sahaj.soes.poc.contrroller;

import com.sahaj.soes.poc.entities.Order;
import com.sahaj.soes.poc.exception.CorruptFileException;
import com.sahaj.soes.poc.service.StockProcessingEngineImpl;
import com.sahaj.soes.poc.util.CSVReaderUtil;
import com.sahaj.soes.poc.validator.FileValidatorImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sukhan on 12/24/2016.
 */
@Controller
public class SoesControllerImpl implements SoesController {

    private static final Logger LOG = Logger.getLogger(SoesController.class);

    @Autowired
    FileValidatorImpl fileValidator;

    @Autowired
    CSVReaderUtil csvReaderUtil;

    @Autowired
    StockProcessingEngineImpl stockProcessingEngine;

    @Override
    @RequestMapping(value = "/")
    public String home(){
        return "index";
    }

    @Override
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("inputFile") MultipartFile file){

        final Map<String, Object> model = new HashMap<>();
        List<String> errorList = new ArrayList<String>();
            try {
                List<Order> orderList = csvReaderUtil.readCSVToBean(file);
                model.put("orders", stockProcessingEngine.execute(orderList));
            } catch (IOException e) {
                LOG.error(e.getMessage());
                errorList.add(e.getMessage());
            }catch (CorruptFileException ex){
                LOG.error(ex.getMessage());
                errorList.add(ex.getMessage());
            }finally {
                if(errorList.size()>0){
                    model.put("errors",errorList);
                }
            }
        return new ModelAndView("index", model);
    }


}
