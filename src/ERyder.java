public class ERyder {

    private static final String COMPANY_NAME = "ERyder";
    private static final double BASE_FARE = 1.0;
    private static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final long LINKED_PHONE_NUMBER;

    private int  totalUsageInMinutes;
    private double totalFare = 60;


    private String bikeID;
    private int batteryLevel;
    private double kmDriven;
    private boolean isAvailable;

    public ERyder() {
        this.bikeID = "000000";
        this.batteryLevel = 100;
        this.kmDriven = 0;
        this.isAvailable = true;
        LINKED_ACCOUNT="AS4";
        LINKED_PHONE_NUMBER=1955679310;
    }
    public ERyder(String bikeID, int batteryLevel, double kmDriven, boolean isAvailable) {
        setBikeID(bikeID);
        setBatteryLevel(batteryLevel);
        setKmDriven(kmDriven);
        setIsAvailable(isAvailable);
        LINKED_ACCOUNT="AS4";
        LINKED_PHONE_NUMBER=1955679310;
    }
    public ERyder(String bikeID, int batteryLevel, double kmDriven, boolean isAvailable,String lINKED_ACCOUNT,long lINKED_PHONE_NUMBER)  {
        this.bikeID = bikeID;
        this.batteryLevel = 100;
        this.kmDriven = 0;
        this.isAvailable = true;
        LINKED_ACCOUNT="AS4";
        LINKED_PHONE_NUMBER=1955679310;
    }

    public  void  printRideDetails(int totalUsageInMinutes){
        System.out.println("Ride Details:");
        System.out.println("You account: " + LINKED_ACCOUNT);
        System.out.println("Linked phone number: " + LINKED_PHONE_NUMBER);
        System.out.println("The usage in minutes is " + totalUsageInMinutes);
        System.out.println("The total fare is "+calculateFare(totalUsageInMinutes)+".");
    }

    private double calculateFare(int totalUsageInMinutes) {
        totalFare = BASE_FARE + (PER_MINUTE_FARE * totalUsageInMinutes);
        System.out.println("Total fare: " + totalFare);
        return totalFare;
    }


    public String getBikeID() {
        return bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
        if (this.batteryLevel <= 0 || this.batteryLevel > 100) {
            System.out.println("Invalid battery level. Battery level must be between 0% and 100%.");
            this.isAvailable = false;
        }
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public void ride() {
        if (this.isAvailable && this.batteryLevel > 0) {
            System.out.println("Riding bike " + this.bikeID + ". Km driven: " + this.kmDriven + ". Battery level: " + this.batteryLevel);
        } else {
            System.out.println("Bike " + this.bikeID + " is not available for use.");
        }
    }

    public void printBikeDetails() {
        System.out.println("Bike ID: " + this.bikeID);
        System.out.println("Battery level: " + this.batteryLevel + "%");
        System.out.println("Km driven: " + this.kmDriven);
        System.out.println("Is available: " + this.isAvailable);
    }
}