import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;


public class Duke {

    private TaskList taskList;

    // Constructor for the bot
    public Duke(TaskList taskList) {
        this.taskList = taskList;
    }

    // Method to intiate the bot
    private static void startBot() throws DukeException, IOException {
        String welcome = "Hello I am Duke!\nHow can I help you?\n";
        System.out.println(welcome);

        Storage storage = new Storage("data" + File.separator +  "duke.txt");

        TaskList dukeTaskList = new TaskList(storage.loadData());

        Duke newBot = new Duke(dukeTaskList);

        UI dukeUI = new UI();

        Parser dukeParser = new Parser();

        boolean isExit = false;

        while (!isExit) {
            String userCommand = dukeUI.readCommand();
            Command cmd = dukeParser.parseCommand(userCommand);
            cmd.execute(dukeTaskList, dukeUI);
            isExit = cmd.isExit();
        }
            storage.writeData(dukeTaskList);
        }
        

    public static void main(String[] args) throws Exception {
        startBot();
    }
}