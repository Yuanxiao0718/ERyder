import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BikeRental {

    // • 使用默认空构造函数创建 UserRegistration 类的对象。当程序要求用户注册且用户尚未注册时，将需要此对象。
    // • 仅声明 ActiveRental 类的对象。该对象稍后将被初始化，然后用于添加到 LinkedList 中。
    // • 创建一个名为 activeRentalsList 的 LinkedList，其类型为 ActiveRental。
    // • 创建一个名为 simulateApplicationInput() 的公共方法。该方法将由管理员控制，
    // 以模拟整个电动自行车租赁过程并测试 LinkedList 的使用。
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userRegistration;
    private LinkedList<ActiveRental> activeRentalsList;
    public BikeRental(boolean isRegisteredUser, String emailAddress, String location, LocalDateTime tripStartTime
            ,String bikeID,boolean locationValid,UserRegistration userRegistration,LinkedList<ActiveRental> activeRentalsList) {
        this.isRegisteredUser = isRegisteredUser;
        this.emailAddress = emailAddress;
        this.location = location;
        this.tripStartTime = tripStartTime;
        this.bikeID = bikeID;
        this.locationValid = locationValid;
        this.userRegistration = new UserRegistration();
        this.activeRentalsList = new LinkedList<>();
    }

    public void simulateApplicationInput() {
        Scanner scanner = new Scanner(System.in);
        // 1. 显示流程开始提示
        System.out.println("This is the simulation of the e-bike rental process.");
        // 2. 接收用户输入
        System.out.print("Is this user registered? (true/false): ");
        isRegisteredUser = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        System.out.print("Please enter your current location: ");
        location=scanner.nextLine();

        System.out.println(" Simulating the analysis of the rental request.");
        this.bikeID=analyseRequest(isRegisteredUser,emailAddress,location);
        if(locationValid){
            System.out.println("Simulating e-bike reservation…");
            reserveBike(bikeID);
            System.out.println("Displaying the active rentals…");
            viewActiveRentals();
            System.out.println("Simulating the end of the trip…");
            removeTrip(bikeID);
            System.out.println("Displaying the active rentals after trip end…");
            viewActiveRentals();
        }else return;
        bikeID=analyseRequest(isRegisteredUser,emailAddress,location);
    }


    private String analyseRequest(boolean isRegistered,String emailAddress,String location) {
        if(isRegistered){
            System.out.println(" Welcome back, ("+emailAddress+")!");
        }else{
            System.out.println(" You’re not our registered user. Please consider registering.");
            userRegistration.registration();
        }
        return validate(location);
    }
    private String validate(String location) {
        return bikeID;
    }

    private String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you\n" +
                "requested. Please try again later.");
        return null;
    }


//            - 通过调用 setIsAvailable() 方法将该自行车的可用性改为 false。
//            - 同样，通过调用 setLastUsedTime() 方法将 lastUsedTime 改为 tripStartTime。


    private void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with the ( " + bikeID + ")." +
                            "\n Please following the on-screen instructions to locate the bike and start your pleasant journey.");
                    ActiveRental activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        }else System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.");
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println(" No active rentals at the moment.");
        } else {
            System.out.println("Current active rentals:");
            for (ActiveRental activeRental : activeRentalsList) {
                System.out.println(activeRental);
            }
        }
     }

//        • 使用 Iterator 循环遍历 activeRentalsList ArrayList，并检查列表中是否有 bikeID 对应的自行车。
//            - 如果存在，则使用 remove() 方法将其移除。记住使用 while 循环通过 hasNext() 方法遍历列表，
//    并使用 next() 方法访问每一个元素。否则，remove() 方法将不起作用。
//            - 移除条目后，跳出循环。• 然后遍历 bikes ArrayList，找到具有相同 bikeID 的自行车。
//            - 如果在列表中找到该自行车，将其 availability 设置为 true，并将 lastUsedTime 设置为当前时间。
//            - 然后显示：您的行程已结束。感谢您的骑行。- 然后跳出循环。
    private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental activeRental = iterator.next();
            if (activeRental.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    bike.setAvailable(true);
                    bike.setLastUsedTime(LocalDateTime.now());
                    System.out.println(" Your trip has ended. Thank you for riding with us.");
                    break;
                }
            }
        }
    }

}