import java.util.*;

class User {

    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;

    }
}

class Examination {
    User currentUser;
    int remainingTime;

    Examination(User user) {
        this.currentUser = user;
        this.remainingTime = 0;
    }

    void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (username.equals(currentUser.username) && password.equals(currentUser.password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("You Entered Invalid credentials. Login failed!");
            System.out.println("Try Again With valid credentials");
            login();

        }
    }

    void startTimer(int timeLimit) {
        remainingTime = timeLimit;
        System.out.println("You have " + remainingTime / 1000 + " seconds for 10 MCQ");

        try {
            Thread.sleep(remainingTime / 2);
            System.out.println("Remaining time: " + remainingTime / 2000 + " seconds");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remainingTime = remainingTime / 2;
        try {
            Thread.sleep(remainingTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Time's up! Submitting the examination.");
        try {
            Thread.sleep(5000);
            System.out.println("Examination submitted successfully!");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

    }

    void closeSession() {
        System.out.println("Closing session. Goodbye!");
        // System.exit(0);
    }
}

class OnlineExamination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you a new User yes/no ");
        String reply = sc.next();

        // Perform login
        String name, password;
        boolean oldUser = false;
        if (reply.equals("yes")) {
            System.out.println("SignUp New Account");
            System.out.println("Enter Username");
            name = sc.next();
            System.out.println("Enter Password");
            password = sc.next();

            System.out.println("SignUP successful!");
        } else {
            name = "Abuzar";
            password = "12112091";
            oldUser = true;
            System.out.println("Login your Account");
        }

        User user = new User(name, password);
        Examination examination = new Examination(user);
        if (oldUser)
            examination.login();
        boolean attempt = false;
        int score = 0;
        while (true) {
            System.out.println("Welcome to the online examination system. What would you like to do?");
            System.out.println("1. Take an exam");
            System.out.println("2. View your results");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the subject choice you would like to take an exam for: ");
                    System.out.println("1. Java");
                    System.out.println("2. C++");
                    System.out.println("3. Python");
                    int subject_choice = sc.nextInt();
                    attempt = true;
                    switch (subject_choice) {
                        case 1:
                            System.out.println(
                                    "There are 10 MCQ of Java programming Language. There is Negative marking");
                            score += 100;
                            break;
                        case 2:
                            System.out
                                    .println("There are 10 MCQ of C++ programming Language. There is Negative marking");
                            score += 100;
                            break;
                        case 3:
                            System.out.println(
                                    "There are 10 MCQ of Python programming Language. There is Negative marking");
                            score += 100;
                            break;
                    }
                    examination.startTimer(10000);
                    examination.closeSession();

                    break;

                case 2:
                    if (!attempt) {
                        System.out.println("You did not attempt any MCQ exam till now so your score is 0");
                    } else {
                        System.out.println("Your score is " + score);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the online examination system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

    }
}