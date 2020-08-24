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
        String echo = "";
        while (true) {
            System.out.println(Underscores());
            echo = input.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            else {
                System.out.println(Underscores());
                System.out.println(echo);
            }
        }
        farewell();
    }
}
