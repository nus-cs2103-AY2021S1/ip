import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DukeFileReader {

    public static boolean isDone(String symbol) {
        if (symbol.equals("✓")) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Task> readStorageTasks(String pathname) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(pathname);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {

            String input = s.nextLine();
            String organisedInput = input.replaceAll("\\[","").replaceAll("\\]","|");
            String[] splitTask = organisedInput.split("\\|");
            // first element of the array is type T, second element of the array is isDone X,
            // last element is description read book

            if (splitTask[0].equals("T")) {
                boolean isDone = isDone(splitTask[1]);
                tasks.add(new Todo(isDone, splitTask[2]));
            } else if (splitTask[0].equals("D")) {
                boolean isDone = isDone(splitTask[1]);
                String organisedDescription = splitTask[2].replaceAll("\\(","").replaceAll("\\)","");
                String[] splitDescription = organisedDescription.split("by:");
                tasks.add(new Deadline(isDone, splitDescription[0], splitDescription[1]));
            } else if (splitTask[0].equals("E")) {
                boolean isDone = isDone(splitTask[1]);
                String organisedDescription = splitTask[2].replaceAll("\\(","").replaceAll("\\)","");
                String[] splitDescription = organisedDescription.split("at:");
                tasks.add(new Event(isDone, splitDescription[0], splitDescription[1]));
            } else {
                System.out.println("error in reading input");
            }
        }
        return tasks;
    }

//    public static void main(String[] args) {
//        try {
//            readStorageTasks();
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }
//    }

}