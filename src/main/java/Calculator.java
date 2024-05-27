import java.util.Locale;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import static java.lang.Math.floor;

public class Calculator {
    HashMap<String, Double> itemsInInvoice = new HashMap<>();
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    String item;
    Double cost;
    Double amount = (double) 0;
    boolean stopProgram = false;
    void calculate (int peoplesInInvoice) {
        while (true) {
            System.out.println("Введите название товара");
            item = scanner.nextLine();
            if (item.equalsIgnoreCase("Завершить")) {
                break;
            }
            System.out.println("Введите стоимость товара в формате рубли.копейки");
            while(!scanner.hasNextDouble()) {
                if (scanner.nextLine().equalsIgnoreCase("Завершить")) {
                    stopProgram = true;
                    break;
                }
                System.out.println("Введите число больше нуля");

            }
            while (!stopProgram && scanner.hasNextDouble()) {
                if ((cost = scanner.nextDouble()) > 0) {
                    scanner.nextLine();
                    break;
                }
                System.out.println("Введите число больше нуля");
            }
            if (stopProgram) {
                break;
            }
            if (!itemsInInvoice.containsKey(item)) {
                itemsInInvoice.put(item, cost);
                System.out.println("Товар " + item + " стоимостью " + costToWord(cost) + " успешно добавлен\n");
            }
            else {
                System.out.println("Товар с именем " + item + " уже есть в списке. Добавить ещё один? (Y/N), (Д/Н)");
                while (true) {
                    String addItem = scanner.nextLine();
                    if (addItem.equalsIgnoreCase("Y") || (addItem.equalsIgnoreCase("Д"))) {
                        int i = 1;
                        while (true) {
                            String dublicateItem = item + " (" + i + ")";
                            if (!itemsInInvoice.containsKey(dublicateItem)) {
                                item = dublicateItem;
                                itemsInInvoice.put(item, cost);
                                System.out.println("Товар " + item + " стоимостью " + costToWord(cost) + " успешно добавлен\n");
                                break;
                            }
                            i++;
                        }
                        break;
                    }
                    else if (addItem.equalsIgnoreCase("N") || (addItem.equalsIgnoreCase("Н"))) {
                        break;
                    }
                    System.out.println("Введите 'Y' ('Д'), если хотите добавить товар. Введите 'N' ('Н'), если не хотите");
                }
            }
        }
        Iterator<String> itemIterator = itemsInInvoice.keySet().iterator();
        if (!itemIterator.hasNext()) {
            System.out.println("Список товаров пуст");
        }
        while (itemIterator.hasNext()) { // до тех пор, пока есть непройденный элемент в списке
            String element = itemIterator.next(); // получаем следующий элемент
            System.out.println(element + ": " + costToWord(itemsInInvoice.get(element)));
            amount += itemsInInvoice.get(element);
        }
        System.out.println("Итого: по " + costToWord(amount/peoplesInInvoice) + " с человека");
    }
int roundDown (Double cost) {
    return (int) floor(cost);
}
String costToWord (Double cost) {
        if (roundDown(cost)%10 >= 5 || roundDown(cost)%10  == 0 ) {
            return String.format("%.2f", cost) + " рублей";
        }
        else if (roundDown(cost)%10  == 1) {
            return String.format("%.2f", cost) + " рубль";
        }
        else {
            return String.format("%.2f", cost) + " рубля";
        }
    }
}
