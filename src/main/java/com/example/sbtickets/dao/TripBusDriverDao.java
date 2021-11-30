package com.example.sbtickets.dao;

import com.example.sbtickets.controller.TripBusController;
import com.example.sbtickets.dao.impl.TripBusDriverImplement;
import com.example.sbtickets.entity.TripBusDriver;
import com.example.sbtickets.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TripBusDriverDao implements TripBusDriverImplement {
    private static final Logger logger = Logger.getLogger(TripBusController.class);

    @Override
    public void insertTripBusDriver(TripBusDriver tripBusDriver) {
        try{
            // start a transaction
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(tripBusDriver);

            //Commit the transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteTripBusDriver(Integer tripBusId) {
        try{
            // start a transaction
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "DELETE FROM TripBusDriver tripDriver " + "WHERE tripDriver.tripbusId = " + tripBusId;
            Query query = session.createQuery(hql);
            query.executeUpdate();

            //Commit the transaction
            session.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }

    }

    @Override
    public void editTripBusDriver(TripBusDriver tripBusDriver) {
        try{
            // start a transaction
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "UPDATE TripBusDriver as tripbus  SET tripbus.driverId = :driverId, tripbus.date = :date, tripbus.wages = :wages " + "WHERE tripbus.tripbusId = :tripBusId and tripbus.roleCar = :roleCar";
            Query query = session.createQuery(hql);
            query.setParameter("driverId", tripBusDriver.getDriverId());
            query.setParameter("date", tripBusDriver.getDate());
            query.setParameter("wages", tripBusDriver.getWages());
            query.setParameter("tripBusId", tripBusDriver.getTripbusId());
            query.setParameter("roleCar", tripBusDriver.getRoleCar());
            query.executeUpdate();

            //Commit the transaction
            session.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<TripBusDriver> getListBusDriver() {
        // start a transaction
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "FROM TripBusDriver";
            Query query = session.createQuery(hql);
            List<TripBusDriver> listData = query.getResultList();
            //Commit the transaction
            session.getTransaction().commit();
            return listData;

        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }
}
