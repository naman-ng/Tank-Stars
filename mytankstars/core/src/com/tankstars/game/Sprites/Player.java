package com.tankstars.game.Sprites;

public class Player {
    private String name;
    private Tank tank;
    private boolean turn;
    private Integer id;

    public Player(String name, Integer id){
        this.name = name;
        this.id = id;
//        this.tank = new Tank()
    }

//    public update


    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Tank getTank() {
        return tank;
    }
}
