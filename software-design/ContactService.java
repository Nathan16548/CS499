package com.snhu.project1;

import java.util.*;

/** In-memory Contact service. */
public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    /** Adds a contact; ID must be unique. */
    public void add(Contact contact) {
        if (contact == null) throw new IllegalArgumentException("contact cannot be null");
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("duplicate contactId: " + id);
        }
        contacts.put(id, contact);
    }

    /** Deletes a contact by id; returns true if removed. */
    public boolean delete(String contactId) {
        if (contactId == null) throw new IllegalArgumentException("contactId cannot be null");
        return contacts.remove(contactId) != null;
    }

    /** Updates allowed fields for an existing contact. */
    public void update(String contactId, String firstName, String lastName, String phone, String address) {
        Contact c = getRequired(contactId);
        if (firstName != null) c.setFirstName(firstName);
        if (lastName != null)  c.setLastName(lastName);
        if (phone != null)     c.setPhone(phone);
        if (address != null)   c.setAddress(address);
    }

    /** Helper to fetch or throw. */
    private Contact getRequired(String contactId) {
        if (contactId == null) throw new IllegalArgumentException("contactId cannot be null");
        Contact c = contacts.get(contactId);
        if (c == null) throw new NoSuchElementException("contact not found: " + contactId);
        return c;
    }

    /** For tests. */
    public Contact get(String contactId) {
        return contacts.get(contactId);
    }

    /** For tests. */
    public int size() { return contacts.size(); }
}
