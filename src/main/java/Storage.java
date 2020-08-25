import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * class responsible for harddrive management
 */
public class Storage {

    /**
     * create directory if it doesnt exist
     * @param dirName name of desired directory
     */
    public void createDirectory(String dirName){
        File file = new File(dirName);

        if(!file.exists()){
            file.mkdir();
        }

    }

    /**
     * creating a td item from filename
     * @param fileName name of file wanted
     */
    public void createToDo(String fileName){
        File file = new File(fileName);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * create file then write object to it
     * @param myTask task to convert into .txt
     * @param todoNum what to label the file as
     */
    public void writeToFile(Task myTask,int todoNum){

        createToDo("ToDo/item"+todoNum+".txt");

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ToDo/item"+todoNum+".txt"));
            out.writeObject(myTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets instance back from a .txt file
     * @param fileDir the location of object in .txt
     * @return the java instance of object
     */
    public Task readFromFile(String fileDir){
        Task myTask = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileDir));
            myTask = (Task) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myTask;

    }

    /**
     * my (To Do) directory synced with arraylist items
     * @param myTaskList the items to copy from
     */
    public void updateDirectory(TaskList myTaskList){
        // deleting all files in directory
        File dir = new File("ToDo");
        File[] myItems = dir.listFiles();
        for (File child : myItems) {
            if(child.toString().substring(0,9).equals("ToDo/item")){
                Path path = FileSystems.getDefault().getPath(child.toString());
                try {
                    Files.delete(path);
                } catch (NoSuchFileException x) {
                    System.err.format("%s: no such" + " file or directory%n", path);
                } catch (IOException x) {
                    System.err.println(x);
                }
            }
        }

        // repopulating directory with that in arraylist taks
        for(int i = 0;i<myTaskList.getTasks().size();i++){
            writeToFile(myTaskList.getTasks().get(i),i);
        }
    }


    /**
     * method to fill up the list with harddrive data
     * @param myList the list to fill
     */
    public void populateList(TaskList myList){
        File dir = new File("ToDo");
        File[] myItems = dir.listFiles();
        for (File child : myItems) {

            if(child.toString().substring(0,9).equals("ToDo/item")){
                myList.getTasks().add(readFromFile(child.toString()));
            }
            // Do something with child
        }

    }
}
