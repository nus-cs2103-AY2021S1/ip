import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected String path;
    protected File file;
    protected boolean isCreated;

    /**
     *  Creates a new storage with a file path.
     * @param path file path to the storage file
     */
    public Storage(String path) {
            this.path = path;
            file = new File(path);
    }

    /**
     * Saves all of the tasks in the task list into the storage file.
     * @param tasks list of tasks to be saved
     * @throws java.io.IOException possible exception when creating the file
     */
    public void saveTasks(TaskList tasks) throws java.io.IOException {
        isCreated = file.createNewFile();
        PrintWriter pw = new PrintWriter(path);
        pw.close();
        PrintWriter writer = new PrintWriter(path);
        for(int i = 0; i < tasks.count(); i++) {
            writer.write(tasks.get(i).toSave() + "\n");
        }
        writer.close();
    }

    /**
     * Loads all of the tasks stored in the file to a task list.
     * @param tasks the list that tasks will be loaded to
     * @throws java.io.IOException possible exception when creating the file
     */
    public void loadTasks(TaskList tasks) throws java.io.IOException {
        isCreated = file.createNewFile();
        int location;
        String description;
        String date;
        LocalDate time;
        Scanner reader = new Scanner(file);

        while(reader.hasNextLine()) {
            String line = reader.nextLine();
            Task task;

            switch (line.charAt(0)) {
                case 'T':
                    task = new Todo(line.substring(4));
                    if (line.charAt(2) == '1') {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                    break;

                case 'D':
                    location = line.indexOf("/");
                    date = line.substring(location + 1);
                    time = LocalDate.parse(date);
                    description = line.substring(4, location);
                    task = new Deadline(description, time);
                    if (line.charAt(2) == '1') {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                    break;

                case 'E':
                    location = line.indexOf("/");
                    date = line.substring(location + 1);
                    time = LocalDate.parse(date);
                    description = line.substring(4, location);
                    task = new Event(description, time);
                    if (line.charAt(2) == '1') {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
            }
        }

    }

}
