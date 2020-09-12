package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import command.Command;
import mugexception.MugException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import validator.Validator;


/**
 * Operations on local Storage.
 */
public class Storage {
    /** mug.txt */
    public static final String MUG_FILE = "mug.txt";
    /** undo.txt */
    public static final String UNDO_FILE = "undo.txt";

    /** File's path */
    protected final String filepath;

    /**
     * Constructs a Storage object(file) at given path.
     *
     * @param filepath File's path
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Initialize Local Storage
     */
    public void initialize() {
        File mainFile = new File(this.filepath);
        File undoFile = new File(Storage.UNDO_FILE);
        undoFile.deleteOnExit();
        try {
            if (!mainFile.exists()) {
                mainFile.createNewFile();
            }
            if (!undoFile.exists()) {
                undoFile.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("An error occurred!!");
            ex.printStackTrace();
        }
    }

    /**
     * Loads Tasks from the file to arraylist of Task.
     *
     * @return Arraylist of Task.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filepath);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] newLine = line.split("[|]");
                assert(newLine.length <= 4 && newLine.length > 0);
                Command command = Validator.command(newLine[0]);
                boolean hasDone = Integer.parseInt(newLine[1]) == 1;

                if (command == Command.TODO) {
                    taskList.add(new Todo(newLine[2], hasDone));
                } else if (command == Command.DEADLINE) {
                    LocalDate date = Validator.date(newLine[3]);
                    taskList.add(new Deadline(newLine[2], date, hasDone));
                } else if (command == Command.EVENT) {
                    LocalDate date = Validator.date(newLine[3]);
                    taskList.add(new Event(newLine[2], date, hasDone));
                }
            }
            br.close();
        } catch (IOException | MugException ex) {
            System.out.println("WARNING: " + ex.getMessage() + " :WARNING");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("WARNING: There is Something wrong with your Storage :WARNING");
        }
        return taskList;
    }

    /**
     * Count the number of line used in a text file
     *
     * @return Number of line.
     * @throws IOException If
     */
    protected int lineCounter(String filepath) throws IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        int lineNum = 0;
        String line = br.readLine();
        while (Optional.ofNullable(line).isPresent()) {
            lineNum++;
            line = br.readLine();
        }
        br.close();
        return lineNum;
    }

    /**
     * Record the action.
     * @param task command given to Mug.
     * @throws MugException If fail to access undo.txt.
     */
    protected void writeUndoRecord(Action task, String info, int taskId) throws MugException {
        String tempFile = "undoTemp.txt";
        File oldFile = new File(Storage.UNDO_FILE);
        File newFile = new File(tempFile);

        try {
            // writer
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // read undo.txt
            FileReader fr = new FileReader(Storage.UNDO_FILE);
            BufferedReader br = new BufferedReader(fr);
            pw.println(task);
            pw.println(taskId);
            pw.println(info);

            String line = br.readLine();
            while (Optional.ofNullable(line).isPresent()) {
                pw.println(line);
                line = br.readLine();
            }
            //close reader and writer
            br.close();
            pw.flush();
            pw.close();
            //rename
            oldFile.delete();
            File renameFile = new File(Storage.UNDO_FILE);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to record task :_:");
        }
    }
}
