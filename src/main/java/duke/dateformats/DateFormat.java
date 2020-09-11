package duke.dateformats;

import java.time.LocalDate;

public interface DateFormat {
    boolean check(String date);
    LocalDate mapToLocalDate(String date);
}
