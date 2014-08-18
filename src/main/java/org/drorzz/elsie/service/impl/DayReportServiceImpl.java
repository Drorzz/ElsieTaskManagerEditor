package org.drorzz.elsie.service.impl;

import org.drorzz.elsie.dao.DayReportDAO;
import org.drorzz.elsie.domain.DayReport;
import org.drorzz.elsie.service.DayReportService;
import org.springframework.stereotype.Service;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Service
public class DayReportServiceImpl extends AbstractEntityServiceImpl<DayReport,DayReportDAO> implements DayReportService {
    @Override
    public void save(DayReport entity) {
        fillFieldsByDefaultValues(entity);
        super.save(entity);
    }

    private void fillFieldsByDefaultValues(DayReport obj){
        if(obj.getProjectText() == null){
            obj.setProjectText("");
        }
    }

}
