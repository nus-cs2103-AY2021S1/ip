package Logic.Storage;

import Logic.Exceptions.DukeException;
import Logic.Tasks.Task;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath = "./src/main/java/Logic/Data/data.txt";
    private String folderPath = "./src/main/java/Logic/Data";

    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;
    }

    public void makeFolder() throws DukeException{
        File savedFolder = new File(folderPath);
        savedFolder.mkdir();
        System.out.println("creating data directory");
        this.makeFile();
    }

    public void makeFile() throws DukeException{
        File savedFile = new File(filePath);
        try{
            savedFile.createNewFile();
            System.out.println("creating data file");
        } catch (IOException e) {
            throw new DukeException("   Error creating data storage file for duke");
        }
    }

    public ArrayList<String> Load() throws DukeException {
        File savedFolder = new File(folderPath);
        File savedFile = new File(filePath);

        if(savedFolder.exists()){
            if (savedFile.exists()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(savedFile));
                    String storedTask = "";
                    ArrayList<String> listOfTasks = new ArrayList<>();

                    while ((storedTask = bufferedReader.readLine()) != null) {
                        listOfTasks.add(storedTask);
                    }
                    bufferedReader.close();
                    return listOfTasks;

                } catch (FileNotFoundException e) {
                    throw new DukeException("   File not found!");
                } catch (IOException e) {
                    throw new DukeException("   IOException encountered in loading data file.");
                }

            } else {
                this.makeFile();
                return new ArrayList<String>();
            }
        } else {
            this.makeFolder();
            return new ArrayList<String>();
        }
    }

    public void Save(ArrayList<String> listOfTasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (String task : listOfTasks) {
                writer.write(task + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("   Error writing to data file");
        }
    }
}
