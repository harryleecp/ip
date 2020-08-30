public class Event extends Task{
    private String doAt;

    public Event(String description, String doAt) {
        super(description.substring(6));
        this.doAt = doAt;
    }

    public String taskPrinter() {
        return "[E]" + super.taskPrinter() + "(at: " + doAt + ")";
    }
}
