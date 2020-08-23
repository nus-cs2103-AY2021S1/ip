import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public static void parseAndAddToList(List<Task> list, String line) {
        String[] line_arr = line.split(" / ");
        if (line_arr[0].equals("T")) {
            if (line_arr[1].equals("1")) {
                list.add(new Todo(true, list.size() + 1, line_arr[2]));
            } else if (line_arr[1].equals("0")) {
                list.add(new Todo(false, list.size() + 1, line_arr[2]));
            }
        } else if (line_arr[0].equals("E")) {
            if (line_arr[1].equals("1")) {
                list.add(new Event(true, list.size() + 1, line_arr[2], line_arr[3]));
            } else if (line_arr[1].equals("0")) {
                list.add(new Event(false, list.size() + 1, line_arr[2], line_arr[3]));
            }
        } else if (line_arr[0].equals("D")) {
            if (line_arr[1].equals("1")) {
                list.add(new Deadline(true, list.size() + 1, line_arr[2], line_arr[3]));
            } else if (line_arr[1].equals("0")) {
                list.add(new Deadline(false, list.size() + 1, line_arr[2], line_arr[3]));
            }
        }
    }

    // reads file and returns a list with value stored
    public static void initialiseFile(List<Task> list) throws FileNotFoundException {
        try {
            File file = new File("data/duke.txt");
            Scanner fr = new Scanner(file);
            while (fr.hasNextLine()) {
                parseAndAddToList(list, fr.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("☹ OOPS!!! I can't find your file!");
        }
    }
}
