import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class FileLoader {
    private static final String directoryPath = "data";
    private static final String filePath = "data/duke.txt";

    public static void readSavedFile(TaskManager taskManager) {
        try {
            Messenger.greet();
            System.out.println(Messenger.FILE_LOADING);
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                System.out.println(Messenger.DIRECTORY_NOT_FOUND);
                directory.mkdir();
            }

            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println(Messenger.FILE_NOT_FOUND);
            } else {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parsed = line.split("\\|");

                    String command = parsed[0];
                    if (TaskStatus.hasTime(command)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        LocalDate ld = LocalDate.parse(parsed[2], formatter);
                        taskManager.getTasks().add(new Task(parsed[1], command, ld));
                    } else {
                        taskManager.getTasks().add(new Task(parsed[1], command));
                    }
                }
                System.out.println(Messenger.FILE_LOADED);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDataToFile(TaskManager taskManager) {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task task : taskManager.getTasks()) {
                writer.write(task.getStatus() + "|" + task.getContent() +
                        (TaskStatus.hasTime(task.getStatus()) ? "|" + task.getTime() : "") + System.lineSeparator());
            }
            writer.close();
            Messenger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
