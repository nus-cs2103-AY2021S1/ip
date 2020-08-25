package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeFileNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected ArrayList<Task> list;
    protected File file;
    protected String fileName;

    public Storage() {
        this.list = new ArrayList<>();
    }

    ArrayList<Task> load() throws DukeException {
        String home = System.getProperty("user.home");
        String dataPath = java.nio.file.Paths.get(home,"data").toString();
        fileName = java.nio.file.Paths.get(dataPath,"duke.txt").toString();

        File dataDirectory = new File(dataPath);
        if (dataDirectory.mkdir()) {
            createFile(fileName);
        } else {
            this.file = new File(fileName);
        }

        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] arrOfString = currentLine.split(" \\| ");
                Integer num = Integer.parseInt(arrOfString[1]);
                Boolean isDone = num.equals(1);
                String title = arrOfString[0];
                String description = arrOfString[2];
                if (title.equals("T")) {
                    this.list.add(new ToDo(description, isDone));
                } else if (title.equals("D")) {
                    this.list.add(new Deadline(description, isDone, LocalDate.parse(arrOfString[3])));
                } else if (title.equals("E")) {
                    this.list.add(new Event(description, isDone, LocalDate.parse(arrOfString[3])));
                }
            }
            return this.list;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException();
        }
    }

    void createFile(String name) throws DukeFileNotFoundException {
        File dukeFile = new File(name);
        try {
            if (dukeFile.createNewFile()) {
                System.out.println("     File created: " + dukeFile.getName());
                this.file = dukeFile;
            } else {
                System.out.println("     File already exists.");
            }
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    public void save(List<Task> list) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (Task task: list) {
                if (task instanceof ToDo) {
                    myWriter.write("T | "
                            + (task.isDone ? "1 | ": "0 | ")
                            + task.description);
                } else if (task instanceof Deadline) {
                    myWriter.write("D | "
                            + (task.isDone ? "1 | ": "0 | ")
                            + task.description + " | "
                            + ((Deadline) task).by);
                } else {
                    myWriter.write("E | "
                            + (task.isDone ? "1 | ": "0 | ")
                            + task.description + " | "
                            + ((Event) task).by);
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }
}
