import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Parser {

    public static LocalDateTime changeDateAndTime(String[] dateAndTime) throws NumberFormatException, ParseException {
        String date = dateAndTime[0];
        String time = dateAndTime[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateAndTime = LocalDateTime.parse((date + " " + time), formatter);
        return localDateAndTime;
    }

    public static Command parse(String fullCommand) throws InvalidFirstDukeException {
        String[] commandArr = fullCommand.split(" "); // split input into string array
        Command command;

        if (commandArr[0].equals("bye")) {
            command = new Command(CommandType.EXITDUKE, null);
        } else if (commandArr[0].equals("list")) {
            command = new Command(CommandType.PRINTALLTASKS, null);
        } else if (commandArr[0].equals("done")) {
            command = new Command(CommandType.MARKASDONE, commandArr);
        } else if (commandArr[0].equals("delete")) {
            command = new Command(CommandType.DELETETASK, commandArr);
        } else if (commandArr[0].equals("todo")) {
            command = new Command(CommandType.ADDTODO, commandArr);
        } else if (commandArr[0].equals("deadline")) {
            command = new Command(CommandType.ADDDEADLINE, commandArr);
        } else if (commandArr[0].equals("event")) {
            command = new Command(CommandType.ADDEVENT, commandArr);
        } else {
            throw new InvalidFirstDukeException();
        }
        return command;
    }

    public static void main(String[] args) {
        try {
            String[] temp = {"2019-12-20", "18:00:00"};
            System.out.println(Parser.changeDateAndTime(temp));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

    }
}
