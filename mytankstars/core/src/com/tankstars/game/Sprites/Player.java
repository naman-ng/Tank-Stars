package com.tankstars.game.Sprites;

import com.badlogic.gdx.physics.box2d.World;

public class Player {
    private final Integer playerId;
    private Tank tank;
    private boolean turn;

    public Player(World world, Integer playerId, Integer tankId){
        this.playerId = playerId;
        this.tank = new Tank(world, tankId, playerId);
    }



    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Tank getTank() {
        return tank;
    }
}
