import tasks.Task;

import userInterface.Storage;
import formats.TaskFormatException;
import userInterface.Ui;
import formats.Validity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storage;
    private static Ui ui;

    /**
     * Instantiate Task objects based on description of each line in text file.
     * If file does not exist, exception will be handled.
     *
     * @param filePath Name of text file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.loadTextFile(tasks);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Commands are executed based on the user's choice
     * Method runs as long as user does not type 'bye' command    *
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        String text = "";
        ui.printGreetingMessage();

        while (true) {
            System.out.println(ui.printUnderscores());
            text = input.nextLine();
            Validity textFormat = new Validity(text);

            if (text.equals("bye")) {
                System.out.println("Saving to file.........");
                try {
                    storage.saveTextFile(tasks);
                } catch (IOException e) {
                    System.out.println("OOPS!!! Something went wrong when saving the file!");
                }
                break;
            } else if (text.equals("list")) {
                System.out.println(ui.printUnderscores());
                ui.printList(tasks);
            } else if (text.equals("help")) {
                ui.printHelp();
            } else {
                try {
                    ui.checkRemainingCases(tasks, textFormat, text);
                } catch (TaskFormatException e) {
                    System.out.println(e.toString().substring(29) + "\nEnter \"help\" for more info");
                }
            }
        }
        ui.printFarewellMessage();
    }

    public static void main(String[] args) {
        new Duke("tasklist.txt").run();
    }
}
