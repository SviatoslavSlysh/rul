//import java.util.Random;
//import java.util.Scanner;
//
//public class RouletteGame {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Random random = new Random();
//        int deposit = 5000;
//        int betAmount;
//
//        while (true) {
//            System.out.println("Your current balance: " + deposit);
//            System.out.println("What do you want to bet on? (red, black, green, or a number): ");
//            String betChoice = scanner.next().toLowerCase();
//
//            System.out.println("Enter your bet amount: ");
//            betAmount = scanner.nextInt();
//            if (betAmount > deposit) {
//                System.out.println("You don't have enough balance.");
//                continue;
//            }
//
//            int result = random.nextInt(37);
//
//            System.out.print("Do you want to bet on a number? (y/n): ");
//            String choice = scanner.next();
//
//            if (choice.equalsIgnoreCase("y")) {
//                System.out.print("Enter your lucky number: ");
//                int luckyNumber = scanner.nextInt();
//
//                if (luckyNumber == result) {
//                    System.out.println("Congratulations! You win!");
//                    deposit += betAmount * 10;
//                } else {
//                    System.out.println("Sorry, you lost. Result: " + result);
//                    deposit -= betAmount;
//                }
//            } else if (choice.equalsIgnoreCase("n")) {
//                // Проверяем на какой цвет поставил пользователь
//                String color;
//                if (result == 0) {
//                    color = "green";
//                } else if (result % 2 == 0) {
//                    color = "black";
//                } else {
//                    color = "red";
//                }
//
//                System.out.println("The ball fell on " + result + " (" + color + ")");
//
//                // Проверяем, совпадает ли цвет с выбором пользователя
//                if (betChoice.equals(color)) {
//                    System.out.println("Congratulations! You win!");
//                    deposit += betAmount;
//                } else {
//                    System.out.println("Sorry, you lost.");
//                    deposit -= betAmount;
//                }
//            } else {
//                System.out.println("Invalid input. Please enter 'y' or 'n'.");
//            }
//
//            if (deposit <= 0) {
//                System.out.println("You are out of balance.");
//                break;
//            }
//
//            System.out.print("Wanna play more? (y/n): ");
//            String playAgain = scanner.next();
//            if (!playAgain.equalsIgnoreCase("y")) {
//                break;
//            }
//        }
//
//        System.out.println("Final balance: " + deposit);
//        scanner.close();
//    }
//}

import java.util.*;

public class RouletteGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static int depositAmount = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Roulette Game!");
            System.out.println("Choose an option:");
            System.out.println("1. Play Roulette");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    playRoulette();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
        }
        return choice;
    }

    private static void playRoulette() {
        try {
            while (true) {
                System.out.println("What do you want to bet on? (red, black, green): ");
                String betColor = scanner.next().toLowerCase();

                System.out.println("Enter your bet amount: ");
                int betAmount = scanner.nextInt();

                int result = random.nextInt(31);
                String resultColor;
                if (result == 0) {
                    resultColor = "green";
                } else if (result % 2 == 0) {
                    resultColor = "black";
                } else {
                    resultColor = "red";
                }

                System.out.println("The ball fell on " + result + " (" + resultColor + ")");

                int winnings = calculateWinnings(betColor, resultColor, betAmount);
                if (winnings > 0) {
                    System.out.println("Congratulations! You win " + winnings + "!");
                    depositAmount += winnings;
                } else {
                    System.out.println("Sorry, you lost " + betAmount + ".");
                    depositAmount -= betAmount;
                }

                System.out.print("Wanna play more? (y/n): ");
                String playAgain = scanner.next();
                if (!playAgain.equalsIgnoreCase("y")) {
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }

    private static int calculateWinnings(String betColor, String resultColor, int betAmount) {
        if (betColor.equals(resultColor) && !resultColor.equals("green")) {
            return betAmount * 2;
        } else if (betColor.equals(resultColor) && resultColor.equals("green")) {
            return betAmount * 30;
        }
        return 0;
    }

    private static void deposit() {
        try {
            System.out.print("Enter deposit amount: ");
            int newDepositAmount = scanner.nextInt();
            if (newDepositAmount > 0) {
                depositAmount += newDepositAmount;
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Invalid deposit amount. Please enter a positive value.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: " + depositAmount);
    }
}
