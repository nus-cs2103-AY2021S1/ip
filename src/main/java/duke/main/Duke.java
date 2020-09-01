package duke.main;

import java.io.IOException;

import duke.task.*;
import duke.exception.DukeException;


/**
 * Represents the Duke object to start the program.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    /**
     * Creates a Duke object with an Ui object, storage object to the saved task list file in the
     * hard disk and a task list object being created after the saved task list file is saved.
     * If the directory to the saved task list file is not found, an IOException error will be raised
     * and caught.
     * @param fileName Name of the saved task list file.
     */
    public Duke(String fileName) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(fileName);
            this.tasklist = storage.formTaskList();
        } catch (IOException error) {
            System.out.println(error.toString());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        try {
            Processor.process(tasklist, storage, ui);
        } catch (DukeException dukeException) {
            ui.printError(dukeException);
        }
    }

    public String getResponse(String userInput) {
        String response = "";
        try {
            String command = Parser.getCommand(userInput);
            if (Keyword.isValid(command)) {
                if (command.equals("bye")) {
                    response = "Bye Master. Hope to see you again soon!";
                } else if (command.equals("list")) {
                    response = ui.getFullList(tasklist);
                } else if (command.equals("done")) {
                    int index = Parser.getIndexTask(userInput);
                    Task task = tasklist.getTask(index - 1);
                    task.markAsDone();
                    response = ui.markTaskAsDone(task);
                } else if (command.equals("delete")) {
                    int index = Parser.getIndexTask(userInput);
                    Task deletedTask = tasklist.getTask(index - 1);
                    tasklist.deleteTask(index - 1);
                    response = ui.uiDeleteTask(deletedTask, tasklist);
                } else if (command.equals("todo")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    Task task = new ToDo(taskDescription);
                    tasklist.addTask(task);
                    response = ui.uiAddTask(task, tasklist);
                } else if (command.equals("deadline")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String deadlineTime = Parser.findTime(Parser.getArgs(userInput), "by");
                    // check whether the date time format is correct
                    Parser.isValidDate(deadlineTime);
                    // check whether time is included
                    boolean hasTime = Parser.hasTime(deadlineTime);
                    Task deadine = new DeadLine(taskDescription, deadlineTime, hasTime, false);
                    tasklist.addTask(deadine);
                    response = ui.uiAddTask(deadine, tasklist);
                } else if (command.equals("event")) {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String eventTime = Parser.findTime(Parser.getArgs(userInput), "at");
                    // check whether the date time format is correct
                    Parser.isValidDate(eventTime);
                    // check whether time is included
                    boolean hasTime = Parser.hasTime(eventTime);
                    Task event = new Event(taskDescription, eventTime, hasTime, false);
                    tasklist.addTask(event);
                    response = ui.uiAddTask(event, tasklist);
                } else {
                    String keyword = Parser.getArgs(userInput);
                    TaskList correspondTaskList = tasklist.findTaskWithKeyword(keyword);
                    response = ui.getMatchingList(correspondTaskList);
                }
            storage.writeTasks(tasklist);

            }
        } catch (DukeException error) {
            response = error.toString();
        } finally {
            return response;
        }
    }
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
