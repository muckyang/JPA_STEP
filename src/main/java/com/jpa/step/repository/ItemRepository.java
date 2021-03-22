package com.jpa.step.repository;

import com.jpa.step.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i ",Item.class)
                .getResultList();
    }

    public void update(Long itemId, int price, int stockQuantity) {
        Item item = update(itemId,price);
        item.setStockQuantity(stockQuantity);
    }

    public Item update(Long itemId, int price) {
        Item item = em.find(Item.class,itemId);
        item.setPrice(price);
        return item;
    }
}
