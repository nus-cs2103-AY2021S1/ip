package parser;

import commands.Commands;
import exceptions.DukeException;
import executables.DeadlineCommand;
import executables.DeleteCommand;
import executables.DoneCommand;
import executables.EventCommand;
import executables.ExitCommand;
import executables.FindCommand;
import executables.ListCommand;
import executables.RescheduleCommand;
import executables.TodoCommand;
import storage.TaskList;
import ui.UI;

/**
 * Parser.Parser class handles userinput from Ui.UI class and parses throught it to create the Commands.Task List
 */
public class Parser {


    /**
     * Takes all the necessary arguments to create the list of tasks
     *
     * @param taskList main list where all tasks are kept on
     * @param ui       scanner that takes user inputs
     */
    public static String parseCode(TaskList taskList, UI ui, String userInput) {

        String echo = userInput;
        try {
            String split = echo;
            String[] arr = split.split(" ", 2);
            String keyword = arr[0];
            Commands command = Commands.findCommand(keyword);
            switch (command) {
            case EXIT:
                return new ExitCommand().execute(taskList, ui);
            case LIST:
                return new ListCommand().execute(taskList, ui);
            case DONE:
                return new DoneCommand(arr).execute(taskList, ui);
            case DEADLINE:
                return new DeadlineCommand(arr).execute(taskList, ui);
            case TODO:
                return new TodoCommand(arr).execute(taskList, ui);
            case EVENT:
                return new EventCommand(arr).execute(taskList, ui);
            case DELETE:
                return new DeleteCommand(arr).execute(taskList, ui);
            case FIND:
                return new FindCommand(arr).execute(taskList, ui);
            case RESCHEDULE:
                return new RescheduleCommand(arr).execute(taskList, ui);
            default: return "Unable to parse";
            }
        } catch (DukeException e) {
            System.out.println(e);
            return e.toString();
        }
    }
}
