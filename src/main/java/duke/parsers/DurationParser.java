package duke.parsers;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.exceptions.DukeException;
import duke.exceptions.DurationErrorType;
import duke.exceptions.DurationFormatException;
import duke.utils.Pair;


public class DurationParser implements Parser<Pair<LocalDateTime, LocalDateTime>> {
    private static final String DURATION_DELIMITER = "~";
    private static final String TIME_DELIMITER = ":";
    private String duration;

    public DurationParser(String duration) {
        this.duration = duration;
    }

    @Override
    public Pair<LocalDateTime, LocalDateTime> parse() throws DukeException {

        String[] splitDuration = duration.split(DURATION_DELIMITER);
        try {
            LocalDateTime startTime = new DateParser(splitDuration[0].trim()).parse();
            String endTimeInput = splitDuration[1].trim();
            LocalDateTime endTime;

            //handle if end time only contains time
            if (checkTimeFormat(endTimeInput)) {
                //it always works, because the startTime is in the standard format
                String[] splitTime = endTimeInput.split(TIME_DELIMITER);
                int hour = Integer.parseInt(splitTime[0]);
                int minute = Integer.parseInt(splitTime[1]);
                endTime = startTime.with(LocalTime.of(hour, minute));
            } else { //when the end date time is specified
                endTime = new DateParser(endTimeInput).parse();
            }

            if (startTime.isAfter(endTime)) {
                throw new DurationFormatException("Start time should be before the end time",
                        DurationErrorType.STARTENDTIMESEQUENCEERROR);
            }
            return new Pair<>(startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DurationFormatException("Duration is not complete", DurationErrorType.INVALIDINPUTERROR);
        }
    }


    private boolean checkTimeFormat(String time) {
        //solutions below adapted from
        // https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s06.html
        return time.matches("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
    }
}
