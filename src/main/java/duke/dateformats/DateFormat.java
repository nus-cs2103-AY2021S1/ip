package duke.dateformats;

import java.time.LocalDateTime;

public interface DateFormat {
    boolean check(String date);
    LocalDateTime formatToStandard(String date);
}
