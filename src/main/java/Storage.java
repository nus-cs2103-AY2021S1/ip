import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static boolean loadTasksFrom(String filepath, TaskList taskList) {
        File dataFile = new File(filepath);
        
        try {
            Scanner fileScanner = new Scanner(dataFile);

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                if (!nextLine.isEmpty()) {
                    String[] tokens = nextLine.split(" [|] ");

                    boolean isDone = !tokens[0].equals("0");

                    if (tokens[1].equals("Todo")) {
                        taskList.addTask(new Todo(tokens[2], isDone));
                    } else if (tokens[1].equals("Event")) {
                        taskList.addTask(new Event(tokens[2], isDone, tokens[3]));
                    } else if (tokens[1].equals("Deadline")) {
                        taskList.addTask(new Deadline(tokens[2], isDone, tokens[3]));
                    }
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            if (!dataFile.getParentFile().mkdirs()) { System.out.println("ERROR");}
        }
        
        return true;
    }
    
    public static boolean saveTasksTo(String filepath, TaskList taskList) {
        ArrayList<Task> tasksArray = taskList.getAllTasks();
        String fileOutput = "";
        
        for (Task task: tasksArray) {
            for (String string: task.serialize()) {
                fileOutput = fileOutput + string + " | ";
            }
            fileOutput = fileOutput + "\n";
        }


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(fileOutput);
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
