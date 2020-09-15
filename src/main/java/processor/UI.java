package processor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import exception.FileCorruptedException;
import task.TaskList;

/**
 * Interacts with users command.
 *
 * <p>The 'UI' supports operators, supported include: </p>
 *
 * <p> (i) interacting with user </p>
 */
public class UI {
    private static final String GREETING = "     Hello! I'm Duke\n"
            + "     What can I do for you?\n";

    private static final String ENDWORDS = "     Bye. Hope to see you again soon!\n";

    private CommandProcessor commandProcessor;
    private Path filePath;
    private TaskList taskList;
    private boolean isDone;

    /**
     * Constructor for this object.
     *
     * Loads data file and creates one if it does not exists
     */
    public UI() {
        Scanner sc = new Scanner(System.in);

        String home = System.getProperty("user.home");
        this.filePath = Paths.get(home, "Downloads", "CS2103 IP", "data", "duke.txt");
        File file = this.filePath.toFile();

        try {
            this.taskList = FileToTaskListConverter.convert(file);
        } catch (FileCorruptedException fce) {
            this.taskList = new TaskList();
        }

        this.commandProcessor = new CommandProcessor(this.taskList);
        isDone = false;

        sc.close();
    }

    public static String getGreeting() {
        return GREETING;
    }

    /**
     * Interacts with user input command.
     * User's command entered through interface is stored and processed
     */
    public String interact(String command) {
        if (command.equals("bye")) {
            FileToTaskListConverter.saveToFile(this.taskList, this.filePath.toFile());
            isDone = true;
            return UI.ENDWORDS;
        } else {
            return this.commandProcessor.runCommand(command);
        }
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }
}
