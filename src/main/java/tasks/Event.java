package tasks;

import formats.DateAndTime;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class Event extends Task implements DateAndTime {

    public String dueDate;

    public Event(String description, String doAt) {
        super(description.substring(6));
        try {
            dueDate = DateAndTime.convertDateFormat(doAt);
        } catch (DateTimeException e) {
            dueDate = doAt;
        }
    }

    public String getTaskType() {
        return "E";
    }

    public String getDueDate() {
        return dueDate;
    }

    public String printTask() {
        return "[E]" + super.printTask() + "(at: " + dueDate + ")";
    }
}
