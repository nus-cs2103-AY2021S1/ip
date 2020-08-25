package duke;

import duke.command.*;

/**
 * Parser class handles any incoming user input and generates the appropriate command
 */
public class Parser {
    /**
     * Genereating a Command object based on user input
     * @param command User input as String
     * @param list Current list of tasks
     * @param storage Access to duke.ser
     * @return The appropriate Command object
     */
    public static Command parse(String command, TaskList list, Storage storage) {
        String horizontalLine = "____________________________________\n";
        Command c = new Command();
        try{
            if (command.equals("bye")) {
                c = new ByeCommand(command, list, storage);
            } else if (command.equals("list")) {
                c = new ListCommand(command, list, storage);
            } else if (command.startsWith("delete")) {
                if (command.equals("delete")) {
                    throw new ResponseException(horizontalLine + "☹ AIYO!!! Please state which task to delete la... \n"
                            + horizontalLine);
                } else {
                    c = new DeleteCommand(command, list, storage);
                }
            } else if (command.startsWith("done")) {
                if (command.equals("done")) {
                    throw new ResponseException(horizontalLine + "☹ AIYO!!! Please state which task is done la... \n"
                            + horizontalLine);
                } else {
                    c = new DoneCommand(command, list, storage);
                }
            } else if (command.startsWith("todo")) {
                if (command.equals("todo")) {
                    throw new ResponseException(horizontalLine
                            + "☹ AIYOYO!!! The description of a todo cannot be empty la... \n" + horizontalLine);
                } else {
                    c = new TodoCommand(command, list, storage);
                }
            } else if (command.startsWith("deadline")) {
                if (command.equals("deadline")) {
                    throw new ResponseException(horizontalLine
                            + "☹ AIYO!!! The description of a deadline cannot be empty la... \n" + horizontalLine);
                } else {
                    c = new DeadlineCommand(command, list, storage);
                }
            } else if (command.startsWith("event")) {
                if (command.equals("event")) {
                    throw new ResponseException(horizontalLine
                            + "☹ AIYOYO!!! The description of a event cannot be empty la... \n" + horizontalLine);
                } else {
                    c = new EventCommand(command, list, storage);
                }
            } else if (command.startsWith("find")) {
                if (command.equals("find")) {
                    throw new ResponseException(horizontalLine
                            + "☹ AIYOYO!!! What do you want me to find sia? \n" + horizontalLine);
                } else {
                    c = new FindCommand(command, list, storage);
                }
            } else {
                throw new ResponseException(horizontalLine + "☹ AIYO!!! What do you mean sia? :-( \n"
                        + horizontalLine);
            }
        } catch (ResponseException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}
