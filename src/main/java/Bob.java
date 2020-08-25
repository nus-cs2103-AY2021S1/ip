import main.java.*;

public class Bob {
    private TaskList tasks = new TaskList();
    private Storage storage;
    private UI uI;

    public Bob(String filePath) {
        uI = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            storage.initialiseStorage();
            storage.loadSave(tasks);
        } catch (BobException e) {
            uI.printError(e.getMessage());
        }
    }

    public void run() {
        uI.greet();

        boolean isExit = false;

        while (!isExit) {
            try {
                String command = uI.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, uI, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                uI.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/save.txt").run();
    }
}
