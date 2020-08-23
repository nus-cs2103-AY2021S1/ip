import java.io.File;
import java.io.IOException;

import java.util.List;

import java.util.Scanner;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class HandleFile {

    protected File storedData;
    protected ListOfItems list;

    public HandleFile(ListOfItems list) {
        this.storedData = new File("stored.txt");
        this.list = list;
        this.checkFile();
    }

    protected void checkFile() {
        try {
            if (!this.storedData.exists()) {
                this.storedData.createNewFile();
            } else {
                readFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void readFile() {
        try {
            Scanner sc = new Scanner(this.storedData);
            while (sc.hasNextLine()) {
                this.list.addStored(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeFile(ListOfItems listOfItems) {
        try {
            List<Task> list = listOfItems.list;

            FileWriter fw = new FileWriter(this.storedData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskText = task.toString();
                bw.write(taskText);
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
