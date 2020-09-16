package duke;

import java.util.ArrayList;

public class Parser {
    private TaskList lines;
    private boolean carryOn = true;

    /**
     * The constructor of the Parser object. It takes in an ArrayList{@link ArrayList} which represents
     * the current set of tasks. The arrayList is then converted into a TaskList object for easier manipulation of the
     * items.
     *
     * @param lines the list of tasks.
     */
    public Parser(ArrayList<String> lines) {
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
    public String parse(String inputString) throws DukeException {
        if (!noHashTag(inputString)) { //checks if the input contains "#" as it will cause problems.
            throw new DukeException("Your input contains the # character, don't do that :(");
        }
        //Checks if the input string is a done command
        if (inputString.indexOf("done ") == 0) {
            try {
                // item number refers to the item index in the user input
                int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                boolean invalidIndex = lines.getNumberOfItems() < itemNumber || itemNumber <= 0;
                if (inputString.length() <= 5) { //This condition is to check if the done command is empty
                    throw new DukeException("You did not specify which task you are done with!");
                } else if (invalidIndex) { //This condition is to check if the index is within lines.
                    throw new DukeException("Hey, no such task exists!");
                } else {
                    String doneTask = lines.getTask(itemNumber - 1);
                    String message = Ui.done(doneTask);
                    lines.updateTask(Ui.updateDoneTask(doneTask), itemNumber - 1);
                    return message;
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid input for done command!");
            }
        } else if (inputString.equals("list")) {
            return Ui.listTasks(lines.getList());
        } else if (inputString.equals("bye")) {
            carryOn = false;
            return Ui.bye();
        } else if (inputString.indexOf("delete ") == 0) { //If the input string is a delete command
            try {
                // item number refers to the item index in the user input
                int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                boolean invalidIndex = lines.getNumberOfItems() < itemNumber || itemNumber <= 0;
                if (inputString.length() <= 7) {
                    throw new DukeException("You did not specify which task you are deleting!");
                } else if (invalidIndex) { //This condition is to check if the index is within lines
                    throw new DukeException("Hey, no such task exists!");
                } else { //remove task and return a string representing the delete message
                    String task = lines.getTask(itemNumber - 1);
                    lines.removeTask(itemNumber - 1);
                    return Ui.deletedTask(task, lines.getNumberOfItems());
                }
            } catch (NumberFormatException e) { //thrown by parseInt
                throw new DukeException("Invalid input for delete command!");
            }
        } else if (inputString.indexOf("find ") == 0) { //If the user input is a find command
            if (inputString.length() == 5) { //Checks if the input string does not contain a keyword
                throw new DukeException("What are you trying to find?");
            } else {
                String keyword = inputString.substring(5);
                ArrayList<String> matchingTasks = lines.find(keyword);
                return Ui.listMatchingTasks(matchingTasks);
            }
        } else if (inputString.indexOf("tag ") == 0) { //If the user input is a tag command
            boolean missingTagDetails = inputString.length() <= 6
                    || inputString.length() == 7 && inputString.lastIndexOf(" ") == 6;
            if (missingTagDetails) {
                throw new DukeException("Hey, your tag command is missing details! Check it again!");
            } else {
                String taskIndex = inputString.substring(4, 6);
                boolean singleDigit = taskIndex.contains(" ");
                if (singleDigit) { // if the tag index is a single digit
                    try {
                        int index = Integer.parseInt(taskIndex.substring(0, 1));
                        if (index < 0 || index > lines.getList().size()) {
                            throw new DukeException("No such task exists!");
                        }
                        String description = inputString.substring(6);
                        boolean tagged = lines.tagItem(index - 1, description);
                        return Ui.taggedTask(lines.getTask(index - 1), tagged);
                    } catch (NumberFormatException e) {
                        throw new DukeException("That is not a valid index input!");
                    }
                } else {
                    try {
                        int index = Integer.parseInt(taskIndex.substring(0, 2));
                        if (index < 0 || index > lines.getList().size()) {
                            throw new DukeException("No such task exists!");
                        }
                        String description = inputString.substring(7);
                        boolean tagged = lines.tagItem(index - 1, description);
                        return Ui.taggedTask(lines.getTask(index - 1), tagged);
                    } catch (NumberFormatException e) {
                        throw new DukeException("That is not a valid index input!");
                    }
                }
            }
        } else if (inputString.indexOf("untag ") == 0) { //if the input is an untag command
            boolean missingIndex = inputString.length() <= 6;
            if (missingIndex) {
                throw new DukeException("Which task are you trying to untag?");
            } else {
                try {
                    int index = Integer.parseInt(inputString.substring(6));
                    boolean tagRemoved = false;
                    if (index < 0 || index > lines.getList().size()) {
                        throw new DukeException("No such task exists!");
                    }
                    if (lines.removeTag(index - 1)) {
                        tagRemoved = true;
                    }
                    return Ui.untaggedTask(lines.getTask(index - 1), tagRemoved);
                } catch (NumberFormatException e) {
                    throw new DukeException("What kind of task index is that?!");
                }
            }
        } else { //Probably a task command, checks which one it is, if any.
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
