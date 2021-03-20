package com.jpa.step.repository;

import com.jpa.step.entity.Item;
import com.jpa.step.entity.Order;
import com.jpa.step.entity.OrderItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(OrderItem orderItem){
        em.persist(orderItem);
    }

    public List<OrderItem> findOrderItemByOrder(Order order){
        return em.createQuery("select oi from OrderItem oi where oi.order = :order",OrderItem.class)
                .setParameter("order",order).getResultList();
    }

    public List<OrderItem> findOrderItemByItem(Item item){
        return em.createQuery("select oi from OrderItem oi where oi.item = :item",OrderItem.class)
                .setParameter("item",item).getResultList();
    }

}
