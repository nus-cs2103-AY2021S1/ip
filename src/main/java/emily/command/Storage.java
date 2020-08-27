package main.java.emily.command;

import main.java.emily.task.Deadline;
import main.java.emily.task.Event;
import main.java.emily.task.Task;
import main.java.emily.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> readData() throws DukeException {

        ArrayList<Task> store = new ArrayList<>();

        File f = new File(filepath);
        f.getParentFile().mkdirs();
        try {
            if (f.exists()) {

                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    char type = line.charAt(0);
                    boolean isCompleted = line.charAt(1) == '1';
                    String[] temp;

                    switch (type) {
                        case 'T':
                            temp = line.split(",", 2);
                            Task t = new ToDos(temp[1]);
                            t.setFinished(isCompleted);
                            store.add(t);
                            break;
                        case 'D':
                            temp = line.split(",", 3);
                            Task d = new Deadline(temp[1], temp[2]);
                            d.setFinished(isCompleted);
                            store.add(d);
                            break;
                        case 'E':
                            temp = line.split(",", 3);
                            Task e = new Event(temp[1], temp[2]);
                            e.setFinished(isCompleted);
                            store.add(e);
                            break;

                    }
                }

            } else {
                f.createNewFile();
            }
            return store;
        } catch (FileNotFoundException e) {
            throw new DukeException("file is not found");
        } catch (IOException e) {
            throw new DukeException("file already existed");
        }

    }

    public void writeToFile(String filePath, String updatedText) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(updatedText);
        fw.close();
    }

    public void reWrite(ArrayList<Task> store) throws DukeException{
        try {
            FileWriter fw = new FileWriter(this.filepath);
            String txt = "";

            for (int i = 0; i < store.size(); i++) {
                String temp = "";
                Task current = store.get(i);
                if (current instanceof ToDos) {
                    temp = "T" + (current.isFinished() ? "1" : "0") + "," + current.getDescription();
                } else if (current instanceof Deadline) {
                    temp = "D" + (current.isFinished() ? "1" : "0") + "," + current.getDescription() + "," + ((Deadline) current).getBy();
                } else if (current instanceof Event) {
                    temp = "E" + (current.isFinished() ? "1" : "0") + "," + current.getDescription() + "," + ((Event) current).getAt();
                }

                txt = txt + temp + System.lineSeparator();
            }

            fw.write(txt);
            fw.close();
        } catch(IOException e){
            throw new DukeException("invalid file");
        }
    }

}
