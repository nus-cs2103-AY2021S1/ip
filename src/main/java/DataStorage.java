import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * All Non-Persistent Data is stored in this class.
 * This includes all Commands at initialisation.
 * Allows for further addition of Command Classes during Runtime for future development.
 */
public class DataStorage {

    /** Stores all the non-persistent tasks in the taskList */
    public static ArrayList<Task> taskList = new ArrayList<>();

    /** Stores all the Commands at initialisation of ChatBot */
    public static ArrayList<Command> commandInit = new ArrayList<>();

    /**
     * Initialises the CommandInit Array with all the possible Commands that User can query.
     *
     * @throws DukeException For the different Commands Initialising here, will never be thrown.
     */
    public static void init() throws DukeException{
        commandInit.add(new Bye());
        commandInit.add(new Clear());
        commandInit.add(new Deadline("init","by",LocalDate.MAX,LocalTime.MIDNIGHT));
        commandInit.add(new Delete(new String[]{"",""}));
        commandInit.add(new Done(new String[]{"",""}));
        commandInit.add(new Event("init","on",LocalDate.MAX,LocalTime.MIDNIGHT));
        commandInit.add(new Help(new String[]{"help"}));
        commandInit.add(new Ls());
        commandInit.add(new Load(new String[]{"",""}));
        commandInit.add(new ToDo("init"));
        commandInit.add(new Save());
    }
}
