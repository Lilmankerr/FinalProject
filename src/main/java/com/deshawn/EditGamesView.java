package com.deshawn;

import com.deshawn.entity.Game;
import com.deshawn.service.GameService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditGamesView implements Serializable {
    private String name;
    private String system;
    private String price;
    private int quantity;

    @EJB
    private GameService gameService;
    private transient Game gameToUpdate;

    public void populateView(long playerId) {
        gameToUpdate = gameService.findById(playerId);
        this.setName(gameToUpdate.getName());
        this.setSystem(gameToUpdate.getSystem());
        this.setPrice(gameToUpdate.getPrice());
        this.setQuantity(gameToUpdate.getQuantity());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String save() {
        Game createdGame = new Game(name, system, price, quantity);
        if (gameToUpdate != null) {
            createdGame.setId(gameToUpdate.getId());
            gameService.update(createdGame);
        } else {
            gameService.create(createdGame);
        }
        nullifyFields();
        return "/game.xhtml?faces-redirect=true";
    }

    private void nullifyFields() {
        gameToUpdate = null;
        this.setName(null);
        this.setSystem(null);
        this.setPrice(null);
        this.setQuantity(0);
    }
}
