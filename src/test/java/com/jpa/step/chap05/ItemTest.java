package com.jpa.step.chap05;

import com.jpa.step.entity.Item;
import com.jpa.step.entity.Order;
import com.jpa.step.entity.User;
import com.jpa.step.repository.ItemRepository;
import com.jpa.step.repository.OrderRepository;
import com.jpa.step.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ItemTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemRepository ir;

    @Autowired
    private UserRepository ur;
    @Autowired
    private OrderRepository or;

    @Test
    @Transactional
    @Rollback(false)
    public void update(){
        ir.update(4L,25000);
        ir.update(5L,27000,30);
        logger.info("업데이트 완료");
        List<Item> list = ir.findAll();
        for(Item i : list){
            logger.info(i.toString());
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findOrdersByUser(){
        User user = ur.findOne(1L);
        for(Order o : or.findOrdersByUser(user)){
            logger.info(o.toString());
        }
    }
}
