public class Task {
    private String task;
    private boolean isDone;

    public Task (String x) {
        this.task = x;
        this.isDone = false;
    }

    public void setDone () {
        this.isDone = true;
    }

    public String taskPrinter() {
        String mark = "";
        if (this.isDone == true) {
            mark = "[\u2713]";
        }
        else {
            mark = "[\u2718]";
        }
        return mark + " " + this.task;
    }
}
