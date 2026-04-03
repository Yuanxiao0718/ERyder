import java.util.Arrays;

public class RegisteredUsers {
    //通过创建另一个名为 RegisteredUsers 的新类来更新此程序。将以下变量添加到该类中。
    // 它们与 UserRegistration 完全相同：
    // • fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, userType。
    // • 添加一个名为 lastThreeTrips 的字符串数组。此数组将存储用户最近三次旅行的描述
    private String[] lastThreeTrips;
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private int cvv;
    private String userType;

    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth, long cardNumber, String cardProvider, String cardExpiryDate, int cvv, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardProvider = cardProvider;
        this.cardExpiryDate = cardExpiryDate;
        this.cvv = cvv;
        this.userType = userType;
    }

    public String[] getLastThreeTrips() {
        return lastThreeTrips;
    }

    public void setLastThreeTrips(String[] lastThreeTrips) {
        this.lastThreeTrips = lastThreeTrips;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "RegisteredUsers{" +
                "lastThreeTrips=" + Arrays.toString(lastThreeTrips) +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", cardNumber=" + cardNumber +
                ", cardProvider='" + cardProvider + '\'' +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cvv=" + cvv +
                ", userType='" + userType + '\'' +
                '}';
    }
}
