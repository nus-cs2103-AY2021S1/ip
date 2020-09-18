package mattbot.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import mattbot.datetime.DateTimeManager;
import mattbot.errors.ErrorExceptions;
import mattbot.tasks.Deadline;
import mattbot.tasks.Event;
import mattbot.tasks.Task;
import mattbot.tasks.TaskManager;
import mattbot.tasks.Todo;




/**
 * Represents a manager that handles all actions related to File.
 */
public class FileManager {
    /**
     * Adds a new task into the save file.
     *
     * @param location file directory of the local save.
     * @param text text to add into file.
     * @throws IOException missing file.
     */
    public static void add(String location, String text) throws IOException {
        assert location.equals("") == false;
        FileWriter f = new FileWriter(location, true);
        f.write(text);
        f.write(System.lineSeparator());
        f.close();
    }

    /**
     * Rewrites the current save file based on the list of tasks.
     *
     * @param location file directory.
     * @param store ArrayList that holds all the task.
     * @throws IOException error when editing the file.
     */
    public static void edit(String location, ArrayList<Task> store) throws IOException {
        assert location.equals("") == false;
        FileWriter fw = new FileWriter(location);
        for (Task i : store) {
            fw.append(TaskManager.read(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns the name of the task in the file.
     *
     * @param s text line from file.
     * @return String name.
     * @throws ErrorExceptions error reading the text.
     */
    private static String getName(String s) throws ErrorExceptions {
        assert s.equals("") == false;
        Scanner sc = new Scanner(s);
        String name = "";
        try {
            sc.next(); // skip the symbols
            String current = sc.next();
            while (current.charAt(0) != '(') {
                name = name + current + " ";
                current = sc.next();
            }
        } catch (NoSuchElementException e) {
            // Do nothing
        }
        return name;
    }

    /**
     * Returns the date and time of the line in the file.
     *
     * @param s text.
     * @return String date and time in String.
     * @throws ErrorExceptions error reading the date and time.
     */
    private static String getDate(String s) throws ErrorExceptions {
        assert s.equals("") == false;
        Scanner sc = new Scanner(s);
        String date = "";
        try {
            String current = sc.next();
            while (current.charAt(0) != '(') {
                current = sc.next();
            }
            current = sc.next();
            while (current.charAt(current.length() - 1) != ')') {
                date = date + current + " ";
                current = sc.next();
            }
            int l = current.length() - 1;
            String last = "";
            for (int i = 0; i < l; i++) {
                last = last + current.charAt(i);
            }
            date = date + last;
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        LocalDateTime dt = LocalDateTime.parse(date, d);
        return dt.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm"));
    }

    /**
     * Returns the type of task in the file.
     *
     * @param s text line.
     * @return int type of task.
     * @throws ErrorExceptions error reading the type in the file.
     */
    private static int getType(String s) throws ErrorExceptions {
        assert s.equals("") == false;
        Scanner sc = new Scanner(s);
        try {
            String current = sc.next();
            char type = current.charAt(1);
            if (type == 'T') {
                return 1;
            } else if (type == 'D') {
                return 2;
            } else if (type == 'E') {
                return 3;
            } else {
                throw new ErrorExceptions("Error: Wrong item type detected, file might be corrupted!"
                        + System.lineSeparator() + type);
            }
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("Failed to load saved file (Type)" + System.lineSeparator()
                    + e);
        }
    }

    /**
     * Returns whether the task is completed.
     *
     * @param s text line.
     * @return boolean completed or not completed.
     * @throws ErrorExceptions error when reading the file.
     */
    private static boolean getDone(String s) throws ErrorExceptions {
        assert s.equals("") == false;
        Scanner sc = new Scanner(s);
        try {
            String current = sc.next();
            char done = current.charAt(4);
            String d = "";
            d = d + done;
            if (d.equals("O")) {
                return true;
            } else if (d.equals("X")) {
                return false;
            } else {
                throw new ErrorExceptions("Error: Cannot determine if item is done, file might be corrupted!");
            }
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("Failed to load saved file (Completed?)" + System.lineSeparator()
                    + e);
        }
    }

    /**
     * Reads the local save file and convert into task to add to list.
     *
     * @param f saved file.
     * @param store ArrayList to store the tasks.
     */
    public static void read(File f, ArrayList<Task> store) {
        assert f.exists();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String current = sc.nextLine();
                try {
                    int type = FileManager.getType(current);
                    String name = FileManager.getName(current);
                    boolean done = FileManager.getDone(current);
                    if (type == 1) {
                        Todo t = new Todo(name, "[T]");
                        if (done) {
                            t.setDone();
                        }
                        store.add(t);
                    } else if (type == 2) {
                        try {
                            String date = FileManager.getDate(current);
                            Deadline d = new Deadline(name, "[D]");
                            if (done) {
                                d.setDone();
                            }
                            DateTimeManager.addDate(d, date);
                            store.add(d);
                        } catch (ErrorExceptions e) {
                            System.out.println(e);
                        }
                    } else {
                        try {
                            String date = FileManager.getDate(current);
                            Event e = new Event(name, "[E]");
                            if (done) {
                                e.setDone();
                            }
                            DateTimeManager.addDate(e, date);
                            store.add(e);
                        } catch (ErrorExceptions e) {
                            System.out.println(e);
                        }
                    }
                } catch (ErrorExceptions e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }
    }
}
