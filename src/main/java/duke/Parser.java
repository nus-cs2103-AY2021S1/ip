package duke;

import duke.command.*;

public class Parser {
    public static Command parse(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        Command c = new Command();
        try{
            if (command.equals("bye")) {
                c = new ByeCommand(command, list, storage);
            } else if (command.equals("list")) {
                c = new ListCommand(command, list, storage);
            } else if (command.startsWith("delete")) {
                if (command.equals("delete")) {
                    throw new ResponseException(hor_line + "☹ AIYO!!! Please state which task to delete la... \n" +
                            hor_line);
                } else {
                    c = new DeleteCommand(command, list, storage);
                }
            } else if (command.startsWith("done")) {
                if (command.equals("done")) {
                    throw new ResponseException(hor_line + "☹ AIYO!!! Please state which task is done la... \n" +
                            hor_line);
                } else {
                    c = new DoneCommand(command, list, storage);
                }
            } else if (command.startsWith("todo")) {
                if (command.equals("todo")) {
                    throw new ResponseException(hor_line + "☹ AIYOYO!!! The description of a todo cannot be empty la... \n"
                            + hor_line);
                } else {
                    c = new TodoCommand(command, list, storage);
                }
            } else if (command.startsWith("deadline")) {
                if (command.equals("deadline")) {
                    throw new ResponseException(hor_line +
                            "☹ AIYO!!! The description of a deadline cannot be empty la... \n" + hor_line);
                } else {
                    c = new DeadlineCommand(command, list, storage);
                }
            } else if (command.startsWith("event")) {
                if (command.equals("event")) {
                    throw new ResponseException(hor_line + "☹ AIYOYO!!! The description of a event cannot be empty la... \n"
                            + hor_line);
                } else {
                    c = new EventCommand(command, list, storage);
                }
            } else {
                throw new ResponseException(hor_line + "☹ AIYO!!! What do you mean sia? :-( \n"
                        + hor_line);
            }
        } catch (ResponseException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}
