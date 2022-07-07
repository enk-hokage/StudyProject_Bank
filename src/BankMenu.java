import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BankMenu {
    private final Bank bank;

    public BankMenu(Bank bank){
        this.bank = bank;
    }

    public void showStartMenu(){
        Scanner console = new Scanner(System.in);
        System.out.println("Добрый день!\nЧто вы хотите сделать?\n1:Авторизоваться\n2:Зарегистрироваться\n3:Выход");

        int a = console.nextInt();
        if (a > 3 || a < 1){
            System.out.println("Такого варианта нет");
            System.exit(0);
        }
        if(a == 3){
            System.exit(0);
        }
        if (a == 1){
            showLogin();
        } else {
            showRegister();
        }
    }

    public void showBankMenu() {
        System.out.println("1.Show my info\n2:Add loan\n3:Add debit card\n0:Exit");

        Scanner console = new Scanner(System.in);
        int answer = console.nextInt();
        if (answer == 3){
            showDebitCard();
        } else if(answer == 2){
            showLoan();
        } else if (answer == 1){
            bank.showMyInfo();
        } else {
            System.exit(0);
        }

        console.close();
    }

    public void showBankMenuAdmin(){
        System.out.println("1.Показать мою информацию\n2:Взять кредит\n3:Оформить кредитную карту\n4:Показать статистику банка\n0:Выход");

        Scanner console = new Scanner(System.in);
        int answer = console.nextInt();
        if (answer == 3){
            showDebitCard();
        } else if(answer == 2){
            showLoan();
        } else if (answer == 1){
            bank.showMyInfo();
        } else if (answer == 4){
            showStatistics();
        } else {
            System.exit(0);
        }

        console.close();
    }
    public void showStatistics(){
        List<User> userListAdmin = bank.getUsersList();


        List <User> filterList = userListAdmin.stream()
                .filter(user -> user.getTimeRegist().after(new Date(user.getTimeRegist().getTime() - 1 *24 * 3600 * 1000)))
                .collect(Collectors.toList());
        System.out.println(filterList);
        System.out.println("------------------");

        List <User> listSorted = userListAdmin.stream()
                .sorted((x,y) -> Integer.compare(y.getCreditList().size(), x.getCreditList().size()))
                .collect(Collectors.toList());
        System.out.println("Пользователи, по кол-ву кредитов: " + listSorted);
    }

    public void showLoan(){
        BufferedReader buffLogin = new BufferedReader(new InputStreamReader(System.in));
        Date userRegistration = new Date();

        Scanner console = new Scanner(System.in);

        System.out.println("Введите желаемую сумму кредита");
        double userSumm = 0;
        try {
            userSumm = buffLogin.read();
        } catch (IOException e) {
            System.out.println();
        }

        System.out.println("Ваша месячная процентная ставка = 3%");
        double userPercentBid = 0.03;

        System.out.println("На какой срок желаете взять кредит?");
        int userDurationLoan = console.nextInt();

        double userPaymentMonthly = userSumm / userDurationLoan + userSumm * userPercentBid;
        System.out.println("Ваш ежемесячный платеж = " + userPaymentMonthly);


        Loan userLoan = new Loan(userRegistration, userSumm, userPercentBid, userDurationLoan, userPaymentMonthly);
        bank.doCreateLoan(userLoan);
    }

    public String generateCardNumber(){
        Random random = new Random();
        int part1 = random.nextInt(88888888)+11111111;
        int part2 = random.nextInt(88888888)+11111111;
        String text1 = Integer.toString(part1);
        String text2 = Integer.toString(part2);

        return text1 + text2;
    }

    public int generateCvv(){
        Random random = new Random();
        return random.nextInt(888)+111;
    }

    public void showDebitCard(){
        try(BufferedReader buffCard = new BufferedReader(new InputStreamReader(System.in));){
            System.out.println("Введите сумму начального взноса");
            double userCurrentBalance = buffCard.read();

            String userCardNumber = generateCardNumber();
            System.out.println("Ваш номер карты: " + userCardNumber);


            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 4);

            int userCvv = generateCvv();
            System.out.println("Ваш код CVV - " + userCvv);

            DebitCard userDebitCard = new DebitCard(userCurrentBalance, userCardNumber, calendar, userCvv);
            bank.doCreateCard(userDebitCard);
        }
        catch (IOException e){
            System.out.println("Не возможно считать с консоли");
        }
    }

    public void showLogin(){
        BufferedReader buffShowLogin = new BufferedReader(new InputStreamReader(System.in));
        Scanner console = new Scanner(System.in);

        System.out.println("Введите Email");
        String email = console.nextLine();
        System.out.println("Введите Пароль");
        String password = console.nextLine();

        if (bank.doLogin(email, password)){
            System.out.println("Вы успешно авторизировались");
            if(email.equals("admin")){
                showBankMenuAdmin();
            } else {
                showBankMenu();
            }
        } else {
            System.out.println("Произошла ошибка авторизации");
        }
    }
    public void showRegister(){
        try(BufferedReader buffShowRegister = new BufferedReader(new InputStreamReader(System.in))) {
            Scanner console = new Scanner(System.in);

            System.out.println("Введите Ваше имя");
            String userFirstName = buffShowRegister.readLine();

            System.out.println("Введите Вашу фамилию");
            String userLastName = buffShowRegister.readLine();

            System.out.println("Введите дату рождения, в формате : yyyy-MM-dd");
            String userDate = buffShowRegister.readLine();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            Date userBirthday;
            try {
                userBirthday = s.parse(userDate);
            } catch (ParseException e) {
                System.out.println("Дата рождения введена в неверном формате");
                return;
            }

            int a = 0;

            boolean userIsMan = false;
            while (a == 0){
                System.out.println("Выберите ваш пол\n1:Мужчина;\n2:Женщина");
                int answer = console.nextInt();
                if (answer > 2 || answer < 1 ){
                    System.out.println("Вы ввели неверный ответ");
                    continue;
                } else if (answer==1){
                    userIsMan = true;
                } else{
                    userIsMan = false;
                }
                a = 1;
            }
            System.out.println("Введите свой Email");
            String userEmail = buffShowRegister.readLine();


            System.out.println("Введите свой пароль");
            String userPassword = buffShowRegister.readLine();

            Date userTimeRegist = new Date();


            User user = new User (userFirstName, userLastName, userBirthday, userIsMan, userEmail, userPassword, userTimeRegist); //данные собрал, но в объект не передал. теряю здесь понимание что я делаю
            bank.doRegister(user);
        }
        catch (IOException e){
            System.out.println("Невозможно считать с консоли");
        }
    }
}
