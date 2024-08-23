//import java.io.*;
//import java.util.*;
//
//public class VehicleFile implements Serializable {
//    private static final String GAS_CAR_FILE = "gasCars.dat";
//    private static final String ELECTRIC_CAR_FILE = "electricCars.dat";
//    private static final String TRUCK_FILE = "trucks.dat";
//
//    public static void addToCollection(){
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Choose a vehicle type:");
//        System.out.println("1. GasolineCar");
//        System.out.println("2. ElectricCar");
//        System.out.println("3. Truck");
//
//        String userInput = scanner.nextLine();
//
//        switch (userInput.toLowerCase()) {
//            case "1":
//            case "gasolinecar":
//                System.out.println("ENTER DETAILS OF GASOLINECAR");
//                GasolineCar gasCar = new GasolineCar();
//                gasCar.getVehicleDetails();
//                addVehicleToFile(gasCar, GAS_CAR_FILE);
//                break;
//
//            case "2":
//            case "electriccar":
//                System.out.println("ENTER DETAILS OF ELECTRIC CAR");
//                ElectricCar electricCar = new ElectricCar();
//                electricCar.getVehicleDetails();
//                addVehicleToFile(electricCar, ELECTRIC_CAR_FILE);
//                break;
//
//            case "3":
//            case "truck":
//                System.out.println("ENTER DETAILS OF TRUCK");
//                Truck truck = new Truck();
//                truck.getVehicleDetails();
//                addVehicleToFile(truck, TRUCK_FILE);
//                break;
//
//            default:
//                System.out.println("Invalid choice. Please select 1, 2, or 3.");
//                // Add code for handling invalid input
//        }
//    }
//
//    private static void addVehicleToFile(Vehicle vehicle, String fileName) {
//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName, true))) {
//            objectOutputStream.writeObject(vehicle);
//            System.out.println("Vehicle added to file: " + fileName);
//            objectOutputStream.flush();
//            objectOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void listVehicles(String pickUpLocation, GasolineCar obj) {
//        listVehiclesFromFile(GAS_CAR_FILE, pickUpLocation, GasolineCar.class);
//    }
//
//    public static void listVehicles(String pickUpLocation, ElectricCar obj) {
//        listVehiclesFromFile(ELECTRIC_CAR_FILE, pickUpLocation, ElectricCar.class);
//    }
//
//    public static void listVehicles(String pickUpLocation, Truck obj) {
//        listVehiclesFromFile(TRUCK_FILE, pickUpLocation, Truck.class);
//    }
//
//
//    private static void listVehiclesFromFile(String fileName, String pickUpLocation, Class<? extends Vehicle> vehicleClass) {
//        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
//            while (true) {
//                try {
//                    Vehicle vehicle = (Vehicle) objectInputStream.readObject();
//                    if (vehicleClass.isInstance(vehicle) && vehicle.getPickUpLocation().equalsIgnoreCase(pickUpLocation)) {
//                        vehicle.displayPrimary();
//                        System.out.println("-----------");
//                    }
////                    objectInputStream.reset();
//                } catch (EOFException e) {
//                    break;  // End of file reached
//                }
//            }
//            objectInputStream.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleFile implements Serializable {
    private static final String GAS_CAR_FILE = "gasCars.dat";
    private static final String ELECTRIC_CAR_FILE = "electricCars.dat";
    private static final String TRUCK_FILE = "trucks.dat";

    private static List<Vehicle> readObjectsFromFile(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Vehicle>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, return an empty list
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static String getFileName(String userInput) {
        return switch (userInput.toLowerCase()) {
            case "1", "gasolinecar" -> GAS_CAR_FILE;
            case "2", "electriccar" -> ELECTRIC_CAR_FILE;
            case "3", "truck" -> TRUCK_FILE;
            default -> throw new IllegalArgumentException("Invalid choice. Please select 1, 2, or 3.");
        };
    }

    public static void addToCollection() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a vehicle type:");
        System.out.println("1. GasolineCar");
        System.out.println("2. ElectricCar");
        System.out.println("3. Truck");

        String userInput = scanner.nextLine();

        List<Vehicle> vehicleList = readObjectsFromFile(getFileName(userInput));

        switch (userInput.toLowerCase()) {
            case "1":
            case "gasolinecar":
                System.out.println("ENTER DETAILS OF GASOLINECAR");
                GasolineCar gasCar = new GasolineCar();
                gasCar.getVehicleDetails();
                vehicleList.add(gasCar);
                break;

            case "2":
            case "electriccar":
                System.out.println("ENTER DETAILS OF ELECTRIC CAR");
                ElectricCar electricCar = new ElectricCar();
                electricCar.getVehicleDetails();
                vehicleList.add(electricCar);
                break;

            case "3":
            case "truck":
                System.out.println("ENTER DETAILS OF TRUCK");
                Truck truck = new Truck();
                truck.getVehicleDetails();
                vehicleList.add(truck);
                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                // Add code for handling invalid input
        }

        writeObjectsToFile(vehicleList, getFileName(userInput));
    }

    private static void writeObjectsToFile(List<Vehicle> vehicleList, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(vehicleList);
            System.out.println("Objects written to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Vehicle> listVehicles(String pickUpLocation, GasolineCar obj) {
        return (listVehiclesFromFile(GAS_CAR_FILE, pickUpLocation, GasolineCar.class));
    }

    public static List<Vehicle> listVehicles(String pickUpLocation, ElectricCar obj) {
        return (listVehiclesFromFile(ELECTRIC_CAR_FILE, pickUpLocation, ElectricCar.class));
    }

    public static List<Vehicle> listVehicles(String pickUpLocation, Truck obj) {
        return (listVehiclesFromFile(TRUCK_FILE, pickUpLocation, Truck.class));
    }

    private static List<Vehicle> listVehiclesFromFile(String fileName, String pickUpLocation, Class<? extends Vehicle> vehicleClass) {
        List<Vehicle> vehicleList = readObjectsFromFile(fileName);

        for (Vehicle vehicle : vehicleList) {
            if (vehicleClass.isInstance(vehicle) && vehicle.getPickUpLocation().equalsIgnoreCase(pickUpLocation)) {
                vehicle.displayPrimary();
                System.out.println("-----------");
            }
        }
        return vehicleList;
    }


}
