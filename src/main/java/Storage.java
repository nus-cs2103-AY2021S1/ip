import java.io.*;
import java.util.ArrayList;

public class Storage {
    protected String filePath;
    protected ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
        loadTasks(tasks);
    }

    public void saveTasks(ArrayList<Task> stored) {
        try {
            // Creates a BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

            // Write tasks as strings into file
            for (int i = 0; i < stored.size(); i++) {
                bufferedWriter.write(stored.get(i).toString() + "\n");
            }

            // Closes the writer
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasks(ArrayList<Task> tasks) {
        BufferedReader bufferedReader = null;

        try {
            String currentLine = null;
            FileReader file = new FileReader(filePath);

            if (file.ready()) { // file exists to be read
                bufferedReader = new BufferedReader(file);

                while ((currentLine = bufferedReader.readLine()) != null) {
                    String taskType = currentLine.substring(1, 2);
                    boolean isDone = currentLine.contains("✗") ? false : true;
                    String taskDescription = currentLine.substring(7);

                    switch (taskType) {
                        case "T":
                            tasks.add(new Todo(taskDescription, isDone));
                            break;
                        case "D":
                            String deadlineDescription = taskDescription.substring(0, taskDescription.indexOf("(") - 1);
                            String deadlineBy = taskDescription.substring(taskDescription.indexOf("by:") + 4, taskDescription.indexOf(")"));
                            tasks.add(new Deadline(deadlineDescription, deadlineBy, isDone));
                            break;
                        case "E":
                            String eventDescription = taskDescription.substring(0, taskDescription.indexOf("(") - 1);
                            String eventAt = taskDescription.substring(taskDescription.indexOf("at:") + 4, taskDescription.indexOf(")"));
                            tasks.add(new Event(eventDescription, eventAt, isDone));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, an empty duke.txt file has been created for you.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

