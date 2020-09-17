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

public class Storage {
    private String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public void loadTextFile(ArrayList<Task> tasks) throws FileNotFoundException{
        File f = new File(filename);
        Scanner s = new Scanner(f);
        String taskType;
        String isDoneValue;
        String taskDescription;
        String dateAndTime = "";

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] items = line.split(" \\| ");
            taskType = items[0];
            isDoneValue = items[1];
            taskDescription = items[2];
            if (items.length == 4) {
                dateAndTime = items[3];
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
                Deadline deadline = new Deadline("deadline " + taskDescription, dateAndTime);
                if (isDoneValue.equals("1")) {
                    deadline.setDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event("event " + taskDescription, dateAndTime);
                if (isDoneValue.equals("1")) {
                    event.setDone();
                }
                tasks.add(event);
                break;
            default:
            }
        }
    }

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
