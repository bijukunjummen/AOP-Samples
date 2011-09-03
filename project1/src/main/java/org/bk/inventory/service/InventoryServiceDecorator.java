package org.bk.inventory.service;

import java.util.List;

import org.bk.inventory.types.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryServiceDecorator implements InventoryService{
    private static Logger logger = LoggerFactory.getLogger(InventoryServiceDecorator.class);
    private InventoryService decorated;

    public InventoryServiceDecorator(InventoryService inventoryService){
        this.decorated = inventoryService;
    }
    
    @Override
    public Inventory create(Inventory inventory) {
        logger.info("before method: create");
        long start = System.nanoTime();

        Inventory inventoryCreated = this.decorated.create(inventory);
        
        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "create", (end-start)) );
        logger.info("after method: create");
        
        return inventoryCreated;
    }

    @Override
    public List<Inventory> list() {
        return this.decorated.list();
    }

    @Override
    public Inventory findByVin(String vin) {
        return this.decorated.findByVin(vin);
    }

    @Override
    public Inventory update(Inventory inventory) {
        return this.decorated.update(inventory);
    }

    @Override
    public boolean delete(Long id) {
        return this.decorated.delete(id);
    }

    @Override
    public Inventory compositeUpdateService(String vin, String newMake) {
        logger.info("before method: compositeUpdate");
        long start = System.nanoTime();

        Inventory inventory =  this.decorated.compositeUpdateService(vin, newMake);

        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "compositeUpdate", (end-start)) );
        logger.info("after method: compositeUpdate");

        
        return inventory;
    }
    
    
    
}
