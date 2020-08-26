package main.java.duke.main;

import main.java.duke.exception.DukeException;

import main.java.duke.task.DeadLine;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;
import main.java.duke.task.TaskList;

/**
 * Handles the logic flow of Duke.
 */
public class Processor {

    /**
     * Processes the run of the program.
     * @param taskList The user's task list.
     * @param storage The storage storing the user's saved task list.
     * @param ui UI that handles interaction with user.
     * @throws DukeException Exception can be thrown due to multiple reasons, such as
     *                       invalid command, empty task description, empty date or
     *                       wrong date format.
     */
    public static void process(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.greetings();
        while(Parser.hasNextLine()) {
            String userInput = Parser.readNextLine();
            String command = Parser.getCommand(userInput);
            if (Keyword.isValid(command)) {
                if (command.equals("bye")) {
                    ui.goodBye();
                    break;
                } else if (command.equals("list")) {
                    ui.printFullList(taskList);
                } else if (command.equals("done")) {
                    int index = Parser.getIndexTask(userInput);
                    Task task = taskList.getTask(index - 1);
                    task.markAsDone();
                    ui.markTaskAsDone(task);
                } else if (command.equals("delete")) {
                    int index = Parser.getIndexTask(userInput);
                    Task deletedTask = taskList.getTask(index - 1);
                    taskList.deleteTask(index - 1);
                    ui.uiDeleteTask(deletedTask, taskList);
                } else if (command.equals("todo")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    Task task = new ToDo(taskDescription);
                    taskList.addTask(task);
                    ui.uiAddTask(task, taskList);
                } else if (command.equals("deadline")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String deadlineTime = Parser.findTime(Parser.getArgs(userInput), "by");
                    // check whether the date time format is correct
                    Parser.isValidDate(deadlineTime);
                    // check whether time is included
                    boolean hasTime = Parser.hasTime(deadlineTime);
                    Task deadine = new DeadLine(taskDescription, deadlineTime, hasTime, false);
                    taskList.addTask(deadine);
                    ui.uiAddTask(deadine, taskList);
                } else if (command.equals("event")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String eventTime = Parser.findTime(Parser.getArgs(userInput), "at");
                    // check whether the date time format is correct
                    Parser.isValidDate(eventTime);
                    // check whether time is included
                    boolean hasTime = Parser.hasTime(eventTime);
                    Task event = new Event(taskDescription, eventTime, hasTime, false);
                    taskList.addTask(event);
                    ui.uiAddTask(event, taskList);
                } else {
                    String keyword = Parser.getArgs(userInput);
                    TaskList correspondTaskList = taskList.findTaskWithKeyword(keyword);
                    ui.printMatchingList(correspondTaskList);
                }
                storage.writeTasks(taskList);
            }
        }
    }
}
