import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all issues related to File.txt.
 */
public class Storage {
    public static String filePath;
    public static File f;
    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    /**
     * Loads the information written on File.txt.
     *
     * @param f File.txt.
     * @return A list of tasks loaded from the file.
     * @throws FileNotFoundException
     */
    public static List<Task> load(File f) throws FileNotFoundException {
        List<Task> temp = new ArrayList<>();
        Scanner scFile = new Scanner(f);
        while (scFile.hasNextLine()) {
            String data = scFile.nextLine();
            Task task = parse(data);
            temp.add(task);
        }
        return temp;
    }

    private static Task parse(String data) {
        char type = data.charAt(0);
        if (type == 'T') {
            return parseTodo(data);
        } else if (type == 'E') {
            return parseEvent(data);
        } else if (type == 'D') {
            return parseDeadline(data);
        } else {
            return new Task("something went wrong, the header is not T/D/E");
        }
    }

    private static Task parseDeadline(String data) {
        //format: {D1bookhotel,tomorrow}
        boolean done;
        String description;
        String due;
        char i = data.charAt(1);
        if (i == '1') {
            done = true;
        } else {
            done = false;
        }
        int indexOfComma = data.indexOf(',');
        description = data.substring(2, indexOfComma);
        due = data.substring(indexOfComma+1);
        LocalDate ddl = LocalDate.parse(due);
        return new Deadline(done,description, ddl);
    }

    private static Task parseEvent(String data) {
        //format: {D1bookhotel,tomorrow}
        boolean done;
        String description;
        String due;
        char i = data.charAt(1);
        if (i == '1') {
            done = true;
        } else {
            done = false;
        }
        int indexOfComma = data.indexOf(',');
        description = data.substring(2, indexOfComma);
        due = data.substring(indexOfComma+1);
        LocalDate ddl = LocalDate.parse(due);
        return new Event(done, description, ddl);
    }

    private static Task parseTodo(String data) {
        //format: {D1bookhotel,tomorrow}
        boolean done;
        String description;
        String due;
        char i = data.charAt(1);
        if (i == '1') {
            done = true;
        } else {
            done = false;
        }
        description = data.substring(2);
        return new Todo(done, description);
    }

    /**
     * Writes tasks in the tasklist onto the file.
     *
     * @param list The tasklist temporarily storing all tasks.
     * @throws IOException
     */
    public static void writeData(List<Task> list) throws IOException {
        f.deleteOnExit();//delete the old file
        File file = new File(filePath);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        for (Task task: list) {
            String temp = Parser.unparse(task);//convert Task into String
            fw.write(temp + System.lineSeparator());
        }
        fw.close();
    }
}
