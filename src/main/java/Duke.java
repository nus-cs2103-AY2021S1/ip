import exceptions.DukeException;

import java.io.IOException;

public class Duke {

    private Storage store = new Storage();
    private TaskList taskList;
    private Ui ui;
    private Parser parser = new Parser();

    public void start() {
        ui = new Ui();
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                store.initializeStorage();
                taskList = new TaskList(store.getTasks());
                Command c = parser.parse(ui.readCommand());
                c.execute(taskList, ui, store);
                isRunning = !c.isExit();
            } catch (IOException e) {
                System.out.println("Error connecting to storage, actions made will not be saved");
            } catch (DukeException e) {
                ui.printMessage(e.getFriendlyMessage());
            }
        }
        ui.stopReading();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
