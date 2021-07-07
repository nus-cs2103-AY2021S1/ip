package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Creates a storage container that handles the storage of the list of tasks.
 */
public class Storage {
    protected String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Updates the list of tasks in the storage.
     *
     * @param list The list of tasks to be updated.
     * @throws DukeException If the file cannot be written.
     */
    protected void updateList(ArrayList<Task> list) throws DukeException {
        Task thisTask;
        assert !list.isEmpty() : "list is empty";
        try {
            FileWriter initialFileWriter = new FileWriter(filepath);
            FileWriter subsequentFileWriter = new FileWriter(filepath, true);
            thisTask = list.get(0);
            initialFileWriter.write("     " + 1 + "." + thisTask.toString() + System.lineSeparator());
            for (int i = 2; i <= list.size(); i++) {
                thisTask = list.get(i - 1);
                subsequentFileWriter.write("     " + i + "." + thisTask.toString() + System.lineSeparator());
            }
            initialFileWriter.close();
            subsequentFileWriter.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    /**
     * Returns the list of tasks contained in the storage in the form of arraylist.
     *
     * @return the arraylist of tasks
     */
    protected ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        String thisLine;
        char identifier;
        boolean status;

        try {
            if (!(new File(filepath).exists())) {
                throw new DukeException("There is not such file.");
            } else {
                File f = new File(filepath);
                try {
                    Scanner s = new Scanner(f);
                    while (s.hasNext()) {
                        thisLine = s.nextLine();
                        identifier = thisLine.charAt(8);
                        status = thisLine.charAt(11) == '\u2713';
                        if (identifier == 'D') {
                            Deadline thisDeadline = new Deadline(thisLine.substring(14, thisLine.indexOf('(') - 1),
                                    status, thisLine.substring((thisLine.indexOf(':') + 2), thisLine.indexOf(')')));
                            thisDeadline.updateDateTime();
                            list.add(thisDeadline);
                        } else if (identifier == 'E') {
                            Event thisEvent = new Event(thisLine.substring(14, thisLine.indexOf('(') - 1),
                                    status, thisLine.substring((thisLine.indexOf(':') + 2), thisLine.indexOf(')')));
                            list.add(thisEvent);
                        } else {
                            Todo thisTodo = new Todo(thisLine.substring(14), status);
                            list.add(thisTodo);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    throw new DukeException(ex.getMessage());
                }
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
