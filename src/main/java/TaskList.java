import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class TaskList {
    String HOME = System.getProperty("user.home");
    java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    Parser p = new Parser();

    int total;

    TaskList(int total) {
        this.total = total;
    }

    int getTotal() {
        return this.total;
    }

    String taskToString(Task t) {
        String s = t.toString();

        String type = t.getType();
        String status = t.getStatusIcon();
        String name = t.getName();
        String time = t.getTime();

        return type + " @ " + status + " @ " + name + " @ " + time;
    }

    void writeData(Task t) throws IOException {
        FileWriter fw = new FileWriter(PATH.toFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(taskToString(t));

        pw.flush();

        pw.close();
        bw.close();
        fw.close();
    }

    void updateTotal(int newTotal) throws IOException {
        File fileToBeModified = PATH.toFile();
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

    void replaceText(String prevTask, String newTask) throws IOException {
        File fileToBeModified = PATH.toFile();
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

    void deleteText(String task) throws IOException {
        File fileToBeModified = PATH.toFile();
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

    Task complete(int taskNumber) throws IOException {
        Scanner myReader = new Scanner(PATH);

        String taskData = "";
        for (int i = 0; i <= taskNumber; i++) {
            taskData = myReader.nextLine();
        }
        Task t = p.stringToTask(taskData);
        t = t.completeTask();
        replaceText(taskData, taskToString(t));

        return t;
    }

    Task delete(int taskNumber) throws IOException {
        Scanner myReader = new Scanner(PATH);
        String taskData = "";
        for (int i = 0; i <= taskNumber; i++) {
            taskData = myReader.nextLine();
        }
        Task t = p.stringToTask(taskData);

        deleteText(taskToString(t));
        total--;
        updateTotal(total);

        return t;
    }

    Task add(String s) throws IOException, IncompleteInputException {
        Task t = p.commandToTask(s);

        writeData(t);
        total++;
        updateTotal(total);

        return t;
    }
}
