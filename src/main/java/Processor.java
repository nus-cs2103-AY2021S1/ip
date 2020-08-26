package main.java;

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
                    Parser.isValidDate(deadlineTime); // check whether the date time format is correct
                    boolean hasTime = Parser.hasTime(deadlineTime); // check whether time is included
                    Task deadLine = new DeadLine(taskDescription, deadlineTime, hasTime, false);
                    taskList.addTask(deadLine);
                    ui.uiAddTask(deadLine, taskList);
                } else {
                    String taskDescription = Parser.findDescription(Parser.getArgs(userInput));
                    String eventTime = Parser.findTime(Parser.getArgs(userInput), "at");
                    Parser.isValidDate(eventTime); // check whether the date time format is correct
                    boolean hasTime = Parser.hasTime(eventTime); // check whether time is included
                    Task event = new DeadLine(taskDescription, eventTime, hasTime, false);
                    taskList.addTask(event);
                    ui.uiAddTask(event, taskList);
                }
                storage.writeTasks(taskList);
            }
        }
        ui.goodBye();
    }
}
