public class Todo extends Task{
    public Todo(String description) {
        super(description.substring(5));
    }

    public String taskPrinter() {
        return "[T]" + super.taskPrinter();
    }
}