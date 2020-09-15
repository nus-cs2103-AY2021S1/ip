//package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static String path;

    static void todoToFile(Task task) throws IOException {
        FileWriter appendFile = new FileWriter(path, true);
        System.out.println(task.toStore());
        appendFile.write(task.toStore());
        appendFile.write("\n");
        appendFile.close();
    }

    File file;
    //Scanner fileSc;
    //Object to store the list
    ArrayList<Task> itemList = new ArrayList<>();

    public Storage (String path) throws IOException {
        this.path = path;
        file = new File(path);
        if (! file.exists()) {
            file.createNewFile();
        }
    }

    public void modifyWithList(ArrayList<Task> list) throws IOException {
        FileWriter clearFile = new FileWriter(path);
        clearFile.write("");
        clearFile.close();
        FileWriter appendFile = new FileWriter(path, true);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            appendFile.write(task.toStore());
            appendFile.write("\n");
        }
        appendFile.close();
    }

    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
        //load data onto itemList
        Scanner fileSc = new Scanner(file);
        itemList = new ArrayList<>();

        while (fileSc.hasNextLine()) {
            Task newItem;
            String taskString = fileSc.nextLine();
            newItem = Parser.parseFileItem(taskString);
            itemList.add(newItem);
        }
        return itemList;
    }


}
