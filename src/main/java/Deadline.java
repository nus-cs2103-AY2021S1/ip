import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Deadline extends Task {
    LocalDate deadlineDate;
    LocalTime deadlineTime;

    public Deadline(String userInput) throws deadlineException {
        String[] StringArr = handleUserInput(userInput);
        this.description = StringArr[0];
        deadlineDate = processDate(StringArr[1]);
        deadlineTime = processTime(StringArr[1]);
    }


    // Constructor for use by Storage.loadFromDisk();
    public Deadline(String description, String deadline, boolean isDone) throws deadlineException {
        super(description);
        deadlineDate = processDate(deadline);
        deadlineTime = processTime(deadline);
        this.isDone = isDone;
    }

    private String[] handleUserInput(String userInput) throws deadlineException {
        String[] StringArr = userInput.split(" /by ");
        if (StringArr.length != 2) throw new deadlineException("\"1.Please provide a deadline is the following " +
                "format: <description /by YYYY/MM/DD TTTT> or <description /by DD/YY/MMMM TTTT>");
        StringArr[0] = StringArr[0].replace("deadline ", "");
        if (StringArr[0].equals("")) throw new deadlineException("Please give a description");
        return StringArr;
    }

    private LocalDate processDate(String deadline) throws deadlineException {
        String[] dateTimeSplit = deadline.split(" ");
        String[] dateSplit = dateTimeSplit[0].split("/");
        if (dateTimeSplit.length != 2 || dateSplit.length != 3)
            throw new deadlineException("3.Please provide a deadline is the following " +
                    "format: <description /by YYYY/MM/DD> or <description /by DD/YY/MMMM>");
        if (dateSplit[0].length() == 4) {
            return LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
        } else {
            return LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0]));
        }
    }

    private LocalTime processTime(String deadline) {
        String[] dateTimeSplit = deadline.split(" ");
        return LocalTime.of(Integer.parseInt(dateTimeSplit[1].substring(0, 2)), Integer.parseInt(dateTimeSplit[1].substring(2)));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " ~ " + deadlineDate.toString().replaceAll("-", "/") + " " + deadlineTime.toString().replace(":", "");
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "[D]" + super.getStatusIcon() + this.description + " (by:" + deadlineDate.toString() + " " + deadlineTime.toString() + ")";
        if (!duration.equals("")) returnString += " (duration: " + this.duration + ")";
        return returnString;
    }
}
