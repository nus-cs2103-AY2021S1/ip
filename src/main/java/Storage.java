import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File dataFile = new File(filePath);
        if (dataFile.exists()) {
            try {
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNextLine()) {
                    String taskEntry = scanner.nextLine();
                    String[] taskInformation = taskEntry.split("\\|");
                    if (taskEntry.startsWith("[T]")) {
                        String description = taskEntry.split("\\|")[1];
                        Task task = new ToDo(description);
                        if (taskInformation[0].contains("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    } else if (taskEntry.startsWith("[D]")) {
                        String description = taskEntry.split("\\|")[1];
                        Task task = new Deadline(description, LocalDate.parse(taskEntry.split("\\|")[2]));
                        if (taskInformation[0].contains("1")) {
                            task.markAsDone();
                        }
                    } else if (taskEntry.startsWith("[E]")) {
                        String description = taskEntry.split("\\|")[1];
                        Task task = new Event(description, LocalDate.parse(taskEntry.split("\\|")[2]));
                        if (taskInformation[0].contains("1")) {
                            task.markAsDone();
                        }
                    }

                }
            } catch (IOException ioException) {
                System.out.println("An error has occurred");
                ioException.printStackTrace();
            }
        }
        return tasks;
    }

    public void saveToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter("data.txt", true);
            String[] strings = task.toString().split("\\|");
            String isDone = task.getIsDone() ? "1" : "0";
            String description = strings[2];
            fileWriter.write(strings[0] + " | " + isDone + " | " + description + "\n");
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println("An error has occurred");
            ioException.printStackTrace();
        }
    }

}
