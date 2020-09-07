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
    private String updateStatus;
    private int updateIndex;

    private UI(Storage storage) {
        this.storage = storage;
        this.parser = new Parser();
        this.updateStatus = "Not updating";
        this.updateIndex = -1;
    }

    /**
     * Initialises the UI with the storage.
     * @param storage The storage that the UI is going to have access to.
     * @return UI The UI that is now ready to communicate with the storage.
     */
    public static UI initialise(Storage storage) {
        return new UI(storage);
    }

    /**
     * Reads a starting message at the start of UI.
     */
    public String startUpMessage(){
        return "Hi! I'm Duke" + this.readSavedTasks() + "\n" + "What can I do for you?";
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
                tasks += "\n" + "  " + this.storage.getTaskFromList(i);
            }
            return tasks;
        }
    }

    private String getListOfMatches(String keyword) {
        int size = this.storage.getSizeofTasks();
        int increment = 1;

        String listOfMatches = " Here are the matching tasks in your list:";

        for (int i = 0; i < size; i++) {
            if (this.storage.getTaskFromList(i).toString().contains(keyword)) {
                listOfMatches += "\n" + "  " + increment + ". " + this.storage.getTaskFromList(i).toString();
                increment++;
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


    public String processInput(String input) throws IOException {
        if (!this.storage.isInitialised()) {
            return "Storage not initialised!";
        }

        try {

            if (this.isUpdating()) {
                int updateStatus = this.parser.processUpdate(input, this.updateStatus);

                Task taskToChange = this.storage.getTaskFromList(this.updateIndex);

                this.proceedUpdate(updateStatus, input);

                Task taskChangedTo = this.storage.getTaskFromList(this.updateIndex);
                this.updateStatus = "Not updating";
                this.updateIndex = -1;

                return "Updated " + taskToChange + "to: " + "\n" + taskChangedTo;
            }

            int parseResult = this.parser.parse(input, this.storage.getSizeofTasks());
            assert(parseResult > 0 && parseResult < 7);

            if (isListCommand(parseResult)) {
                return this.readSavedTasks();
            }
            if (isDoneCommand(parseResult)) {
                int index = this.parser.getIndex(input);
                this.storage.markDone(index);

                return "Nice! I've marked this task as done:" + "\n" + "  " + this.storage.getTaskFromList(index).toString();
            }
            if (isDeleteCommand(parseResult)) {
                int index = this.parser.getIndex(input);
                Task toBeDeleted = this.storage.getTaskFromList(index);
                this.storage.deleteTask(index);

                return "Noted. I've removed this task:" + "\n" + "  " + toBeDeleted
                        + "\n" + "Now you have " + (this.storage.getSizeofTasks()) + " tasks in the list.";
            }
            if (isFindCommand(parseResult)) {
                String keyword = this.parser.getKeyword(input);

                return getListOfMatches(keyword);
            }
            if (isUpdateCommand(parseResult)) {
                //Get update type: time, task, desc, date
                String updateType = this.parser.getUpdateType(input);
                int index = this.parser.getUpdateIndex(input) - 1;
                String taskType = this.storage.getTaskType(index);

                this.parser.verifyTaskCanUpdate(updateType, taskType);

                this.updateStatus = updateType;
                this.updateIndex = index;

                return "Ok, Please type in the new " + updateType;
            }
            if (parser.isTerminateCommand(input)) {
                this.storage.save();

                return "See you again!";
            }

            //If reached here, means input must be a task command
            Task newTask = this.parser.getTask(input);
            this.storage.addTask(newTask);

            return "Got it. I've added this task:" + "\n" + "  " +
                    this.storage.getTaskFromList(this.storage.getSizeofTasks() - 1) +
                    "\n" + "Now you have " + this.storage.getSizeofTasks() + " tasks in the list.";
        } catch (InvalidCommandException e) {
            return e + "\n" + "Please enter a valid command";
        } catch (InvalidInputException e) {
            return e + "\n" + "Please enter a valid input";
        }
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
        return !this.updateStatus.equals("Not updating");
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
