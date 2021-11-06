package LockerManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Package implements LockerItem {
    private final String id;
    private final Size size;

    @Override
    public Size getSize() {
        return this.size;
    }
}
