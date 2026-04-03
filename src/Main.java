import java.util.Random;


public class Main {
    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.registration();
//        System.out.println(userRegistration);

        AdminPanel adminPanel = new AdminPanel();
        adminPanel.userManagementOptions();

        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";
        Feedback feedback1 = new Feedback("Johns", "Walker", "19556279310@163.com");
        feedback1.analyseFeedback(false, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback1);


        ERyder bike1 = new ERyder();
        bike1.printRideDetails(10);
        bike1.printBikeDetails();
        bike1.ride();

        System.out.println("************************");

        Random random = new Random();
        int randomBatteryLevel = random.nextInt(101);
        double KmDriven = randomBatteryLevel * 0.9;
        ERyder bike2 = new ERyder(124521 , randomBatteryLevel, KmDriven, true);
        bike2.printRideDetails(15);
        bike2.printBikeDetails();
        bike2.ride();

    }
}