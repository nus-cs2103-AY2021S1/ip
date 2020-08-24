import java.io.*;
import java.util.Scanner;

public class FileEditor {
    static String HOME = System.getProperty("user.home");
    static java.nio.file.Path path = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    static FileWriter fw = null;
    static BufferedWriter bw = null;
    static PrintWriter pw = null;

    static void readFile() throws IOException {
        int counter = 1;
        Scanner myReader = new Scanner(path);
        myReader.nextLine();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(counter + ". " + convertText(data));
            counter++;
        }
        System.out.println("______________________");
    }

    static String parseTask(Task t) {
        String s = t.toString();

        String type = t.getType();
        String status = t.getStatusIcon();
        String name = t.getName();
        String time = t.getTime();

        return type + " @ " + status + " @ " + name + " @ " + time;
    }

    static Task convertText(String s) {
        String[] arr = s.split(" @");
        String type = arr[0];
        String status = arr[1].substring(1);
        Boolean completed;
        String name = arr[2].substring(1);
        String time = arr[3].substring(1);

        if (status.equals("x")) {
            completed = false;
        } else {
            completed = true;
        }

        if (type.equals("todo")) {
            return new Todo(name, completed);
        } else if (type.equals("deadline")) {
            return new Deadline(name, completed, time);
        } else {
            return new Event(name, completed, time);
        }
    }

    static void writeData(Task t) throws IOException {
        fw = new FileWriter(path.toFile(), true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);

        pw.println(parseTask(t));

        pw.flush();

        pw.close();
        bw.close();
        fw.close();
    }

    static void updateTotal(int newTotal) throws IOException {
        File fileToBeModified = path.toFile();
        String newText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));

        newText = newTotal + System.lineSeparator();
        String line = reader.readLine();
        line = reader.readLine();

        while (line != null) {
            newText = newText + line + System.lineSeparator();
            line = reader.readLine();
        }

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    static void deleteText(String task) throws IOException {
        File fileToBeModified = path.toFile();
        String newText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));

        String line = reader.readLine();

        while (line != null) {
            if (line.equals(task)) {
                line = reader.readLine();
            } else {
                newText = newText + line + System.lineSeparator();
                line = reader.readLine();
            }
        }

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    static void replaceText(String prevTask, String newTask) throws IOException {
        File fileToBeModified = path.toFile();
        String oldText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));
        String line = reader.readLine();

        while (line != null) {
            oldText = oldText + line + System.lineSeparator();
            line = reader.readLine();
        }

        String newText = oldText.replaceAll(prevTask, newTask);

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }
}
