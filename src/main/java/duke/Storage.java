package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import ui.Ui;

public class Storage {

    private Scanner readSc;

    /**
     * Write the content of the list into a file
     */
    public void writeFile(TaskList tl) {
        try {
            ArrayList<Task> tasks = tl.getTaskList();
            StringBuilder replacementText = new StringBuilder();
            createFile("data/duke.txt");
            tasks.forEach(x -> {
                replacementText.append(x.toString() + "\n");
            });
            appendToFile("data/duke.txt", replacementText.toString());
        } catch (IOException e) {
            System.out.println("Folder data not found! " + e);
        }
    }

    /**
     * Adds a string to a file
     * @param filePath The file path (destination)
     * @param textToAppend The content of the file
     * @throws IOException - IOException
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Create a new file to the filepath
     * @param filePath The file path (destination)
     * @throws IOException - IOException
     */
    public void createFile(String filePath) throws IOException {
        File dataFolder = new File("data");
        if (!dataFolder.isDirectory()) {
            dataFolder.mkdir();
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Read data from a file and put it inside the list
     * @param tl TaskList object to add the tasks inside the file to the list
     * @param ui Ui object to print user display
     * @param filePath The file path (destination)
     * @throws FileNotFoundException - FileNotFoundException
     * @throws NullPointerException - NullPointerException
     */
    public String readFile(TaskList tl, Ui ui, String filePath)
            throws FileNotFoundException, NullPointerException {
        File f = new File(filePath);
        readSc = new Scanner(f);

        while (readSc.hasNextLine()) {
            String curr = readSc.nextLine();
            char taskType = curr.charAt(1);
            char taskState = curr.charAt(4);
            switch (taskType) {
            case 'T':
                try {
                    if (taskState == 'X') {
                        tl.addTodo(ui, curr.split(" ", 2)[1], false, false);
                    } else {
                        tl.addTodo(ui, curr.split(" ", 2)[1], false, true);
                    }
                } catch (InvalidTodoException e) {
                    return e.toString();
                }
                break;
            case 'D':
                try {
                    if (taskState == 'X') {
                        tl.addDeadline(ui, curr.split(" ", 2)[1], false, false);
                    } else {
                        tl.addDeadline(ui, curr.split(" ", 2)[1], false, true);
                    }
                } catch (InvalidDeadlineException e) {
                    return e.toString();
                }
                break;
            case 'E':
                try {
                    if (taskState == 'X') {
                        tl.addEvent(ui, curr.split(" ", 2)[1], false, false);
                    } else {
                        tl.addEvent(ui, curr.split(" ", 2)[1], false, true);
                    }
                } catch (InvalidEventException e) {
                    return e.toString();
                }
                break;
            default:
                return "Unknown task";
            }
        }
        return null;
    }
}
