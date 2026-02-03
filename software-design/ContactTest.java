package com.snhu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void ctor_validatesAllFields() {
        Contact c = new Contact("ID123", "John", "Doe", "1234567890", "1 Infinite Loop");
        assertEquals("ID123", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("1 Infinite Loop", c.getAddress());
    }

    @Test
    void id_isRequired_andMax10_andImmutable() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "A","B","1234567890","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("", "A","B","1234567890","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "A","B","1234567890","Addr"));
        Contact c = new Contact("1234567890", "A","B","1234567890","Addr");
        // no setter exists for id -> implicitly immutable
        assertEquals("1234567890", c.getContactId());
    }

    @Test
    void firstName_lastName_required_andMax10() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", null,"B","1234567890","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "ABCDEFGHIJK","B","1234567890","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A", null,"1234567890","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A", "ABCDEFGHIJK","1234567890","Addr"));
    }

    @Test
    void phone_required_andExactly10Digits() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A","B", null,"Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A","B", "123456789","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A","B", "12345678901","Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A","B", "123-456-7890","Addr"));
    }

    @Test
    void address_required_andMax30() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A","B","1234567890", null));
        String longAddr = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // 31 chars
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "A","B","1234567890", longAddr));
    }

    @Test
    void setters_validate() {
        Contact c = new Contact("1", "A","B","1234567890","Addr");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("abcdefghij"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
        c.setFirstName("Alice");
        c.setLastName("Smith");
        c.setPhone("0987654321");
        c.setAddress("123 Main St");
        assertEquals("Alice", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }
}
