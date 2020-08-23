import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileClass {

    private String pathName;
    private File file;
    private boolean fileExists;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

    public FileClass(String pathName) {
        this.pathName = pathName;
        this.file = new File(pathName);
        this.fileExists = file.exists();
    }

    public void printFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public List<Task> readFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        List<Task> tasksList = new ArrayList<>();
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskDetail = nextLine.split("\\|");
            switch (taskDetail[0]) {
                case "T":
                    boolean todoIsDone = taskDetail[1].equals("0");
                    Todo newTodo = new Todo(todoIsDone, taskDetail[2]);
                    tasksList.add(newTodo);
                    break;
                case "D":
                    boolean deadlineIsDone = taskDetail[1].equals("0");
                    LocalDateTime deadline = LocalDateTime.parse(taskDetail[3], this.formatter);
                    Deadline newDeadline = new Deadline(deadlineIsDone, taskDetail[2], deadline);
                    tasksList.add(newDeadline);
                    break;
                case "E":
                    boolean eventIsDone = taskDetail[1].equals("0");
                    LocalDateTime dateTime = LocalDateTime.parse(taskDetail[3], this.formatter);
                    Event newEvent = new Event(eventIsDone, taskDetail[2], dateTime);
                    tasksList.add(newEvent);
                    break;
                default:
                    System.out.println("File format is wrong!");
                    break;
            }
        }
        s.close();
        return tasksList;
    }

    public void writeToFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.pathName);
        StringBuilder textToAdd = new StringBuilder("");

        list.forEach((task) -> {
            textToAdd.append(task.fileFormat());
            textToAdd.append("\n");
        });

        fw.write(textToAdd.toString());
        fw.close();
    }
}
