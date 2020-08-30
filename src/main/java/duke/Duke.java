package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandParser;
import duke.task.TaskList;
import duke.exception.DukeException;

public class Duke {
    public static void main(String[] args) {
        try {
            Storage storage = new Storage();
            TaskList taskList = TaskList.initialiseTaskList(storage);
            CommandParser parser = new CommandParser();

            Scanner sc = new Scanner(System.in);
            Ui.welcomeMessage();
            boolean isExit = false;

            while (!isExit) {
                try {
                    String userCommand = sc.nextLine();
                    Command parsedCommand = parser.parseCommand(userCommand);
                    parsedCommand.execute(taskList, storage);
                    isExit = parsedCommand.isExit();
                } catch (DukeException e) {
                    Ui.errorMessage(e.getUiMessage());
                }
            }
        } catch (DukeException e) {
            Ui.errorMessage(e.getUiMessage());
        }
    }
}
