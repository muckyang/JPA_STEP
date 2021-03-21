package com.jpa.step.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;


<<<<<<< HEAD
=======
    public String toString(){
        return "\n"+
                "    품명 : "+this.getName() + " \n" +
                "    가격 : "+ this.getPrice() + "\n" +
                "   할인율 : "+this.getStockQuantity() + "%";
    }
>>>>>>> chapter05
}
