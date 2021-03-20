import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the central backend component of the Duke application. This
 * component coordinates the roles of other components (i.e. storage, parser,
 * user interface, task list) in executing a user command.
 */
public class Command {

    private String userCommand;

    /**
     * Constructs a Command object that has not received
     * any user command yet.
     */
    public Command() {
        this.userCommand = "";
    }

    /**
     * Receives a user command from the main window.
     * @param userCommand User command.
     */
    public void receiveUserCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Indicates whether the Duke application should exit.
     * @return Boolean value indicating whether the Duke application should exit.
     */
    public boolean exit() {
        return userCommand.equals("bye");
    }

    /**
     * Executes a user command. The Command object directs the user command to the parser
     * for parsing. It then proceeds to alert the task list, storage, and user interface to
     * perform the appropriate actions based on the user command.
     * @param parser Parser of the Duke application.
     * @param taskList Task list of the Duke application
     * @param storage Storage system of the Duke application.
     * @param ui User interface of the Duke application.
     * @return Response of the Command object to the main window.
     * @throws IOException If an error occurs while accessing or creating the directory or file
     * containing the tasks.
     * @throws InvalidTaskArgumentException If an error occurs while parsing a command to add tasks.
     * @throws InvalidDoneException If an error occurs while parsing a command to mark tasks as done.
     * @throws InvalidCommandException If the user command type is invalid.
     * @throws InvalidDeleteException If an error occurs while parsing a command to delete tasks.
     * @throws DateException If an error occurs while parsing the dates of events or deadlines.
     */
    public String executeUserCommand(Parser parser, TaskList taskList, Storage storage, Ui ui)
            throws IOException, InvalidTaskArgumentException, InvalidDoneException, InvalidCommandException,
            InvalidDeleteException, InvalidFindException, DateException {
        ArrayList<String> userCommandDetails = parser.parseUserCommand(userCommand, taskList.getLength());
        String response = "";
        String userCommandType = userCommandDetails.get(0);
        assert isValidCommand(userCommandType) : "The user command is not a valid operation.";
        if (userCommandType.equals("Show")) {
            response = taskList.showList(ui);
            storage.save(taskList.getTasks());
        }
        if (userCommandType.equals("Done")) {
            int taskPosition = Integer.parseInt(userCommandDetails.get(1));
            response = taskList.markDone(taskPosition, ui);
            storage.save(taskList.getTasks());
        }
        if (userCommandType.equals("Add")) {
            String taskType = userCommandDetails.get(1);
            assert isValidTaskType(taskType) : "The task type should be a todo, deadline, or event.";
            if (taskType.equals("ToDo")) {
                String todoDescription = userCommandDetails.get(2);
                ToDo todo = new ToDo(todoDescription);
                response = taskList.addTask(todo, ui);
            }
            if (taskType.equals("Deadline")) {
                String deadlineDescription = userCommandDetails.get(2);
                String deadlineDate = userCommandDetails.get(3);
                Deadline deadline = new Deadline(deadlineDescription, deadlineDate);
                response = taskList.addTask(deadline, ui);
            }
            if (taskType.equals("Event")) {
                String eventDescription = userCommandDetails.get(2);
                String eventDate = userCommandDetails.get(3);
                Event event = new Event(eventDescription, eventDate);
                response = taskList.addTask(event, ui);
            }
            storage.save(taskList.getTasks());
        }
        if (userCommandType.equals("Find")) {
            String keyword = userCommandDetails.get(1);
            response = taskList.findTask(keyword, ui);
        }
        if (userCommandType.equals("Delete")) {
            boolean isDeleteAll = userCommandDetails.get(1).equals("All");
            if (isDeleteAll) {
                response = taskList.deleteAll(ui);
            } else {
                ArrayList<String> deletedTasks = new ArrayList<>();
                for (int i = 1; i < userCommandDetails.size(); i++) {
                    deletedTasks.add(userCommandDetails.get(i));
                }
                response = taskList.deleteTasks(deletedTasks, ui);
            }
            storage.save(taskList.getTasks());
        }
        return response;
    }

    private boolean isValidCommand(String userCommandType) {
        return userCommandType.equals("Show") || userCommandType.equals("Done")
                || userCommandType.equals("Add") || userCommandType.equals("Find")
                || userCommandType.equals("Delete");
    }

    private boolean isValidTaskType(String taskType) {
        return taskType.equals("ToDo") || taskType.equals("Deadline") || taskType.equals("Event");
    }
}
