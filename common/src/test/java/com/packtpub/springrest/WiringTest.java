package com.packtpub.springrest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packtpub.springrest.config.HibernateTestConfig;

/**
 * Test that the Spring wiring can be loaded.
 *
 * @author Ludovic Dewailly
 */
@RunWith(SpringJUnit4ClassRunner.class)                      // JUnit classics
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class WiringTest {

    @PersistenceContext                  // for injection: JPA classics
    private EntityManager entityManager;

    @Test
    public void test() {
        assertNotNull(entityManager);
    }
    
    @Test
    public void testConcatenate() {
        assertEquals("one", "one");
    }

}
