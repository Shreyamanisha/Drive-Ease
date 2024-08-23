import java.io.Serializable;
import java.util.Scanner;

public class Truck extends Vehicle {
    double height;
    double width;
    double loadingSize;
    int volume;
    int vehicleWeight;
    int maxWeightCharge;
    private double fuelEfficiency;

    public void displayComplete() {

    }

    public void displayPrimary() {
    }

    public void getVehicleDetails() {
        super.getVehicleDetails();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Fuel Efficiency");
        fuelEfficiency = scanner.nextDouble();

        System.out.print("Enter height: ");
        height = scanner.nextDouble();

        System.out.print("Enter width: ");
        width = scanner.nextDouble();

        System.out.print("Enter loading size: ");
        loadingSize = scanner.nextDouble();

        System.out.print("Enter volume: ");
        volume = scanner.nextInt();

        System.out.print("Enter vehicle weight: ");
        vehicleWeight = scanner.nextInt();

        System.out.print("Enter max weight charge: ");
        maxWeightCharge = scanner.nextInt();

    }
}
