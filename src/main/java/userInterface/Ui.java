package userInterface;

import formats.TaskFormatException;
import formats.Validity;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Ui {
    public String printUnderscores() {
        return "__________________________________________";
    }

    public void printHelp() {
        String instructions = "The following is a list of commands that you can enter:\n"
                + "1) help - For showing all commands available\n"
                + "2) list - To display all the tasks in your list\n"
                + "3) todo - Add a to-do task into your list (Example: todo borrow book)\n"
                + "4) deadline - Use \" /by \" to denote due date and time (Example: deadline return book /by 19/09/2020 1500)\n"
                + "5) event - Use \" /at \" to denote when it takes place (Example: event meeting /at 20/09/2020 0900)\n"
                + "6) printbydate - Prints deadlines and events according to date\n"
                + "7) bye - To exit from the program";
        System.out.println(instructions);
    }

    public void printGreetingMessage() {
        String logo = " _______    __   ___      ___  _______\n"
                + "|__   __|  /  \\  \\  \\    /  / |   ____|\n"
                + "   | |    / __ \\  \\  \\  /  /  |  |__\n"
                + "   | |   / |__| \\  \\  \\/  /   |   __|\n"
                + " __| |  /   __   \\  \\    /    |  |____\n"
                + "|____| /___|  |___\\  \\__/     |_______|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(printUnderscores() + "\nHello! I'm Jave" + "\nWhat can I do for you?");
    }

    public void printFarewellMessage() {
        System.out.println(printUnderscores() + "\nBye. Hope to see you again soon!\n" + printUnderscores());
    }

    public void printList(ArrayList<Task> items) {
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

    public ArrayList<Task> findTasks(ArrayList<Task> tasks, String keywords) {
        ArrayList<Task> filteredTasks;
        filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t instanceof Deadline || t instanceof Event)
                .filter((t) -> t.printTask().contains(keywords))
                .collect(Collectors.toList());
        return filteredTasks;
    }

    public void printByDate(ArrayList<Task> tasks, String description) {
        ArrayList<Task> filteredTasks;
        filteredTasks = (ArrayList<Task>) tasks.stream()
            .filter((t) -> t instanceof Deadline | t instanceof Event)
            .filter((t) -> t.getDueDate().contains(description))
            .collect(Collectors.toList());
        printList(filteredTasks);
    }

    public void showLoadingError() {
        System.out.println("File tasklist.txt is not found, creating new file.......");
    }

    public void markDone(int index, ArrayList<Task> tasks) {
        if ((index <= tasks.size()) && (index > 0)) {
            (tasks.get(index - 1)).setDone();
            System.out.println(printUnderscores());
            System.out.println("Nice! I've marked this as done:\n  " + tasks.get(index - 1).printTask());
        } else {
            System.out.println("Invalid index!");
        }
    }

    public void deleteTask(int index, ArrayList<Task> tasks) {
        if ((index <= tasks.size()) && (index > 0)) {
            System.out.println("Noted. I've removed this task:\n  " + tasks.get(index - 1).printTask());
            tasks.remove(index - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Invalid index!");
        }
    }

    public void addedTask(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n  "
                + tasks.get(tasks.size()-1).printTask()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list");
    }

    public void checkRemainingCases(ArrayList<Task> tasks, Validity taskFormat, String task) throws TaskFormatException {
        String[] words = task.split(" ");
        switch (words[0].toLowerCase()) {
        case "done":
            words = taskFormat.checkDone();
            if (taskFormat.isValid) {
                int index = Integer.parseInt(words[1]);
                markDone(index, tasks);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The description of done cannot be empty and must be a digit.");
            }
            break;
        case "find":
            taskFormat.checkFind();
            if (taskFormat.isValid) {
                printList(findTasks(tasks, task.substring(5)));
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The description of find cannot be empty.");
            }
            break;
        case "printbydate":
            taskFormat.checkPrintByDate();
            if (taskFormat.isValid) {
                printByDate(tasks, task.substring(12));
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The description of printbydate cannot be empty");
            }
            break;
        case "delete":
            words = taskFormat.checkDelete();
            if (taskFormat.isValid) {
                int index = Integer.parseInt(words[1]);
                deleteTask(index, tasks);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The description of delete cannot be empty and must be a digit.");
            }
            break;
        case "event":
            words = taskFormat.checkEvent();
            if (taskFormat.isValid) {
                tasks.add(new Event(words[0], words[1]));
                addedTask(tasks);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The event description format is wrong.");
            }
            break;
        case "deadline":
            words = taskFormat.checkDeadline();
            if (taskFormat.isValid) {
                tasks.add(new Deadline(words[0], words[1]));
                addedTask(tasks);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The deadline description format is wrong.");
            }
            break;
        case "todo":
            if (words.length > 1) {
                tasks.add(new Todo(task));
                addedTask(tasks);
            } else {
                throw new TaskFormatException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        default:
            throw new TaskFormatException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
