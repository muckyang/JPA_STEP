package com.jpa.step.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }


    public void addOrderItem(OrderItem orderItem){

        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("\n").append(this.getUser()).append("님의 주문서").append(this.getId()).append("\n");
        List<OrderItem> list =this.getOrderItems();
        for(OrderItem oi : list){
            stringBuffer.append(oi.toString());
        }
        return stringBuffer.toString();
    }

    public enum OrderStatus {
        ORDER, CANCEL;
    }
}
