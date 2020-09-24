package formats;

/**
 * This class checks for all the validity of the commands entered by the user.<p>
 * As users might not be aware of the formats, this class can handle all the possible exceptions
 * without the need for restarting the program.
 *
 * @author Lee Chein Pong Harry
 */
public class Validity {
    private String description;
    private String[] words;
    private boolean isValid;

    public Validity(String description) {
        this.description = description;
        isValid = true;
        splitDescription();
    }

    public void splitDescription() {
        words = description.split(" ");
    }

    public boolean getIsValid() {
        return isValid;
    }
    /**
     * Verifies the format of the 'done' command.
     * Returns the split string containing the command and index if correct.
     *
     * @return Array of split string.
     */
    public String[] checkDone() {
        if (words.length != 2) {
            isValid = false;
        }
        try {
            int index = Integer.parseInt(words[1]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            isValid = false;
        }
        return words;
    }

    /**
     * Verifies the format 'delete' command.
     * Returns the split string containing the command and index if correct.
     *
     * @return Array of split string.
     */
    public String[] checkDelete() {
        if (words.length != 2) {
            isValid = false;
        }
        try {
            int index = Integer.parseInt(words[1]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            isValid = false;
        }
        return words;
    }

    /**
     * Verifies the format of 'deadline' command.
     * If correct, split string contains command, description and due date.
     *
     * @return Array of split string.
     */
    public String[] checkDeadline() {
        if(!(description.contains("/by"))) {
            isValid = false;
        } else {
            words = description.split(" /by ");
            if (words.length <= 1) {
                isValid = false;
            }
        }

        return words;
    }

    /**
     * Verifies the format of 'event' command.
     * If correct, split string contains command, description and due date.
     *
     * @return Array of split string.
     */
    public String[] checkEvent() {
        if(!(description.contains("/at"))) {
            isValid = false;
        } else {
            words = description.split(" /at ");
            if (words.length <= 1) {
                isValid = false;
            }
        }

        return words;
    }

    /**
     * Verifies the format of 'find' command.
     */
    public void checkFind() {
        if (words.length == 1) {
            isValid = false;
        }
    }

    /**
     * Verifies the format of 'printbydate' command.
     */
    public void checkPrintByDate() {
        if (words.length == 1) {
            isValid = false;
        }
    }
}
