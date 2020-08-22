package main.java;

import Command.AddDeadlineCommand;
import Command.PrintListCommand;
import Command.DeleteCommand;
import Command.DoneCommand;
import Command.AddEventCommand;
import Command.AddTodoCommand;
import Command.ExitCommand;
import Command.Command;
import Command.UnknownCommand;

/**
 * A class to deals with making sense of the input command.
 */
public class Parser {

    /**
     * A function to parse the input from the user.
     * @param fullCommand The input of the user which is saved in String.
     * @return Command which is grouped into different command.
     */
    public static Command parse (String fullCommand) {
        String[] parsed = fullCommand.split(" ",2);
        if (parsed[0].equals("list")) {
            return new PrintListCommand(parsed);
        } else if (parsed[0].equals("done")) {
            return new DoneCommand(parsed);
        } else if (parsed[0].equals("delete")) {
            return new DeleteCommand(parsed);
        } else if (parsed[0].equals("event")) {
            return new AddEventCommand(parsed);
        } else if (parsed[0].equals("deadline")) {
            return new AddDeadlineCommand(parsed);
        } else if (parsed[0].equals("todo")) {
            return new AddTodoCommand(parsed);
        } else if (parsed[0].equals("bye")) {
            return new ExitCommand(parsed);
        } else {
            return new UnknownCommand(parsed);
        }

    }


}
