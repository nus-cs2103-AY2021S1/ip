package dobby;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Main class which begins the running of the chat bot
 */
public class Dobby {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Dobby (String filePath) {
        this.tasks = new TaskList();
        parser = new Parser(this.tasks);
        this.storage = new Storage(filePath, tasks);
        ui = new Ui();
    }

    /**
     * Takes in user input as long as user gives and terminated when user
     * enter bye
     */
    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);
        this.storage.readFile();

        while (true) {
            String text = ui.getInput();
            try {
                ui.reply(parser.getMessage(text));
            } catch (DobbyException e) { // prints error message
                ui.reply(e.getMessage());
            }
            if (text.equals("bye")) { // terminates program after bye command
                storage.rewriteFile();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        File dobbyFile = new File("../dobbylist.txt");

        try {
            if (!dobbyFile.exists()){
                dobbyFile.getParentFile().mkdirs(); // Will create parent directories if not exists
                dobbyFile.createNewFile();
                FileOutputStream s = new FileOutputStream(dobbyFile, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Dobby(dobbyFile.getPath()).run();
    }
}