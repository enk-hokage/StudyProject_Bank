import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private Date birthday;
    private boolean isMan;
    private String email;
    private String password;
    private List<Loan> creditList = new ArrayList<>();
    private List<DebitCard> cardList = new ArrayList<>();
    private Date timeRegist;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, Date birthday, boolean isMan, String email, String password, Date timeRegist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.isMan = isMan;
        this.email = email;
        this.password = password;
        this.timeRegist = timeRegist;
    }

    public void setFirstName(String userFirstName){
        firstName = userFirstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String userLastName){
        lastName = userLastName;
    }

    public String getLastName(){
        return lastName;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public void setBirthday(Date userBirthday){
        birthday = userBirthday;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setEmail(String userEmail){
        email = userEmail;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String userPassword){
        password = userPassword;
    }

    public String getPassword(){
        return password;
    }

    public List<Loan> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Loan> creditList) {
        this.creditList = creditList;
    }

    public List<DebitCard> getCardList() {
        return cardList;
    }

    public int getCreditListSize(){
        return creditList.size();
    }

    public void setCardList(List<DebitCard> cardList) {
        this.cardList = cardList;
    }

    public void setTimeRegist(Date userTimeRegist){
        timeRegist = userTimeRegist;
    }

    public Date getTimeRegist(){
        return timeRegist;
    }

    @Override
    public String toString(){
        return "First name: " + firstName + "\nLast name: " + lastName+"\nBirthday: "+birthday+"\nEmail: "+email+"\nPassword: "+password+"\nCredit List: "+creditList+"\nCard list: "+cardList;
    }

}

