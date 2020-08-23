import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    BufferedReader reader;
    PrintWriter printWriter;
    ArrayList<Task> list;

    public Storage(String filepath) throws IOException {
        try{
            this.reader = new BufferedReader(new FileReader(filepath));
            this.printWriter = new PrintWriter(filepath);
        } catch (FileNotFoundException e) {
            String directoryName = "/data";
            String fileName = "duke.txt";
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directoryName + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.reader = new BufferedReader(new FileReader("data/duke.txt"));
            this.printWriter = new PrintWriter("data/duke.txt");
        }
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        // returns the string list from the txt file
        ArrayList<Task> list = new ArrayList<>();
        String line;
        System.out.println("bbb");

        while ((line = this.reader.readLine()) != null) {
            System.out.println("xxx");
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
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that line means.");
            }
        }
        this.list = list;
        return list;
    }

    public void update(ArrayList<Task> list) {
        this.list = list;
        StringBuilder listOutput = new StringBuilder();
        for (int j = 0; j < list.size(); j++) {
            int num = j + 1;
            Task task = list.get(j);
            listOutput.append(num + "." + task.toString() + "\n");
        }
        String text = listOutput.toString();
        this.printWriter.println(text);
        this.printWriter.close();
    }

}
