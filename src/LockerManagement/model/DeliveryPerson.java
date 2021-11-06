package LockerManagement.model;

import lombok.NonNull;

public class DeliveryPerson extends LockerUser {
    public DeliveryPerson(@NonNull final Contact contact) {
        super(contact);
    }
}
