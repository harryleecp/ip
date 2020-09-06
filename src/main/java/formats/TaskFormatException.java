package formats;

public class TaskFormatException extends Exception {
    public TaskFormatException(String taskType) {
        super(taskType);
    }
}
