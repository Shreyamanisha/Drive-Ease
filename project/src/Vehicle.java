import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;


public abstract class Vehicle implements Serializable {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1000); // Starting ID

    private String registrationNumber;
    private long vehicleID;
    private String manufacturer;
    private String model;

    private int yearOfManufacture;
    private String transmission;
    private String pickUpLocation;
    private int minDrivingAge;

    private int co2Emission;
    private int horsePower;
    private int noOfPeople;
    private int noOfDoors;

    private transient LinkedList<Customer> renters;
    private boolean available;
    private double ratePerHour;

    public abstract void displayPrimary();
    public abstract void displayComplete();

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public long getVehicleID() {

        return vehicleID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getTransmission(){ return transmission;}

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public int getMinDrivingAge() {
        return minDrivingAge;
    }

    public int getCo2Emission() {
        return co2Emission;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public LinkedList<Customer> getRenters() {
        return renters;
    } // For the Admin to see previous renters

    public boolean isAvailable() {
        return available;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRenters(Customer renter) {
        this.renters.add(renter);
    }
    public void setVehicleID(){ vehicleID = ID_GENERATOR.getAndIncrement(); }


    public void getVehicleDetails(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Registration Number");
        registrationNumber = scanner.nextLine();

        System.out.print("Enter model: ");
        model = scanner.nextLine();

        System.out.print("Enter manufacturer: ");
        manufacturer = scanner.nextLine();

        System.out.print("Enter year of manufacture: ");
        yearOfManufacture = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.println("Enter Transmission Type: ");
        transmission = scanner.nextLine();

        System.out.print("Enter pick-up location: ");
        pickUpLocation = scanner.nextLine();

        System.out.print("Enter minimum driving age: ");
        minDrivingAge = scanner.nextInt();

        System.out.print("Enter CO2 emission: ");
        co2Emission = scanner.nextInt();

        System.out.print("Enter horsepower: ");
        horsePower = scanner.nextInt();

        System.out.print("Enter number of people: ");
        noOfPeople = scanner.nextInt();

        System.out.print("Enter number of doors: ");
        noOfDoors = scanner.nextInt();

        available = true;

        System.out.println("Enter Rate/hour");
        ratePerHour = scanner.nextDouble();

    }
}







