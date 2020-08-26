import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class InputHandler {
    private final TaskList TASK_LIST = new TaskList();
    private final Scanner SC = new Scanner(System.in);
    private final CommandExecutor EXE = new CommandExecutor();
    private final Ui UI = new Ui();
    private final Storage STORAGE;


    public InputHandler(Path filePath) throws IOException {
        this.STORAGE = new Storage(filePath);
    }

    private void handleStart() {
        UI.print("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void run() {
        handleStart();
        STORAGE.loadSaveFile(TASK_LIST, EXE);
        String input, msgBody;

        while (true) {
            input = SC.nextLine().trim();
            try {
                msgBody = EXE.execute(input, TASK_LIST, STORAGE);
                UI.print(msgBody);
                if (EXE.shouldExit()) {
                    break;
                }
            } catch (DukeException e) {
                UI.printErr(e.getMessage());
            }
        }
    }
}
