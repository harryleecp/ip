import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static String Underscores() {
        return "__________________________________________";
    }

    public static void help() {
        String instructions = "The following is a list of commands that you can enter:\n"
                + "1) help - For showing all commands available\n"
                + "2) list - To display all the tasks in your list\n"
                + "3) todo - Add a to-do task into your list (Example: todo borrow book)\n"
                + "4) deadline - Use \" /by \" to denote deadline (Example: deadline return book /by Sunday)\n"
                + "5) event - Use \" /at \" to denote when the event takes place (Example: event meeting /at Mon 2-4pm)\n";
        System.out.println(instructions);
    }

    public static void greet() {
        System.out.println(Underscores() + "\nHello! I'm Jave" + "\nWhat can I do for you?");
    }

    public static void farewell() {
        System.out.println(Underscores() + "\nBye. Hope to see you again soon!\n" + Underscores());
    }

    public static void listPrinter(ArrayList<Task> items) {
        int index = 1;
        for (Task item : items) {
            System.out.println(index + ". " + (item).taskPrinter());
            index++;
        }
    }

    public static void markDone(int index, ArrayList<Task> texts) {
        if ((index <= texts.size() + 1) && (index > 0)) {
            (texts.get(index - 1)).setDone();
            System.out.println(Underscores());
            System.out.println("Nice! I've marked this as done:\n  " + texts.get(index - 1).taskPrinter());
        } else {
            System.out.println("Invalid index!");
        }
    }

    public static void addedTask(ArrayList<Task> texts) {
        System.out.println("Got it. I've added this task:\n  "
                + texts.get(texts.size()-1).taskPrinter()
                + "\nNow you have "
                + texts.size()
                + " tasks in the list");
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
        ArrayList<Task> texts = new ArrayList<>();

        while (true) {
            System.out.println(Underscores());
            text = input.nextLine();
            Validity textFormat = new Validity(text);

            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                System.out.println(Underscores());
                listPrinter(texts);
            } else if (text.equals("help")) {
                help();
            } else {
                String[] words = text.split(" ");
                switch (words[0]) {
                case "done":
                    words = textFormat.checkDone();
                    if (textFormat.isValid) {
                        int index = Integer.parseInt(words[1]);
                        markDone(index, texts);
                    }
                    break;
                case "event":
                    words = textFormat.checkEvent();
                    if (textFormat.isValid) {
                        texts.add(new Event(words[0], words[1]));
                        addedTask(texts);
                    }
                    break;
                case "deadline":
                    words = textFormat.checkDeadline();
                    if (textFormat.isValid) {
                        texts.add(new Deadline(words[0], words[1]));
                        addedTask(texts);
                    }
                    break;
                case "todo":
                    if (words.length > 1) {
                        texts.add(new Todo(text));
                        addedTask(texts);
                    }
                    break;
                default:
                    System.out.println("Invalid command or format! Enter 'help' for guidance");
                }
            }
        }
        farewell();
    }
}
