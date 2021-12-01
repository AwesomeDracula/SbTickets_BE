package com.example.sbtickets.dao;

import com.example.sbtickets.controller.TripBusController;
import com.example.sbtickets.dao.impl.TripBusCustomerInterface;
import com.example.sbtickets.entity.TripBusCustomer;
import com.example.sbtickets.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Transactional
@Service
public class TripBusCustomerDao implements TripBusCustomerInterface {
    private static final Logger logger = Logger.getLogger(TripBusController.class);
    @Override
    public void insertTripBusCustomer(TripBusCustomer tripBusCustomer) {
        try{
            // start a transaction
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(tripBusCustomer);

            //Commit the transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteTripBusCustomer(Integer tripBusId) {
        try{
            // start a transaction
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "DELETE FROM TripBusCustomer tripCustomer" + "WHERE tripCustomer.tripbusId = " + tripBusId;
            Query query = session.createQuery(hql);
            query.executeUpdate();

            //Commit the transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void editTripBusCustomer(TripBusCustomer tripBusCustomer) {
        try{
            // start a transaction
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE TripBusCustomer as tripCustomer SET tripCustomer.roleCar = :numSeat " + "WHERE tripCustomer.tripbusId = :tripBusId AND tripCustomer.customerId = :customerId";
            Query query = session.createQuery(hql);
            query.executeUpdate();

            //Commit the transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
