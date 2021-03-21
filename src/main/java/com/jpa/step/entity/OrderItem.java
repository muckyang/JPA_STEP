package com.jpa.step.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    //주문시 가격!
    private int orderPrice;


    private int count;

    public OrderItem() {

    }

    public OrderItem(Order order, Item item, int count) {
        this.setOrder(order);
        this.item = item;
        this.count = count;
    }

    public void setOrder(Order order) {
        this.order = order;
        order.getOrderItems().add(this);
    }

    public String toString(){
        return "\n" + this.getItem().getName() + " : " + this.getOrderPrice();
    }

}
