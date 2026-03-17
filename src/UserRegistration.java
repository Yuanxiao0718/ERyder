import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UserRegistration {
    public static final  double  VIP_DISCOUNT_UNDER_18_BIRTHDAY=0.75;
    public static final  double VIP_DISCOUNT_UNDER_18=0.8;
    public static final  int VIP_BASE_FEE=100;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    //——存储银行卡提供商（VISA、Mastercard、American Express）
    String cardExpiryDate;//存储银行卡到期日期，类型为字符串
    private  double totalFee;//——存储用户需要支付的总费用，类型为双精度浮点型
    private int cvv;//——存储卡片验证值，通常是3或4位数字，类型为整型

    private String userType;//–以字符串形式存储用户类型
    private boolean emailValid;//一个布尔值，用于指示用户提供的电子邮件是否有效
    private boolean minorAndBirthday;//–一个布尔值，用于指示用户是否未满18岁以及今天是否是他们的生日
    private boolean minor;//一个布尔值，用于指示用户是否未满18岁
    private boolean ageValid;//–一个布尔值，用于指示用户的年龄是否有效
    private boolean cardNumberValid;//–一个布尔值，用于指示用户提供的卡号是否有效
    private boolean cardStillValid;//–一个布尔值，用于指示卡片是否已过期
    private boolean validCVV;//–一个布尔值，用于指示用户提供的CVV是否有效



//    创建一个名为 registration() 的公共方法。该方法接受用户详细信息并调用不同的方法来验证这些详细信息：
//            • 首先，向用户显示以下信息：欢迎来到 ERyder 注册。您有以下两个选项：1. 注册为普通用户 2. 注册为 VIP 用户 请输入您的选择（1 或 2）：
//            • 接受用户输入并将其存储在名为 choice 的变量中。
//            • 如果选择是 '1'，则 userType 变量必须存储“普通用户” - 否则，userType 变量必须存储“VIP 用户”。

//    然后提示用户输入他们的全名，并将其存储在 fullName 变量中。
//     • 然后提示用户输入他们的电子邮件地址，并将其存储在 emailAddress 变量中。
//            - 调用方法 analyseEmail()，以 emailAddress 作为参数。
//            - analyseEmail() 方法将返回一个布尔值。将其存储在 emailValid 变量中。
//     • 然后提示用户以 YYYY-MM-DD 格式输入他们的出生日期，并将其存储在 dateOfBirth 变量中。将 dateOfBirth 转换为名为 dob 的 LocalDate 对象。
//            - 调用方法 analyseAge() 并传入 dob 作为参数。
//            - analyseAge() 方法将返回一个布尔值。将其存储在 ageValid 变量中。
//     • 然后提示用户输入他们的卡号，同时告知只能接受 Visa、MasterCard 和 American Express 卡。将卡号存储在 cardNumber 变量中。
//            - 调用方法 analyseCardNumber() 并以 cardNumber 作为参数。
//            - analyseCardNumber() 方法将返回一个布尔值。将其存储在 cardNumberValid 变量中。

    public void registration(){
        System.out.println("欢迎来到 ERyder 注册。您有以下两个选项：");
        System.out.println("1. 注册为普通用户");
        System.out.println("2. 注册为 VIP 用户");
        System.out.println("请输入您的选择（1 或 2）：");

        Scanner sc=new Scanner(System.in);
        while (true) {
            int choice=sc.nextInt();
            switch (choice){
                case 1:

                    userType="普通用户";
                    print(sc);
                    finalCheckpoint();
                    break;
                case 2:
                    userType="VIP 用户";
                    print(sc);
                    break;
                default:
                    System.out.println("请输入正确的选项！");
                    return;
            }
            if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
                System.out.println("注册成功！");
                sc.close();
                break;
            } else {
                System.out.println("注册失败！请重新输入。");
            }
        }


    }
//    • 然后提示用户输入他们的卡片到期日期，并将其存储在 cardExpiryDate 变量中
//            - 调用一个方法 analyseCardExpiryDate()，并将 cardExpiryDate 作为参数传入。
//            - analyseCardExpiryDate() 方法将返回一个布尔值。将其存储在 cardStillValid 变量中。
//    • 然后提示用户输入卡片的 CVV，并将其存储在 cvv 变量中。
//            - 调用一个方法 analyseCVV()，并将 cvv 作为参数传入。
//            - analyseCVV() 方法将返回一个布尔值。将其存储在 validCVV 变量中。
//    • 然后调用 finalCheckpoint() 方法。
//    • registration() 方法不返回任何值
//    • 记得在必要时处理换行符，并在接收完所有用户信息后关闭 Scanner 对象


//    创建一个私有方法 analyseEmail()，该方法返回布尔值。此方法检查用户的电子邮件地址并确保其格式正确。
//            • 检查电子邮件是否包含 '@' 和 '.' 字符。(提示：使用 String 类的 contains() 方法)
//            • 如果包含这些字符，则打印 "Email is valid"
//            • 如果缺少这两个字符中的任何一个，则打印 "Invalid email address. Going back to the start of the registration"
//            • 调用 registration() 方法。
//

    private boolean analyseEmail(String email){
        emailValid=email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        if(!emailValid){
            System.out.println("Invalid email address. Going back to the start of the registration.");
            registration();
        }else {
            System.out.println("Email is valid");
        }
        return emailValid;
    }
//    创建一个私有方法 analyseAge()，该方法返回布尔值。此方法检查用户的出生日期，并判断其是否为有效日期、是否为用户生日，以及用户是否未满 18 岁。
//            • 计算 dob 参数与当前日期的差异，获取年份差。(提示：使用 Period 类的 getYears() 方法)
//            • 创建一个布尔变量 isBirthday，并检查 dob 与当前日期是否匹配。(提示：比较 dob 对象的月份和日期值是否与当前日期的月份和日期值相同)

//    • 然后在 analyseAge() 方法中，检查 userType 是否为“VIP 用户”
//        - 如果是，则检查当前日期是否为用户的生日，并且用户的年龄是否小于或等于 18 岁且大于 12 岁
//        - 如果以上所有条件都为真，则打印：生日快乐！由于今天是您的生日且未满 18 岁，您可以获得 VIP 订阅费用 25% 的折扣！
//        - 将 minorAndBirthday 变量改为 true
//        - 否则，检查当前日期是否不是用户的生日，并且用户的年龄是否小于或等于 18 岁且大于 12 岁
//        - 如果以上所有条件都为真，则打印：由于您未满 18 岁，您可以获得 VIP 订阅费用 20% 的折扣！
//        - 将 minor 变量改为 true
//    • 然后，检查用户的年龄是否小于或等于 12 岁或大于 120 岁
//        - 如果任何上述条件为真，则打印：看起来您要么太年轻，要么已经去世。抱歉，您不能成为我们的用户。祝您有美好的一天
//        - 然后退出整个程序。使用 System.exit(0) 来退出整个程序。
    private boolean analyseAge(LocalDate dob){
        boolean isBirthday = dob.getMonthValue()==LocalDate.now().getMonthValue()&&
                             dob.getDayOfMonth()==LocalDate.now().getDayOfMonth();
        int  period =Period.between(dob,LocalDate.now()).getYears();
        ageValid= period >=18||(period==17&&isBirthday);
        if (!ageValid) {
            System.out.println("Sorry, you must be at least 18 years old to register.");
        }else {
            System.out.println("Congratulations! You are old enough to register.");
        }
        if (userType.equals("VIP 用户")){
            if(isBirthday){
                if(period<=18&&period>12){
                    System.out.println("Happy birthday! Since you are under 18 and your birthday is today, you will receive a 25% discount on your subscription fee.");
                    minorAndBirthday=true;
                }
            }else{
                if(period<=18&&period>12){
                    System.out.println("Since you are under 18, you will receive a 20% discount on your subscription fee.");
                    minor=true;
                }

            }
        }
        if (period<=12||period>=120) {
            System.out.println("It looks like you either are too young or have passed away. We're sorry, but you cannot become a user.");
            System.exit(0);
        }
        return ageValid;
    }

//    创建一个私有方法 analyseCardNumber()，该方法返回一个布尔值。该方法检查卡号长度是否正确，并根据该号码识别卡提供商。
//        • 将 long 类型的 cardNumber 存储到 String 变量 cardNumStr 中
//        • 创建一个 int 变量 firstTwoDigits。
//            - 首先从 cardNumStr 中提取从索引 0 到索引 2 的子字符串
//            - 然后使用 parseInt() 将该子字符串转换为 int
//        • 同样，创建一个名为 firstFourDigits 的 int 变量
//            - 使用与 firstTwoDigits 相似的逻辑
//        • 然后检查 cardNumStr 的长度是否为 13 或 15，并且检查 cardNumStr 的首位数字是否为 '4' (提示：使用 String 方法 startsWith())
//            - 如果上述条件为真，则将 "VISA" 存储到 cardProvider 变量中
//        • 否则，检查 cardNumStr 的长度是否为 16，并且 firstTwoDigits 是否在 51 到 55 之间，或者 firstFourDigits 是否在 2221 到 2720 之间
//            - 如果上述条件为真，则将 "MasterCard" 存储到 cardProvider 变量中
//        • 否则，检查 cardNumStr 的长度是否为 15，并且 cardNumStr 是否以 "34" 或 "37" 开头 (提示：使用 String 方法 startsWith())
//            - 如果上述条件为真，则将 "American Express" 存储到 cardProvider 变量中
    private boolean analyseCardNumber(long cardNumber){
        String cardNumStr = Long.toString(cardNumber);
        int firstTwoDigits=parseInt(cardNumStr.substring(0,2));
        int firstFourDigits=parseInt(cardNumStr.substring(0,4));
        if(cardNumStr.startsWith("4")&&(cardNumStr.length()==13||cardNumStr.length()==15)){

            cardProvider="VISA";
            cardNumberValid=true;

        }else if(cardNumStr.length()==16){
            if(firstTwoDigits>=51&&firstTwoDigits<=55||firstFourDigits>=2221&&firstFourDigits<=2720){
                cardProvider="MasterCard";
                cardNumberValid=true;
            }
        } else if (cardNumStr.length()== 15 &&(cardNumStr.startsWith("34")||cardNumStr.startsWith("37"))) {
            cardProvider="American Express";
            cardNumberValid=true;
        }
        if (!cardNumberValid) {
            System.out.println("Sorry, we only accept VISA, Mastercard, or American Express. Please try again with a valid card. Return to the registration start page.");
        }else {
            System.out.println("Your card provider is " + cardProvider);
        }
        registration();
        return cardNumberValid;
    }

//    • 如果上述条件都不满足，则只需打印“抱歉，我们只接受VISA、万事达卡或美国运通卡。请使用有效的卡重试。返回注册起始页。”
//    • 然后调用registration()方法。创建一个私有方法analyseCardExpiryDate()，返回一个布尔值。该方法将检查卡片是否过期。
//    • 创建一个int变量month。
//         - 从cardExpiryDate变量中提取从索引0到索引2的子字符串，然后使用parseInt()将该子字符串转换为int。
//         - 将上述值存储到month中
//    • 创建一个int变量year
//         - 从cardExpiryDate变量中提取从索引3到索引5的子字符串，然后使用parseInt()将该子字符串转换为int。然后给该int值加上2000。
//         - 将上述值存储到year中

//    • 创建一个名为 currentDate 的 LocalDate 对象来存储当前日期。
//            - 从 currentDate 中提取年份并存储在一个 int 变量 currentYear 中
//            - 从 currentDate 对象中提取月份并存储在一个 int 变量 currentMonth 中（提示：使用 getMonthValue() 获取数字形式的月份）
//    • 检查年份变量是否大于 currentYear。或者，检查年份是否与 currentYear 相同，并且月份是否大于或等于 currentMonth
//            - 如果上述任何条件为真，则打印“卡片仍然有效”
//            - 否则，打印“抱歉，您的卡已过期。请使用其他卡。返回注册过程的开始…”
//            - 然后调用 registration() 方法
    private boolean analyseCardExpiryDate(String cardExpiryDate){
        int month=parseInt(cardExpiryDate.substring(0,2));
        int year=parseInt(cardExpiryDate.substring(3,5))+2000;
        LocalDate currentDate=LocalDate.now();
        int currentYear=currentDate.getYear();
        int currentMonth=currentDate.getMonthValue();
        if(year>currentYear||(year==currentYear&&month>=currentMonth)) {
            System.out.println("Your card is still valid.");
            cardStillValid = true;
        }else {
            cardStillValid=false;
            System.out.println("Sorry, your card has expired. Please use another card. Return to the registration start page.");
        }
        registration();
        return cardStillValid;
    }

//    创建一个私有方法 analyseCVV()，返回一个布尔值。该方法检查 CVV 号码的长度。
//    • 将 cvv 参数转换为字符串并存储在 cvvStr 中
//    • 检查 cardProvider 是否为“American Express”且 cvvStr 长度为 4，
//    或者 cardProvider 是否为“VISA”且 cvvStr 长度为 3，或者 cardProvider 是否为“MasterCard”且 cvvStr 长度为 3
//            - 如果以上任何条件为真，则简单打印“Card CVV is valid.”
//            - 否则，打印“Invalid CVV for the given card. Going back to the start of the registration process.”
//    • 然后调用 registration() 方法。
    private boolean analyseCVV(int cvv){
        String cvvStr = Integer.toString(cvv);
        if(cardProvider.equals("American Express")&&cvvStr.length()==4||cardProvider.equals("VISA")&&cvvStr.length()==3||cardProvider.equals("MasterCard")&&cvvStr.length()==3){
            System.out.println("Card CVV is valid.");
            validCVV=true;
        }else {
            System.out.println("Invalid CVV for the given card. Going back to the start of the registration process.");
            validCVV=false;
        }
        registration();
        return validCVV;
    }

//    创建一个私有方法 finalCheckpoint()，它不返回任何值。该方法检查所有重要的布尔变量，并决定是否向用户收费。
//    • 首先检查 emailValid、ageValid、cardNumberValid、cardStillValid 和 validCVV 是否全为 true。
//         - 如果以上所有都为 true，则调用方法 chargeFees()
//         - 如果以上任意一个变量不为 true，则先打印：抱歉，您的注册未成功，原因如下：
//         - 然后检查 emailValid 是否为 false。如果是，则打印：电子邮件地址无效
//         - 然后检查 ageValid 是否为 false。如果是，则打印：年龄无效
//         - 然后检查 cardNumberValid 是否为 false。如果是，则打印：卡号无效
//         - 然后检查 cardStillValid 是否为 false。如果是，则打印：卡已过期
//         - 然后检查 validCVV 是否为 false。如果是，则打印：CVV 无效
//         - 然后打印：返回注册流程的开始。
//         - 然后调用 registration() 方法。
    private void finalCheckpoint(){
        if(emailValid&&ageValid&&cardNumberValid&&cardStillValid&&validCVV){
            chargeFees();
        }else {
            System.out.println("Sorry, your registration was not successful. The reasons are as follows:");
            if(!emailValid){
                System.out.println("Email address invalid.");
            }
            if(!ageValid){
                System.out.println("Age invalid.");
            }
            if(!cardNumberValid){
                System.out.println("Card number invalid.");
            }
            if(!cardStillValid){
                System.out.println("Card expired.");
                System.out.println("Returning to the start of the registration process.");
                registration();
            }
        }
    }

//    创建一个私有方法 chargeFees()，该方法无返回值。此方法会检查用户是否生日以及是否未满18岁，并相应地收取费用。
//    • 首先检查 minordAndBirthday 变量是否为 true。
//            - 如果是，则对 VIP_BASE_FEE 应用 25% 的折扣，并将其存储在 feeToCharge 变量中。
//    • 检查 minor 变量是否为 true。
//            - 如果是，则对 VIP_BASE_FEE 应用 20% 的折扣，并将其存储在 feeToCharge 变量中。
//    • 否则，只需将 VIP_BASE_FEE 的值存储在 feeToCharge 变量中。
//    • 然后打印以下内容：感谢您的付款。已向您尾号为 **** 的卡收取 #### 的费用。####
//            - 存储在 feeToCharge 的值
//    • **** - 卡号的最后四位数字。（提示：使用 String 的 substring() 方法，只需传入从卡号倒数第四位开始的起始索引）
    private void chargeFees(){
        double feeToCharge;
        if(minorAndBirthday){
            feeToCharge=VIP_BASE_FEE*VIP_DISCOUNT_UNDER_18_BIRTHDAY;
        }else if(minor){
            feeToCharge=VIP_BASE_FEE*VIP_DISCOUNT_UNDER_18;
        }else{
            feeToCharge=VIP_BASE_FEE;
        }
        String s= Long.toString(cardNumber);
        System.out.println("Thank you for your payment. A fee of " + feeToCharge + " has been charged to your card ending with " + s.substring(s.length()-4));
    }

//    创建一个 toString() 方法。正如你应该已经知道的，这个方法是 @Overridden 的，并且每次程序打印 UserRegistration 类的对象时都会调用该方法。
//    • 首先，将 long 类型的 cardNumber 转换为名为 cardNumberStr 的 String 变量
//    • 然后创建一个名为 censoredPart 的 String 变量
//    • 然后将 cardNumberStr 中除了最后四位数字之外的所有数字替换为*
//    （提示：使用 substring() 提取除了最后四位之外的所有数字。然后使用 replaceAll(".", "*") 将这些数字替换为 *。这里的 "." 将匹配任意字符并替换为 "*"）
//    • 然后使用 substring() 方法仅提取 cardNumberStr 的最后四位数字，并只使用从数字倒数第四位开始的起始索引。就像你之前在 chargeFees() 方法中所做的那样。将其存储在名为 lastFourDigits 的另一个 String 变量中
//    • 然后创建一个名为 censoredNumber 的 String 变量，将 censoredPart 和 lastFourDigits 变量连接起来
    @Override
    public String toString() {
        String cardNumberStr = Long.toString(cardNumber);
        String censoredPart = cardNumberStr.replaceAll("lastFourDigits", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length()-4);
        String censoredNumber = censoredPart + lastFourDigits;
        return "Registration successful! Here are your details:\n"+"User Type:"+ userType+"\nFull Name:  " + fullName + "\nEmail Address:" + emailAddress + "\nDate of Birth: " + dateOfBirth + "\nCard Number: " + censoredNumber + "\nCard Provider: " + cardProvider + "\nCard Expiry Date: "
                + cardExpiryDate;
    }
    private void print(Scanner sc ){
        System.out.println("请输入您的全名：");
        fullName=sc.nextLine();
        System.out.println("请输入您的电子邮件地址：");
        emailAddress=sc.nextLine();
        emailValid=analyseEmail(emailAddress);
        System.out.println("请输入您的出生日期（YYYY-MM-DD）：");
        dateOfBirth=sc.nextLine();
        LocalDate dob= LocalDate.parse(dateOfBirth);
        ageValid=analyseAge(dob);
        System.out.println("请输入您的银行卡号：");
        System.out.println("请输入您的银行卡提供商（VISA、Mastercard 或 American Express）：");
        cardNumber=sc.nextLong();
        cardNumberValid=analyseCardNumber(cardNumber);
        System.out.println("请输入卡片到期日期：");
        cardExpiryDate=sc.nextLine();
        cardStillValid=analyseCardExpiryDate(cardExpiryDate);
        System.out.println("请输入卡片的 CVV：");
        cvv=sc.nextInt();
        validCVV=analyseCVV(cvv);
    }

}
