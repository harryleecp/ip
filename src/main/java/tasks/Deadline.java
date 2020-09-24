package tasks;

import formats.DateAndTime;

import java.time.DateTimeException;

/**
 * This class is meant for storing upcoming deadlines on a specific date and time.<p>
 * The format of date is in "MMM dd yyyy" while the format of time is in "h:ma".
 *
 * @author Lee Chein Pong Harry
 */
public class Deadline extends Task implements DateAndTime{
    private String dueDate;

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

    /**
     * Returns full description including object type, whether if it's done and due date.
     *
     * @return Full description of the Deadline object
     */
    public String printTask() {
        return "[D]" + super.printTask() + "(by: " + dueDate + ")";
    }
}
