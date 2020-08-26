import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Storage class handles saving and loading tasks.
 */
public class Storage {

    private final String path;
    private final File file;

    /**
     * Creates storage
     * @param filePath  Directory path of saved file.
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        File tempFile = new File(filePath);

        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        this.file = tempFile;
        this.path = filePath;
    }

    // Format of task in file is "D/0/return book/June 6th"
    // where "0" means undone while "1" means done
    public ArrayList<Task> getList() throws FileNotFoundException, InvalidDateTimeException {
        Scanner sc = new Scanner(this.file);
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split("//");

            if (split[0].equals("T")) {
                String description = split[2];
                ToDo todo = new ToDo(description);;
                if (split[1].equals("1")) {
                    todo.markAsDone();
                }
                list.add(todo);
            } else if (split[0].equals("D")) {
                String description = split[2];
                String date = split[3];
                String time = split[4];
                Deadline deadline = new Deadline(description, date, time);
                if (split[1].equals("1")) {
                    deadline.markAsDone();
                }
                list.add(deadline);
            } else if (split[0].equals("E")) {
                String description = split[2];
                String date = split[3];
                String time = split[4];
                Event event = new Event(description, date, time);
                if (split[1].equals("1")) {
                    event.markAsDone();
                }
                list.add(event);
            }
        }
        sc.close();
        return list;
    }

    public void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        String text = "";
        for (Task task : list) {
            text += task.toData() + System.lineSeparator();
        }
        fw.write(text);
        fw.close();
    }

}
