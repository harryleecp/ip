import formats.TaskFormatException;
import formats.Validity;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static String printUnderscores() {
        return "__________________________________________";
    }

    public static void printHelp() {
        String instructions = "The following is a list of commands that you can enter:\n"
                + "1) help - For showing all commands available\n"
                + "2) list - To display all the tasks in your list\n"
                + "3) todo - Add a to-do task into your list (Example: todo borrow book)\n"
                + "4) deadline - Use \" /by \" to denote deadline (Example: deadline return book /by Sunday)\n"
                + "5) event - Use \" /at \" to denote when the event takes place (Example: event meeting /at Mon 2-4pm)\n"
                + "6) bye - To exit from the program";
        System.out.println(instructions);
    }

    public static void printGreetingMessage() {
        System.out.println(printUnderscores() + "\nHello! I'm Jave" + "\nWhat can I do for you?");
    }

    public static void printFarewellMessage() {
        System.out.println(printUnderscores() + "\nBye. Hope to see you again soon!\n" + printUnderscores());
    }

    public static void printList(ArrayList<Task> items) {
        int index = 1;
        if (items.size() == 0) {
            System.out.println("There is nothing in your list currently");
        } else {
            for (Task item : items) {
                System.out.println(index + ". " + (item).printTask());
                index++;
            }
        }
    }

    public static void markDone(int index, ArrayList<Task> texts) {
        if ((index <= texts.size() + 1) && (index > 0)) {
            (texts.get(index - 1)).setDone();
            System.out.println(printUnderscores());
            System.out.println("Nice! I've marked this as done:\n  " + texts.get(index - 1).printTask());
        } else {
            System.out.println("Invalid index!");
        }
    }

    public static void addedTask(ArrayList<Task> texts) {
        System.out.println("Got it. I've added this task:\n  "
                + texts.get(texts.size()-1).printTask()
                + "\nNow you have "
                + texts.size()
                + " tasks in the list");
    }

    public static void checkRemainingCases(ArrayList<Task> texts, Validity textFormat, String text) throws TaskFormatException {
        String[] words = text.split(" ");
        switch (words[0]) {
        case "done":
            words = textFormat.checkDone();
            if (textFormat.isValid) {
                int index = Integer.parseInt(words[1]);
                markDone(index, texts);
            } else {
                System.out.println("I'm here");
                throw new TaskFormatException("\u2639 OOPS!!! The description of done cannot be empty and must be a digit.");
            }
            break;
        case "event":
            words = textFormat.checkEvent();
            if (textFormat.isValid) {
                texts.add(new Event(words[0], words[1]));
                addedTask(texts);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The event description format is wrong.");
            }
            break;
        case "deadline":
            words = textFormat.checkDeadline();
            if (textFormat.isValid) {
                texts.add(new Deadline(words[0], words[1]));
                addedTask(texts);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The deadline description format is wrong.");
            }
            break;
        case "todo":
            if (words.length > 1) {
               texts.add(new Todo(text));
               addedTask(texts);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        default:
            throw new TaskFormatException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
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
        printGreetingMessage();
        ArrayList<Task> texts = new ArrayList<>();

        while (true) {
            System.out.println(printUnderscores());
            text = input.nextLine();
            Validity textFormat = new Validity(text);

            if (text.equals("bye")) {
                break;
            } else if (text.equals("list")) {
                System.out.println(printUnderscores());
                printList(texts);
            } else if (text.equals("help")) {
                printHelp();
            } else {
                try {
                    checkRemainingCases(texts, textFormat, text);
                } catch (TaskFormatException e) {
                    System.out.println(e.toString().substring(21) + "\nEnter \"help\" for more info");
                }
            }
        }
        printFarewellMessage();
    }
}
