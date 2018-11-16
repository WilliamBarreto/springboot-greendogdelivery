package com.wb.casadocodigo.greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wb.casadocodigo.greendogdelivery.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
