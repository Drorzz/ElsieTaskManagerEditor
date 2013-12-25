package org.drorzz.dao.impl;

import org.drorzz.dao.DayReportDAO;
import org.drorzz.model.DayReport;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.12.13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class DayReportDAOImpl extends AbstractDAOImpl<DayReport> implements DayReportDAO {

public DayReportDAOImpl() {
        super(DayReport.class);
}

@Override
public DayReport getByName(String name) {
        return (DayReport) getCurrentSession().bySimpleNaturalId(genericClass).load(name);
}

}
