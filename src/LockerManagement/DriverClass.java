package LockerManagement;

import LockerManagement.model.Locker;
import LockerManagement.model.LockerItem;
import LockerManagement.model.Package;
import LockerManagement.model.Size;
import LockerManagement.model.Slot;
import LockerManagement.repository.ILockerRepository;
import LockerManagement.repository.ISlotOtpRepository;
import LockerManagement.repository.LocakerRepositoryInMemory;
import LockerManagement.service.LockerService;
import LockerManagement.strategies.IRandomNumberGenerator;
import LockerManagement.strategies.ISlotAssignmentStrategy;
import LockerManagement.strategies.ISlotFilteringStrategy;
import LockerManagement.strategies.RandomGeneratorDefault;
import LockerManagement.strategies.SlotAssignmentStrategyRandom;
import LockerManagement.strategies.SlotFilteringStrategySizeBased;

import java.util.List;
import java.util.UUID;

public class DriverClass {
    public static void main(String args[]) {
        IRandomNumberGenerator iRandomNumberGenerator = new RandomGeneratorDefault();
        ISlotAssignmentStrategy iSlotAssignmentStrategy = new SlotAssignmentStrategyRandom(iRandomNumberGenerator);
        ISlotFilteringStrategy iSlotFilteringStrategy = new SlotFilteringStrategySizeBased();


        ILockerRepository iLockerRepository = new LocakerRepositoryInMemory();

        Locker locker1 = iLockerRepository.createLocker(UUID.randomUUID().toString());
        Locker locker2 = iLockerRepository.createLocker(UUID.randomUUID().toString());


        LockerService lockerService = LockerService.getInstance(iSlotAssignmentStrategy, iSlotFilteringStrategy, iLockerRepository);

        lockerService.createSlot(locker1, new Size(10, 15));
        lockerService.createSlot(locker2, new Size(5, 10));
        lockerService.createSlot(locker1, new Size(10, 20));

        LockerItem lockerItem1 = new Package(UUID.randomUUID().toString(), new Size(10, 20));
        LockerItem lockerItem2 = new Package(UUID.randomUUID().toString(), new Size(10, 15));
        List<Slot> slots = lockerService.getAllAvailableSlot();
        System.out.println("Before  Allocation=>" + slots);
        lockerService.allocateSlot(lockerItem1);
        lockerService.allocateSlot(lockerItem2);
        slots = lockerService.getAllAvailableSlot();
        System.out.println("After Allocation =>" + slots);

    }
}
