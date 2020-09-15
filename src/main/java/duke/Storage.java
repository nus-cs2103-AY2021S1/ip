package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private ArrayList<Task> txtData;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.txtData = new ArrayList<>();
    }

    public ArrayList<Task> loadData() throws DukeException {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
                FileWriter fw = new FileWriter("data");
                fw.close();
            }
            Scanner f = new Scanner(new File(this.filePath));
            while (f.hasNext()) {
                String curr = f.nextLine();
                String[] info = curr.split(", ", 4);
                Task newTask = generateNewTask(info);
                txtData.add(newTask);
            }
            return txtData;
        } catch (FileNotFoundException ex1) {
            throw new DukeException("Saved Data not found");
        } catch (DateTimeParseException ex2) {
            throw new DukeException("Date/Time incorrect format");
        } catch (IOException ex3) {
            throw new DukeException("Something went wrong...");
        }
    }

    public Task generateNewTask(String[] info) throws DukeException {
        Task tobeAdded = new Task("");
        if (info[0].equals("T")) {
            tobeAdded = new ToDo(info[2]);
            if (info[1].equals("1")) {
                tobeAdded.markAsDone();
            }
        } else if (info[0].equals("D")) {
            tobeAdded = new Deadline(info[2], Parser.dateParser(info[3]));
            if (info[1].equals("1")) {
                tobeAdded.markAsDone();
            }
        } else if (info[0].equals("E")) {
            String[] t = info[3].split(" ", 2);
            tobeAdded = new Event(info[2], Parser.dateParser(t[0]), t[1]);
            if (info[1].equals("1")) {
                tobeAdded.markAsDone();
            }
        }
        return tobeAdded;
    }

    public void overwriteData(ArrayList<Task> data) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/data");
            String newData = "";
            for (Task k : data) {
                String textToAdd = k.storageForm();
                newData += textToAdd + System.lineSeparator();
            }
            fw.write(newData);
            fw.close();
        } catch (IOException ex1) {
            System.out.println("Something went wrong... " + ex1.getMessage());
        }
    }
}


