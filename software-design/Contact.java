package com.snhu.project1;

import java.util.Objects;

/** Contact model with validation per SNHU requirements. */
public final class Contact {
    private final String contactId; // required, max 10, not updatable
    private String firstName;       // required, max 10
    private String lastName;        // required, max 10
    private String phone;           // required, exactly 10 digits
    private String address;         // required, max 30

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = validateId(contactId);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    private static String validateId(String id) {
        if (id == null || id.length() == 0 || id.length() > 10) {
            throw new IllegalArgumentException("contactId must be 1..10 chars");
        }
        return id;
    }

    private static String validateNotNullAndLen(String value, int maxLen, String field) {
        if (value == null) throw new IllegalArgumentException(field + " cannot be null");
        if (value.length() > maxLen) throw new IllegalArgumentException(field + " max length is " + maxLen);
        return value;
    }

    public String getContactId() { return contactId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = validateNotNullAndLen(firstName, 10, "firstName");
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = validateNotNullAndLen(lastName, 10, "lastName");
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        if (phone == null) throw new IllegalArgumentException("phone cannot be null");
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        this.phone = phone;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        this.address = validateNotNullAndLen(address, 30, "address");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return contactId.equals(contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
}
