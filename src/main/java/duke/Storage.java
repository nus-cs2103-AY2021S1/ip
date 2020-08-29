package duke;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
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
     * @param list takes in the list of tasks
     * @throws DukeException when the file path cannot be found
     */
    public void saveFile(List<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(path));
            String contents = "";
            for (Task x : list) {
                if (x.getClass().getSimpleName().equals("ToDo")) {
                    String temp = "ToDo\n" + x.isDone + "\n" + x.getName() + "\n\n";
                    contents += temp;
                } else if (x.getClass().getSimpleName().equals("Deadlines")) {
                    String temp = "Deadlines\n" + x.isDone + "\n" + x.getName() + "\n" + x.getTime() + "\n\n";
                    contents += temp;
                } else {
                    String temp = "Events\n" + x.isDone + "\n" + x.getName() + "\n" + x.getTime() + "\n\n";
                    contents += temp;
                }
                fw.write(contents);
                fw.close();
            }
            } catch(IOException e){
                throw new DukeException("File path not found");
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
            List<Task> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                String type = sc.nextLine();
                String done = sc.nextLine();
                String name = sc.nextLine();

                if (type.equals("ToDo")) {
                    sc.nextLine();
                    Task temp = new ToDo(name);
                    if (done.equals("true")) {
                        temp.completeTask();
                    }
                    list.add(temp);
                } else if (type.equals("Deadlines")) {
                    String time = sc.nextLine();
                    sc.nextLine();
                    Task temp = new Deadlines(name, time);
                    if (done.equals("true")) {
                        temp.completeTask();
                    }
                    list.add(temp);
                } else {
                    String time = sc.nextLine();
                    sc.nextLine();
                    Task temp = new Events(name, time);
                    if (done.equals("true")) {
                        temp.completeTask();
                    }
                    list.add(temp);
                }
            }
                return list;

        } catch (IOException e) {
            throw new DukeException("Unable to find load file");
        }
    }

}



