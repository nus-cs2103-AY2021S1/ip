package duke.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeHelper {
    private String dateTime;
    private LocalDate fullDate;
    private String fullTime = "";
    private String dateStr = "";
    private String timeStr = "";
    public DateTimeHelper(String deadline) {
        this.dateTime = deadline;
    }
    public String processTimeStr() {
        try {
            int time = Integer.parseInt(timeStr);
            int hours = (time / 100) % 12;
            int minutes = time % 100;

            String identifier = time / 100 > 12 ? "pm" : "am";
            if (time / 100 == 12) {
                hours = 12;
                identifier = "pm";
            }
            else if (time / 100 == 24)
                identifier = "am";

            String paddingForHour = hours < 10 ? "0" : "";
            String paddingForMinute = minutes < 10 ? "0" : "";
            this.fullTime = paddingForHour + hours + ":" + paddingForMinute + minutes + identifier;
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    public String processDateStr() {
        try {
            this.fullDate = LocalDate.parse(dateStr);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    public String processInput() {
        try {
            //String timeStr = "", dateStr = "";
            String[] tokens = this.dateTime.split(" ");
            for (String token : tokens) {
                if (!token.equals("")) {
                    if (dateStr.equals("")) dateStr = token;
                    else {
                        timeStr = token;
                        break;
                    }
                }
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    public static String getStringRep(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    public LocalDate getDate() {
        return this.fullDate;
        //return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + " " + fullTime;
    }
    public static DateTimeHelper processDateTime(String input) {
        DateTimeHelper dtHelper = new DateTimeHelper(input.strip());
        String result = dtHelper.processInput();
        String resDate = dtHelper.processDateStr();

        if (!result.equals("error") && !resDate.equals("error")) {
            return dtHelper;
        } else {
            return null;
        }
    }
    public String getTime() {
        return this.fullTime;
    }
}
