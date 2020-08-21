import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {

    protected static List<String> inputFormat = Arrays.asList("dd/MM/yyyy HHmm", "yyyy-mm-dd Haaa");
    protected static SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy h:mma");

    public static String DateParser(String str){
        Date date = null;
        for (String input : inputFormat) {
            try {
                date = new SimpleDateFormat(input).parse(str);
            } catch (ParseException ignored) { }
        }
        if (date == null) {
            return str;
        } else {
            return outputFormat.format(date);
        }
    }

    public static Command parse(String input) throws CommandException, EmptyDescriptionException {
        switch (input) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
        }
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length == 1) {
            throw new EmptyDescriptionException(("The description of " + inputArray[0] + " cannot be empty. Please re-enter"));
        }
        switch (inputArray[0]) {
            case "done":
                return new DoneCommand(Integer.parseInt(inputArray[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(inputArray[1]));
            case "todo": {
                return new TodoCommand(inputArray[1]);
            }
            case "deadline": {
                String str = inputArray[1].split(" /", 2)[0];
                String date = inputArray[1].split("/", 2)[1].split(" ", 2)[1];
                return new DeadlineCommand(str,DateParser(date));
            }
            case "event": {
                String str = inputArray[1].split(" /", 2)[0];
                String date = inputArray[1].split("/", 2)[1].split(" ", 2)[1];
                return new EventCommand(str, DateParser(date));
            }
            default:
                throw new CommandException("Im sorry, I do not understand what you mean. Please re-enter:");
        }
    }


}
