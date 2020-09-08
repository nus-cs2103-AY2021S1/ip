import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

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

    static ArrayList<Task> readFile(){
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
