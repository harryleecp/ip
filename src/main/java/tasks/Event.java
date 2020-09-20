package tasks;

import formats.DateAndTime;

import java.time.DateTimeException;

/**
 * This class is meant for storing events that are happening on a specific date and time.<p>
 * The format of date is in "MMM dd yyyy" while the format of time is in "h:ma".
 *
 * @author Lee Chein Pong Harry
 */
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

    /**
     * Returns full description including object type, whether if it's done and due date.
     *
     * @return Full description of the Event object
     */
    public String printTask() {
        return "[E]" + super.printTask() + "(at: " + dueDate + ")";
    }
}
