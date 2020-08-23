import java.io.FileNotFoundException;

public class Duke {
    private DukeStorage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        storage = new DukeStorage(filePath);
        ui = new Ui();
        taskList = new TaskList();
        // try to open the duke file
        try {
            storage.reloadStorage(taskList.getTasks());
        } catch (FileNotFoundException ex) {
            System.out.println("Duke data do not exist!");
        } catch (DukeException ex) {
            ui.format("Its a duke exception!");
        }
    }

    public void run() {
        ui.greet();
        boolean isCompleted = false;
        while (!isCompleted) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                isCompleted = c.isCompleted();
            } catch (DukeException ex) {
                ui.throwDukeException(ex);
            }
        }
        ui.getScanner().close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
