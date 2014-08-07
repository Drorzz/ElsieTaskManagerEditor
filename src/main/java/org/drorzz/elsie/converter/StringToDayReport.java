package org.drorzz.elsie.converter;

import org.apache.log4j.Logger;
import org.drorzz.elsie.dao.DayReportDAO;
import org.drorzz.elsie.domain.DayReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Drorzz on 07.08.2014.
 */
public class StringToDayReport implements Converter<String, DayReport> {
    private static final Logger logger = Logger.getLogger(StringToDayReport.class);

    @Autowired
    DayReportDAO dayReportDAO;

    @Override
    public DayReport convert(String source) {
        logger.info("Converting: ".concat(source));
        try {
            return dayReportDAO.getById(Integer.parseInt(source));
        } catch(NumberFormatException nfe){
            throw new RuntimeException(nfe.getMessage());
        }
    }
}
