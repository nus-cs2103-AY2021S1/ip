import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static String line = "________________________________________________";
    private final String filePath;
    private final BufferedReader br;

    public Storage(String filePath){
        String filePath1;
        BufferedReader tempBr;
        try {
            tempBr = new BufferedReader(new FileReader(filePath));
            filePath1 = filePath;
        } catch (IOException e)  {
            filePath1 = filePath;
            tempBr = null;
            System.out.println(e.getMessage());
        }

        this.br = tempBr;
        this.filePath = filePath1;
    }

    public ArrayList<Task> load() throws DukeException {
        String task;
        ArrayList<Task> temp = new ArrayList<>();
        try {
            while ((task = this.br.readLine()) != null) {
                String type = task.substring(0, 1);
                String name = task.substring(8);
                int isDone = Integer.parseInt(task.substring(4, 5));
                boolean isTaskDone = isDone == 1;
                switch (type) {
                    case "T":
                        temp.add(new Todo(name, isTaskDone));
                        break;
                    case "D":
                        int indexOfLine = name.indexOf("|");
                        temp.add(new Deadline(name.substring(0, indexOfLine - 1), isTaskDone, name.substring(indexOfLine + 2)));
                        break;
                    case "E":
                        int iOL = name.indexOf("|");
                        temp.add(new Event(name.substring(0, iOL  - 1), isTaskDone, name.substring(iOL + 2)));
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        return temp;
    }

    public void putToDatabase(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            PrintWriter pw = new PrintWriter(fw);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    pw.println(task.getType() + " | " + task.isDone() + " | " + task.getName());
                } else {
                    pw.println(task.getType() + " | " + task.isDone() + " | " + task.getName() + " | " + task.getEnd());
                }
            }
            pw.close();
        } catch (IOException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }
}
