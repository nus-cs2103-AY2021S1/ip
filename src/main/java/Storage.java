import java.io.*;

public class Storage {

    public String path;

    public Storage(String path) {
        this.path = path;
    }

    public void saveTasks() throws IOException {
        BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
        String tasks = "";
        for (Task task: TaskList.taskList) {
            tasks += task.toSaveString() + "\n";
        }
        taskWriter.write(tasks);
        taskWriter.close();
    }

    public void handleLoad() throws IOException {
        BufferedReader taskLoader = new BufferedReader(new FileReader(path));
        String longCommand = taskLoader.readLine();
        while (longCommand != null) {
            String[] keywords = longCommand.split(" \\|\\| ");
            Task cur = null;
            switch (keywords[1]) {
                case "todo":
                    cur = new Todo(keywords[2]);
                    break;
                case "deadline":
                    cur = new Deadline(keywords[2], keywords[3]);
                    break;
                case "event":
                    cur = new Event(keywords[2], keywords[3]);
                    break;
                default:
                    System.out.println("error");
                    break;
            }

            if (keywords[0].equals("1")) {
                cur.markAsDone();
            }
            TaskList.taskList.add(cur);
            longCommand = taskLoader.readLine();
        }
        taskLoader.close();
    }
}
