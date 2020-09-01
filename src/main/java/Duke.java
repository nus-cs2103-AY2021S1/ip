import duke.command.Command;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.utils.Parser;
import duke.utils.Ui;

public class Duke {

    private TaskList tasks;
    private Ui ui;

    public Duke(String path, String fileName) {
        ui = new Ui();
        tasks = new TaskList(path, fileName);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.sayGoodBye();
    }

    public static void main(String[] args) {
        new Duke("data/", "duke.txt").run();
    }

}
