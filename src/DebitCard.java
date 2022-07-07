import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DebitCard implements Serializable {
    private double currentBalance;
    private String cardNumber;
    private Calendar endOfCard;
    private int cvv;


    public DebitCard(double currentBalance, String cardNumber, Calendar endOfCard, int cvv){
        this.currentBalance = currentBalance;
        this.cardNumber = cardNumber;
        this.endOfCard = endOfCard;
        this.cvv = cvv;
    }

    public void setCurrentBalance(double userCurrentBalance){
        currentBalance=userCurrentBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCardNumber(String userCardNumber) {
        cardNumber = userCardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setEndOfCard(Calendar userEndOfCard) {
        endOfCard = userEndOfCard;
    }

    public Calendar getEndOfCard() {
        return endOfCard;
    }

    public void setCvv(int userCvv) {
        cvv = userCvv;
    }
    @Override
    public String toString(){
        return "Current balance: "+currentBalance+"\nCard number: "+cardNumber+"\nEnd of card: "+endOfCard+"\nCVV: "+cvv;
    }
}
