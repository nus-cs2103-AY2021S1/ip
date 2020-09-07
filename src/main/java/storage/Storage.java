package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import command.Command;
import mugexception.MugException;
import validator.Validator;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;


/**
 * Operations on local file.
 */
public class Storage {

    /** File's path */
    private final String filepath;

    /**
     * Constructs a Storage object(file) at given path.
     *
     * @param filepath File's path
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        File mainFile = new File(filepath);
        File undoFile = new File("undo.txt");
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

            while (br.ready()) {
                line = br.readLine();
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

        } catch (IOException | MugException ex) {
            System.out.println("WARNING: " + ex.getMessage() + " :WARNING");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("WARNING: There is Something wrong with your Storage :WARNING");
        }
        return taskList;
    }

    /**
     * Adds Task to local file.
     *
     * @param command User command.
     * @param info Task description.
     * @throws MugException When MugException cause by other method.
     */
    public void appendTask(Command command, String info) throws MugException {
        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            switch (command) {
            case TODO:
                pw.println("TODO|0|" + info);
                break;
            case DEADLINE:
                String[] deadlineInfo = info.split(" /by ");
                // Validate info
                Validator.input(command, deadlineInfo.length, true);
                Validator.info(command, deadlineInfo[1], true);
                // info
                String deadlineEvent = deadlineInfo[0];
                LocalDate deadlineTime = Validator.date(deadlineInfo[1]);
                pw.println("DEADLINE|0|" + deadlineEvent + "|" + deadlineTime);
                break;
            case EVENT:
                String[] eventInfo = info.split(" /at ");
                // Validate info
                Validator.input(command, eventInfo.length, true);
                Validator.info(command, eventInfo[1], true);
                // info
                String eventEvent = eventInfo[0];
                LocalDate eventTime = Validator.date(eventInfo[1]);
                pw.println("EVENT|0|" + eventEvent + "|" + eventTime);
                break;
            default:
                break;
            }
            pw.flush();
            pw.close();
            FileReader fr = new FileReader(this.filepath);
            BufferedReader br = new BufferedReader(fr);
            int lineNum = 0;
            while (br.ready()) {
                br.readLine();
                lineNum++;
            }
            writeUndoRecord(Action.ADD, "", lineNum);
        } catch (MugException ex) {
            throw new MugException(ex.getMessage());
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to add the Tasks.Task :_:");
        }
    }

    /**
     * Deletes Task from local file.
     *
     * @param taskId Task index
     * @throws MugException When MugException cause by other method.
     */
    public void deleteTask(int taskId) throws MugException {
        String tempFile = "temp.txt";
        File oldFile = new File(this.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 0;
        String line;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(this.filepath);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                line = br.readLine();
                taskTrack++;
                if (taskTrack != taskId) {
                    pw.println(line);
                } else {
                    writeUndoRecord(Action.DELETE, line, taskId);
                }
            }

            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File renameFile = new File(this.filepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to delete the Tasks.Task :_:");
        }
    }

    /**
     * Marks Task done in local file.
     *
     * @param taskId Task index.
     * @throws MugException When MugException cause by other method.
     */
    public void doneTask(int taskId) throws MugException {
        String tempFile = "temp.txt";
        File oldFile = new File(this.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 0;
        String line;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(this.filepath);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                line = br.readLine();
                taskTrack++;
                if (taskTrack != taskId) {
                    pw.println(line);
                } else {
                    writeUndoRecord(Action.DONE, line, taskId);
                    String[] newLine = line.split("[|]", 3);
                    pw.println(newLine[0] + "|" + 1 + "|" + newLine[2]);
                }
            }

            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File renameFile = new File(this.filepath);
            newFile.renameTo(renameFile);
        } catch (FileNotFoundException e) {
            throw new MugException("File not found");
        } catch (IOException e) {
            throw new MugException("Something went wrong");
        }
    }

    /**
     * Record the action.
     * @param task command given to Mug.
     * @throws MugException If fail to access undo.txt.
     */
    private void writeUndoRecord(Action task, String info, int taskId) throws MugException {
        String tempFile = "undoTemp.txt";
        File oldFile = new File("undo.txt");
        File newFile = new File(tempFile);

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            // read undo.txt
            FileReader fr = new FileReader("undo.txt");
            BufferedReader br = new BufferedReader(fr);
            pw.println(task);
            pw.println(taskId);
            pw.println(info);
            while (br.ready()) {
                pw.println(br.readLine());
            }
            //close reader and writer
            br.close();
            pw.flush();
            pw.close();
            //rename
            oldFile.delete();
            File renameFile = new File("undo.txt");
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to record task :_:");
        }
    }
}
