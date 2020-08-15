import command.Command;

/**
 * An agent to process incoming command and return the feedback.
 */
public class CommandAgent {
    private static TaskList taskList;

    public CommandAgent() {
        this.taskList = new TaskList();
    }

    /**
     * Takes in the command and handle it based on the request
     * of the command.
     *
     * @param command the command parsed from user input.
     * @return a String feedback for the user.
     */
    public String handleCommand(Command command) {
        String feedback;
        switch (command.sendRequest()) {
        case "end":
            feedback = "Bye. Hope to see you again soon!";
            break;
        case "create":
            String name = command.getContent().get(0);
            taskList = taskList.addTask(new Task(name));
            feedback = "added: " + name;
            break;
        case "retrieval":
            feedback = taskList.printTasks();
            break;
        default:
            feedback = "";
        }
        return feedback;
    }
}
