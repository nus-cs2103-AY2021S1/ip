package duke;

import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidUpdateInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Storage deals with loading tasks from the file and saving tasks in the file.
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
        boolean fileNonExistent = !tempFile.exists();

        if (fileNonExistent) {
            tempFile.createNewFile();
        }
        this.file = tempFile;
        this.path = filePath;
    }

    /**
     * Convert file contents into a list of tasks.
     * @return Arraylist containing tasks
     * @throws FileNotFoundException
     * @throws InvalidDateTimeException
     */
    public ArrayList<Task> getList() throws FileNotFoundException, InvalidDateTimeException, InvalidUpdateInputException {
        Scanner sc = new Scanner(this.file);
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split("//");

            switch (split[0]) {
                case "T": {
                    String description = split[2];
                    ToDo todo = new ToDo(description);;
                    if (split[1].equals("1")) {
                        todo.markAsDone();
                    }
                    list.add(todo);
                    break;
                }
                case "D": {
                    String description = split[2];
                    String date = split[3];
                    String time = split[4];
                    Deadline deadline = new Deadline(description, date, time);
                    if (split[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    break;
                }
                case "E": {
                    String description = split[2];
                    String date = split[3];
                    String time = split[4];
                    Event event = new Event(description, date, time);
                    if (split[1].equals("1")) {
                        event.markAsDone();
                    }
                    list.add(event);
                    break;
                }
                default:
                    assert false;
            }
        }
        sc.close();
        return list;
    }

    /**
     * Update data in save file.
     * @param list  Arraylist containing Tasks
     * @throws IOException
     */
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
