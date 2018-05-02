package com.havban.congklak.models;


public interface Hole {

    int getNumberOfSeed();

    boolean incSeed();

    int takeAllSeed();

    void addSeed(int newSeeds);

    boolean isKacang();

    void setKacang(boolean status);

}
