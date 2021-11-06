package LockerManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class LockerUser {
    private final Contact contact;
}
