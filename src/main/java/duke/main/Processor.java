package duke.main;

import duke.exception.DukeException;

import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;

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
        while(Parser.hasNextLine()) {
            String userInput = Parser.readNextLine();
            String command = Parser.getCommand(userInput);
            String response;
            if (Keyword.isValid(command)) {
                if (command.equals("bye")) {
                    ui.printToScreen(ui.getGoodByeMessage());
                    break;
                } else if (command.equals("list")) {
                    response = ui.getFullList(taskList);
                } else if (command.equals("done")) {
                    int index = Parser.getIndexTask(userInput);
                    Task task = taskList.getTask(index - 1);
                    task.markAsDone();
                    response = ui.getMarkTaskAsDoneMessage(task);
                } else if (command.equals("delete")) {
                    int index = Parser.getIndexTask(userInput);
                    Task deletedTask = taskList.getTask(index - 1);
                    taskList.deleteTask(index - 1);
                    response = ui.getDeleteTaskMessage(deletedTask, taskList);
                } else if (command.equals("todo")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    Task task = new ToDo(taskDescription);
                    taskList.addTask(task);
                    response = ui.getAddTaskMessage(task, taskList);
                } else if (command.equals("deadline")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String deadlineTime = Parser.findTime(Parser.getArgs(userInput), "by");
                    // check whether the date time format is correct
                    Parser.isValidDate(deadlineTime);
                    // check whether time is included
                    boolean hasTime = Parser.hasTime(deadlineTime);
                    Task deadine = new DeadLine(taskDescription, deadlineTime, hasTime, false);
                    taskList.addTask(deadine);
                    response = ui.getAddTaskMessage(deadine, taskList);
                } else if (command.equals("event")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String eventTime = Parser.findTime(Parser.getArgs(userInput), "at");
                    // check whether the date time format is correct
                    Parser.isValidDate(eventTime);
                    // check whether time is included
                    boolean hasTime = Parser.hasTime(eventTime);
                    Task event = new Event(taskDescription, eventTime, hasTime, false);
                    taskList.addTask(event);
                    response = ui.getAddTaskMessage(event, taskList);
                } else {
                    String keyword = Parser.getArgs(userInput);
                    TaskList correspondTaskList = taskList.findTaskWithKeyword(keyword);
                    response = ui.getMatchingList(correspondTaskList);
                }
                storage.writeTasks(taskList);
                ui.printToScreen(response);
            }
        }
    }
}
