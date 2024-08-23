import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;


public class Booking {

    private String bookingName;
    private String carModel;
    private String bookingTime;
    private String pickUpLocation;
    private String returnLocation;
    private String pickUpTime;
    private String returnTime;

    public Booking() {
    }

    public Booking(String bookingName, String carModel, String bookingTime,
                   String pickUpLocation, String returnLocation,
                   String pickUpTime, String returnTime) {
        this.bookingName = bookingName;
        this.carModel = carModel;
        this.bookingTime = bookingTime;
        this.pickUpLocation = pickUpLocation;
        this.returnLocation = returnLocation;
        this.pickUpTime = pickUpTime;
        this.returnTime = returnTime;
    }


    public void book() {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose your Wheeled Crusader\n" +
                "1) Gasoline Car\n" +
                "2) Electric Car\n" +
                "3) Vans & Trucks\n");

        System.out.println("Your choice :");

        //Choose the type of Car

        switch (in.nextLine()) {
            case "1":
            case "gasoline car":
                System.out.println("YOU ARE RENTING A GASOLINE CAR");
                getBookingDetails();
                GasolineCar gasDummy = null;
                List<Vehicle> availableGasCars = VehicleFile.listVehicles(pickUpLocation, gasDummy);
                filterCars(availableGasCars);
                for(Vehicle car : availableGasCars){
                    car.displayPrimary();
                }
                break;
            case "2":
            case "electric car":
                System.out.println("YOU ARE RENTING AN ELECTRIC CAR");
                getBookingDetails();
                ElectricCar electricDummy = null;
                List<Vehicle> availableElectricCars = VehicleFile.listVehicles(pickUpLocation, electricDummy);
                filterCars(availableElectricCars);
                for(Vehicle car : availableElectricCars){
                    car.displayPrimary();
                }
                break;
            case "3":
            case "truck":
                System.out.println("YOU ARE RENTING A VAN OR TRUCK");
                getBookingDetails();
                Truck truckDummy = null;
                List<Vehicle> availableTrucks = VehicleFile.listVehicles(pickUpLocation, truckDummy);
                filterCars(availableTrucks);
                for(Vehicle car : availableTrucks){
                    car.displayPrimary();
                }
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    // Get the Booking Details for the Booking
    // LocalDateTime is used to check formatting and logical errors

    private void getBookingDetails() {
        Scanner scanner = new Scanner(System.in);
        //Using Array-List for fast iteration and random access
        List<String> availableLocations = new ArrayList<>(List.of("Zurich", "Geneva", "Bern", "Lucerne"));
        // Implement accurate searching here if possible
        System.out.println("Choose A location:");
        int i = 0;
        for (String loc : availableLocations) {
            i++;
            System.out.println(i + ")" + loc);
        }
        do {
            try {
                System.out.print("\nType-in your pick-up location: ");
                pickUpLocation = scanner.nextLine();

                if (!availableLocations.contains(pickUpLocation)) {
                    throw new InvalidCityException("Service not Available in your City");
                }

            } catch (InvalidCityException ex) {
                System.out.println(ex.getMessage());
            }
        } while (pickUpLocation == null || !availableLocations.contains(pickUpLocation));

        do {
            try {
                System.out.print("\nType-in your return location: ");
                returnLocation = scanner.nextLine();

                if (!availableLocations.contains(returnLocation)) {
                    throw new InvalidCityException("Service not Available in your City");
                }
            } catch (InvalidCityException ex) {
                System.out.println(ex.getMessage());
            }
        } while (returnLocation == null || !availableLocations.contains(returnLocation));

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        do {
            try {
                LocalDateTime currentDateTime = LocalDateTime.now();

                System.out.println("Enter pick-up date (format: dd-MM-yyyy): ");
                String pickUpDate = scanner.nextLine();

                System.out.print("Enter pick-up time (format: HH:mm): ");
                pickUpTime = scanner.nextLine();

                pickUpTime = pickUpDate.concat(" ").concat(pickUpTime);

                validateDateTime(pickUpTime, currentDateTime);

            } catch (InvalidDateTimeFormatException ex) {
                System.out.println(ex.getMessage());
                pickUpTime = null;
            }
        } while (pickUpTime == null);

        do {
            try {
                System.out.println("Enter return date (format: dd-MM-yyyy): ");
                String returnDate = scanner.nextLine();
                System.out.print("Enter return time (format: HH:mm): ");
                returnTime = scanner.nextLine();
                returnTime = returnDate.concat(" ").concat(returnTime);
                validateDateTime(returnTime, LocalDateTime.parse(pickUpTime, dateTimeFormat));

            } catch (InvalidDateTimeFormatException ex) {
                System.out.println(ex.getMessage());
                returnTime = null;
            }
        } while (returnTime == null);
    }

    private static LocalDateTime validateDateTime(String dateTime, LocalDateTime currentDateTime) throws InvalidDateTimeFormatException {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //To check the time format

        try {
            LocalDateTime parsedTime = LocalDateTime.parse(dateTime, timeFormat);
            if ((parsedTime.isBefore(currentDateTime))) {
                throw new InvalidDateTimeFormatException("Error. The entered time is before the current Time");
            }
            return parsedTime;
            // Convert String input to Date object (here time)
        } catch (DateTimeParseException ex) {
            throw new InvalidDateTimeFormatException("Invalid time format. Please try again");
        }
//        return null;
    }

    private <T> void filterCars(List<? extends Vehicle> filterList){

        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER FILTERS: \n");
        System.out.println("1) Transmission");
        System.out.println("2) Seats");
        System.out.println("3) Price Range");
        System.out.println("4) HorsePower");
        System.out.println("5) Manufacturer");
        System.out.println("6) Fuel Efficiency");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().toLowerCase();
        while(true){
        switch (choice) {
            case "1":
            case "transmission":
                System.out.println("Transmission:");
                System.out.println("Automatic");
                System.out.println("Mannual");
                System.out.println("Both");
                System.out.print("Enter your choice: ");
                String transmission = scanner.nextLine();
                filter(filterList, vehicle -> vehicle.getTransmission().equalsIgnoreCase(transmission));
                break;
            case "2":
            case "seats":
                System.out.println("Seats:");
                System.out.println("2");
                System.out.println("4");
                System.out.println("7");
                System.out.print("Enter your choice: ");
                int seats = scanner.nextInt();
                scanner.nextLine();
                filter(filterList, vehicle -> vehicle.getNoOfPeople() >= seats);
                break;
            case "3":
            case "price range":
                System.out.println("Price Range:");
                System.out.print("Enter minimum price: ");
                double minPrice = scanner.nextDouble();
                System.out.print("Enter maximum price: ");
                double maxPrice = scanner.nextDouble();
                scanner.nextLine();
                // Throw exceptions here
                filter(filterList, vehicle -> vehicle.getRatePerHour() >= minPrice && vehicle.getRatePerHour() <= maxPrice);
                break;
            case "4":
            case "horsepower":
                System.out.println("Horsepower:");
                System.out.print("Enter minimum horsepower: ");
                double minHorsepower = scanner.nextDouble();
                scanner.nextLine();
                filter(filterList, vehicle -> vehicle.getHorsePower() >= minHorsepower);
                break;
            case "5":
            case "manufacturer":
                System.out.println("Manufacturer:");
                System.out.print("Enter manufacturer name: ");
                String manufacturer = scanner.nextLine();
                filter(filterList, vehicle -> vehicle.getManufacturer().equalsIgnoreCase(manufacturer));
                break;
//            case "6":
//            case "fuel efficiency":
//                System.out.println("Fuel Efficiency:");
//                System.out.print("Enter minimum fuel efficiency: ");
//                double minFuelEfficiency = scanner.nextDouble();
//                filter(filterList, vehicle -> vehicle.get)
//                break;
            case "0":
            case "exit":
                System.out.println("Exiting program. Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");

        }
        }
    }

    private static void filter(List<? extends Vehicle> filterList, Predicate<Vehicle> criteria){
        filterList.removeIf(criteria.negate());
    }
}