package tasks;

/**
 * This class is for storing information of a task
 * such as the description and if the task is complete.
 *
 * @author Lee Chein Pong Harry
 */
public abstract class Task {
    public String task;
    public boolean isDone;

    public abstract String getTaskType();
    public abstract String getDueDate();

    public Task (String userInput) {
        task = userInput;
        isDone = false;
    }

    public String getTask() {
        return task;
    }

    public void setDone () {
        this.isDone = true;
    }

    public String printTask() {
        String mark = "";
        if (this.isDone) {
            mark = "[\u2713]";
        } else {
            mark = "[\u2718]";
        }
        return mark + " " + this.task;
    }
}
