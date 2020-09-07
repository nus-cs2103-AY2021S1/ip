package Duke.Helpers;

import Duke.Errors.DukeException;
import Duke.Errors.FIleEmptyException;
import Duke.Errors.FileAbsentException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor assigns filePath to filePath
     * @param filePath assigns this value to variable
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Converts the string form of tasks on the file to Task objects
     *
     * @return the List of tasks containing Task instead of String
     * @throws DukeException when a file with FilePath doesnt exist.
     */
    public List<Task> load() throws DukeException {
        File f = new File(this.filePath);
        try {
            List<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(f);
            if(sc.hasNext()) {
                do{
                    String input = sc.nextLine();
                    char bool = input.charAt(4); //gives the char of 1 or 0 as it is always present at index 4
                    boolean isDone = (bool == '1'); //since 1 indicates done
                    if(input.charAt(0) == 'T'){
                        ToDo todoPresent = todoPresent(input, isDone);
                        tasks.add(todoPresent);
                    }else if(input.charAt(0) == 'E'){
                        Event eventPresent = eventPresent(input, isDone);
                        tasks.add(eventPresent);
                    }else if(input.charAt(0) == 'D'){
                        Deadline deadlinePresent = deadlinePresent(input, isDone);
                        tasks.add(deadlinePresent);
                    }else{

                    }
                } while (sc.hasNextLine());
            }
            if(tasks.size() == 0){
                throw new FIleEmptyException();
            }else {
                return tasks;
            }
        } catch (IOException error){
            throw new FileAbsentException(this.filePath);
        }
    }

    /**
     * Returns ToDo task for that present in the list in storage
     *
     * @param input string from the file in storage
     * @param isDone boolean of whether task is completed or not. True if completed and false otherwise.
     * @return ToDo task
     */
    private ToDo todoPresent(String input, boolean isDone){
        return new ToDo(input.substring(8), isDone); // since the string after index 8
    }

    /**
     * Returns Event task for that present in list in storage
     *
     * @param input string from file in storage
     * @param isDone boolean of whether task is completed or not. True if completed and false otherwise.
     * @return Event task
     */
    private Event eventPresent(String input, boolean isDone){
        String string = "";
        int index = -1;
        for(int i = 8; i < input.length(); i++){
            if(input.charAt(i) == '|'){
                index = i;
                break;
            }
            string = string + input.charAt(i); //character "|" splits the description of event and time.
        }
        String another = "";
        for(int i = index + 2; i < input.length(); i++){
            if(input.charAt(i) == '-'){
                index = i;
                break;
            }
            another = another + input.charAt(i); // character "-" separates the start and end time.
        }
        return new Event(string, isDone, another, input.substring(index + 1));
    }
    /**
     * Returns Deadline task for that present in list in storage
     *
     * @param input string from file in storage
     * @param isDone boolean of whether task is completed or not. True if completed and false otherwise.
     * @return Deadline task
     */
    private Deadline deadlinePresent(String input, boolean isDone){
        String string = "";
        int index = -1;
        for(int i = 8; i < input.length(); i++){
            if(input.charAt(i) == '|'){
                index = i;
                break;
            }
            string = string + input.charAt(i); //line '|' splits the description of deadline and time.
        }
        return new Deadline(string, isDone, input.substring(index + 2));

    }
    /**
     * gives the filePath
     * @return the value of filePath
     */
    public String getFilePath() {
        return filePath;
    }
}
