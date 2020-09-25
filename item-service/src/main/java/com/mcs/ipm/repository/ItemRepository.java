package com.mcs.ipm.repository;

import com.mcs.ipm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String itemName);
}
