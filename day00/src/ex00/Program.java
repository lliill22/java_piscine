package ex00;

public class Program {
    private static int number = 479598;
    private static int sum = 0;
    public static void main(String[] args) {

        sum += number%10;
        number = number / 10;
        sum += number%10;
        number = number / 10;
        sum += number%10;
        number = number / 10;
        sum += number%10;
        number = number / 10;
        sum += number%10;
        number = number / 10;
        sum += number%10;
        number = number / 10;

        System.out.println(sum);
    }
}
