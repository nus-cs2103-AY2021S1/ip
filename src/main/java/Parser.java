import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    //makes sense of user command and processes it to various component esp if is add tasks
    //e.g. if to-do task -> [T,task description]
    //bye = [bye]
    //list = [list]
    //print = [print,date]
    //done = [done,taskNum]
    //delete = [delete,taskNum]
    //deadline = [D, task description, date, time]
    //event = [E, task description, date, startTime, endTime]
    //invalid input = throw exception
    public String[] parse(String command) throws InvalidDateAndTimeException, InvalidTaskNumber, NoDescriptionException, NotTaskException {
        String[] cmd = command.split(" ");
        switch(cmd[0].toLowerCase()) {
        case("bye"):
        case("list"):
            return cmd;
        case("print"):
            //check if is valid date
            try {
                LocalDate.parse(cmd[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return cmd;
            } catch (DateTimeParseException e) {
                throw new InvalidDateAndTimeException();
            }
        //check if an integer is given for taskNum
        case("done"):
        case("delete"):
            try {
                Integer.parseInt(cmd[1]);
                return cmd;
            } catch (NumberFormatException e) {
                throw new InvalidTaskNumber();
            }
        case("todo"):
            if (cmd.length < 2) {
                throw new NoDescriptionException(cmd[0]);
            } else {
                return new String[]{cmd[0], command.replaceFirst(cmd[0] + " ", "")};
            }
        //check if it is valid date and time
        case("deadline"):
            if (cmd.length < 2) {
                throw new NoDescriptionException(cmd[0]);
            } else {
                try {
                    LocalDate.parse(cmd[cmd.length - 2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime.parse(cmd[cmd.length - 1], DateTimeFormatter.ofPattern("HHmm"));
                    String[] splitBySlash = command.split("/");
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                                        cmd[cmd.length - 2], cmd[cmd.length - 1]};
                } catch (DateTimeParseException e){
                    throw new InvalidDateAndTimeException();
                }
            }
        //check for valid date and time
        case("event"):
            if (cmd.length < 2) {
                throw new NoDescriptionException(cmd[0]);
            } else {
                try {
                    System.out.println(Arrays.toString(cmd));
                    LocalDate.parse(cmd[cmd.length - 2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime.parse(cmd[cmd.length - 1].split("-")[0], DateTimeFormatter.ofPattern("HHmm"));
                    LocalTime.parse(cmd[cmd.length - 1].split("-")[1], DateTimeFormatter.ofPattern("HHmm"));
                    String[] splitBySlash = command.split("/");
                    return new String[]{cmd[0], splitBySlash[0].replaceFirst(cmd[0] + " ", ""),
                            cmd[cmd.length - 2], cmd[cmd.length - 1].split("-")[0],
                            cmd[cmd.length - 1].split("-")[1]};
                } catch (DateTimeParseException e) {
                    throw new InvalidDateAndTimeException();
                }
            }
        default:
            throw new NotTaskException();
        }
    }
}
