import exception.FileCorruptedException;
import task.TaskList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public abstract class UI {
    private static final String GREETING = "    ____________________________________________________________\n"
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + "    ____________________________________________________________\n";

    private static final String ENDWORDS = "    ____________________________________________________________\n"
            + "     Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________\n";

    public static void interact() {
        try {
            System.out.println(UI.GREETING);
            Scanner sc = new Scanner(System.in);

            String home = System.getProperty("user.home");
            Path path = Paths.get(home, "Downloads", "CS2103 IP", "data", "duke.txt");
            File file = path.toFile();

            TaskList list = FileToTaskListConverter.convert(file);

            String command = sc.nextLine();
            CommandProcessor commandProcessor = new CommandProcessor(list);

            while (!command.equals("bye")) {
                commandProcessor.runCommand(command);
                command = sc.nextLine();
            }

            FileToTaskListConverter.saveToFile(list, path.toFile());
            sc.close();
            System.out.println(UI.ENDWORDS);
        } catch (FileCorruptedException fce) {
            System.out.println(fce);
            System.out.println("Program terminates");
        }
    }
}