package com.CPAN_PROJECT_DV.cpan228_project_dv.data;

import com.CPAN_PROJECT_DV.cpan228_project_dv.model.Distribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {

    @NonNull
    Page<Distribution> findAll(@NonNull Pageable pageable);

    List<Distribution> findAllByOrderByNameAsc();

    @Query("SELECT d FROM Distribution d WHERE " 
         + "SQRT(POWER(d.latitude - :latitude, 2) + POWER(d.longitude - :longitude, 2)) <= :radius")
    List<Distribution> findWithinRadius(
        @Param("latitude") double latitude,
        @Param("longitude") double longitude,
        @Param("radius") double radius
    );

    @Query("SELECT d FROM Distribution d WHERE d.name = :name") 
    List<Distribution> findByName(@Param("name") String name);
}
