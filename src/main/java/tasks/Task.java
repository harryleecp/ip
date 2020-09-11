package tasks;

public abstract class Task {
    public String task;
    public boolean isDone;

    public abstract String getTaskType();
    public abstract String getDueDate();

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
