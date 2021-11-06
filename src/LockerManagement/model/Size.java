package LockerManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Size {
    private final double width;
    private final double height;

    public boolean canAccommodate(@NonNull final Size size) {
        return this.width >= size.width && this.height >= size.height;
    }
}
