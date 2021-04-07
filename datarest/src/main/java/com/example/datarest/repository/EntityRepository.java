package com.example.datarest.repository;

import com.example.datarest.entity.EntityA;
import com.example.datarest.projection.EntityAView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<EntityA, String> {


    @Query(value = "select a.ENTITY_AVAR2 as entityAVar2 from EntityA a, EntityB b where a.ENTITY_AVAR2 = b.ENTITY_BVAR2", nativeQuery = true)
    List<EntityAView> getEntity();

}
