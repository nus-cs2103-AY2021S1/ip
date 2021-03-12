/**
 * Saves tasks into a file
 */

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String filePath;

    /**
     * Storage constructor
     * @param filePath the filepath to be read from and write to
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * write list of tasks into a file
     * @param lst list of tasks to be written
     */
    public static void writeToFile(TaskList lst){
        try{
            FileWriter fw = new FileWriter(filePath);
            for(Task t : lst.tasks){
                fw.write(t.write() + '\n');
            }
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * read list of tasks from a file
     * @return a list of tasks that has been read form the file
     */
    static ArrayList<Task> readFile(String filePath){
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            Scanner fileReader = new Scanner(new File(filePath));
            while(fileReader.hasNextLine()){
                String nextLine = fileReader.nextLine();
                String[] split = nextLine.split("\\|");
                switch(split[0]){
                    case "T":
                        tasks.add(new Todo(split[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(split[2], LocalDate.parse(split[3])));
                        break;
                    case "E":
                        tasks.add(new Event(split[2], LocalDate.parse(split[3])));
                }
            }
            fileReader.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return tasks;
    }
}
