package main.java;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public final String FILE_PATH;
    private File data;

    Storage(String filepath){
        this.FILE_PATH = filepath;
        String[] tokens = filepath.split("/");
        int pathLength = tokens.length;
        if (pathLength == 0){
            System.out.println(UI.errorBox("Invalid file path"));
        } else {
            String path = tokens[0];
            for (int i = 1; i < pathLength - 1; i++){
                File directory = new File(path);
                if (!directory.exists()){
                    directory.mkdir();
                }
                path += "/" + tokens[i];
            }
            path += "/" + tokens[pathLength-1];
            try {
                data = new File(path);
                data.createNewFile();
            } catch (IOException e){
                System.out.println(UI.errorBox("Error occurred while loading asset."));
            }
        }
    }

    // method to load the asset into items
    public ArrayList<Task> load(){
        ArrayList<Task> items = new ArrayList<>();
        try{
            FileReader input = new FileReader(data.getAbsoluteFile());
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()){
                String data[] = scanner.nextLine().split("@",4);
                Task loadedItem;
                switch (data[0]){
                    case "T":
                        loadedItem = new ToDo(data[2]);
                        break;
                    case "D":
                        loadedItem = new Deadline(data[2], LocalDateTime.parse(data[3]));
                        break;
                    default:
                        loadedItem = new Event(data[2],data[3]);
                }
                if (data[1].equals("1")){
                    loadedItem.markAsDone();
                }
                items.add(loadedItem);
            }
            input.close();
            scanner.close();
        } catch (IOException e){
            System.out.println(UI.errorBox("Error occurred while reading asset."));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(UI.errorBox("Asset file is corrupted."));
        }
        return items;
    }

    // method to persist the items into asset
    public boolean persistData(ArrayList<Task> items){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : items) {
                String isLoaded = task.isDone() ? "1" : "0";
                if (task.getClass().isAssignableFrom(ToDo.class)) {
                    String s = "T@" + isLoaded + "@" + task.description;
                    output.write(s);
                } else if (task.getClass().isAssignableFrom(Event.class)){
                    String s = "E@" + isLoaded + "@" + task.description + "@" + ((Event) task).time;
                    output.write(s);
                } else {
                    String s = "D@" + isLoaded + "@" + task.description + "@" + ((Deadline) task).by;
                    output.write(s);
                }
                output.newLine();
            }
            output.close();
        } catch (IOException e){
            System.out.println(UI.errorBox("Error was encountered when saving list to asset."));
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
