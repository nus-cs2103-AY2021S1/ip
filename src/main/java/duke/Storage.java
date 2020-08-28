package duke;

import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /**
     * Write the content of the list into a file
     * @param list The task list
     */
    public void writeFile(ArrayList<Task> list) {
        try {
            StringBuilder replacementText = new StringBuilder();
            createFile("data/duke.txt");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Todo) {
                    replacementText.append(list.get(i).toString() + "\n");
                } else {
                    replacementText.append(list.get(i).toString() + " on " + list.get(i).getDate() + "\n");
                }
            }
            appendToFile("data/duke.txt", replacementText.toString());
        } catch(IOException e) {
            System.out.println("Folder data not found! " + e);
        }
    }

    /**
     * Adds a string to a file
     * @param filePath The file path (destination)
     * @param textToAppend The content of the file
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Create a new file to the filepath
     * @param filePath The file path (destination)
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
     * @param readSc Scanner object to read the file content
     * @param tl TaskList object to add the tasks inside the file to the list
     * @param ui Ui object to print user display
     * @param list The task list
     * @param filePath The file path (destination)
     */
    public void readFile(Scanner readSc, TaskList tl, Ui ui, ArrayList<Task> list, String filePath)
            throws FileNotFoundException, NullPointerException {
        File f = new File(filePath);
        readSc = new Scanner(f);

        while (readSc.hasNextLine()) {
            String curr = readSc.nextLine();
            switch (curr.charAt(1)) {
                case 'T':
                    try {
                        if (curr.charAt(4) == '✗') {
                            tl.addTodo(ui, list, curr.split(" ", 2)[1], false, false);
                        } else {
                            tl.addTodo(ui, list, curr.split(" ", 2)[1], false, true);
                        }
                    } catch (InvalidTodoException e) {
                        System.out.println(e);
                    }
                    break;
                case 'D':
                    try {
                        if (curr.charAt(4) == '✗') {
                            tl.addDeadline(ui, list, curr.split(" ", 2)[1], false, false);
                        } else {
                            tl.addDeadline(ui, list, curr.split(" ", 2)[1], false, true);
                        }
                    } catch (InvalidDeadlineException e) {
                        System.out.println(e);
                    }
                    break;
                case 'E':
                    try {
                        if (curr.charAt(4) == '✗') {
                            tl.addEvent(ui, list, curr.split(" ", 2)[1], false, false);
                        } else {
                            tl.addEvent(ui, list, curr.split(" ", 2)[1], false, true);
                        }
                    } catch (InvalidEventException e) {
                        System.out.println( e);
                    }
                    break;
                default:
                    System.out.println("Unknown task");
            }
        }
    }
}
