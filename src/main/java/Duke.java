import Exception.FileCorruptedException;
import Task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        introduction();
        Scanner sc  = new Scanner(System.in);
        interact(sc);
        sc.close();
    }

    public static void introduction() {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void interact(Scanner sc) {
        try {
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, "Downloads", "CS2103 IP", "data", "duke.txt");
            File file = path.toFile();

            List<Task> list = FileToTaskListConverter.convert(file);

            String command = sc.nextLine();
            CommandProcessor commandProcessor = new CommandProcessor(list);

            while (!command.equals("bye")) {
                commandProcessor.runCommand(command);
                command = sc.nextLine();
            }

            saveToFile(list, path.toFile());
            System.out.println("    ____________________________________________________________\n"
                    + "     Bye. Hope to see you again soon!\n"
                    + "    ____________________________________________________________\n"
            );
        } catch (FileCorruptedException fce) {
            System.out.println(fce);
            System.out.println("Program terminates");
        }
    }

    public static void saveToFile(List<Task> list, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String type = task.getType();

                sb.append(type + "//");
                sb.append(task.isDone() ? "✓" : "✘");
                sb.append("//");
                sb.append(task.getDescription() + "//");

                if (type.equals("D") || type.equals("E")) {
                    sb.append(task.getDateInput() + "//");
                    sb.append(task.getTimeInput());
                }
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save");
        }
    }
}
