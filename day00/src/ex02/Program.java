package ex02;

import java.util.Scanner;

public class Program {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isSimple(int number) {
        boolean status = true;
        int i = 2;
        if (number > 1) {
            while (i < number && status) {
                if (number % i == 0)
                    return false;
                i++;
            }
            return true;
        } else {
            return false;
        }
    }

    private static int sum(int number) {

        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    private static int counter = 0;



    public static void main(String[] args) {
        int number = 0;
        while (number != 42) {
            number = scanner.nextInt();
            if (number >= 1) {
                if (isSimple(sum(number))) {
                    counter++;
                }
            }
        }
        System.out.println("Count of coffee - request - " + counter);
    }
}
