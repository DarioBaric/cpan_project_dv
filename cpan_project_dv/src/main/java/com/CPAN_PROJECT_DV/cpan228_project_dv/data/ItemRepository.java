package com.CPAN_PROJECT_DV.cpan228_project_dv.data;

import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Item;
import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByBrand(Brand brand, Pageable pageable);

    List<Item> findAllByOrderByBrandAsc();

    @Query("SELECT i FROM Item i WHERE i.brand = :brand AND i.yearOfCreation = 2022")
    Page<Item> findByBrandAndYear(@Param("brand") Brand brand, Pageable pageable);
}
