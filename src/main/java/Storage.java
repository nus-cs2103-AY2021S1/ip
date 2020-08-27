import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final String filePath;

     public Storage(String filePath){
         this.filePath = filePath;
     }

     public List<Task> load() throws DukeException, IOException {
         List<Task> taskList = new ArrayList<>();
         File file = new File(filePath);
         if(!file.exists()){
             return taskList;
         }
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line = reader.readLine();
         while(line != null){
             String[] splitLine = line.split(" \\| ");
             switch (splitLine[0]){
                 case "E":
                     taskList.add(new EventTask(splitLine[2], Boolean.parseBoolean(splitLine[1]),splitLine[3]));
                     break;
                 case "T":
                     taskList.add(new TodoTask(splitLine[2], Boolean.parseBoolean(splitLine[1])));
                     break;
                 case "D":
                     taskList.add(new DeadlineTask(splitLine[2], Boolean.parseBoolean(splitLine[1]),splitLine[3]));
                     break;
             }

             line = reader.readLine();
         }
         return taskList;
     }

     public void save(TaskList tasks) throws IOException {
         String line = "";
         File file = new File(filePath);
         if(!file.exists()){
             file.createNewFile();
         }
         BufferedWriter writer = new BufferedWriter(new FileWriter(file));
         for(Task task : tasks.getList()){
             String type = task.getType();
             switch (type){
                 case "E":
                 case "D":
                     line = type+" | "+ task.isDone()+" | " + task.getName() + " | " + task.getTime();
                     break;
                 case "T":
                     line = type+" | "+ task.isDone()+" | " + task.getName();
                     break;
             }
             writer.write(line);
             writer.newLine();
             line="";
         }
         writer.close();
     }
}
