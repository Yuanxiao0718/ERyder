import java.time.LocalDateTime;
import java.util.*;

public class BikeService {
    //创建一个 ERyderLogs 栈（这是对象的栈，就像对象数组一样），创建一个 ERyderLogs 对象，然后将其推入栈中。
    // 例如，一旦自行车成功租出，将 bikeID、位置和时间戳的值存储在 ERyderLogs POJO 的变量中，然后将该对象推入栈中
    private Stack<ERyderLog> logStack=new Stack<>();

    public void addLog(String logId,String event){
        ERyderLog eryderLog=new ERyderLog(logId,event,LocalDateTime.now());
        logStack.push(eryderLog);
    }
    public void viewSystemLogs() {
        if(!logStack.isEmpty()){
            for (ERyderLog eryderLog : logStack)
                System.out.println(eryderLog);}
        else
            System.out.println("No system logs available.");
    }

    private Queue<BikeRequest> bikeRequestQueue = new ArrayDeque<>();

    public void addToQueue(String email, String location) {
        BikeRequest req = new BikeRequest(email, location, LocalDateTime.now());
        bikeRequestQueue.add(req);
    }

    private String emailAddress;
    private LocalDateTime tripStartTime;
    private boolean locationValid;
    UserRegistration userRegistration = new UserRegistration();
    LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
//    RentalService rentalService = new RentalService();
    public void reserveBike(String bikeID){
        if(bikeID!=null){
            for(Bike bike:BikeDatabase.bikes){
                if(bikeID.equals(bike.getBikeID()))tripStartTime = LocalDateTime.now();
                bike.setIsAvailable(false);
                bike.setLastUsedTime(tripStartTime);
                System.out.println(" Reserving the bike with the "+bikeID+". Please following the on-screen instructions." +
                        "to locate the bike and start your pleasant journey.");
                ActiveRental activeRental = new ActiveRental(bikeID,emailAddress,tripStartTime);
                activeRentalsList.add(activeRental);
                addLog(bikeID, "Bike rented by " + emailAddress + " at " + tripStartTime);
                break;
            }
        }
        else{
            System.out.println(" Sorry, we’re unable to reserve a bike at this time. Please try again later.");
            addLog("NULL", "Failed to reserve bike for " + emailAddress);
        }
    }
    public void viewActiveRentals(){
        if(activeRentalsList.isEmpty())System.out.println("No active rentals at the moment.");
        else {
            for(ActiveRental activeRental:activeRentalsList){
                System.out.println(activeRental);
            }
        }
    }
    public String validateLocation(String location){
        for(Bike bike : BikeDatabase.bikes){
            if(location.equals(bike.getLocation()) && bike.getIsAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }

        }
        System.out.println("Sorry, no bikes are available at the location you" +
                "requested. Please try again later.");
        // 如果没有可用自行车，将用户加入等待队列
        addToQueue(emailAddress, location);
        return null;
    }
    public void removeTrip(String bikeID){

        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while(iterator.hasNext()){
            ActiveRental rental = iterator.next();
            if(rental.getBikeID().equals(bikeID)){
                iterator.remove();
                break;
            }
        }
        Iterator<Bike>bikeIterator = BikeDatabase.bikes.iterator();
        while(bikeIterator.hasNext()){
            Bike bike = bikeIterator.next();
            if(bike.getBikeID().equals(bikeID)){
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                addLog(bikeID, "Trip ended for bike " + bikeID + " at " + LocalDateTime.now());
                break;
            }
        }
        if (!bikeRequestQueue.isEmpty()) {
            BikeRequest next = bikeRequestQueue.poll(); // 队列方法
            System.out.println("Assign to the next user:" + next.getUserEmail());
            }
    }
    public void viewQueue() {
        System.out.println("=== Pending Requests ===");
        for (BikeRequest req : bikeRequestQueue) {
            System.out.println(req);
        }
    }


    public void removeFirstRequest() {
        if (!bikeRequestQueue.isEmpty()) {
            bikeRequestQueue.remove();
        }
    }
}
