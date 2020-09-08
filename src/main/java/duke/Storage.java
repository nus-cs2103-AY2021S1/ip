package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class to save the data on the computer
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Storage {

    // file path to direct the program where to save the file
    private Path path;

    /**
     * Storage constructor to initialize a storage object which directs the path and creates a file
     * if no file exists
     *
     * @throws DukeException when the program is unable to create a new file
     */
    public Storage() throws DukeException {
        String home = System.getProperty("user.home");
        String fp = "/save.txt";
        path = Paths.get(home, fp);
        if (Files.notExists(path)) {
            File newDir = new File(path.toString());
            try {
                newDir.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Failed to create new file");
            }
        }
    }

    /**
     * saveFile method which saves the list of tasks as a file on the computer
     *
     * @param tasks takes in the list of tasks
     * @throws DukeException when the file path cannot be found
     */
    public void saveFile(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(path));
            String contents = "";
            for (Task x : tasks) {
                if (x.getClass().getSimpleName().equals("ToDo")) {
                    String temp = "ToDo\n" + x.getDone() + "\n" + x.getName() + "\n\n";
                    contents += temp;
                } else if (x.getClass().getSimpleName().equals("Deadlines")) {
                    String temp = "Deadlines\n" + x.getDone() + "\n" + x.getName() + "\n"
                            + x.getDate() + "\n" + x.getTime() + "\n\n";
                    contents += temp;
                } else {
                    String temp = "Events\n" + x.getDone() + "\n" + x.getName() + "\n"
                            + x.getDate() + "\n" + x.getTime() + "\n\n";
                    contents += temp;
                }
            }
            fw.write(contents);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File cannot be located or opened");
        }
    }

    /**
     * loadFile method which retrieves the file from the path and returns the data
     *
     * @return returns the list of tasks retrieved from the file on the computer
     * @throws DukeException when it is unable to load file from file path
     */
    public List<Task> loadFile() throws DukeException {
        try {
            Scanner sc = new Scanner(path);
            List<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                String type = sc.nextLine();
                String done = sc.nextLine();
                String name = sc.nextLine();
                if (type.equals("ToDo")) {
                    Task temp = new ToDo(name);
                    if (done.equals("true")) {
                        temp.completeTask();
                    }
                    tasks.add(temp);
                    sc.nextLine();
                } else if (type.equals("Deadlines")) {
                    String date = sc.nextLine();
                    String time = sc.nextLine();
                    assert !date.isBlank();
                    assert !time.isBlank();
                    Task temp = new Deadlines(name, date, time);
                    if (done.equals("true")) {
                        temp.completeTask();
                    }
                    tasks.add(temp);
                    sc.nextLine();
                } else {
                    String date = sc.nextLine();
                    String time = sc.nextLine();
                    assert !date.isBlank();
                    assert !time.isBlank();
                    Task temp = new Events(name, date, time);
                    if (done.equals("true")) {
                        temp.completeTask();
                    }
                    tasks.add(temp);
                    sc.nextLine();
                }
            }
            return tasks;

        } catch (IOException e) {
            throw new DukeException("Unable to find load file");
        }
    }

}



