package userInterface;

import formats.TaskFormatException;
import formats.Validity;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class mostly handles all the User Interface features.<p>
 * Users get to modify their list of tasks through adding and deleting.
 * Moreover they also get to search for tasks using keywords or filter with date and time
 * in case the list gets too long to view one by one.
 *
 * @author Lee Chein Pong Harry
 */
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
                + "7) find - Prints tasks containing keyword(s) entered by the user\n"
                + "8) bye - To save the list of tasks before exiting from the program";
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

    public void showLoadingError() {
        System.out.println("File tasklist.txt is not found, creating new file.......");
    }

    /**
     * Notifies user about the newly added task.
     * Prints error message when index is out of bounds.
     *
     * @param tasks Task objects stored in the array list.
     */
    public void addedTask(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n  "
                + tasks.get(tasks.size()-1).printTask()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list");
    }

    /**
     * Notifies user about the deleted task.
     * Prints error message when index is out of bounds.
     *
     * @param index Index of Task object to be deleted.
     * @param tasks Task objects stored in the array list.
     */
    public void deleteTask(int index, ArrayList<Task> tasks) {
        if ((index <= tasks.size()) && (index > 0)) {
            System.out.println("Noted. I've removed this task:\n  " + tasks.get(index - 1).printTask());
            tasks.remove(index - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Invalid index!");
        }
    }

    /**
     * Notifies user about the Task object that is marked as done.
     * Prints error message when index is out of bounds.
     *
     * @param index Index of Task object to be marked as done.
     * @param tasks Task objects stored in the array list.
     */
    public void markDone(int index, ArrayList<Task> tasks) {
        if ((index <= tasks.size()) && (index > 0)) {
            (tasks.get(index - 1)).setDone();
            System.out.println(printUnderscores());
            System.out.println("Nice! I've marked this as done:\n  " + tasks.get(index - 1).printTask());
        } else {
            System.out.println("Invalid index!");
        }
    }

    /**
     * Prints Task objects containing on the keyword(s) entered by the user.
     *
     * @param tasks Task objects stored in the array list.
     * @param keywords Keyword(s) for narrowing down the search.
     */
    public void findTasks(ArrayList<Task> tasks, String keywords) {
        ArrayList<Task> filteredTasks;
        filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getTask().contains(keywords))
                .collect(Collectors.toList());
        if (filteredTasks.size() == 0) {
            System.out.println("OOPS!!! We can't find anything that contains the description.");
        } else {
            System.out.println("Here's what we have found: ");
            printList(filteredTasks);
        }
    }

    /**
     * Prints all deadlines and events that containing the due date entered by the user.
     *
     * @param tasks Task objects stored in the array list.
     * @param description Date and/or time entered by the user.
     */
    public void printByDate(ArrayList<Task> tasks, String description) {
        ArrayList<Task> filteredTasks;
        filteredTasks = (ArrayList<Task>) tasks.stream()
            .filter((t) -> t instanceof Deadline | t instanceof Event)
            .filter((t) -> t.getDueDate().contains(description))
            .collect(Collectors.toList());
        if (filteredTasks.size() == 0) {
            System.out.println("OOPS!!! We can't find anything according to this date.");
        } else {
            System.out.println("Here's what we have found: ");
            printList(filteredTasks);
        }
    }

    /**
     * Checks the validity of the command entered by the user.
     * If command is of the wrong format or not recognised, error message will be printed.
     *
     * @param tasks Task objects stored in the array list
     * @param taskFormat TaskFormat object containing the command entered by the user
     * @param task Full description of the command
     * @throws TaskFormatException If command entered is of the wrong format
     */
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
                findTasks(tasks, task.substring(5));
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
