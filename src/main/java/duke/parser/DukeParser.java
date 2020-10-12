package duke.parser;

import java.util.ArrayList;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DukeParser {
    private TaskList lines;
    private boolean carryOn = true;

    /**
     * The constructor of the Parser object. It takes in an ArrayList{@link ArrayList} which represents
     * the current set of tasks. The arrayList is then converted into a TaskList object for easier manipulation of the
     * items.
     *
     * @param lines the list of tasks.
     */
    public DukeParser(ArrayList<String> lines) {
        this.lines = new TaskList(lines);
    }

    /**
     * Checks if duke.Duke should carry on.
     *
     * @return True if duke.Duke is not terminated with a Bye command, false otherwise.
     */
    public boolean shouldContinueDuke() {
        return carryOn;
    }

    /**
     * Simply returns the current set of lines. This should be called when duke.Duke is terminated.
     *
     * @return The finalized set of lines
     */
    public ArrayList<String> finalizedLines() {
        return lines.getList();
    }

    /**
     * Checks if a string contains the # character
     *
     * @param input the string to be checked
     * @return true if the string contains "#", false otherwise.
     */
    public boolean noHashTag(String input) {
        if (input.contains("#")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Parses the input given by the users. The method checks if the user's input is a done, list, delete, bye or add
     * command and executes the appropriate response. If the input is invalid, a DukeException is thrown.
     *
     * @param inputString The user input to be parsed
     * @return The string representing the appropriate response.
     * @throws DukeException The exception explaining why the input was invalid
     */
    public Command parse(String inputString) throws DukeException {
        if (!noHashTag(inputString)) { // Checks if the input contains "#" as it will cause problems.
            throw new DukeException("Your input contains the # character, don't do that :(");
        }
        // Checks if the input string is a done command
        if (inputString.indexOf("done ") == 0) {
            DoneParser doneParser = new DoneParser(inputString, lines);
            return new DoneCommand(lines, doneParser);
        } else if (inputString.equals("list")) {
            return new ListCommand(lines);
        } else if (inputString.equals("bye")) {
            carryOn = false;
            return new ByeCommand();
        } else if (inputString.indexOf("delete ") == 0) { // If the input string is a delete command
            DeleteParser deleteParser = new DeleteParser(inputString, lines);
            return new DeleteCommand(lines, deleteParser);
        } else if (inputString.indexOf("find ") == 0) { // If the user input is a find command
            FindParser findParser = new FindParser(inputString);
            return new FindCommand(lines, findParser);
        } else if (inputString.indexOf("tag ") == 0) { // If the user input is a tag command
            TagParser tagParser = new TagParser(inputString, lines);
            return new TagCommand(lines, tagParser);
        } else if (inputString.indexOf("untag ") == 0) { // If the input is an untag command
            UntagParser untagParser = new UntagParser(inputString, lines);
            return new UntagCommand(lines, untagParser);
        } else if (inputString.indexOf("todo ") == 0) { // If the input is a TodoTask command
            TodoParser todoParser = new TodoParser(inputString);
            return new TodoCommand(lines, todoParser);
        } else if (inputString.indexOf("deadline ") == 0) { // If the input is a DeadlineTask command
            DeadlineParser deadlineParser = new DeadlineParser(inputString);
            return new DeadlineCommand(lines, deadlineParser);
        } else if (inputString.indexOf("event ") == 0) { // If the input is a event command

        }
            Task task = null;
            if (inputString.indexOf("todo ") == 0) {
                boolean emptyDescription = inputString.length() == 5;
                if (emptyDescription) { //Checks if there is an input for the task command.
                    throw new DukeException("Hey! Your Todo is empty >:(");
                } else if (inputString.indexOf(" ") != 4) {
                    throw new DukeException("What are you even saying?!");
                } else {
                    task = new Todo(inputString.substring(5));
                }
            } else if (inputString.indexOf("deadline ") == 0) {
                boolean containsBy = inputString.contains(" /by ");
                boolean missingDate = inputString.substring(inputString.indexOf(" /by ")).length() == 5;
                boolean missingTaskDescription = inputString.contains("deadline /by ");
                int byIndex = inputString.indexOf(" /by ");
                if (missingTaskDescription) {
                    throw new DukeException("You aren't setting anything for your deadline?!");
                } else if (!containsBy || missingDate) {
                    throw new DukeException("Oi, when is this deadline due??");
                } else {
                    if (Deadline.checkDateFormat(inputString.substring(byIndex + 5))) {
                        task = new Deadline(inputString.substring(9, byIndex),
                                inputString.substring(byIndex + 5));
                    }
                }
            } else if (inputString.indexOf("event ") == 0) {
                boolean containsAt = inputString.contains(" /at ");
                boolean missingLocation = inputString.substring(inputString.indexOf(" /at ")).length() == 5;
                boolean missingDescription = inputString.contains("event /at ");
                int atIndex = inputString.indexOf(" /at ");
                if (!containsAt || missingLocation) {
                    throw new DukeException("Oi, when is this event on??");
                } else if (missingDescription) {
                    throw new DukeException("You aren't setting anything as your event?!");
                } else {
                    task = new Event(inputString.substring(6, atIndex), inputString.substring(atIndex + 4));
                }
            } else {
                throw new DukeException("What are you even saying?!");
            }
            if (task != null) { //There shouldn't be a case where task would be null. This is just in case.
                String newTask = task.toString();
                lines.addTask(newTask);
                return Ui.addedTask(task, lines.getNumberOfItems());
            } else {
                return "";
            }
        }
    }
}
