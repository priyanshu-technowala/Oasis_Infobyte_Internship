import java.util.Scanner;

class NumberGuessingGame {

    static int more_attempt = 1, attempt_no = 0, numberOfGuesses = 0, total_score = 0;

    public static void game(int validAttempt, int extraAttempt) {
        Scanner scanner = new Scanner(System.in);
        int secretNumber = (int) (Math.random() * 100) + 1;

        int curr_score = 0;
        System.out.println("Welcome to the number guessing game! I'm thinking of a number between 1 and 100.");

        while (attempt_no < extraAttempt + validAttempt) {
            System.out.println("What is your guess? ");
            int guess = scanner.nextInt();
            attempt_no++;
            numberOfGuesses++;

            if (guess == secretNumber) {
                curr_score = 700;
                System.out.println(
                        "Congratulations! You Successfully guessed the number in " + numberOfGuesses + " guesses!");
                if (numberOfGuesses <= 7) {
                    total_score = total_score + curr_score / 7;
                    System.out.println("Excellent! You got " + curr_score / 7 + " score out of 100");
                    System.out.println("Your total score is " + total_score);
                    numberOfGuesses = 0;
                    attempt_no = 0;
                } else if (numberOfGuesses > 7 && numberOfGuesses <= 17) {
                    total_score = total_score + curr_score / numberOfGuesses;
                    System.out.println("Good! You got " + curr_score / numberOfGuesses + " score out of 100");
                    System.out.println("Your total score is " + total_score);
                    numberOfGuesses = 0;
                } else {
                    total_score = total_score + curr_score / numberOfGuesses;
                    System.out.println("Poor! You got " + curr_score / numberOfGuesses + " score out of 100");
                    System.out.println("Your total score is " + total_score);
                    numberOfGuesses = 0;
                }
                System.out.println("Would you like to play again? (Y/N)");
                String playAgain = scanner.next();

                if (playAgain.equals("Y")) {

                    game(validAttempt, extraAttempt);
                } else {
                    System.out.println("Thank you for playing!");
                    more_attempt = 0;
                    break;
                }

            } else if (guess < secretNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }
        if (more_attempt == 1) {
            System.out.println("Sad! you have exceed the limit of number of attempt");
            System.out.println("Would you like to play again? (Y/N)");
            String playAgain = scanner.next();

            if (playAgain.equals("Y")) {
                System.out.println("How many extra attempt you want??");
                int extrattempt = scanner.nextInt();

                game(validAttempt, extraAttempt + extrattempt);
            } else {
                System.out.println("Thank you for playing!");
            }
        }
    }

    public static void main(String[] args) {

        // validAttempt=log2(100)=7
        NumberGuessingGame g = new NumberGuessingGame();
        g.game(7, 0);
    }
}