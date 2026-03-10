import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();
        bike1.ride();

        System.out.println("************************");

        String regex = "^[0-9]{6}$";
        String inputID = "";
        while (true) {
            System.out.println("Enter the bike ID of the second bike:");
            Scanner sc2 = new Scanner(System.in);
            String bikeID2 = sc2.next();


            if (Pattern.matches(regex, bikeID2)) {
                inputID = bikeID2;
                break;
            } else {
                System.out.println("Error: BikeID must be a 6-digit number only! This assignment is invalid, default ID will be used：000000");
                // 非法值赋默认6位ID
            }

        }
        Random random = new Random();
        int randomBatteryLevel = random.nextInt(101);
        double KmDriven = randomBatteryLevel * 0.9;
        ERyder bike2 = new ERyder(inputID, randomBatteryLevel, KmDriven, true);
        bike2.printBikeDetails();
        bike2.ride();

    }
}