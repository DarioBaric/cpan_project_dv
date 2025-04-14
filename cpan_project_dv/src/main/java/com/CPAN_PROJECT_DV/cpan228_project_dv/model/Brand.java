package com.CPAN_PROJECT_DV.cpan228_project_dv.model;

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