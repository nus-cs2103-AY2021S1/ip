import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private static Command parsing(String input) throws DukeException {
        Command command;
        AdditionalInfo info;

        String firstWord = input.split(" ")[0].trim();
//        System.out.println("firstWord: " + firstWord);
        if (input.equals("bye")) {
            command = new Command(Command.EXIT);
        } else if (input.equals("list")) {
            command = new Command(Command.LIST);
        } else if (firstWord.matches("done|delete")) {
            String[] splitted = input.split("\\s+");
            int taskIndex = Integer.parseInt(splitted[1]) - 1;

            info = new AdditionalInfo(taskIndex);
            command = new Command(firstWord.equals("done")
                    ? Command.DONE
                    : Command.DELETE,
                    info);
        } else if (firstWord.matches("todo|deadline|event")) {
            if (firstWord.equals("todo")) {
                String[] arr = input.split("todo ");
                if (arr.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    String task = arr[1].trim();
                    info = new AdditionalInfo(task);
//                    newTask = new ToDo(tsk);
                }
            } else {
                String[] arr = input.split(firstWord.equals("deadline") ? "deadline" : "event");
                if (arr.length <= 1) {
                    String exceptionMessage = String.format("The description of %s cannot be empty.",
                            firstWord.equals("deadline") ? "a deadline" : "an event");
                    throw new DukeException(exceptionMessage);
                } else {
                    if (firstWord.equals("deadline")) {
                        String[] parsing = input.split("/by ");
                        String task = parsing[0].split("deadline")[1].trim();
                        String deadline = parsing[1].trim();
                        String dateString = deadline.split(" ")[0];
                        String time = deadline.split(" ")[1];
                        LocalDate date = LocalDate.parse(dateString, Duke.BASIC_FORMATTER);
                        info = new AdditionalInfo(task, time, date);
                    } else {
                        String[] parsing = input.split("/at ");
                        String time = parsing[1].trim();
                        String task = parsing[0].split("event ")[1].trim();
                        info = new AdditionalInfo(task, time);
                    }
                }
            }

            command = new Command(firstWord.equals("todo")
                    ? Command.CREATE_TODO
                    : firstWord.equals("deadline")
                    ? Command.CREATE_DEADLINE
                    : Command.CREATE_EVENT,
                    info);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
        return command;
    }

    public static Command parse(String input) {
        try {
            return parsing(input);
        } catch (DukeException e) {
            System.out.println("in catch of Parse");
            Ui.printException(e);
            return new Command(Command.INVALID);
        }
    }
}
