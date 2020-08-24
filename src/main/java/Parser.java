import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser { //extract info and pass it to deadlinecommand
    //write as multiple functions
    //1. parseDeadlineCommand (string -> deadlinecommand obj)
    //create functions for all commands (for consistency)
    public static Command decideCategory(String input) throws IllegalArgumentException {
        String category = getCategory(input);
        String description = getDescription(input);
        switch (category) {
            case "todo":
                parseTodoCommand(description);
                //return new TodoCommand(Category.TODO, description);
            case "deadline":
                parseDeadlineCommand(description);
                //return new DeadlineCommand(Category.DEADLINE, description);
            case "event":
                parseEventCommand(description);
                //return new EventCommand(Category.EVENT, description);
            case "done":
                parseDoneCommand(description);
                //return new DoneCommand(Category.DONE, description);
            case "delete":
                parseDeleteCommand(description);
                //return new DeleteCommand(Category.DELETE, description);
            case "list":
                parseListCommand(description);
                //return new ListCommand(Category.LIST, description);
            case "bye":
                parseByeCommand(description);
                //return new ByeCommand(Category.BYE, description);
            default:
                throw new IllegalArgumentException("-------------------------------------------\n" +
                        "☹ OOPS!!! Invalid input. Try again!\n"
                        + "-------------------------------------------");
        }
    }

    public static String getCategory(String input) throws IllegalArgumentException {
        String[] wordsArray = input.split(" ", 2);
        String categoryWord = wordsArray[0];
        return categoryWord;
    }

    public static String getDescription(String input) {
        String[] wordsArray = input.split(" ", 2);
        String description = wordsArray.length == 2 ? wordsArray[1] : null;
        return description;
    }

    public static TodoCommand parseTodoCommand(String description) {
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! The description of a todo cannot be empty. Try again!\n"
                    +"-------------------------------------------");
        } else {
            System.out.println("-------------------------------------------\n" +
                    "Got it. I've added this task:");
            return new TodoCommand(description);
        }
    }

    public static DeadlineCommand parseDeadlineCommand(String description) {
        String[] descriptionArray = description.split("/by");
        String deadlineName = descriptionArray[0];
        if(deadlineName == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! The description of a deadline cannot be empty. Try again!\n"
                    +"-------------------------------------------");
        } else if(descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated");
        } else if(descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated");
        } else {
            System.out.println("-------------------------------------------\n" +
                    "Got it. I've added this task:");

            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
            return new DeadlineCommand(deadlineName, deadlineDateTime);
        }
    }

    public static EventCommand parseEventCommand(String description) {
        String[] descriptionArray = description.split("/at");
        String eventName = descriptionArray[0];
        if(eventName == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! The description of an event cannot be empty. Try again!\n"
                    +"-------------------------------------------");
        } else if(descriptionArray.length == 1) { //no "/at" present
            throw new IllegalArgumentException("Invalid input, no deadline stated");
        } else if(descriptionArray.length > 2) {
            throw new IllegalArgumentException("Invalid input, multiple deadlines stated");
        } else {
            System.out.println("-------------------------------------------\n" +
                    "Got it. I've added this task:");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(" dd/MM/yyyy HH:mm");
            LocalDateTime eventDateTime = LocalDateTime.parse(descriptionArray[1], inputFormat);
            return new EventCommand(eventName, eventDateTime);
        }
    }

    public static DoneCommand parseDoneCommand(String description) {
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Not sure which task is to be indicated as done. Try again!\n"
                    +"-------------------------------------------");
        }
            return new DoneCommand(description);
        }

    public static DeleteCommand parseDeleteCommand(String description) {
        if(description == null) {
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Not sure which task is to be deleted. Try again!\n"
                    +"-------------------------------------------");
        }
        return new DeleteCommand(description);
    }

    public static ListCommand parseListCommand(String description) {
        if(description != null){
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Invalid input. Try again!\n"
                    +"-------------------------------------------");
        }
        return new ListCommand(null);
    }

    public static ByeCommand parseByeCommand(String description) {
        if(description != null){
            throw new IllegalArgumentException("-------------------------------------------\n" +
                    "☹ OOPS!!! Invalid input. Try again!\n"
                    +"-------------------------------------------");
        }
        return new ByeCommand(description);
    }






    }

