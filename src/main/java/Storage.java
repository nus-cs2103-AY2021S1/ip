import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage saves the list in a file so the state of the list can be preserved
 * when the program restarts.
 */
public class Storage {
    File file;
    boolean added = false;
    Storage() throws IOException {
        String dir = System.getProperty("user.dir") + "/data";
        File path = new File(dir);
        if(!path.exists()) {
            path.mkdir();
        }
        file = new File(path + "/duke.txt");
        boolean result = file.createNewFile();
        if(result) {
            System.out.println("file created " + file.getCanonicalPath());
        } else {
            System.out.println("file exists at: " + file.getCanonicalPath());
        }
    }

    /**
     * Makes the storage file empty.
     */
    void reset() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
            bufferedWriter.write("");
            added = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses the text file into tasks and return them in a list.
     */
    ArrayList<Task> load() {
        System.out.println("reading... ");
        ArrayList<Task> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] parts = line.split("</>");
                Task task;
                if (parts[0].equals("TODO") && parts.length == 3) {
                    task = new Todo(parts[2]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("DEADLINE") && parts.length == 4){
                    task = new Deadline(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("EVENT") && parts.length == 4) {
                    task = new Event(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                }
                line = bufferedReader.readLine();
            }
//            System.out.println(list);
            added = true;
        } catch (FileNotFoundException e) {
            // Exception handling
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Parses the text file into tasks and add them to the list given as parameter.
     * @param list the list for tasks to be added into
     */
    void readAll(List<Task> list) {
        if (added) {
            return;
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] parts = line.split("</>");
                Task task;
                if (parts[0].equals("TODO") && parts.length == 3) {
                    task = new Todo(parts[2]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("DEADLINE") && parts.length == 4){
                    task = new Deadline(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("EVENT") && parts.length == 4) {
                    task = new Event(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                }
                line = bufferedReader.readLine();
            }
//            System.out.println(list);
            added = true;
        } catch (FileNotFoundException e) {
            // Exception handling
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds one task to the storage file.
     * @param task the task to be added
     */
    void addTask(Task task) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            String separator = "</>";
            Task.Type type = task.getType();
            String fileContent = type.toString() + separator + task.getCompleted() + separator + task.getName();
            if (type == Task.Type.DEADLINE) {
                fileContent += separator + ((Deadline) task).getDeadline();
            } else if (type == Task.Type.EVENT) {
                fileContent += separator + ((Event) task).getTime();
            }
//            System.out.println(type);
            bufferedWriter.write(fileContent);
            bufferedWriter.newLine();
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds all the tasks in the given list to the file.
     * @param list the list containing the tasks to be added
     */
    void addAll(List<Task> list) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            for(Task t : list) {
                addTask(t);
            }
//            System.out.println(list);
        } catch (FileNotFoundException e) {
            // Exception handling
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }
}
