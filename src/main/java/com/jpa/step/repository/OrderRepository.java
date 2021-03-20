package com.jpa.step.repository;

import com.jpa.step.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class,orderId);
    }

    public void delete(Long orderId) {
        Order order = this.findOne(orderId);
        logger.info("삭제 요청");
        em.remove(order);
        logger.info("삭제 완료!");
    }
}
