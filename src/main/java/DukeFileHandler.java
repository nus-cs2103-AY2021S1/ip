import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to handle the saving of files
 */
public class DukeFileHandler {
    private final String path;

    public DukeFileHandler(String path) {
        this.path = path;
    }

    public List<Task> readFile() throws FileNotFoundException {
        File file = new File(path);

        if (!file.exists()) {
            return new ArrayList<>();

        } else {
            Scanner scan = new Scanner(file);
            List<Task> toDoList = new ArrayList<>();

            while (scan.hasNext()) {
                toDoList.add(createTask(scan.nextLine()));
            }

            return toDoList;
        }
    }


    // input is of format TaskType | isDone | Detail | Date (can be null)
    private Task createTask(String input) {
        String[] inputArr = input.split("\\|");
        boolean isDone = inputArr[1].trim().equals("1");


        switch (inputArr[0].trim()) {
        case "T":
            return new Task(inputArr[2].trim(), isDone);
        case "D":
            return new Deadline(inputArr[2].trim(), isDone, inputArr[3].trim());
        case "E":
            return new Events(inputArr[2].trim(), isDone, inputArr[3].trim());

        default:
            return null;
        }
    }


    public void writeToFile(List<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        StringBuilder content = new StringBuilder();

        for(Task task : list){
            content.append(task.toCustomString()).append(System.lineSeparator());
        }


        fileWriter.write(content.toString());
        fileWriter.close();
    }

}