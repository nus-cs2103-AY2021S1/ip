import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main Storage class for Ultron
 */
public class Storage {

    private final File f;

    /**
     * The Storage class
     * This writes the data to a file and retrieves it when needed
     * @param path Path to the storage file
     */
    public Storage(String path){
        //Create a new file object
        f = new File(path);

    }

    /**
     * Encodes a Task to a string
     * @param task A Task to be encoded
     * @return String The encoded String of the task
     */
    private String encode(Task task){
        /*
          @param task Task to be encoded to string
         * @return String containing the command
         */
        return String.format("%s~%s", task.getType(), task.getCommand());
    }

    /**
     * Decodes a string to a Task
     * @param string String to be decoded to a Task
     * @return task A task based on the string
     * @throws UltronException If the command or line is invalid
     **/
    private Task decode(String string) throws UltronException{


        //Split the string according to the ,
        String[] data = string.split("~");
        Command command;

        try{
            //Get the command based on the first entry
            command = Command.valueOf(data[0].toUpperCase());
        } catch (IllegalArgumentException e){
            throw new UltronException(data[0], ExceptionType.INVALID_COMMAND);
        }

        //Return the task based on the data
        return command.commandParser.apply(data[1]);

    }

    /**
     * Fetches all of the data in the storage file to an arraylist of task
     * @return taskArrayList An Arraylist containing the tasks stored
     * @throws UltronException If there is an error decoding the file or if there is an IO error
     */
    public ArrayList<Task> fetchAll() throws UltronException{

        ArrayList<Task> taskArrayList = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()){
                String nextLine = scanner.nextLine();
                taskArrayList.add(decode(nextLine));
            }
            scanner.close();
            return taskArrayList;
        } catch (FileNotFoundException e){
            return taskArrayList;
        }
    }

    public void writeAll(ArrayList<Task> taskArrayList) throws UltronException{

        //Check if the directory exist
        if(!f.exists()){
            try{
                Files.createDirectory(Path.of(this.f.getPath()));
            }catch (IOException e){
                throw new UltronException(this.f.getPath(), ExceptionType.DIRECTORY_NOT_CREATED);
            }
        }

        try {
            FileWriter fw = new FileWriter(f.getPath());
            for(Task task: taskArrayList){
                    fw.write(encode(task)+"\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new UltronException(f.getPath(), ExceptionType.IO_EXCEPTION);
        }
    }


    public static void main(String args[]){
        Storage hello = new Storage("data.txt");
        try{
            ArrayList<Task> lst = hello.fetchAll();
            lst.add(new Todo("hello"));
            lst.add(new Deadline("hello", "Tomorrow"));
            for(Task tsk: lst){
                System.out.println(tsk);
            }
            hello.writeAll(lst);
        } catch (UltronException e){
            System.out.println(e.getMessage());
        }

    }
}
