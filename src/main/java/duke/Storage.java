package duke;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a storage object that deals with outputting and inputting tasks from a tasks.txt file.
 */
public class Storage {

    private BufferedReader br;
    private PrintWriter printWriter;
    private ArrayList<duke.Task> list;
    private String filePath;

    public BufferedReader getBr() {
        return br;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Storage(String filePath) throws IOException {
        assert filePath != null : "FilePath should not be null";
        try{
            setFilePath(filePath);
            setBr(new BufferedReader(new FileReader(filePath)));
        } catch (FileNotFoundException e) {
            setFilePath("data/tasks.txt");
            String directoryName = "data";
            String fileName = "tasks.txt";
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directoryName + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            setBr(new BufferedReader(new FileReader("data/tasks.txt")));
            setPrintWriter(new PrintWriter("data/tasks.txt"));
        }
    }

    /**
     * Loads the tasks.txt file when the bot is run.
     *
     * @return List of tasks.
     * @throws IOException
     * @throws duke.DukeException
     */
    public ArrayList<Task> loadTask() throws IOException, DukeException {
        ArrayList<duke.Task> list = new ArrayList<>();
        String line = getBr().readLine();

        if (line == null) {
            return list;
        }

        while (!line.isEmpty()) {
            boolean isDone = String.valueOf(line.charAt(6)).equals("\u2713");

            String icon = String.valueOf(line.charAt(3));
            switch (icon) {
                case "T":
                    Todo todo = new Todo(line.substring(9));
                    if (isDone) {
                        todo.setDone();
                    }
                    list.add(todo);
                    break;
                case "D":
                    int indexOfColon = line.indexOf(":");
                    String name = line.substring(9, indexOfColon - 4);
                    String date = line.substring(indexOfColon + 2, line.length() - 1);
                    Deadline deadline = new Deadline(name, date);
                    if (isDone) {
                        deadline.setDone();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    indexOfColon = line.indexOf(":");
                    name = line.substring(9, indexOfColon - 4);
                    date = line.substring(indexOfColon + 2, line.length() - 1);
                    Event event = new Event(name, date);
                    if (isDone) {
                        event.setDone();
                    }
                    list.add(event);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that line means.");
            }
            line = this.br.readLine();
        }
        setList(list);
        return list;
    }

    /**
     * Updates the tasks.txt file whenever a task is added or removed from the task list.
     *
     * @param list Task list.
     * @throws FileNotFoundException
     */
    public void update(ArrayList<Task> list) throws FileNotFoundException {
        setPrintWriter(new PrintWriter(filePath));
        setList(list);
        StringBuilder listOutput = new StringBuilder();
        for (int j = 0; j < list.size(); j++) {
            int num = j + 1;
            duke.Task task = list.get(j);
            listOutput.append(num + "." + task.toString() + "\n");
        }
        String text = listOutput.toString();
        getPrintWriter().println(text);
        getPrintWriter().close();
    }
}
