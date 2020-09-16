package bob;

import bob.command.Command;
import bob.exception.BobException;
import javafx.scene.Scene;

public class Bob {

    private Scene scene;
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

    public Bob() {

    }

    public void run() {
        System.out.println(uI.greet());

        boolean isExit = false;

        while (!isExit) {
            try {
                String command = uI.readCommand();
                Command c = Parser.parse(command);
                assert c != null : "A command should be provided";
                System.out.println(c.execute(tasks, uI, storage));
                isExit = c.isExit();
            } catch (BobException e) {
                System.out.println(uI.printError(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/save.txt").run();
    }


    String getResponse(String input) throws BobException {
            String command = input;
            Command c = Parser.parse(command);
            assert c != null : "A command should be provided";
            return c.execute(tasks, uI, storage);
    }
}

