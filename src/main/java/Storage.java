import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //This function creates file if it does not exist, else return
    private void createFile() throws IOException {
        File f = new File(this.filePath);
        if (f.exists()) {
            return;
        } else {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
    }

    //This function returns a list of tasks to be loaded into tasklist
    public List<Task> load() throws IOException {
        this.createFile();
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            //each line is a T|X|description or D|X|description|time format
            String line = sc.nextLine();
            String[] taskComponent = line.split(Pattern.quote("|"));
            switch (taskComponent[0]) {
            case "T":
                Task todoTask = new Todo(taskComponent[2]);
                taskList.add(todoTask);
                if (taskComponent[1].equals("\u2713")) {
                    todoTask.markAsDone();
                }
                break;
            case "D":
                Task deadlineTask = new Deadline(taskComponent[2], taskComponent[3]);
                taskList.add(deadlineTask);
                if (taskComponent[1].equals("\u2713")) {
                    deadlineTask.markAsDone();
                }
                break;
            case "E":
                Task eventTask = new Event(taskComponent[2], taskComponent[3]);
                taskList.add(eventTask);
                if (taskComponent[1].equals("\u2713")) {
                    eventTask.markAsDone();
                }
                break;
            }
        }
        return taskList;
    }

    //This function write to file
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    //This function updates file content when tasklist updated
    public void updateFile(String text) throws IOException {
        writeToFile(this.filePath, text);
    }
}
