import java.util.*;
class User {
    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String oldid = scanner.nextLine();

        System.out.print("Enter Password: ");
        String oldpassword = scanner.nextLine();

        if (oldid.equals(id) && oldpassword.equals(password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("You Entered Invalid credentials. Login failed!");
            System.out.println("Try Again With valid credentials");
            login();

        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String pnr;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String journeyDate;

    public Reservation(String pnr, String trainNumber, String trainName, String classType, String journeyDate) {
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.journeyDate = journeyDate;
    }

    // Getters
    public String getPnr() {
        return pnr;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClassType() {
        return classType;
    }

    public String getJourneyDate() {
        return journeyDate;
    }
}

class OnlineReservationSystem {
    private Map<String, User> users;
    private Map<String, Reservation> reservations;

    public OnlineReservationSystem() {
        this.users = new HashMap<>();
        this.reservations = new HashMap<>();
    }

    // Add a user to the system
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    // Perform login
    public boolean login(String id, String password) {
        User user = users.get(id);
        return user != null && user.getPassword().equals(password);
    }

    // Perform reservation
    public void makeReservation(String pnr, String trainNumber, String trainName, String classType,
            String journeyDate) {
        Reservation reservation = new Reservation(pnr, trainNumber, trainName, classType, journeyDate);
        reservations.put(pnr, reservation);
        System.out.println("Your Reservation is Successfully Done");
        System.out.println("Your PNR: " + reservation.getPnr());
    }

    // Perform cancellation
    public void cancelReservation(String pnr) {
        Reservation reservation = reservations.get(pnr);
        Scanner sc = new Scanner(System.in);
        if (reservation != null) {
            System.out.println("Cancellation details for PNR: " + pnr);
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Train Name: " + reservation.getTrainName());
            System.out.println("Class Type: " + reservation.getClassType());
            System.out.println("Journey Date: " + reservation.getJourneyDate());
            System.out.println("Press OK to confirm cancellation.");
            String ok = sc.next();
            if (ok.equals("OK")) {
                System.out.println("Your cancellation has been done for PNR " + pnr);
            }

        } else {
            System.out.println("Reservation not found for PNR: " + pnr);
        }
    }

    public void viewYourReservation(String pnr) {
        Reservation reservation = reservations.get(pnr);
        Scanner sc = new Scanner(System.in);
        if (reservation != null) {
            System.out.println("Details for Your Reservation pnr: " + pnr);
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Train Name: " + reservation.getTrainName());
            System.out.println("Class Type: " + reservation.getClassType());
            System.out.println("Journey Date: " + reservation.getJourneyDate());
        } else {
            System.out.println("You do not have any reservations for pnr " + pnr);
        }
    }
}

class OnlineReservation {
    public static void main(String[] args) {
      
        OnlineReservationSystem reservationSystem = new OnlineReservationSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System.");
        // Perform login
        System.out.println("Are you a new User yes/no ");
        String reply = sc.next();

        String id, password;
        boolean oldUser = false;

        if (reply.equals("yes")) {
            System.out.println("SignUp New Account");
            System.out.println("Enter UserId");
            id = sc.next();
            System.out.println("Enter Password");
            password = sc.next();

            System.out.println("SignUP successful!");
        } else {
            id = "aliabu";
            password = "usa";
            oldUser = true;
            System.out.println("Login your Account");
        }

        User user = new User(id, password);
        if (oldUser)
            user.login();
        reservationSystem.addUser(user);

        while (true) {
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Your Reservation Detail");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Reservation form
                    System.out.print("Enter PNR: ");
                    String pnr = sc.next();
                    System.out.print("Enter Train Number: ");
                    String trainNumber = sc.next();
                    System.out.print("Enter Train Name: ");
                    String trainName = sc.next();
                    System.out.print("Enter Class Type: ");
                    String classType = sc.next();
                    System.out.print("Enter Journey Date: ");
                    String journeyDate = sc.next();
                    reservationSystem.makeReservation(pnr, trainNumber, trainName, classType, journeyDate);
                    break;

                case 2:
                    // Cancellation form
                    System.out.print("Enter PNR: ");
                    String cancelPnr = sc.next();
                    reservationSystem.cancelReservation(cancelPnr);
                    break;
                case 3:
                    System.out.print("Enter PNR: ");
                    String Pnr = sc.next();
                    reservationSystem.viewYourReservation(Pnr);
                    break;
                case 4:
                    System.out.println("Thanks for Using Online Reservation System");
                    return;
            }
        }
    }
}