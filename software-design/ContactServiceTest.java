package com.snhu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    @Test
    void add_enforcesUniqueId_andStores() {
        ContactService svc = new ContactService();
        Contact c1 = new Contact("1","A","B","1234567890","Addr");
        svc.add(c1);
        assertEquals(1, svc.size());
        assertEquals(c1, svc.get("1"));

        Contact c2 = new Contact("1","C","D","0987654321","Addr2");
        assertThrows(IllegalArgumentException.class, () -> svc.add(c2));
    }

    @Test
    void delete_removesById() {
        ContactService svc = new ContactService();
        svc.add(new Contact("1","A","B","1234567890","Addr"));
        assertTrue(svc.delete("1"));
        assertEquals(0, svc.size());
        assertFalse(svc.delete("nope"));
    }

    @Test
    void update_changesAllowedFields_only() {
        ContactService svc = new ContactService();
        svc.add(new Contact("1","A","B","1234567890","Addr"));
        svc.update("1","Alice","Brown","1112223333","New Address");
        Contact c = svc.get("1");
        assertEquals("Alice", c.getFirstName());
        assertEquals("Brown", c.getLastName());
        assertEquals("1112223333", c.getPhone());
        assertEquals("New Address", c.getAddress());
    }
}
