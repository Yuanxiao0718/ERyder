import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    //此类允许管理员添加、删除、修改和查看所有注册用户。
    // 此类将使用 RegisteredUsers 对象的 ArrayList。在类级别，创建一个名为 registeredUsersList 的 ArrayList
    // ，类型为 RegisteredUsers。也就是说，List<RegisteredUsers> registeredUsers = new ArrayList<>();
    // 创建一个名为 userManagementOptions() 的公共方法。• 该方法不返回任何内容。• 该方法首先为管理员提供以下选项以供选择：
    List<RegisteredUsers> registeredUsers = new ArrayList<>();
    public void userManagementOptions(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1.Add New Users");
            System.out.println("2.View Registered Users");
            System.out.println("3.Remove Registered Users");
            System.out.println("4.Update Registered Users");
            System.out.println("5.EXIT");
            int choice = sc.nextInt();
            switch (choice) {
                case 1->addNewUsers();
                case 2->viewRegisteredUsers();
                case 3->removeRegisteredUsers();
                case 4->updateRegisteredUsers();
                default -> System.out.println("Invalid choice. Please try again");

            }
        }

    }
//    创建一个名为 addNewUsers() 的私有方法
//    • 该方法不返回任何内容。• 首先，询问管理员想要添加多少用户
//    • 然后开始接受以下值：name, , dateOfBirth, cardNumber,c, cardExpiryDate, cvv, and userType.
//        - 这与您在上一练习中为 UserRegistration 类所做的相同。
//        - 不同之处在于您不会验证这些值，因为假设管理员会在手动输入系统之前进行验证。
//    • 您还必须询问最近三次行程。由于这是一个数组，您需要使用循环来询问每次行程的以下详细信息：
//        - 询问行程的日期，格式为 YYYY-MM-DD，以字符串形式接受
//        - 询问该行程的出发地和目的地。例如，（出发地：NJIT Gate 5，目的地：Wending Square）

//    private void addNewUsers() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("How many users do you want to add?");
//        int numberOfUsers = sc.nextInt();
//
////            String tripDate = null;
//            for (int j = 0; j < 3; j++) {
//                System.out.println("The date of the trip(YYYY-MM-DD):");
//                String tripDate = sc.next();
//
//                System.out.println("Enter fare:");
//                double fare = sc.nextDouble();
//                System.out.println("Enter feedback (can be empty):");
//                String feedback = sc.next();
//                StringBuilder sb = new StringBuilder();
//                //Date: trip date value, Source: source value, Destination: destination value, Fare (€): fare
//              sb
//                //    - 然后将上述 StringBuilder 对象存储在 lastThreeTrips 数组中。记住先将上述 StringBuilder 对象转换为字符串。
////            • 然后使用以上所有输入创建一个新的 RegisteredUsers 对象。
////            • 使用 add() 方法将上述对象添加到 registeredUserList ArrayList 中。
////            • 继续接受更多用户，并根据管理员在此方法开始时输入的用户数量重复整个过程。
//
//
//            }
//            System.out.println("Enter the expenses paid for this trip:");
//            double expenses = sc.nextDouble();
//
//
//
////
//
//           - 询问用户在该次旅行中支付的费用。
//           - 询问用户可能输入的反馈。它可以为空（NULL）。
//           - 然后使用 StringBuilder 将所有行程输入组合成一个单独的 StringBuilder 对象，
//            如下所示：Date: trip date value, Source: source value, Destination: destination value, Fare (€): fare
//            value, Feedback: feedback value
//

    private void addNewUsers(){
        String fullName;
        String emailAddress;
        String dateOfBrith;
        long cardNumber;
        String cardProvider = "";
        String cardExpiryDate;
        int cvv;
        String userType = "";
        String[] lastThreeTrips = new String[3];

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many users you want to add?");
        int numberNeedAdd = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberNeedAdd; i ++){
            System.out.println("Please input your full name.");
            fullName = scanner.nextLine();

            System.out.println("Please input your email address.");
            emailAddress = scanner.nextLine();

            System.out.println("Please input your birthday(in the form of YYYY-MM-DD).");
            dateOfBrith = scanner.nextLine();
            LocalDate dob = LocalDate.parse(dateOfBrith);

            System.out.println("Please input your card number(only for Visa, MasterCard and American Express).");
            cardNumber = Long.parseLong(scanner.nextLine());

            System.out.println("Please input your card deadline.");
            cardExpiryDate = scanner.nextLine();

            System.out.println("Please input your CVV.");
            cvv = Integer.parseInt(scanner.nextLine());

            System.out.println("Please input the last three trips.");

            for (int j = 0; j < 3; j ++){
                System.out.println("Input the date (YYYY-MM-DD) :");
                String tripDate = scanner.nextLine();

                System.out.println("Enter source  E.g., (Source: NJIT Gate 5): ");
                String source = scanner.next();
                System.out.println("Enter destination E.g., (Destination: Wending Square): ");
                String destination = scanner.next();

                System.out.println("Input the fee:");
                double tripFee = Double.parseDouble(scanner.nextLine());

                System.out.println("Input your feedback (can null) :");
                String feedback = scanner.nextLine();
                if (feedback.isEmpty()){
                    feedback = "NULL";
                }

                StringBuilder tripInfo = new StringBuilder();
                tripInfo.append("Date: ").append(tripDate)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(destination)
                        .append(", Fare (€): ").append(tripFee)
                        .append(", Feedback: ").append(feedback );

                lastThreeTrips[j] = tripInfo.toString();

            }

            RegisteredUsers user = new RegisteredUsers(fullName, emailAddress, dateOfBrith, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);

            registeredUsers.add(user);

        }


    }

    //    创建一个名为 viewRegisteredUsers() 的新的私有方法
//    • 该方法不返回任何内容
//    • 首先使用 isEmpty() 方法检查 registeredUsersList 是否为空。如果为空，则显示“没有可显示的注册用户”
//    • 如果列表不为空，则— 使用 for-each 循环显示 registeredUsersList ArrayList 中的每个元素
//      — 记得同时显示 lastThreeTrips 数组中的所有值。 创建一个名为 removeRegisteredUsers() 的新的私有方法
//    • 该方法不返回任何内容 • 首先检查 registeredUsersList 是否为空。如果为空，则显示“没有可删除的注册用户”
//    • 如果列表不为空，则— 询问必须删除的用户的电子邮件地址 — 如果未找到该电子邮件地址，则显示“未找到使用此电子邮件地址的用户”
//        - 如果找到该电子邮件地址，则使用迭代器遍历 registeredUsersListArrayList。
//        - 然后检查 ArrayList 中的每个对象是否与该电子邮件地址匹配。
//        - 如果电子邮件地址匹配，则只需使用 iterator.remove() 删除该对象。
private void viewRegisteredUsers(){
    if (registeredUsers.isEmpty()){
        System.out.println("No registered users to display");
    }else {
        for (int i = 0; i < registeredUsers.size(); i ++){
            System.out.println(registeredUsers.get(i));
        }
    }
}



    private void removeRegisteredUsers(){
        Scanner scanner = new Scanner(System.in);

        if (registeredUsers.isEmpty()){
            System.out.println("No registered users to remove");
        }else{
            String emailToRemove = scanner.nextLine();
            boolean found = false;
            Iterator<RegisteredUsers> iterator = registeredUsers.iterator();

            while (iterator.hasNext()){
                RegisteredUsers user = iterator.next();

                if (user.getEmailAddress().equals(emailToRemove)){
                    found = true;
                    iterator.remove();
                    break;
                }
            }

            if (!found){
                System.out.println("No user found with this email address");
            }
        }
    }

    private void updateRegisteredUsers(){
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        String emailToUpdate = scanner.nextLine();
        RegisteredUsers userToUpdate = null;

        for (RegisteredUsers user : registeredUsers){
            if (user.getEmailAddress().equals(emailToUpdate)){
                userToUpdate = user;

            }else {
                System.out.println("No user found with this email address");
                userToUpdate = null;
            }
        }

        if (userToUpdate == null){
            System.out.println("No registered users to remove");
            return;
        }

        System.out.println("Type new full name(Press ENTER for no change):");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()){
            userToUpdate.setFullName(newFullName);
        }

        System.out.println("Type new card number(Enter '0' for no change):");
        String newCardNumberInput = scanner.nextLine();
        if (!newCardNumberInput.isEmpty() && !newCardNumberInput.equals("0")){
            long newCardNumber = Long.parseLong(newCardNumberInput);
            userToUpdate.setCardNumber(newCardNumber);
        }


    }

}

//• 首先检查 registeredUsersList 是否为空。如果为空，只需显示“没有要移除的注册用户”
    // • 如果列表不为空，则
    // - 询问必须移除的用户的电子邮件地址
    // - 如果未找到该电子邮件地址，则只需显示“未找到该电子邮件地址的用户”
    // - 如果找到电子邮件地址，则使用 Iterator 循环遍历 registeredUsersListArrayList
    // - 然后检查 ArrayList 中的每个对象的电子邮件地址是否匹配
    // - 如果电子邮件地址匹配，则使用 iterator.remove() 移除该对象


    // • 首先检查 registeredUsersList 是否为空。如果为空，只需显示“没有要移除的注册用户”
    // • 如果列表不为空，则
    // - 询问必须更新对象的用户的电子邮件地址
    // - 如果未找到该电子邮件地址，则只需显示“未找到该电子邮件地址的用户”
    // - 如果找到电子邮件地址，则重新询问所有详细信息 练习 #7 问题陈述（续）
    // - 在接受字符串值时，说明用户可以按 ENTER 保持原有值。例如：输入新的全名：（按 ENTER 不做更改）
    // - 在接受输入后，检查值是否为空。使用 isEmpty() 方法
    // - 如果输入不为空，则仅在此情况下调用 RegisteredUsers 类的相应 setter 设置新值。
    // 例如：if(!fullName.isEmpty()){ registeredUser.setFullName(fullName); }
    // - 对于数字如 cardNumber 执行相同操作。但提示用户输入 '0' 表示不更改。然后仅在输入不为 '0' 时设置新值。
    // - 没必要更新 lastThreeTrips[] 数组。但如果你想尝试，也可以。
    // - 通过使用 RegisteredUsers 类的 setter，你实际上是在修改 registeredUsersListArrayList 中各个对象的值。

