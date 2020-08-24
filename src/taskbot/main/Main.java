package taskbot.main;

import taskbot.command.Command;
import taskbot.Parser.Parser;
import taskbot.exceptions.TaskbotException;
import taskbot.ui.Ui;

import taskbot.task.TaskList;

/**
 * This is the main driver class.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        //Initialise Taskbot & required classes
        Ui ui = new Ui();

        //Prints the title to the console and greets the user
        ui.printTitle();
        TaskList taskList = new TaskList();
        ui.greet();

        //Exit condition
        boolean isExit = false;

        //Loops while the user does not input "bye"
        while (!isExit) {
            try {
                //Gets the full command, basically use Scanner //
                String command = ui.readCommand();
                ui.showLine();
                Command cmd = Parser.parse(command);

                cmd.execute(taskList, ui);
                isExit = cmd.isExit();
            } catch (TaskbotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }
}
