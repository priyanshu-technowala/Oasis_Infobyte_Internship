import java.util.Scanner;

class ATM {
    // Initialize balance
    static double balance = 100000.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM!");
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw money");
            System.out.println("3. Deposit money");
            System.out.println("4. Exit");
            System.out.println();

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double amount = input.nextDouble();
                    withdrawMoney(amount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    amount = input.nextDouble();
                    depositMoney(amount);
                    break;
                case 4:
                    System.out.println("Thanks for using our ATM!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your balance is " + balance);
    }

    private static void withdrawMoney(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Please try a different amount.");
        } else {
            balance -= amount;
            System.out.println(" withdrawn. New balance: " + balance);
        }
    }

    private static void depositMoney(double amount) {
        balance += amount;
        System.out.println(" deposited. New balance: " + balance);
    }
}