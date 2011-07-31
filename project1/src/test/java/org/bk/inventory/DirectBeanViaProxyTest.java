package org.bk.inventory;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.bk.inventory.proxy.AuditProxy;
import org.bk.inventory.service.InventoryService;
import org.bk.inventory.service.DefaultInventoryService;
import org.bk.inventory.types.Inventory;
import org.junit.Before;
import org.junit.Test;

public class DirectBeanViaProxyTest {

    InventoryService inventoryService;
    
    @Before 
    public void setUp(){
        this.inventoryService = (InventoryService)AuditProxy.newInstance(new DefaultInventoryService());
    }
    
    @Test
    public void testInventoryService() {
        Inventory inventory = this.inventoryService.create(new Inventory("testmake", "testmodel","testtrim","testvin" ));
        assertThat(inventory.getId(), is(1L));
        
        assertThat(this.inventoryService.delete(1L), is(true));
        assertThat(this.inventoryService.compositeUpdateService("vin","newmake").getMake(),is("newmake"));
    }

}
