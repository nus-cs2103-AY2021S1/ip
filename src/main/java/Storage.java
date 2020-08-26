import java.io.*;
import java.util.ArrayList;

public class Storage {

    String filePath;
    BufferedReader reader;
    boolean fileExist = false;

    public Storage(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        File outputFile = new File(filePath);
        if(outputFile.exists()){
            fileExist = true;
            reader = new BufferedReader(new FileReader(filePath));
        }
    }

    public static void writeArrayList2file(TaskList list, FileWriter fileWriter){
        try{
            for(int i = 0; i < list.getSize(); i++){
                if(list.get(i) instanceof Todo){
                    Todo todo = (Todo)list.get(i);
                    fileWriter.write("T|" + (todo.isDone ? "1" : "0") + "|" + todo.getDescription() + "\n");
                }
                else if(list.get(i) instanceof Deadline){
                    Deadline ddl = (Deadline)list.get(i);
                    fileWriter.write("D|" + (ddl.isDone ? "1" : "0") + "|" + ddl.getDescription() +
                            "|" + ddl.getBy() + "\n");
                }
                else{
                    Event event = (Event)list.get(i);
                    fileWriter.write("E|" + (event.isDone ? "1" : "0") + "|" + event.getDescription() +
                            "|" + event.getAt() + "\n");
                }
            }
            fileWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public BufferedReader load(){
        return reader;
    }

    public boolean isFileExist(){
        return fileExist;
    }

    public void writeFile(TaskList list) throws IOException {
        if(!fileExist){
            new File("./data").mkdir();
        }
        FileWriter fileWriter = new FileWriter("./data/duke.txt");
        writeArrayList2file(list, fileWriter);
    }
}
