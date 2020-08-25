package king;

import tasks.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public final String FILE_PATH;
    private File data;

    /**
     * Creates the directories and the file given the filepath
     * if file does not exists. Then, returns Storage which handles
     * the manipulation of asset in the filepath.
     *
     * @param filepath file path for an existing asset or to initialise a new asset.
     *
     */
    Storage(String filepath){
        this.FILE_PATH = filepath;
        String[] tokens = filepath.split("/");
        int pathLength = tokens.length;
        if (pathLength == 0){
            System.out.println(UI.errorBox("Invalid file path"));
        } else {
            String path = tokens[0];
            for (int i = 1; i < pathLength; i++){
                File directory = new File(path);
                if (!directory.exists()){
                    directory.mkdir();
                }
                path += "/" + tokens[i];
            }
            try {
                data = new File(path);
                data.createNewFile();
            } catch (IOException e){
                System.out.println(UI.errorBox("Error occurred while loading asset."));
            }
        }
    }

    /**
     * Storage read the asset in the filepath and loads the
     * Tasks in the asset into an ArrayList.
     *
     * @return ArrayList<Task>
     */
    public ArrayList<Task> load(){
        ArrayList<Task> items = new ArrayList<>();
        try{
            FileReader input = new FileReader(data.getAbsoluteFile());
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()){
                items.add(dataToTask(scanner.nextLine()));
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

    /**
     * Reads the tasks in the taskList and updates the tasks in the
     * filepath.
     * @param taskList
     * @return boolean returns true if taskList is successfully persisted.
     */
    public boolean persistTaskList(TaskList taskList){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(FILE_PATH));
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String isLoaded = task.isDone() ? "1" : "0";
                if (task.getClass().isAssignableFrom(ToDo.class)) {
                    String s = "T@" + isLoaded + "@" + task.getDescription();
                    output.write(s);
                } else if (task.getClass().isAssignableFrom(Event.class)){
                    String s = "E@" + isLoaded + "@" + task.getDescription() + "@" + ((Event) task).getTime();
                    output.write(s);
                } else {
                    String s = "D@" + isLoaded + "@" + task.getDescription() + "@" + ((Deadline) task).getBy();
                    output.write(s);
                }
                output.newLine();
            }
            output.close();
        } catch (IOException e){
            System.out.println(UI.errorBox("Error was encountered when saving list to asset."));
            return false;
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public TaskList find(String keyword){
        TaskList tasksFound = new TaskList();
        try{
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()){
                String item = scanner.nextLine();
                if (item.contains(keyword)){
                    tasksFound.add(dataToTask(item));
                }
            }
        } catch (IOException e){
            System.out.println(UI.errorBox("Error was encountered when reading asset file."));
        }
        return tasksFound;
    }

    // data to Task
    private Task dataToTask(String data){
        String dataTokens[] = data.split("@",4);
        Task task;
        switch (dataTokens[0]){
            case "T":
                task = new ToDo(dataTokens[2]);
                break;
            case "D":
                task = new Deadline(dataTokens[2], LocalDateTime.parse(dataTokens[3]));
                break;
            default:
                task = new Event(dataTokens[2],dataTokens[3]);
        }
        if (dataTokens[1].equals("1")){
            task.markAsDone();
        }
        return task;
    }
}
