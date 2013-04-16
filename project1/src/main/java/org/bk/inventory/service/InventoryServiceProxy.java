package org.bk.inventory.service;

import java.util.List;

import org.bk.inventory.types.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryServiceProxy implements InventoryService{
    private static Logger logger = LoggerFactory.getLogger(InventoryServiceProxy.class);
    private InventoryService proxied;

    public InventoryServiceProxy(InventoryService inventoryService){
        this.proxied = inventoryService;
    }
    
    @Override
    public Inventory create(Inventory inventory) {
        logger.info("before method: create");
        long start = System.nanoTime();

        Inventory inventoryCreated = this.proxied.create(inventory);
        
        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "create", (end-start)) );
        logger.info("after method: create");
        
        return inventoryCreated;
    }

    @Override
    public List<Inventory> list() {
        logger.info("before method: list");
        long start = System.nanoTime();       	
        
        List<Inventory> list =  this.proxied.list();
        
        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "list", (end-start)) );
        logger.info("after method: list");     
        
        return list;
    }

    @Override
    public Inventory findByVin(String vin) {
        logger.info("before method: findByVin");
        long start = System.nanoTime();   
        
        Inventory inventory = this.proxied.findByVin(vin);
        
        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "findByVin", (end-start)) );
        logger.info("after method: findByVin"); 
        
        return inventory;
    }

    @Override
    public Inventory update(Inventory inventory) {
        logger.info("before method: update");
        long start = System.nanoTime();       
        
        Inventory updatedInventory = this.proxied.update(inventory);
        
        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "update", (end-start)) );
        logger.info("after method: update");       
        
        return updatedInventory;
    }

    @Override
    public boolean delete(Long id) {
        logger.info("before method: delete");
        long start = System.nanoTime();    	
        
        boolean deleteStatus = this.proxied.delete(id);

        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "delete", (end-start)) );
        logger.info("after method: delete");
        return deleteStatus;
    }

    @Override
    public Inventory compositeUpdateService(String vin, String newMake) {
        logger.info("before method: compositeUpdate");
        long start = System.nanoTime();

        Inventory inventory =  this.proxied.compositeUpdateService(vin, newMake);

        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", "compositeUpdate", (end-start)) );
        logger.info("after method: compositeUpdate");

        
        return inventory;
    }
    
    
    
}
