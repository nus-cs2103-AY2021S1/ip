import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves Task Data in a Persistent File.
 */
public class Save extends Command{

    String DIR_PATH = "./data/";
    String FILE_NAME = "duke.txt";

    Save(){
        this.name = "save";
        this.usage = "save";
        this.description = "Saves current list into file called " +
                "'ip/data/duke.txt'. Creates file and folder if they do not exist";
    }

    private void createDirectory(){
        new File(DIR_PATH).mkdirs();
    }

    private void createFile() throws IOException{
        createDirectory();
        File dir = new File(DIR_PATH);
        if(dir.exists()){
            File saveDatafile = new File(DIR_PATH+FILE_NAME);
            saveDatafile.createNewFile();
        } else{
            throw new FileNotFoundException("The directory " + DIR_PATH +
                    " does not exist");
        }
    }

    public void writeToFile() throws IOException {
        createFile();
        File file = new File(DIR_PATH + FILE_NAME);
        if(file.exists()){
            new FileWriter(file,false).close();
            FileWriter fileWriter = new FileWriter(file, true);
            String allTasks = DataStorageInterface.getSaveRepresentation();
            System.out.println("The following tasks have been saved.\n" + allTasks);
            fileWriter.write(allTasks);
            fileWriter.close();
        } else{
            throw new FileNotFoundException("The file " + DIR_PATH +
                    FILE_NAME + " does not exist");
        }
    }

    public String response(){
        return "File saved successfully at " + DIR_PATH + FILE_NAME;
    }

}
