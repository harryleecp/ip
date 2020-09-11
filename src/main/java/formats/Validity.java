package formats;

public class Validity {
    private String description;
    public boolean isValid;

    public Validity(String description) {
        this.description = description;
        this.isValid = true;
    }

    public String[] checkDone() {
        String[] words = description.split(" ");
        if (!(words[0].equals("done")) || (words.length != 2)) {
            isValid = false;
        }
        try {
            int index = Integer.parseInt(words[1]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            isValid = false;
        }
        return words;
    }

    public String[] checkDeadline() {
        String[] words = description.split(" ");
        if (!(words[0].equals("deadline"))) {
            isValid = false;
        } else if(!(description.contains("/by"))) {
            isValid = false;
        } else {
            words = description.split(" /by ");
            if (words.length <= 1) {
                isValid = false;
            }
        }

        return words;
    }

    public String[] checkEvent() {
        String[] words = description.split(" ");
        if (!(words[0].equals("event"))) {
            isValid = false;
        } else if(!(description.contains("/at"))) {
            isValid = false;
        } else {
            words = description.split(" /at ");
            if (words.length <= 1) {
                isValid = false;
            }
        }

        return words;
    }

    public String[] checkDelete() {
        String[] words = description.split(" ");
        if (!(words[0].equals("delete")) || (words.length != 2)) {
            isValid = false;
        }
        try {
            int index = Integer.parseInt(words[1]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            isValid = false;
        }
        return words;
    }
}
