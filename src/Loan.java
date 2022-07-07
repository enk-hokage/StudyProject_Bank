import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {
    private Date registration = new Date();
    private double summ;
    private double percentBid;
    private int durationLoan;
    private double paymentMonthly;
    public int loanCount;

    public Loan(){

    }
    public Loan(Date registration, double summ, double percentBid, int durationLoan, double paymentMonthly){
        this.registration = registration;
        this.summ = summ;
        this.percentBid = percentBid;
        this.durationLoan = durationLoan;
        this.paymentMonthly = paymentMonthly;
    }

    public void setRegistration(Date userRegistration){
        registration=userRegistration;
    }

    public Date getRegistration(){
        return registration;
    }

    public void setSumm(double userSumm){
        summ=userSumm;
    }

    public double getSumm(){
        return summ;
    }

    public void setPercentBid(double userPercentBid){
        percentBid=userPercentBid;
    }

    public double getPercentBid(){
        return percentBid;
    }

    public void setDurationLoan(int userDurationLoan){
        durationLoan=userDurationLoan;
    }

    public int getDurationLoan(){
        return durationLoan;
    }

    public void setPaymentMonthly(double userPaymentM){
        paymentMonthly =userPaymentM;
    }
    @Override
    public String toString(){
        return "Date registration: "+registration+"\nSum: "+summ+"\nPercent bid: "+percentBid+"\nDuration loan: "+durationLoan+"\nMonthly payment: " + paymentMonthly;
    }
}
