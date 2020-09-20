package formats;

/**
 * This user-defined exception is meant to be thrown
 * when the date and time format entered is wrong.
 *
 * @author Lee Chein Pong Harry
 */
public class TaskFormatException extends Exception {
    public TaskFormatException(String taskType) {
        super(taskType);
    }
}
