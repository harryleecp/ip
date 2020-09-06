public class Deadline extends Task {
    private String doBy;

    public Deadline(String description, String doBy) {
        super(description.substring(9));
        this.doBy = doBy;
    }

    public String printTask() {
        return "[D]" + super.printTask() + "(by: " + doBy + ")";
    }
}
