package com.jpa.step.chap04;

import com.jpa.step.entity.Item;
import com.jpa.step.entity.Order;
import com.jpa.step.entity.OrderItem;
import com.jpa.step.entity.User;
import com.jpa.step.repository.ItemRepository;
import com.jpa.step.repository.OrderItemRepository;
import com.jpa.step.repository.OrderRepository;
import com.jpa.step.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest

public class UserTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository ur;
    @Autowired
    private OrderRepository or;
    @Autowired
    private ItemRepository ir;
    @Autowired
    private OrderItemRepository oir;


    // 유저 생성
    @Test
    @Transactional
    @Rollback(false)
    // 테스트지만 DB에 적용되도록 변경
    void userSave() {
        User user = new User();
        user.setCity("인천");
        user.setName("박재림");
        user.setStreet("김포대로122길");
        user.setZipcode("74201");
        ur.save(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    void saveItem() {
        Item item = new Item();
        item.setName("족발");
        item.setPrice(32000);
        item.setStockQuantity(0);
        ir.save(item);
    }

    @Test
    @Transactional
    @Rollback(false)
    void orderSave() {
        User user = ur.findOne(1L);

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setUser(user);
        order.setStatus(Order.OrderStatus.ORDER);

        //전체 item 리스트를 주문해버림!
        List<Item> itemList = ir.findAll();
        for (Item i : itemList) {
            order.addOrderItem(makeOrderItem(order, i, 1));
        }

        or.save(order);

        logger.info("OrderItem find By Item 0번");
        oir.findOrderItemByItem(itemList.get(0));
        logger.info("OrderItem find By Order");
        oir.findOrderItemByOrder(order);
        logger.warn("종료");
    }

    private OrderItem makeOrderItem(Order order, Item item, int count) {
        OrderItem orderItem = new OrderItem(order, item, count);
        orderItem.setOrderPrice(getOrderPrice(item.getPrice(), item.getStockQuantity()));
        oir.save(orderItem);
        return orderItem;
    }

    private int getOrderPrice(int price, int stockQuantity) {
        return Math.round(price * (100 - stockQuantity) / 100);
    }


    @Test
    @Transactional
    @Rollback(false)
    void getOrder() {
        User user = ur.findOne(1L);
        logger.info("유저 1의 주문 목록조회");
        for (Order o : user.getOrders()) {
            logger.info(o.getId() + "주문목록 출력한거임 ");
        }
    }


    @Test
    @Transactional
    @Rollback(false)
        // 테스트지만 DB에 적용되도록 변경
    void orderDelete() {
        or.delete(2L);
    }

    @Test
    @Rollback(false)
    @Transactional
    void ThreeAddOrders() {
        orderSave();
        orderSave();
        orderSave();
    }


}
