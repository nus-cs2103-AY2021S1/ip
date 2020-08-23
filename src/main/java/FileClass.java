import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileClass {
    String pathName;
    File file;
    boolean fileExists;

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
                    Deadline newDeadline = new Deadline(deadlineIsDone, taskDetail[2], taskDetail[3]);
                    tasksList.add(newDeadline);
                    break;
                case "E":
                    boolean eventIsDone = taskDetail[1].equals("0");
                    Event newEvent = new Event(eventIsDone, taskDetail[2], taskDetail[3]);
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
