import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator cal = new Calculator();
        int peoplesInInvoice;
        boolean stopProgram = false;
        System.out.println("На сколько человек делим счет?");
            while(!scanner.hasNextInt()) {
                if (scanner.nextLine().equalsIgnoreCase("Завершить")) {
                    stopProgram = true;
                    break;
            }
                System.out.println("Введите число больше единицы");

            }
            while (!stopProgram && scanner.hasNextInt()) {
                if ((peoplesInInvoice = scanner.nextInt()) > 1) {
                    cal.calculate(peoplesInInvoice);
                    break;
                }
                System.out.println("Введите число больше единиц");
            }

    }
}