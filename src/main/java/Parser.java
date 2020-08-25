import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser(){
    }

    public static Command parse(String fullCommand) throws EmptyTaskException, EmptyTimeException, CommandNotFoundException,
            WrongDateFormatException {
        String[] parseArray = fullCommand.trim().split(" ", 2);
        String type = parseArray[0];
        Command command = null;
        switch(type) {
            case "bye":
                command =  new ExitCommand();
                break;
            case "list":
                if (parseArray.length == 1) {
                    command = new ListCommand(null);
                } else {
                    String time = parseArray[1];
                    LocalDate date = LocalDate.parse(time);
                    command = new ListCommand(date);
                }
                break;
            case "done":
                if (parseArray.length == 1) {
                    throw new EmptyTaskException();
                } else {
                    String time = parseArray[1];
                    int index = Integer.parseInt(time) - 1;
                    command = new DoneCommand(index);
                }
                break;
            case "delete":
                if (parseArray.length == 1) {
                    throw new EmptyTaskException();
                } else {
                    String rest = parseArray[1];
                    int index = Integer.parseInt(rest) - 1;
                    command = new DeleteCommand(index);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (parseArray.length == 1) {
                    throw new EmptyTaskException();
                } else {
                    String rest = parseArray[1];
                    switch(type) {
                        case "todo":
                            command = new AddCommand(type, rest, null);
                        break;
                        case "deadline":
                            if (rest.split("/").length == 1) {
                                throw new EmptyTimeException("Please specify deadline using \"/by\". (´∀`)");
                            } else {
                                String description = rest.split(" /")[0];
                                try {
                                    String time = rest.split(" /")[1].split(" ", 2)[1];
                                    LocalDate date = LocalDate.parse(time);
                                    command = new AddCommand(type, description, date);
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new EmptyTimeException("Please don't leave the deadline blank~ (´∀`)");
                                } catch (DateTimeParseException ex) {
                                    throw new WrongDateFormatException();
                                }
                            }
                            break;
                        case "event":
                            if (rest.split("/").length == 1) {
                                throw new EmptyTimeException("Please specify event time using \"/at\". (´∀`)");
                            } else {
                                String description = rest.split(" /")[0];
                                try {
                                    String time = rest.split(" /")[1].split(" ", 2)[1];
                                    LocalDate date = LocalDate.parse(time);
                                    command =  new AddCommand(type, description, date);
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new EmptyTimeException("Please don't leave the event time blank~ (´∀`)");
                                } catch (DateTimeParseException ex) {
                                    throw new WrongDateFormatException();
                                }
                            }
                    }
                    break;
                }
            default:
            throw new CommandNotFoundException();
        }
        return command;
    }


}
