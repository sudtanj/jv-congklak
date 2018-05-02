package com.havban.congklak;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();

        new Thread(game).start();

    }
}
