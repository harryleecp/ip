package formats;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classes implementing this interface converts the format of date and time entered by the user.<p>
 * Users do not have to worry about following the format as this only serves to standardize the date and time
 * format for more convenient reading and filtering if need be.
 *
 * @author Lee Chein Pong Harry
 */
public interface DateAndTime {
    /**
     * Converts the formats of date and time into specific configurations.
     *
     * @param description Date and time entered by the user.
     * @return Converted format of date and time.
     * @throws DateTimeException If date and/or time cannot be parsed.
     */
     static String convertDateFormat(String description) throws DateTimeException {
        String[] dateDetails = description.split(" ");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/y");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        LocalDate date = LocalDate.parse(dateDetails[0], dateFormatter);
        LocalTime time = LocalTime.parse(dateDetails[1], timeFormatter);

        String dateString = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).toString();
        String timeString = time.format(DateTimeFormatter.ofPattern("h:mma")).toString();

        return dateString + " " + timeString;
    }
}
