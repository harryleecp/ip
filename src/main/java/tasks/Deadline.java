package tasks;

import formats.DateAndTime;

import java.time.DateTimeException;

public class Deadline extends Task {
    public String dueDate;

    public Deadline(String description, String doBy) {
        super(description.substring(9));
        try {
            dueDate = DateAndTime.convertDateFormat(doBy);
        } catch (DateTimeException e) {
            dueDate = doBy;
        }
    }

    public String getTaskType() {
        return "D";
    }

    public String getDueDate() {
        return dueDate;
    }
    public String printTask() {
        return "[D]" + super.printTask() + "(by: " + dueDate + ")";
    }
}
