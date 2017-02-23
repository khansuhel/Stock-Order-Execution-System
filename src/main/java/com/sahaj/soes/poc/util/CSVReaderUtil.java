package com.sahaj.soes.poc.util;

import com.sahaj.soes.poc.entities.Order;
import com.sahaj.soes.poc.exception.CorruptFileException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhan on 1/13/2017.
 */
@Service
public class CSVReaderUtil {

    private static final Logger LOG = Logger.getLogger(CSVReaderUtil.class);

    public List<Order> readCSVToBean(MultipartFile file) throws IOException, CorruptFileException {
        LOG.error("Inside readCSVToBean method");
        ICsvBeanReader beanReader = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            beanReader = new CsvBeanReader(new InputStreamReader(file.getInputStream()),
                    CsvPreference.STANDARD_PREFERENCE);
            // the name mapping provide the basis for bean setters
            final String[] nameMapping = new String[]{"stockID","side","company","quantity"};
            //just read the header, so that it don't get mapped to Employee object
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            Order order;
            while ((order = beanReader.read(Order.class, nameMapping,
                    processors)) != null) {
                orders.add(order);
            }
        }catch (Exception ex){
            LOG.error(ex.getMessage());
            throw new CorruptFileException(ex.getMessage());
        }
        finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
        return orders;
    }


    private CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new UniqueHashCode(), // Stock ID (must be unique)
                new NotNull(), // Side
                new NotNull(), // Company
                new NotNull() // Quantity
        };
        return processors;
    }

}
