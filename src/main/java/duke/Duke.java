package duke;
import duke.command.Commands;
import duke.exception.InvalidArgumentException;
import duke.exception.UserException;
import duke.misc.Parser;
import duke.misc.Ui;
import duke.task.TaskList;

public class Duke {
    private TaskList taskList;

    public String getResponse(String input) {
        try {
            return Commands.create(Parser.parseCommand(input)).run(taskList);
        } catch (UserException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unhandled exception: \n"
                    + e.getMessage() + "\n"
                    + "tasks list: " + taskList;
        }
    }

    public Duke() {
        taskList = new TaskList();
        try {
            taskList.initialize();
        } catch (InvalidArgumentException e) {
            System.out.println("Error parsing file");
        }
    }

    /**
     * Run the program.
     */
    public void runCli() {
        Ui.start();
        String input = "";
        do {
            try {
                input = Ui.feed();
                String response = Commands.create(Parser.parseCommand(input)).run(taskList);
                Ui.wrap(() -> System.out.println(response));
            } catch (UserException e) {
                Ui.wrap(() -> System.out.println(e.getMessage()));
            } catch (Exception e) {
                System.out.println("Unhandled exception:");
                System.out.println(e.getMessage());
            }
        }
        while (!input.equals("bye"));
        Ui.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runCli();
    }
}











