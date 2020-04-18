package com.deshawn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "findAllGames", query = "SELECT x FROM Game x")
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String system;
    private String price;
    private int quantity;

    public Game(String name, String system, String price, int quantity) {
        this.name = name;
        this.system = system;
        this.price = price;
        this.quantity = quantity;
    }
}
