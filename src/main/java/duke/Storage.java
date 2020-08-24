package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    BufferedReader br;
    PrintWriter printWriter;
    ArrayList<duke.Task> list;
    String filepath;

    public Storage(String filepath) throws IOException {
        this.filepath = filepath;
        try{
            this.br = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            String directoryName = "data";
            String fileName = "tasks.txt";
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directoryName + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("aaa");
            }
            System.out.println("xxx");
            this.br = new BufferedReader(new FileReader("data/tasks.txt"));
            this.printWriter = new PrintWriter("data/tasks.txt");
        }
    }

    public ArrayList<duke.Task> loadTask() throws IOException, duke.DukeException {
        ArrayList<duke.Task> list = new ArrayList<>();
        String line = this.br.readLine();

        while (!line.isEmpty()) {
            boolean isDone = String.valueOf(line.charAt(6)).equals("\u2713");
            if (String.valueOf(line.charAt(3)).equals("T")) {
                Todo todo = new Todo(line.substring(9));
                if (isDone) {
                    todo.setDone();
                }
                list.add(todo);
            } else if (String.valueOf(line.charAt(3)).equals("D")) {
                int indexOfColon = line.indexOf(":");
                Deadline deadline = new Deadline(line.substring(9, indexOfColon-4), line.substring(indexOfColon+2));
                if (isDone) {
                    deadline.setDone();
                }
                list.add(deadline);
            } else if (String.valueOf(line.charAt(3)).equals("E")) {
                int indexOfColon = line.indexOf(":");
                Event event = new Event(line.substring(9, indexOfColon-4), line.substring(indexOfColon+2));
                if (isDone) {
                    event.setDone();
                }
                list.add(event);
            } else {
                throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that line means.");
            }
            line = this.br.readLine();
        }
        this.list = list;
        return list;
    }

    public void update(ArrayList<duke.Task> list) throws FileNotFoundException {
        this.printWriter = new PrintWriter(filepath);
        this.list = list;
        StringBuilder listOutput = new StringBuilder();
        for (int j = 0; j < list.size(); j++) {
            int num = j + 1;
            duke.Task task = list.get(j);
            listOutput.append(num + "." + task.toString() + "\n");
        }
        String text = listOutput.toString();
        this.printWriter.println(text);
        this.printWriter.close();
    }

}
