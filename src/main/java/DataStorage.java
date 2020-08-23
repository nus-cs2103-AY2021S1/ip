import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {

    public ArrayList<Task> loadData() throws IOException {
        File f = new File("./src/data/duke.txt"); // create a File for the given file path
        if (f.createNewFile()) {
            System.out.println("New data created: " + f.getName());
        } else {
            System.out.println("Data already exists.");
        }

        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();

        while (s.hasNext()) {
            String[] temp = s.nextLine().split(" , ");

            if (temp[0].equals("T")) {
                tasks.add(new Todo(temp[2]));
            }
            else if (temp[0].equals("E")) {
                tasks.add(new Event(temp[2], temp[3]));
            }
            else {
                tasks.add(new Deadline(temp[2], temp[3]));
            }

            if (temp[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }

        return tasks;
    }

    public ArrayList<Task> takeData() {
        ArrayList<Task> res;
        try {
            res = loadData();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            res = new ArrayList<>();
        }
        return res;
    }

    public void convertData(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("./src/data/duke.txt");
        ArrayList<Task> tasks = taskList;
        String input = tasks.get(0).saveString();
        tasks.remove(0);

        while (!tasks.isEmpty()) {
            input = input + "\n" + tasks.get(0).saveString();
            tasks.remove(0);
        }

        fw.write(input);
        fw.close();
    }

    public void writeData(ArrayList<Task> taskList) {
        try {
            convertData(taskList);
            System.out.println("Updated your data!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
