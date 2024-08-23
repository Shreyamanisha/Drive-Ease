import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class ElectricCar extends Vehicle {
    private double batteryCapacity;
    private double chargingTime;
    private ArrayList<String> compatibleChargingStations = new ArrayList<>();
    public void displayComplete() {

    }

    public void displayPrimary() {
    }

    public void getVehicleDetails() {
        super.getVehicleDetails();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter battery Capacity");
        batteryCapacity = scanner.nextDouble();

        System.out.println("Enter Charging Time (in hours)");
        chargingTime = scanner.nextDouble();

        System.out.println("Enter Compatible Charging Stations (type 'exit' to stop):");
        int i = 0;
        while (true) {
            System.out.print(i + " ) ");
            String station = scanner.nextLine();

            if (station.equalsIgnoreCase("exit")) {
                break; // Exit the loop if the user enters 'exit'
            }

            compatibleChargingStations.add(station);
            i++;
        }


    }
}
