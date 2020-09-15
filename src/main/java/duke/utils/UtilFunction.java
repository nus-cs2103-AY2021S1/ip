package duke.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.dateformats.DateFormat;
import duke.exceptions.DateFormatException;
import duke.exceptions.DurationErrorType;
import duke.exceptions.DurationFormatException;

/**
 * @author Eddy
 * @version 1.0.0
 */
public class UtilFunction {

    private static final DateTimeFormatter STANDARDDATEFORMATTER =
            new DateTimeFormatterBuilder().appendPattern("MMM d yyyy")
                    .optionalStart().appendPattern(" HH:mm")
                    .optionalEnd().toFormatter();
    /**
     * Util function used to match pattern of the string.
     *
     * @param patternStr the pattern you want to check
     * @param string the string to check
     * @return true if the string matches the string pattern
     */
    public static Boolean matchPattern(String patternStr, String string) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    /**
     * Util function used to format available date input to standard date format.
     *
     * @param dateString input string to format
     * @return date in standard format "MMM d yyyy"
     * @throws DateFormatException when input string date formal is invalid
     * @see duke.exceptions.DateFormatException
     */
    public static String formatDateTimeToStandard(String dateString) throws DateFormatException {

        LocalDateTime ldt;
        for (DateFormat format: Constants.DATE_TIME_FORMAT_LIST) {
            if (format.check(dateString)) {
                ldt = format.mapToLocalDateTime(dateString);
                String standardDate = STANDARDDATEFORMATTER.format(ldt);
                return standardDate;
            }
        }

        throw new DateFormatException("The date format is not valid.");
    }

    /**
     * Util function that formats duration string to standard forms.
     * @param durationStr
     * @return standard format of duration
     * @throws DurationFormatException when duration does not consist 2 valid sequence time
     * @throws DateFormatException when the 2 dates in the duration are invalid
     */
    public static String formatDurationToStandard(String durationStr)
            throws DurationFormatException, DateFormatException {
        String[] duration = durationStr.split("~");
        try {
            String startTime = formatDateTimeToStandard(duration[0].trim());
            String endTimeInput = duration[1].trim();
            String endTime;
            if (checkTimeFormat(endTimeInput)) {
                //it always works, because the startTime is in the standard format
                String[] splitStartTime = startTime.split(" ");
                splitStartTime[3] = endTimeInput;
                endTime = String.join(" ", splitStartTime);
            } else {
                endTime = formatDateTimeToStandard(endTimeInput);
            }
            if (isAfter(startTime, endTime)) {
                throw new DurationFormatException("Start time should be before the end time",
                        DurationErrorType.STARTENDTIMESEQUENCEERROR);
            }
            return startTime + " ~ " + endTime;
        } catch (IndexOutOfBoundsException e) {
            throw new DurationFormatException("Duration is not complete", DurationErrorType.INVALIDINPUTERROR);
        }
    }

    private static boolean isAfter(String time1, String time2) {
        LocalDateTime time1Obj = LocalDateTime.parse(time1, STANDARDDATEFORMATTER);
        LocalDateTime time2Obj = LocalDateTime.parse(time2, STANDARDDATEFORMATTER);
        return time1Obj.isAfter(time2Obj);
    }

    private static boolean checkTimeFormat(String time) {
        //solutions below adapted from
        // https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s06.html
        return time.matches("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
    }


    /**
     * Util function used to print message within {@value duke.utils.Constants#CONSOLEWIDTH}.
     * @param output the string meant to be printed in the console
     */
    public static void printLimit(String output) {
        String[] sentences = output.split("\n");
        for (String sentence: sentences) {
            if (sentence.length() < Constants.CONSOLEWIDTH) {
                System.out.println(sentence);
            } else {
                assert sentence.length() >= Constants.CONSOLEWIDTH : "sentence length: " + sentence.length();
                wrapAndDisplay(sentence);
            }
        }
    }

    private static void wrapAndDisplay(String sentence) {
        String[] words = sentence.split("\\s+");
        int count = 0;
        for (String word: words) {
            count += word.length();
            if (count < Constants.CONSOLEWIDTH) {
                System.out.print(word + " ");
            } else {
                System.out.print('\n' + word + " ");
                count = word.length();
            }
        }
        System.out.print('\n');
    }

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date now = new Date(System.currentTimeMillis());
        return formatter.format(now);
    }


}
