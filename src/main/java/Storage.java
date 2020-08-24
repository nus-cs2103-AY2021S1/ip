package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    File storageFile;

    public Storage(String fileName) {
        File temp = new File(fileName);
        if (!temp.exists()) {
            File dir = new File(temp.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                temp.createNewFile();
                storageFile = temp;
            } catch (Exception e1) {
                e1.getStackTrace();
            }
        } else {
            storageFile = temp;
        }
    }

    public List<Task> load() {
        List<Task> result = new ArrayList<>();
        Scanner storage;
        try{
            storage = new Scanner(storageFile);

            while (storage.hasNext()) {
                String type = storage.nextLine();
                boolean status = storage.nextLine().equals("1");
                String content = storage.nextLine();
                if (type.equals("T")) {
                    result.add(new Todo(status, content));
                } else if (type.equals("E")) {
                    String time = storage.nextLine().replace(" ", "");
                    result.add(new Event(status, content, time));
                } else if (type.equals("D")) {
                    String time = storage.nextLine().replace(" ", "");
                    result.add(new Deadline(status, content, time));
                }
            }
            storage.close();
        } catch (FileNotFoundException e) {

        }
        return result;
    }

    public void save(List<Task> taskList) {
        try{
            FileWriter fileWriter = new FileWriter(storageFile);

            String data = "";
            for (int i = 0; i < taskList.size(); i++) {
                String temp = "";
                if (taskList.get(i) instanceof Todo) {
                    Todo holding = (Todo) taskList.get(i);
                    temp += "T\n";
                    temp += (holding.isDone() ? "1" : "0") + "\n";
                    temp += holding.getContent() + "\n";
                    data += temp;
                    temp = "";
                } else if (taskList.get(i) instanceof Event) {
                    Event holding = (Event) taskList.get(i);
                    temp += "E\n";
                    temp += (holding.isDone() ? "1" : "0") + "\n";
                    temp += holding.getContent() + "\n";
                    temp += holding.getTime() + "\n";
                    data += temp;
                    temp = "";
                } else if (taskList.get(i) instanceof Deadline) {
                    Deadline holding = (Deadline) taskList.get(i);
                    temp += "D\n";
                    temp += (holding.isDone() ? "1" : "0") + "\n";
                    temp += holding.getContent() + "\n";
                    temp += holding.getTime() + "\n";
                    data += temp;
                    temp = "";
                }
            }
            fileWriter.write(data);

            fileWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
