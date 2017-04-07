package com.packtpub.springrest.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packtpub.springrest.inventory.config.InventoryTestConfig;

/**
 * Test that the Spring wiring can be loaded.
 *
 * @author Ludovic Dewailly
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InventoryTestConfig.class})
public class WiringTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void test() {
        assertNotNull(inventoryService);
    }
    
    @Test
    public void testConcatenateInventory() {
        assertEquals("one", "two");
    }
}
