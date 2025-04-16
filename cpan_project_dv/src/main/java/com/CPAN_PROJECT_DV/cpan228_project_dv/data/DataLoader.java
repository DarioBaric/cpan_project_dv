package com.CPAN_PROJECT_DV.cpan228_project_dv.data;

import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Item;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Brand;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Distribution;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemRepository itemRepo;
    private final DistributionRepository distributionRepo;

    public DataLoader(ItemRepository itemRepo, DistributionRepository distributionRepo) {
        this.itemRepo = itemRepo;
        this.distributionRepo = distributionRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Distribution distribution = new Distribution(null, "Main Distribution", 37.7749, -122.4194, null);
        distributionRepo.save(distribution);

        Item item1 = new Item(null, "Item 1", Brand.BALENCIAGA, 2022, 1500.0, 10);
        item1.setDistribution(distribution);

        Item item2 = new Item(null, "Item 2", Brand.DIOR, 2023, 2200.0, 5);
        item2.setDistribution(distribution);

        Item item3 = new Item(null, "Item 3", Brand.GUCCI, 2021, 1800.0,15);
        item3.setDistribution(distribution);

        Item item4 = new Item(null, "Item 4", Brand.PRADA, 2020, 2500.0, 15);
        item4.setDistribution(distribution);

        Item item5 = new Item(null, "Item 5", Brand.STONE_ISLAND, 2024, 1200.0, 5);
        item5.setDistribution(distribution);

        itemRepo.save(item1);
        itemRepo.save(item2);
        itemRepo.save(item3);
        itemRepo.save(item4);
        itemRepo.save(item5);

        System.out.println("Database populated with sample distributions and items");
    }
}