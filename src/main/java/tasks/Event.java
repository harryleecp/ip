package tasks;

public class Event extends Task{

    public String dueDate;

    public Event(String description, String doAt) {
        super(description.substring(6));
        dueDate = doAt;
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
