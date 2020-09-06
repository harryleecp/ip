package tasks;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public Task (String x) {
        this.task = x;
        this.isDone = false;
    }

    public void setDone () {
        this.isDone = true;
    }

    public String printTask() {
        String mark = "";
        if (this.isDone == true) {
            mark = "[\u2713]";
        } else {
            mark = "[\u2718]";
        }
        return mark + " " + this.task;
    }
}
