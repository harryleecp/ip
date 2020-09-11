package tasks;

public class Deadline extends Task {
    public String dueDate;

    public Deadline(String description, String doBy) {
        super(description.substring(9));
        dueDate = doBy;
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
