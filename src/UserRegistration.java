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




    public void registration(){
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.println("Please enter your choice (1 or 2):");

        Scanner sc=new Scanner(System.in);
        while (true) {
            int choice=sc.nextInt();
            switch (choice){
                case 1:

                    userType="Regular User";
                    print(sc);
                    finalCheckpoint();
                    break;
                case 2:
                    userType="VIP User";
                    print(sc);
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    return;
            }
            if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
                System.out.println("Registration successful!");
                sc.close();
                break;
            } else {
                System.out.println("Registration failed! Please try again.");
            }
        }


    }
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
        if (userType.equals("VIP User")){
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
        System.out.println("Please enter your full name:");
        fullName=sc.nextLine();
        System.out.println("Please enter your email address:");
        emailAddress=sc.nextLine();
        emailValid=analyseEmail(emailAddress);
        System.out.println("Please enter your date of birth (YYYY-MM-DD):");
        dateOfBirth=sc.nextLine();
        LocalDate dob= LocalDate.parse(dateOfBirth);
        ageValid=analyseAge(dob);
        System.out.println("Please enter your bank card number:");
        System.out.println("Please enter your card provider (VISA, Mastercard or American Express):");
        cardNumber=sc.nextLong();
        cardNumberValid=analyseCardNumber(cardNumber);
        System.out.println("Please enter the card expiration date:");
        cardExpiryDate=sc.nextLine();
        cardStillValid=analyseCardExpiryDate(cardExpiryDate);
        System.out.println("Please enter the card's CVV:");
        cvv=sc.nextInt();
        validCVV=analyseCVV(cvv);
    }

}
