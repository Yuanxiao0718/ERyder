public class uMain {
    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.registration();
        System.out.println(userRegistration);

        AdminPanel adminPanel = new AdminPanel();
        adminPanel.userManagementOptions();
    }
}
