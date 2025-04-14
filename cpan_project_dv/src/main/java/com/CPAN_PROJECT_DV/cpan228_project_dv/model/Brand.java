package com.CPAN_A3_DV.cpan228_a3_dv.model;

public enum Brand{
    BALENCIAGA("Balenciaga"), 
    STONE_ISLAND("Stone Island"), 
    DIOR("Dior"), 
    GUCCI("Gucci"), 
    PRADA("Prada");

    public final String name;

    private Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}