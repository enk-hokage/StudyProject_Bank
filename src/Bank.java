import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank {
    private List<User> usersList = new ArrayList<>();
    private User loggedUser;

    public List<User> getUsersList() {
        return usersList;
    }

    public void showMyInfo(){
        System.out.println(loggedUser);
    }

    public void start(){
        deserializeUser();

        BankMenu bank1 = new BankMenu(this);
        bank1.showStartMenu();
    }
    public boolean doLogin(String email, String password){
        for(User u: usersList){
            if (u.getEmail().equals(email) && u.getPassword().equals(password)){
                System.out.println("Authorization successful");
                loggedUser = u;
                return true;
            }
        }
        System.out.println("Invalid Email or Password");
        return false;
    }


    public void doRegister (User user){
        usersList.add(user);
        serializeUsers(usersList);
    }

    public void doCreateLoan(Loan userLoan){
        loggedUser.getCreditList().add(userLoan);
        serializeUsers(usersList);
    }


    public void doCreateCard(DebitCard userDebitCard){
        loggedUser.getCardList().add(userDebitCard);
        serializeUsers(usersList);
    }


    public void serializeUsers(List<User> usersList){
        try {
            FileOutputStream fos = new FileOutputStream("Users.data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(usersList);

            oos.close();
        } catch (IOException e) {
            System.out.println("File read error");
        }
    }

    public void deserializeUser(){
        try {
            FileInputStream fis = new FileInputStream("Users.data");
            ObjectInputStream ois = new ObjectInputStream(fis);

            usersList = (List<User>) ois.readObject();

        } catch (FileNotFoundException e) {
            try {
                new File("Users.data").createNewFile();
            } catch (IOException ex) {
                System.out.println("File read error");
            }

            String adminFirstName = "Admin";
            String adminLastName = "Admin";
            Date adminBirthday = new Date();
            boolean adminIsMan = true;
            String adminEmail = "admin";
            String adminPassword = "adminPass";
            Date adminRegist = new Date();

            User user = new User(adminFirstName, adminLastName, adminBirthday, adminIsMan, adminEmail, adminPassword, adminRegist);
            doRegister(user);
        } catch (IOException e) {
            System.out.println("File read error");
        } catch (ClassNotFoundException e) {
            System.out.println("Class error");
        }
    }
}
