package duke.commands;

import duke.exceptions.DoneException;
import duke.exceptions.DukeException;
import duke.exceptions.HelpException;
import duke.exceptions.TaskAlreadyDoneException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Completes a task on the taskList;
 */
public class HelpCommand extends Command{

    private final static String BYE_LINE = "bye - Saves the Task List into a txt file and terminates the program\n" +
            "Enter \"bye\"\n";

    private final static String DEADLINE_LINE = "deadline - Adds a deadline into the Task List\n" +
            "Enter \"deadline\" followed by a spacing before entering the task\n" +
            "followed by \"/by\" and another spacing before the date in the following format : \n" +
            "YYYY-MM-DD HHMM OR YYYY-MM-DD \n" +
            "e.g deadline Math Assignment /by 2020-09-08 2359 \n";

    private final static String DELETE_LINE = "delete - Deletes the task at the specified position\n" +
            "Enter \"delete\" followed by the index of the Task in the Task List\n" +
            "e.g delete 1\n";

    private final static String DONE_LINE = "done - Completes the task at the specified position\n" +
            "Enter \"done\" followed by the index of the Task in the Task List\n" +
            "e.g done 1 \n";


    private final static String EVENT_LINE = "event - Adds an event into the Task List\n" +
            "Enter \"event\" followed by a spacing before entering the task\n" +
            "followed by \"/at\" and another spacing before the date in the following format : \n" +
            "YYYY-MM-DD HHMM OR YYYY-MM-DD \n" +
            "e.g event John's Birthday /at 2020-09-08 1800 \n";


    private final static String FIND_LINE = "find - Finds tasks in the Task List that contains the keyword\n" +
            "Enter \"find\" followed by a spacing before entering the keyword\n" +
            "e.g find Birthday \n";

    private final static String HELP_LINE = "help - Returns a list of commands and their uses\n" +
            "Enter \"help\" returns the full list of commands\n" +
            "Enter \"help\" followed by the command returns the specific instructions for the command\n" +
            "e.g help OR help todo \n";


    private final static String LIST_LINE = "list - Returns the list of tasks in the Task List\n" +
            "Enter \"list\"\n";

    private final static String TODO_LINE = "todo - Adds a TODO into the Task List\n" +
            "Enter \"todo\" followed by a spacing before entering the task\n" +
            "e.g todo read a book \n";

    private final static String HELP_MESSAGE = "Enter \"help\" returns the full list of commands\n" +
            "Enter \"help\" followed by the command returns the specific instructions for the command\n" +
            "e.g help OR help todo \n";




    public HelpCommand(String description){

        super(description);
    }

    /**
     * Generates a list of all Commands
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return String of the completed Task
     * @throws DukeException
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);

        //if user only enters "help"
        if (inputList.length == 1){
            return HELP_MESSAGE +
                    "-bye\n-deadline\n-delete\n-done\n-event\n-find\n-help\n" +
                    "-list\n-todo\n";
        }

        //if user enter help with another command
        if (Parser.isBye(inputList[1])){
            return BYE_LINE;
        }
        if (Parser.isDeadline(inputList[1])){
            return DEADLINE_LINE;
        }

        if (Parser.isDelete(inputList[1])){
            return DELETE_LINE;
        }
        if (Parser.isComplete(inputList[1])){
            return DONE_LINE;
        }
        if (Parser.isFind(inputList[1])){
            return FIND_LINE;
        }
        if (Parser.isHelp(inputList[1])){
            return HELP_LINE;
        }
        if (Parser.isList(inputList[1])){
            return LIST_LINE;
        }
        if (Parser.isEvent(inputList[1])){
            return EVENT_LINE;
        }
        if (Parser.isToDo(inputList[1])){
            return TODO_LINE;
        }
        else {
            return new HelpException().getMessage();
        }

    }

    @Override
    public boolean isComplete(){
        return false;
    }
}
