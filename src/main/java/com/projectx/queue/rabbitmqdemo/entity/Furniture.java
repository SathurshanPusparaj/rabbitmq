package com.projectx.queue.rabbitmqdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Furniture {

    private String color;

    private String material;

    private String name;

    private double price;

    public Furniture(String name, String color, String material,  double price) {
        this.color = color;
        this.material = material;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
