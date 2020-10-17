package duke.parser;

import java.util.ArrayList;

import duke.DukeException;
import duke.storage.TaskList;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;
import duke.command.UntagCommand;



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
     *
     * @param inputString
     * @return
     * @throws DukeException
     */
    public Command parse(String inputString) throws DukeException {
        if (!noHashTag(inputString)) { // Checks if the input contains "#" as it will cause problems.
            throw new DukeException("Your input contains the # character, don't do that :(");
        }
        if (inputString.indexOf("done ") == 0) { // Checks if the input string is a done command
            DoneParser doneParser = new DoneParser(inputString, lines);
            return new DoneCommand(lines, doneParser);
        } else if (inputString.equals("list")) { // Checks if the input string is a list command
            return new ListCommand(lines);
        } else if (inputString.equals("bye")) { // Checks if the input string is a bye command
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
            EventParser eventParser = new EventParser(inputString);
            return new EventCommand(lines, eventParser);
        } else {
            throw new DukeException("I have no idea what you are saying!");
        }
    }
}
