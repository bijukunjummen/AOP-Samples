package org.bk.inventory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.bk.inventory.service.DefaultInventoryService;
import org.bk.inventory.service.InventoryService;
import org.bk.inventory.types.Inventory;
import org.junit.Before;
import org.junit.Test;

public class DirectBeanTest {

    InventoryService inventoryService;
    
    @Before 
    public void setUp(){
        this.inventoryService = new DefaultInventoryService();
    }
    
    @Test
    public void testInventoryService() {
        Inventory inventory = this.inventoryService.create(new Inventory("testmake", "testmodel","testtrim","testvin" ));
        assertThat(inventory.getId(), is(1L));
        this.inventoryService.findByVin("vin");
        assertThat(this.inventoryService.compositeUpdateService("vin","newmake").getMake(),is("newmake"));
    }

}
