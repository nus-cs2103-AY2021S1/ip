package seedu.duke;

import java.security.InvalidParameterException;

/**
 * Holds the execution of orders.
 */
public class Executor {

    /**
     * Output of Duke.
     */
    private Ui ui;

    /**
     * Parser of user orders.
     */
    private Parser parser;

    /**
     * Holds tasks of user.
     */
    private TaskList taskList;

    /**
     * Initializes the executor and sets default parameters.
     *
     * @param parser Assigned parser of Duke.
     * @param ui Assigned ui object of Duke.
     * @param taskList Assigned task list of Duke.
     */
    public Executor(Parser parser, Ui ui, TaskList taskList) {
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Holds the main structure of execution.
     * This method will first tell what order user tapped in and decided how to execute them.
     *
     * @param userInput User input represent the order.
     * @return A string represent the execution result.
     */
    public String execute(String userInput) {
        String result;
        if (userInput.equals("bye")) {
            result = ui.goodBye();
            System.exit(0);
        } else if (userInput.equals("list")) {
            result = ui.listTask(taskList);
        } else if (userInput.startsWith("done")) {
            try {
                int doneNumber = parser.parseDoneOrder(userInput, taskList);
                taskList.markDone(doneNumber);
                result = ui.taskDone(taskList.getTask(doneNumber));
            } catch (IndexOutOfBoundsException e) {
                result = ui.taskDoesNotExist();
            } catch (Exception e) {
                result = ui.invalidDoneOrder();
            }
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput
                .startsWith("event")) {
            try {
                Task newTask = parser.parseTask(userInput);
                taskList.addNewTask(newTask);
                result = ui.newTaskAdded(newTask, taskList);
            } catch (InvalidParameterException e) {
                result = ui.parseFail(userInput);
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int deletingIndex = parser.parseDeleteOrder(userInput, taskList);
                Task deletingTask = taskList.getTask(deletingIndex);
                taskList.deleteTask(deletingIndex);
                result = ui.taskDeleted(taskList, deletingTask);
            } catch (IndexOutOfBoundsException e) {
                result = ui.taskDoesNotExist();
            } catch (Exception e) {
                result = ui.invalidDeleteOrder();
            }
        } else if (userInput.startsWith("find")) {
            try {
                String keyWord = parser.parseFindOrder(userInput);
                result = ui.showFindResult(taskList.find(keyWord));
            } catch (InvalidParameterException e) {
                result = ui.invalidFindOrder();
            }
        } else if (userInput.equals("all lists")) {
            result = ui.showAllLists();
        } else if (userInput.equals("current list")) {
            result = ui.showCurrentList();
        } else if (userInput.startsWith("switch")) {
            try {
                String listPath = parser.parseSwitch(userInput);
                this.taskList.switchList(listPath);
                result = ui.listSwitched(userInput);
            } catch (InvalidParameterException e) {
                result = ui.invalidSwitchOrder();
            } catch (Exception e) {
                result = "An error happened, please try again.";
            }
        } else if (userInput.isEmpty()) {
            result = ui.noOrder();
        } else {
            result = ui.generalError();
        }
        assert !result.isEmpty(); //Ensure execution result is not empty.
        return result;
    }

    /**
     * Gives the task list.
     *
     * @return Current task list.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
