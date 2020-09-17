package Duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private String filePath = "";
    private File taskFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        taskFile = new File(filePath);
        if (!taskFile.exists())
            taskFile.getParentFile().mkdirs();
            try {
                boolean isCreated = taskFile.createNewFile();
                assert isCreated == true : "should create a file here";
            }
            catch (IOException e2){
                System.out.println("Sorry, an error occurs while loading."+
                        " Please contact developers");
            }
    }

    public ArrayList<Task> load(){
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split("|");
                String taskType = line[0];
                Boolean taskState;
                Task t = new Task();
                if (line[1].compareTo("1")==0)
                    taskState = false;
                else
                    taskState = true;
                switch (taskType) {
                    case "T":
                        t = new TodoTask(line[2]);
                        break;
                    case "E":
                        t = new EventTask(line[2], line[3]);
                        break;
                    case "D":
                        t = new DeadlineTask(line[2], line[3]);
                        break;
                }
                taskList.add(t);
            }
            return taskList;
        } catch (FileNotFoundException e){
            System.out.println("Sorry, DUKE can't find saved todo list."+
                    " Please contact developers.");
        } catch (ParseErrorException e) {
            System.out.println("This Duke.ParseErrorException should not happen"+
                    " Please contact developers");
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(taskFile);

            for (Task t : taskList) {
                switch (t.getTaskType()) {
                    case "T":
                        if (t.checkDone())
                            fw.write("T|1|"+t.getContent());
                        else
                            fw.write("T|0|"+t.getContent());
                        break;
                    case "D":
                        if (t.checkDone())
                            fw.write("D|1|"+t.getContent()+"|"+t.getDateTime().toString());
                        else
                            fw.write("D|0|"+t.getContent()+"|"+t.getDateTime().toString());
                        break;
                    case "E":
                        if (t.checkDone())
                            fw.write("E|1|"+t.getContent()+"|"+t.getDateTime().toString());
                        else
                            fw.write("E|0|"+t.getContent()+"|"+t.getDateTime().toString());
                        break;
                    default:
                        System.out.println("Whoops, this should not happen");
                        break;
                }
                fw.write("\n");
            }

            fw.close();
        }
        catch (IOException e){
                System.out.println("Whoops, an error occurs while saving."+
                        " Please contact developers");
        }
    }
}
