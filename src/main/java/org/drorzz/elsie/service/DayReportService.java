package org.drorzz.elsie.service;

import org.drorzz.elsie.dao.DayReportDAO;
import org.drorzz.elsie.domain.DayReport;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Drorzz on 08.08.2014.
 */
@Transactional(readOnly = true)
public interface DayReportService extends AbstractEntityService<DayReport, DayReportDAO> {


}
