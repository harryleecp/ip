package userInterface;

import tasks.Task;
import tasks.Deadline;
import tasks.Todo;
import tasks.Event;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class handles the reading and writing of text file that contains the description of tasks.<p>
 * Each line contains the task type, whether if it is done, description and due date (if it is a deadline or an event).
 * This allows users to store their list of tasks using persistent storage.
 *
 * @author Lee Chein Pong Harry
 */
public class Storage {
    private String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * Reads from text file and stores Task objects corresponding to description of each line.
     * If file is not found, exception will be thrown and no array list will be created.
     *
     * @param tasks Task objects stored in the array list.
     * @throws FileNotFoundException If file does not exist.
     */
    public void loadTextFile(ArrayList<Task> tasks) throws FileNotFoundException{
        File f = new File(filename);
        Scanner s = new Scanner(f);
        String taskType;
        String isDoneValue;
        String taskDescription;
        String dueDate = "";

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] items = line.split(" \\| ");
            taskType = items[0];
            isDoneValue = items[1];
            taskDescription = items[2];
            if (items.length == 4) {
                //Only Deadline and Event objects have due date
                dueDate = items[3];
            }

            switch (taskType) {
            case "T":
                Todo todo = new Todo("todo " + taskDescription);
                if (isDoneValue.equals("1")) {
                    todo.setDone();
                }
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline("deadline " + taskDescription, dueDate);
                if (isDoneValue.equals("1")) {
                    deadline.setDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event("event " + taskDescription, dueDate);
                if (isDoneValue.equals("1")) {
                    event.setDone();
                }
                tasks.add(event);
                break;
            default:
            }
        }
    }

    /**
     * Stores description of each Task object into a text file.
     * The objects are stored in the same order as the array list.
     *
     * @param tasks Task objects stored in the array list.
     * @throws IOException If file cannot be saved.
     */
    public void saveTextFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filename);
        String textToWrite = "";
        for (Task item : tasks) {
            String doneIndex = (item.isDone) ? "1" : "0";
            if (item.getTaskType().equals("T")) {
                textToWrite += "T | " + doneIndex + " | " + item.task + "\n";
            } else {
                textToWrite += item.getTaskType() + " | " + doneIndex + " | "+ item.task + " | " + item.getDueDate() + "\n";
            }
        }
        fw.write(textToWrite);
        fw.close();
    }
}
