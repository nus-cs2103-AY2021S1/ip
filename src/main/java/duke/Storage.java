package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;

import java.nio.file.Path;
import java.time.LocalDate;

import java.util.ArrayList;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    Path filepath;

    /**
     * Constructor for a new driver system.
     *
     * @param filepath  the file path of the schedule text file
     */
    public Storage(Path filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes tasks into the schedule file.
     *
     * @param task      the task that's to be written into the text file
     */
    public void write(Task task) throws IOException {

        File saveFile = filepath.toFile();
        try {
            if (!saveFile.exists()) {
                System.out.println("Save file does not exist, creating one at " + this.filepath);
                saveFile.getParentFile().mkdirs();
            }
            FileWriter todoWriter = new FileWriter(saveFile, true);
            todoWriter.write(task.splitToString());
            todoWriter.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't save to file :(");
        }
    }


        /**
         * Loads a schedule file.
         *
         * @return  a TaskList that has all the tasks in the schedule text file
         */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> todoList = new ArrayList<Task>();
        File saveFile = filepath.toFile();
        if(!saveFile.exists()) {
            return new ArrayList<Task>();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] taskArr = line.split("/");
                if (taskArr[0].equals("T")) {
                    todoList.add(new Todo(taskArr[2], taskArr[1]));
                } else if (taskArr[0].equals("E")) {
                    todoList.add(new Event(taskArr[1], taskArr[2], LocalDate.parse(taskArr[3])));
                } else if (taskArr[0].equals("D")) {
                    todoList.add(new Deadline(taskArr[1], taskArr[2], LocalDate.parse(taskArr[3])));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new DukeException("error! i couldn't read the data file correctly, please do a system check!");
        }

        return todoList;
    }

    /**
     * Writes tasks into the schedule file.
     *
     * @param tl      the TaskList that's to be over written into the text file
     */
    public void overwrite(TaskList tl) throws IOException {
        File saveFile = filepath.toFile();
        FileWriter todoWriter = new FileWriter(saveFile, false);
        for (Task task: tl.todoList) {
            todoWriter.write(task.splitToString());
        }
        todoWriter.close();
    }

}
