import main.java.*;

public class Bob {
    private TaskList tasks = new TaskList();
    private Storage storage;
    private UI ui;

    public Bob(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            storage.initialiseStorage();
            storage.loadSave(tasks);
        } catch (BobException e) {
            ui.printError(e.getMessage());
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/save.txt").run();
    }
}
