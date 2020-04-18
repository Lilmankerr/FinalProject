package com.deshawn;

import com.deshawn.entity.Game;
import com.deshawn.service.GameService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AllGamesView {
    private List<Game> games;

    @EJB
    private GameService gameService;

    @PostConstruct
    public void init() {
        games = new ArrayList<>();
        games.addAll(gameService.getAll());
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> players) {
        this.games = players;
    }

    public String deleteGame(long id) {
        gameService.delete(gameService.findById(id));
        return "/game.xhtml?faces-redirect=true";
    }


    public String redirectToEditGame() {
        return "/editGame.xhtml?faces-redirect=true";
    }

}
