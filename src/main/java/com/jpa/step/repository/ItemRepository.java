package com.jpa.step.repository;

import com.jpa.step.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultStream().collect(Collectors.toList());
    }

    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }


    //TODO 이거만들면 거의 완성임
    public void update(Item item, int price) {

    }
}
