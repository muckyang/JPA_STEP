package com.jpa.step.repository;

import com.jpa.step.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
<<<<<<< HEAD
import java.util.stream.Collectors;
=======
>>>>>>> chapter05

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

<<<<<<< HEAD
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultStream().collect(Collectors.toList());
    }

=======
>>>>>>> chapter05
    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }

<<<<<<< HEAD

    //TODO 이거만들면 거의 완성임
    public void update(Item item, int price) {

=======
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
>>>>>>> chapter05
    }
}
