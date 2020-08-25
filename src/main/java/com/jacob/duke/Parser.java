package main.java.com.jacob.duke;

import main.java.com.jacob.duke.command.*;

public class Parser {
    /**
     * Parses the full command and decides which command object to create and return for execution
     * @param fullCommand the full user console input command
     * @return Command for execution
     * @throws DukeException thrown when invalid message is given
     */
    public Command parse(String fullCommand) throws DukeException {
        String[] splitStrings = fullCommand.split(" ");
        String firstInput = splitStrings[0];
        Command c;
        switch (firstInput) {
        case "todo":
            //prints the task description and updates the relevant task into taskList
            c = new TodoCommand(fullCommand);
            break;
        case "deadline":
            //create the deadline and update the relevant deadline into taskList
            c = new DeadlineCommand(fullCommand);
            break;
        case "event":
            //create the event and update the relevant event into taskList
            c = new EventCommand(fullCommand);
            break;
        case "delete":
            //delete the task from taskList
            c = new DeleteCommand(fullCommand);
            break;
        case "list":
            //iterate through the taskList and print all active members
            c = new PrintListCommand();
            break;
        case "list-due":
            //iterate through the taskList and print all active members
            c = new PrintFilteredListDateTimeCommand(fullCommand);
            break;
        case "find":
            //iterate through the taskList and print all active members
            c = new FindCommand(fullCommand);
            break;
        case "done":
            //doneEventHandler();
            c = new DoneCommand(fullCommand);
            break;
        case "bye":
            c = new ByeCommand();
            break;
        default:
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

}
