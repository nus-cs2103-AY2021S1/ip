import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class to handle the saving of files
 */
public class DukeFileHandler {

    public static List<Task> readFile() throws FileNotFoundException {
        File file = new File("data/dukeData.txt");

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
    private static Task createTask(String input) {
        String[] inputArr = input.split("\\|");
        boolean isDone = inputArr[1].trim().equals("1");

        System.out.println("Task is: " + Arrays.toString(inputArr));

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

}