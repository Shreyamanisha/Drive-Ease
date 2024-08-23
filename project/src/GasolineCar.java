import java.util.Scanner;

public class GasolineCar extends Vehicle {
    double fuelEfficiency;

    public GasolineCar(){}
    @Override
    public void displayComplete() {
        //System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Model: " + getManufacturer().toUpperCase() + " " + getModel());
        System.out.println("Year of Manufacture: " + getYearOfManufacture());
        System.out.println("Pick-up Location: " + getPickUpLocation());
        System.out.println("Minimum Driving Age: " + getMinDrivingAge());
        System.out.println("CO2 Emission: " + getCo2Emission());
        System.out.println("Horsepower: " + getHorsePower());
        System.out.println("Number of People: " + getNoOfPeople());
        System.out.println("Number of Doors: " + getNoOfDoors());

        //System.out.println("Renters: " + renters); // Assumes you want to print the list directly
        System.out.println("Available:");if(isAvailable()) System.out.println("Yes");else System.out.println("Try later");
    }
    public void displayPrimary() {
        System.out.println("Model: " + getManufacturer().toUpperCase() + " " + getModel());
        System.out.println("Pick-up Location: " + getPickUpLocation());
        System.out.println("Minimum Driving Age: " + getMinDrivingAge());
        System.out.println("Number of People: " + getNoOfPeople());
    }

    public void getVehicleDetails() {
        Scanner scanner = new Scanner(System.in);
        super.getVehicleDetails();
        System.out.println("Enter fuel Efficiency");
        fuelEfficiency = scanner.nextDouble();

    }

}