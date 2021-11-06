package LockerManagement.model;

import lombok.NonNull;

public class Buyer extends LockerUser {
    public Buyer(@NonNull final Contact contact) {
        super(contact);
    }
}
