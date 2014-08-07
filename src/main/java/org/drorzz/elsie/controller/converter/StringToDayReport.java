package org.drorzz.elsie.controller.converter;

import org.apache.log4j.Logger;
import org.drorzz.elsie.domain.DayReport;
import org.drorzz.elsie.service.DayReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Drorzz on 07.08.2014.
 */
public class StringToDayReport implements Converter<String, DayReport> {
    private static final Logger logger = Logger.getLogger(StringToDayReport.class);

    @Autowired
    DayReportService dayReportService;

    @Override
    public DayReport convert(String source) {
        logger.info("Converting: ".concat(source));
        try {
            return dayReportService.getById(Integer.parseInt(source));
        } catch(NumberFormatException nfe){
            throw new RuntimeException(nfe.getMessage());
        }
    }
}
