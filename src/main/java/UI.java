import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The user interface class that communicates with the user and the system to get
 * what the user wants.
 */
public class UI {

    private Storage storage;
    private Parser parser;
    private String updateType;
    private int updateIndex;

    private UI(Storage storage) {
        this.storage = storage;
        this.parser = new Parser();
        this.updateType = "Not updating";
        this.updateIndex = -1;
    }

    /**
     * Initialises the UI with the storage, so that UI can now access the storage information.
     * @param storage The storage that the UI is going to have access to.
     * @return UI The UI that is now ready to communicate with the storage.
     */
    public static UI getAccessTo(Storage storage) {
        return new UI(storage);
    }

    /**
     * Reads a starting message at the start of UI.
     */
    public String showStartMessage() {
        return "Hi! I'm Duke" + "\n" + this.readSavedTasks() + "\n" + "What can I do for you?";
    }

    /**
     * Reads the list of saved tasks in the storage.
     */
    private String readSavedTasks() {
        int size = this.storage.getSizeofTasks();

        if (size == 0) {
            return "You currently have no tasks!";
        } else {
            String tasks = "Here are your current tasks:";

            for (int i = 0; i < size; i++) {
                tasks += "\n" + (i + 1) + ". " + this.storage.getTaskFromList(i);
            }
            return tasks;
        }
    }

    private String getListOfMatches(String ... keywords) {
        int size = this.storage.getSizeofTasks();
        int increment = 1;

        String listOfMatches = " Here are the matching tasks in your list:";

        for (int i = 0; i < keywords.length; i++) {
            String keyword = keywords[i];
            for (int j = 0; j < size; j++) {
                if (this.storage.getTaskFromList(j).toString().contains(keyword)) {
                    listOfMatches += "\n" + "  " + increment + ". " + this.storage.getTaskFromList(j).toString();
                    increment++;
                }
            }
        }

        //If increment is 1 means that there are no matching tasks
        if (increment == 1) {
            return "There seems to be no matches...";
        }

        return listOfMatches;
    }

    /**
     * Catches the exception and reads the corresponding message of the exception.
     */
    public String showError(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Processes the input of the user.
     * @param input The user input.
     * @return String The response of the Duke bot.
     * @throws IOException If there is an error with the user input.
     */
    public String processInput(String input) throws IOException {
        try {
            if (this.isUpdating()) {
                return this.processUpdate(input);
            }

            int parseResult = this.parser.parse(input, this.storage.getSizeofTasks());
            assert(parseResult > 0 && parseResult < 7);

            if (isListCommand(parseResult)) {
                return this.readSavedTasks();
            }
            if (isDoneCommand(parseResult)) {
                return processDoneCommand(input);
            }
            if (isDeleteCommand(parseResult)) {
                return processDeleteCommand(input);
            }
            if (isFindCommand(parseResult)) {
                return processFindCommand(input);
            }
            if (isUpdateCommand(parseResult)) {
                return verifyUpdateCommand(input);
            }
            if (parser.isTerminateCommand(input)) {
                return processTerminateCommand();
            }
            //If reached here, means input must be a task command
            return processTaskCommand(input);
        } catch (InvalidCommandException e) {
            return e + "\n" + "Please enter a valid command";
        } catch (InvalidInputException e) {
            return e + "\n" + "Please enter a valid input";
        }
    }

    private String processUpdate(String input) {

        if (this.parser.isAbortUpdate(input)) {
            this.updateType = "Not updating";
            this.updateIndex = -1;

            return "Update aborted.";
        }

        Task taskToBeChange = this.storage.getTaskFromList(this.updateIndex);

        try {
            int updateStatus = this.parser.checkInputMatchesUpdate(input, this.updateType);
            this.proceedUpdate(updateStatus, input);
        } catch (InvalidCommandException e) {
            return e.toString() + "\nType \"abort\" to cancel update.";
        } catch (InvalidInputException e) {
            return e.toString() + "\nType \"abort\" to cancel update.";
        }

        Task taskChangedTo = this.storage.getTaskFromList(this.updateIndex);

        this.updateType = "Not updating";
        this.updateIndex = -1;

        return "Updated:" + "\n" + taskToBeChange + "\n" + "    ↓" + "\n" + taskChangedTo;
    }

    private String processDoneCommand(String input) {
        int index = this.parser.getIndex(input);
        this.storage.markDone(index);

        return "Nice! I've marked this task as done:"
                + "\n" + "  " + this.storage.getTaskFromList(index).toString();
    }

    private String processDeleteCommand(String input) {
        int index = this.parser.getIndex(input);
        Task toBeDeleted = this.storage.getTaskFromList(index);
        this.storage.deleteTask(index);

        return "Noted. I've removed this task:" + "\n" + "  " + toBeDeleted
                + "\n" + "Now you have " + (this.storage.getSizeofTasks()) + " tasks in the list.";
    }

    private String processFindCommand(String input) {
        String[] keywords = this.parser.getKeyword(input);
        return getListOfMatches(keywords);
    }

    private String verifyUpdateCommand(String input) throws InvalidCommandException {
        //Get update type: time, task, desc, date
        String updateType = this.parser.getUpdateType(input);
        int index = this.parser.getIndex(input);
        String taskType = this.storage.getTaskType(index);

        //Check if the task at the index can be updated this way
        this.parser.verifyTaskCanUpdate(updateType, taskType);

        this.updateType = updateType;
        this.updateIndex = index;

        return "Ok, please type in the new " + updateType + "," + "\nor type \"abort\" to cancel update.";
    }

    private String processTerminateCommand() throws IOException {
        this.storage.save();

        return "See you again!";
    }

    private String processTaskCommand(String input) throws InvalidInputException, InvalidCommandException {
        Task newTask = this.parser.getTask(input);
        this.storage.addTask(newTask);

        return "Got it. I've added this task:"
                + "\n" + "  " + this.storage.getTaskFromList(this.storage.getSizeofTasks() - 1)
                + "\n" + "Now you have " + this.storage.getSizeofTasks() + " tasks in the list.";
    }

    private void proceedUpdate(int updateStatus, String input) throws InvalidInputException, InvalidCommandException {
        String trimmedInput = input.trim();

        if (updateStatus == 1) {
            //replace date
            LocalDate newDate = LocalDate.parse(trimmedInput);
            this.storage.changeDate(this.updateIndex, newDate);
        }

        if (updateStatus == 2) {
            //replace time
            LocalTime newTime = LocalTime.parse(trimmedInput);
            this.storage.changeTime(this.updateIndex, newTime);
        }

        if (updateStatus == 3) {
            //replace task
            Task newTask = this.parser.getTask(trimmedInput);

            this.storage.replaceTask(this.updateIndex, newTask);
        }

        if (updateStatus == 4) {
            this.storage.changeDesc(this.updateIndex, trimmedInput);
        }
    }

    private boolean isUpdating() {
        return !this.updateType.equals("Not updating");
    }

    private boolean isListCommand(int num) {
        return num == 1;
    }

    private boolean isDoneCommand(int num) {
        return num == 2;
    }

    private boolean isDeleteCommand(int num) {
        return num == 3;
    }

    private boolean isFindCommand(int num) {
        return num == 4;
    }

    private boolean isUpdateCommand(int num) {
        return num == 5;
    }

}
