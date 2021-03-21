package com.jpa.step.repository;

import com.jpa.step.entity.Order;
import com.jpa.step.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public List<Order> findOrdersByUser(User user){
        return em.createQuery("select o from Order o where o.user = :user",Order.class)
                .setParameter("user",user).getResultList();
    }


    public void cancelOrder(Long orderId){
        em.find(Order.class,orderId).setStatus(Order.OrderStatus.CANCEL);
    }

    public void delete(Long orderId) {
        Order order = this.findOne(orderId);
        logger.info("삭제 요청");
        em.remove(order);
        logger.info("삭제 완료!");
    }
}
