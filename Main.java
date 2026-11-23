import java.util.Scanner;

class Vehicle {
    String vehicleNumber;
    String vehicleType;

    Vehicle(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }
}

class ParkingSlot {
    int slotId;
    Vehicle vehicle;
    boolean isAvailable;

    ParkingSlot(int slotId) {
        this.slotId = slotId;
        this.vehicle = null;
        this.isAvailable = true;
    }
}

public class Main {

    static ParkingSlot[] parkingSlots = new ParkingSlot[5];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < parkingSlots.length; i++) {
            parkingSlots[i] = new ParkingSlot(i + 1);
        }

        while (true) {

            System.out.println("\n--- PARKING SLOT ALLOCATION SYSTEM ---");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Display Parking Slots");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Vehicle Number: ");
                    String number = sc.next();

                    System.out.print("Enter Vehicle Type: ");
                    String type = sc.next();

                    boolean parked = false;

                    for (ParkingSlot slot : parkingSlots) {

                        if (slot.isAvailable) {

                            slot.vehicle = new Vehicle(number, type);
                            slot.isAvailable = false;

                            System.out.println("Vehicle parked successfully!");
                            System.out.println("Allocated Slot: " + slot.slotId);

                            parked = true;
                            break;
                        }
                    }

                    if (!parked) {
                        System.out.println("Parking is Full!");
                    }

                    break;

                case 2:
                    System.out.print("Enter Vehicle Number: ");
                    String removeNumber = sc.next();

                    boolean removed = false;

                    for (ParkingSlot slot : parkingSlots) {

                        if (!slot.isAvailable &&
                            slot.vehicle.vehicleNumber.equalsIgnoreCase(removeNumber)) {

                            slot.vehicle = null;
                            slot.isAvailable = true;

                            System.out.println("Vehicle removed successfully!");
                            removed = true;
                            break;
                        }
                    }

                    if (!removed) {
                        System.out.println("Vehicle not found!");
                    }

                    break;

                case 3:
                    System.out.println("\n--- PARKING SLOT STATUS ---");

                    for (ParkingSlot slot : parkingSlots) {

                        if (slot.isAvailable) {
                            System.out.println("Slot " + slot.slotId + " : Available");
                        } else {
                            System.out.println("Slot " + slot.slotId + " : Occupied - "
                                    + slot.vehicle.vehicleNumber);
                        }
                    }

                    break;

                case 4:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
