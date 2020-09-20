package tasks;

/**
 * This class is meant for storing tasks without the need for due dates.
 *
 * @author Lee Chein Pong Harry
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description.substring(5));
    }

    public String getTaskType() {
        return "T";
    }

    public String getDueDate() {
        return "";
    }

    /**
     * Returns full description including object and whether if it is done
     *
     * @return Full description of Todo object
     */
    public String printTask() {
        return "[T]" + super.printTask();
    }
}
