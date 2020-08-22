import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    //This function creates file if does not exit, else continue
    private void createFile() {
        File f = new File(this.fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("error occured");
        }
    }

    //This function returns a list of tasks to be loaded into tasklist
    public List<Task> load() throws FileNotFoundException {
        this.createFile();
        File f = new File(this.fileName);
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
    public void updateFile(String text) {
        try {
            writeToFile(this.fileName, text);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

        /*
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            content.append(taskList.get(i));
            content.append(System.lineSeparator());
        }
        try {
            writeToFile(this.file, content.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

         */
}
