package ui;

import java.util.Scanner;

import command.Result;
import duke.TaskList;
import task.Task;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        return sc.nextLine();
    }

    public String displayResult(Result result) {
        System.out.println(result.toString());
        return result.toString();
    }

    /**
     * Greets user
     * @return the greeting message
     */
    public String greet() {
        return "Greetings User, Bit at your service!";
    }

    public String markDoneMessage(Task task) {
        return "Making great progress User.\n" + task.toString() + "\n";
    }

    public String taskAlreadyDoneMessage() {
        return "the task is already marked done User.\n";
    }

    public String taskListEmptyMessage() {
        return "The task list is empty User";
    }

    public String invalidIndexMessage() {
        return "Please enter a valid index User!\n";
    }

    public String noIndexGivenMessage() {
        return "Please enter a task number so that I know which to handle User.\n";
    }

    public String invalidCommandMessage() {
        return "I am sorry, I do not understand that command.\n";
    }

    public String displayTaskListMessage(TaskList taskList) {
        String taskListInString = taskList.toString();
        if (taskListInString.isEmpty()) {
            return "There is no task to display User.";
        } else {
            return "Here are your tasks User :\n" + taskListInString;
        }
    }

    public String findTaskByKeyWordMessage(String listOfTask, String keyWord) {
        if (listOfTask.isBlank()) {
            return "There is no task with keyword: " + keyWord + "\n";
        } else {
            return "Here are the task with keyword " + keyWord + " :\n" + listOfTask;
        }
    }

    public String findTaskDueMessage(String listOfTask, String date) {
        if (listOfTask.isBlank()) {
            return "There is no task due on " + date + " User.\n";
        } else {
            return "Here are the task due on " + date+ " :\n" + listOfTask;
        }
    }

    public String invalidDateMessage() {
        return "The input date should be dd-mm-yyyy User!";
    }

    public String fileIssueMessage() {
        return "there is an issue with the file User!";
    }

    public String deletedTaskMessage(Task deletedTask, int noTask) {
        return "I've deleted the task from the list User: \n " + deletedTask.toString()
                + "\nYou now have " + String.valueOf(noTask) + " task in the list.\n";
    }

    public String deletedAliasMessage(String alias) {
        return "Mapping has been deleted User : " + alias;
    }

    public String noAliasMessage() {
        return "Please enter an alias to delete User!";
    }

    public String aliasDoesNotExistMessage(String alias) {
        return "The alias " + alias + " does not exist User!";
    }

    public String addTaskMessage(Task newTask, int noTask) {
        return "I have added the task User : \n \t"
                + newTask.toString() + "\nyou have " + String.valueOf(noTask) + " Tasks in the list.\n";
    }

    public String inSufficientParamsMessage(String taskType) {
        return "Command for " + taskType + " is incomplete User!";
    }

    public String newMappingMessage(String mapping) {
        return "New mapping has been created User: \n" + mapping;
    }

    public String inCompleteAliasCommandMessage() {
        return "Please fill up the command fully User: alias <Alias name> <Command name>";
    }

    public String aliasAlreadyExistMessage(String alias) {
        return "The alias " + alias + " already exist User!";
    }

    public String noDateMessage() {
        return "Please input a date User!";
    }

    public String farewellMessage() {
        return "Farewell User pleasure to serve you!\n";
    }

}
