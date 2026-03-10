public class ERyder {

    private static final String COMPANY_NAME = "ERyder";
    private static final double BASE_FARE = 1.0;
    private static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final long LINKED_PHONE_NUMBER;

    private int usageInMinutes;
    private double totalFare;


    private int bikeID;
    private int batteryLevel;
    private double kmDriven;
    private boolean isAvailable;

    public ERyder() {
        this.bikeID = 0;
        this.batteryLevel = 100;
        this.kmDriven = 0;
        this.isAvailable = true;
        LINKED_ACCOUNT="AS412345";
        LINKED_PHONE_NUMBER=1955679310;
    }
    public ERyder(int bikeID, int batteryLevel, double kmDriven, boolean isAvailable) {
        this.bikeID = bikeID;
        this.batteryLevel = 100;
        this.kmDriven = 0;
        this.isAvailable = true;
        LINKED_ACCOUNT="AS412345";
        LINKED_PHONE_NUMBER=1955679310;
    }
    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, float kmDriven, String linkedAccount, long linkedPhoneNumber){
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        LINKED_ACCOUNT = linkedAccount;
        LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }

    public void printRideDetails(int usageInMinutes){
        System.out.println("The linked account is "+LINKED_ACCOUNT+".");
        System.out.println("The linked phone number is "+LINKED_PHONE_NUMBER+".");
        System.out.println("The bike ID is "+bikeID+".");
        System.out.println("The usage in minutes is "+usageInMinutes+".");
        System.out.println("The total fare is "+calculateFare(usageInMinutes)+".");
    }
    private double calculateFare(int usageInMinutes){
        totalFare = BASE_FARE + (PER_MINUTE_FARE*usageInMinutes);
        return usageInMinutes*PER_MINUTE_FARE+BASE_FARE;
    }


    public int getBikeID() {
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

    public void setBikeID(int bikeID) {
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