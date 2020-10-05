package com.mcs.ipm.service;

import com.mcs.ipm.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ItemServiceFallback implements ItemService {

    @Override
    public Item getItemByName(String itemName) {
        log.info("Inside Fallback method");
        return new Item(5000L, "Default-Item", "Default-Item-Description", new BigDecimal(1000));
    }
}
