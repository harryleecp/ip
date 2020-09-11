package tasks;

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

    public String printTask() {
        return "[T]" + super.printTask();
    }
}
