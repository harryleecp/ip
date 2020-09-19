package formats;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public interface DateAndTime {
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
