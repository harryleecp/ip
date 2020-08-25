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

    public static void listPrinter(Task[] items, int index) {
        for (int i = 0; i < index; i++) {
            System.out.println(i+1 + ". " + (items[i]).taskPrinter());
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String text = "";
        String logo = " _______    __   ___      ___  _______\n"
                + "|__   __|  /  \\  \\  \\    /  / |   ____|\n"
                + "   | |    / __ \\  \\  \\  /  /  |  |__\n"
                + "   | |   / |__| \\  \\  \\/  /   |   __|\n"
                + " __| |  /   __   \\  \\    /    |  |____\n"
                + "|____| /___|  |___\\  \\__/     |_______|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Task[] texts = new Task[100];
        int index = 0;
        while (true) {
            System.out.println(Underscores());
            text = input.nextLine();
            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                System.out.println(Underscores());
                listPrinter(texts, index);
            } else if ((text.indexOf("done") == 0) && (text.split(" ")).length == 2) {
                String[] words = text.split(" ");
                int value = Integer.parseInt(words[1]);
                if (value <= index) {
                    (texts[value - 1]).setDone();
                    System.out.println(Underscores());
                    System.out.println("Nice! I've marked this as done:\n  " + texts[value-1].taskPrinter());
                }
            } else {
                texts[index] = new Task (text);
                index++;
                System.out.println(Underscores());
                System.out.println("added: " + text);
            }
        }
        farewell();
    }
}
