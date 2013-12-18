package org.drorzz.dao;

import org.drorzz.model.DayReport;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 18.12.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public interface DayReportDAO extends AbstractDAO<DayReport> {

    public DayReport getByName(String name);
}
