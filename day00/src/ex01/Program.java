package ex01;

import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);
    private static int number;
    private static boolean status = true;
    public static void main(String[] args) {
        number = scanner.nextInt();
        int i = 2;
        if (number > 1) {
            while (i < number && status) {
                if (number % i == 0)
                    status = false;
                i++;
            }
            System.out.println(status);
        } else {
            System.err.print("Illegal Argument");
        }
    }
}
