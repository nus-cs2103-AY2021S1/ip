package duke.parser;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.ToDoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.common.CustomException;

public class Parser {

    public static Command parse(String command) throws CustomException {
        String[] parts = command.split(" ", 2);
//        System.out.println(parts[0] + "|" + parts[1]);
        switch(parts[0].toLowerCase()) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(Integer.parseInt(parts[1].trim()));
            case "delete":
                return new DeleteCommand(Integer.parseInt(parts[1].trim()));
            case "todo":
                return new ToDoCommand(parts[1].trim());
            case "deadline":
                try {
                    String[] deadline = parts[1].split("/by");
                    return new DeadlineCommand(deadline[0].trim(), deadline[1].trim());
                } catch (Exception e) {
                    throw new CustomException("Deadline formatting is wrong! Please include /by.");
                }
            case "event":
                try {
                    String[] event = parts[1].split("/at");
                    return new EventCommand(event[0].trim(), event[1].trim());
                } catch (Exception e) {
                    throw new CustomException("Event formatting is wrong! Please include /at.");
                }

            default:
                throw new CustomException("Please try again!");
        }
    }
}
