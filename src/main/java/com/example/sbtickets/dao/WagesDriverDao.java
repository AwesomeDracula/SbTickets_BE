package com.example.sbtickets.dao;

import com.example.sbtickets.bean.WagesDriverBean;
import com.example.sbtickets.dao.impl.WagesDriverImplement;
import com.example.sbtickets.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class WagesDriverDao implements WagesDriverImplement {
    private static final Logger logger = Logger.getLogger(WagesDriverDao.class);

    @Override
    public List<WagesDriverBean> getList(Integer driverId, String scrapTime) {
        // start a transaction
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {

            String queryString = "SELECT td.tripBus_id AS tripBusId, dr.driver_name AS driverName, dr.fixed_salary AS fixedSalary, " +
                    "td.wages AS wages, td.role_car AS roleCar\n" +
                    "FROM tripbus_driver td JOIN driver dr  ON td.driver_id = dr.id WHERE dr.id = :driverId and td.scrap_dateTime >= :scrapTimeFrom and " +
                    "td.scrap_dateTime <= :scrapTimeTo";
            Query query = session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(WagesDriverBean.class))
                    .setParameter("driverId", driverId)
                    .setParameter("scrapTimeFrom", scrapTime+"-01")
                    .setParameter("scrapTimeTo", scrapTime+"-30");
            List<WagesDriverBean> listData = query.list();
            session.getTransaction().commit();
            return listData;
        }
        catch (Exception ex){
            session.getTransaction().rollback();
            return null;
        }
    }
}
