package duke.dateformats;

import java.time.LocalDateTime;

import duke.exceptions.DateFormatException;


public interface DateFormat {
    boolean check(String date);
    LocalDateTime mapToLocalDateTime(String date) throws DateFormatException;
}
