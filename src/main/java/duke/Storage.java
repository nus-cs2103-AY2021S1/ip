package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the chat bot that can load and write data into the hard disk.
 */
public class Storage {
    /** the path to the file for saved tasks. */
    private String filePath;

    /**
     * Constructor for Storage.
     * @param filePath the file path of the saved tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks data from the hard disk.
     * @return the current task list.
     * @throws FileNotFoundException if there is no file data in the hard disk.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputs = input.split("#");
            Commands command = Commands.valueOf(inputs[0]);
            Task task;
            if (command.equals(Commands.TODO)) {
                task = new Todo(inputs[1], Boolean.parseBoolean(inputs[2]));
                if (inputs.length > 3) {
                    task.setTag(inputs[3]);
                }
            } else if (command.equals(Commands.EVENT)) {
                task = new Event(inputs[1], Boolean.parseBoolean(inputs[2]), inputs[3]);
                if (inputs.length > 4) {
                    task.setTag(inputs[4]);
                }
            } else {
                task = new Event(inputs[1], Boolean.parseBoolean(inputs[2]), inputs[3]);
                if (inputs.length > 4) {
                    task.setTag(inputs[4]);
                }
            }
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Writes tasks into the hard disk.
     * @param taskList the current task list.
     * @throws IOException if there is an error in writing the tasks into file in hard disk.
     */
    public void writeData(TaskList taskList) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        assert file.exists();
        FileWriter fw = new FileWriter(filePath);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            textToAdd.append(task.getData()).append("\n");
        }
        fw.write(textToAdd.toString());
        fw.close();
    }
}
