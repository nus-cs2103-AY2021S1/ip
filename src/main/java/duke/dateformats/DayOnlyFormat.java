package duke.dateformats;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayOnlyFormat implements DateFormat {
    private final List<String> days = Arrays.asList("^MON(DAY)?$", "^TUE(SDAY)?$", "^WED(NESDAY)?$", "^THU(RSDAY)?$",
            "^FRI(DAY)?$", "^SAT(URDAY)?$", "^SUN(DAY)?$");

    @Override
    public boolean check(String date) {
        return days.stream().anyMatch(day -> date.toUpperCase().matches(day));
    }

    /**
     * Format date string to standard date format as LocalDateTime object.
     * @param date input date string
     * @return
     */
    public LocalDate mapToLocalDate(String date) {
        try {
            return days.stream().filter(day -> date.toUpperCase().matches(day)).map(day ->
                            LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.of(days.indexOf(day) + 1)))
            ).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            assert false : "should only be called after checked";
            return null;
        }
    }
}
