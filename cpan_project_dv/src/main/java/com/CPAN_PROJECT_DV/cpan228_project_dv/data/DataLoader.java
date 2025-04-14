package com.CPAN_A3_DV.cpan228_a3_dv.data;

import com.CPAN_A3_DV.cpan228_a3_dv.model.Item;
import com.CPAN_A3_DV.cpan228_a3_dv.model.Brand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemRepository itemRepo;

    public DataLoader(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        
        Item item1 = new Item(null, "Item 1", Brand.BALENCIAGA, 2022, 1500.0);
        Item item2 = new Item(null, "Item 2", Brand.DIOR, 2023, 2200.0);
        Item item3 = new Item(null, "Item 3", Brand.GUCCI, 2021, 1800.0);
        Item item4 = new Item(null, "Item 4", Brand.PRADA, 2020, 2500.0);
        Item item5 = new Item(null, "Item 5", Brand.STONE_ISLAND, 2024, 1200.0);

        itemRepo.save(item1);
        itemRepo.save(item2);
        itemRepo.save(item3);
        itemRepo.save(item4);
        itemRepo.save(item5);

        
        System.out.println("Database populated with sample items");
    }
}
