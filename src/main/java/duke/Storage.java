package duke;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    String filePath;
    BufferedReader reader;
    boolean fileExist = false;

    /**
     * Construct a Storage object
     * @param filePath the file path
     * @throws FileNotFoundException
     */
    public Storage(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        File outputFile = new File(filePath);
        if(outputFile.exists()){
            fileExist = true;
            reader = new BufferedReader(new FileReader(filePath));
        }
    }

    /**
     * Writes the TaskList to a text file
     * @param list the TaskList to be written to text file
     * @param fileWriter the fileWriter to write the file
     */
    public static void writeArrayList2file(TaskList list, FileWriter fileWriter){
        try{
            for(int i = 0; i < list.getSize(); i++){
                if(list.get(i) instanceof Todo){
                    Todo todo = (Todo)list.get(i);
                    fileWriter.write("T|" + (todo.isDone ? "1" : "0") + "|" + todo.getDescription() + "\n");
                } else if(list.get(i) instanceof Deadline){
                    Deadline ddl = (Deadline)list.get(i);
                    fileWriter.write("D|" + (ddl.isDone ? "1" : "0") + "|" + ddl.getDescription() +
                            "|" + ddl.getBy() + "\n");
                } else{
                    Event event = (Event)list.get(i);
                    fileWriter.write("E|" + (event.isDone ? "1" : "0") + "|" + event.getDescription() +
                            "|" + event.getAt() + "\n");
                }
            }
            fileWriter.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns the BufferedReader for ./data/duke.txt
     * @return the BufferedReader for ./data/duke.txt
     */
    public BufferedReader load(){
        return reader;
    }

    /**
     * Returns whether ./data/duke.txt exists
     * @return whether ./data/duke.txt exists
     */
    public boolean isFileExist(){
        return fileExist;
    }

    /**
     * Writes TaskList to text file
     * @param list the TaskList to be written into text file
     * @throws IOException
     */
    public void writeFile(TaskList list) throws IOException {
        if(!fileExist){
            new File("./data").mkdir();
        }
        FileWriter fileWriter = new FileWriter("./data/duke.txt");
        writeArrayList2file(list, fileWriter);
    }
}
