import java.util.Scanner;
public class Duke {
    public static String Underscores() {
        return "__________________________________________";
    }

    public static void greet() {
        System.out.println(Underscores() + "\nHello! I'm Jave" + "\nWhat can I do for you?");
    }

    public static void farewell() {
        System.out.println(Underscores() + "\nBye. Hope to see you again soon!\n" + Underscores());
    }

    public static void listPrinter(String[] items, int index) {
        for (int i = 0; i < index; i++) {
            System.out.println(i+1 + ". " + items[i]);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " _______    __   ___      ___  _______\n"
                + "|__   __|  /  \\  \\  \\    /  / |   ____|\n"
                + "   | |    / __ \\  \\  \\  /  /  |  |__\n"
                + "   | |   / |__| \\  \\  \\/  /   |   __|\n"
                + " __| |  /   __   \\  \\    /    |  |____\n"
                + "|____| /___|  |___\\  \\__/     |_______|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        String[] texts = new String[100];
        int index = 0;
        while (true) {
            String text = "";
            System.out.println(Underscores());
            text = input.nextLine();
            if (text.equals("bye")) {
                break;
            }
            else if (text.equals("list")) {
                System.out.println(Underscores());
                listPrinter(texts, index);
            }
            else {
                texts[index] = text;
                index++;
                System.out.println(Underscores());
                System.out.println("added: " + text);
            }
        }
        farewell();
    }
}
