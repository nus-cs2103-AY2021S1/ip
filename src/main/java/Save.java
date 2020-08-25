import  java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    private final Path path;

     public Save(String filePath) throws IOException {
//         try {
//             String dir = System.getProperty("user.dir");
//             path = Path.of(dir, filePath);
//             if (Files.notExists(path)) {
//                 File createDirectory = new File(String.valueOf(path));
//                 //createDirectory.createNewFile();
//             }
//         } catch (IOException ex) {
//             System.err.println("Error in creating duke.txt");
//         }
         String dir = System.getProperty("user.dir");
         path = Path.of(dir, filePath);
         File file = new File(String.valueOf(path));
         if (!file.exists()) {
             file.getParentFile().mkdirs();
         } try {
             file.createNewFile();
         } catch (IOException ex) {
             System.err.println("Error in creating duke.txt");
         }
     }

     public void writeToFile(ArrayList<Task> tasks) throws IOException {
         try {
             FileWriter fileWriter = new FileWriter(path.toString());
             for (Task task : tasks) {
                 String isDone = task.isDone ? "done" : "undone";
                 String description = task.getTask();
                 Type taskType = task.getType(); //null
                 String stringTaskType = taskType.toString();
                 switch (taskType) {
                     case TODO:
                         String writeTodo = String.format("%s | %s | %s\n", stringTaskType, isDone, description);
                         fileWriter.write(writeTodo);
                         break;
                     case DEADLINE:
                     case EVENT:
                         String dnt = task.getTime();
                         String write = String.format("%s | %s | %s | %s\n", stringTaskType, isDone,
                                 description, dnt);
                         fileWriter.write(write);
                         break;
                 }
             }
             fileWriter.close();
         } catch (IOException ex) {
             System.err.println(ex.getMessage());
         }
     }

    public ArrayList<Task> read() throws FileNotFoundException {
         ArrayList<Task> tasks = new ArrayList<>();
         try {
             File f = path.toFile();
             Scanner scanner = new Scanner(f);
             while (scanner.hasNext()) {
                 String input = scanner.nextLine();
                 String[] splitString = input.split(" \\| ");
                 boolean check = splitString[1].equals("done");
                 switch (splitString[0]) {
                     case "ToDo":
                         Task td = new ToDo(splitString[2], check);
                         tasks.add(td);
                         break;
                     case "Deadline":
                         Task dl = new Deadline(splitString[2], splitString[3], check);
                         tasks.add(dl);
                         break;
                     case "Event":
                         Task ev = new Event(splitString[2], splitString[3], check);
                         tasks.add(ev);
                         break;
                 }
             }
         } catch (FileNotFoundException ex) {
             System.err.print(ex.getMessage());
         }
         return tasks;
    }
}
