package dobby;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class which begins the running of the chat bot
 */
public class Dobby {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private boolean isInitialized = false;

    public Dobby() {
    }

    /**
     * Takes in user input as long as user gives and terminated when user
     * enter bye
     */
    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);

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

    /**
     * Loads the dobbylist storage file and initializes other components of package
     */
    public void initialize() {
        File dobbyFile = new File("../dobbylist.txt");

        try {
            if (!dobbyFile.exists()) {
                dobbyFile.getParentFile().mkdirs(); // Will create parent directories if not exists
                dobbyFile.createNewFile();
                FileOutputStream s = new FileOutputStream(dobbyFile, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.tasks = new TaskList();
        this.parser = new Parser(this.tasks);
        this.storage = new Storage(dobbyFile.getPath(), tasks);
        this.ui = new Ui();
        this.storage.readFile();
        isInitialized = true;
    }

    /**
     * Returns the text to be replied to the user based on input
     * @param input
     * @return response
     */
    public String getResponse(String input) {
        String response;
        if (!isInitialized) {
            this.initialize();
        }
        try {
            response = this.parser.getMessage(input);
        } catch (DobbyException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        Dobby dobby = new Dobby();
        dobby.initialize();
        dobby.run();
    }
}
