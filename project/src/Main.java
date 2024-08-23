import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDRIVE-EASE!!!\nTired of giving thumbs up to random drivers\n" +
                "on the road? Fret no more!!\n\n" +
                "Pick your Poison\n" +
                "1) Book A Car!!\n" +
                "2) Login\n" +
                "3) Return Car\n" +
                "4) View your bookings\n" +
                "5) Cancel Booking\n");

        System.out.printf("%-15s","Your choice: ");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("BOOKING DETAILS:\n");
                Booking demo1 = new Booking();
                demo1.book();

                break;
            case 2:
                System.out.println("Case 2");
                break;
            case 3:
                System.out.println("Case 3");
                break;
            case 4:
                System.out.println("Case 4");
                break;
            case 5:
                System.out.println();
            case 6:
                System.out.println("WELCOME TO THE SECRET MENU:\n");
                VehicleFile.addToCollection();

            default:
                System.out.println("Default Case");

        }
    }


}




