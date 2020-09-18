package ui;

import java.util.Scanner;

import command.Result;
import duke.TaskList;
import task.Task;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Displays result message to users.
     * @param result result from executing command
     */
    public void displayResult(Result result) {
        System.out.println(result.toString());
    }

    /**
     * Greets user
     *
     * @return The message to be displayed to the user.
     */
    public String greet() {
        return "Greetings User, Bit at your service!";
    }

    /**
     * Signals that the task has been marked done.
     *
     * @param task The task that was marked done.
     * @return The message to be displayed to the user.
     */
    public String markDoneMessage(Task task) {
        return "Making great progress User.\n" + task.toString() + "\n";
    }

    /**
     * Signals to the user that the task to be marked done is already marked as done
     *
     * @return The message to be displayed to the user.
     */
    public String taskAlreadyDoneMessage() {
        return "the task is already marked done User.\n";
    }

    /**
     * Signals to the user that the task list is empty.
     *
     * @return The message to be displayed to the user.
     */
    public String taskListEmptyMessage() {
        return "The task list is empty User";
    }

    /**
     * Signals to the user that the index provided is invalid.
     *
     * @return The message to be displayed to the user.
     */
    public String invalidIndexMessage() {
        return "Please enter a valid index User!\n";
    }

    /**
     * Signals to the user that there is no index provided
     *
     * @return The message to be displayed to the user.
     */
    public String noIndexGivenMessage() {
        return "Please enter a task number so that I know which to handle User.\n";
    }

    /**
     * Signals to the user that the command is unrecognizable.
     *
     * @return The message to be displayed to the user.
     */
    public String invalidCommandMessage() {
        return "I am sorry, I do not understand that command.\n";
    }

    /**
     * Signals to the user the current list of tasks.
     *
     * @param taskList The list of tasks.
     * @return The message to be displayed to the user.
     */
    public String displayTaskListMessage(TaskList taskList) {
        String taskListInString = taskList.toString();
        if (taskListInString.isEmpty()) {
            return "There is no task to display User.";
        } else {
            return "Here are your tasks User :\n" + taskListInString;
        }
    }

    /**
     * Signals to the user the list of tasks with the specified keyword.
     *
     * @param listOfTask List of tasks.
     * @param keyWord Keyword to search for specified tasks.
     * @return The message to be displayed to the user.
     */
    public String findTaskByKeyWordMessage(String listOfTask, String keyWord) {
        if (listOfTask.isBlank()) {
            return "There is no task with keyword: " + keyWord + "\n";
        } else {
            return "Here are the task with keyword " + keyWord + " :\n" + listOfTask;
        }
    }

    /**
     * Signals to the user the list of tasks with the specified due date
     *
     * @param listOfTask List of tasks.
     * @param date date to search for tasks against.
     * @return The message to be displayed to the user.
     */
    public String findTaskDueMessage(String listOfTask, String date) {
        if (listOfTask.isBlank()) {
            return "There is no task due on " + date + " User.\n";
        } else {
            return "Here are the task due on " + date + " :\n" + listOfTask;
        }
    }

    /**
     * Signals to the user that the date provided is of an invalid format
     *
     * @return The message to be displayed to the user.
     */
    public String invalidDateMessage() {
        return "The input date should be dd-mm-yyyy User!";
    }

    /**
     * Signals to the user that there is a problem with the file processing
     *
     * @return The message to be displayed to the user.
     */
    public String fileIssueMessage() {
        return "there is an issue with the file User!";
    }

    /**
     * Signals to the user that the task has been deleted from the list of tasks.
     *
     * @param deletedTask The task that was deleted.
     * @param noTask The number of tasks left.
     * @return the message to be displayed to the user.
     */
    public String deletedTaskMessage(Task deletedTask, int noTask) {
        return "I've deleted the task from the list User: \n " + deletedTask.toString()
                + "\nYou now have " + String.valueOf(noTask) + " task in the list.\n";
    }

    /**
     * Signals to the user that the specified alias to command mapping has been deleting.
     *
     * @param alias The alias that is deleted from the mapping.
     * @return the message to be displayed to the user
     */
    public String deletedAliasMessage(String alias) {
        return "Mapping has been deleted User : " + alias;
    }

    /**
     * Signals to the user that there is no alias specified.
     *
     * @return The message to be displayed to the user.
     */
    public String noAliasMessage() {
        return "Please enter an alias User!";
    }

    /**
     * Signals to the user that the specified alias to delete does not exist
     *
     * @param alias The alias that is meant to be deleted.
     * @return The message to be displaye to the user.
     */
    public String aliasDoesNotExistMessage(String alias) {
        return "The alias " + alias + " does not exist User!";
    }

    /**
     * Signals to the use that the specified task has been added to the list of tasks.
     *
     * @param newTask The task that was added.
     * @param noTask The number of task in the list.
     * @return The message to be displayed to the user.
     */
    public String addTaskMessage(Task newTask, int noTask) {
        return "I have added the task User : \n \t"
                + newTask.toString() + "\nyou have " + String.valueOf(noTask) + " Tasks in the list.\n";
    }

    /**
     * Signals to the user that command to add task is incomplete.
     *
     * @param taskType The type of task addition command.
     * @return The message to be displayed to the user.
     */
    public String inSufficientParamsMessage(String taskType) {
        return "Command for " + taskType + " is incomplete User!";
    }

    /**
     * Signals to the user that the alias to command mapping is successful.
     *
     * @param mapping The alias to command mapping.
     * @return The message to be displayed to the user.
     */
    public String newMappingMessage(String mapping) {
        return "New mapping has been created User: \n" + mapping;
    }

    /**
     * Signals to the user that the alias creation command is incomplete.
     *
     * @return The message to be displayed to the user.
     */
    public String inCompleteAliasCommandMessage() {
        return "Please fill up the command fully User: alias <Alias name> <Command name>";
    }

    /**
     * Signals to the use that the alias to be created already exists.
     *
     * @param alias The alias that was supposed to be mapped.
     * @return The message to be displayed to the user.
     */
    public String aliasAlreadyExistMessage(String alias) {
        return "The alias " + alias + " already exist User!";
    }


    /**
     * Signals to the user that there is no date being provided in the command.
     *
     * @return The message to be displayed to the user.
     */
    public String noDateMessage() {
        return "Please input a date User!";
    }

    /**
     * Bid user farewell
     *
     * @return The message to be displayed to the user.
     */
    public String farewellMessage() {
        return "Farewell User pleasure to serve you!\n";
    }

}
