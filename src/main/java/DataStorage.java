import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DataStorage {

    public static ArrayList<Task> taskList = new ArrayList<>();
    public static ArrayList<Command> commandInit = new ArrayList<>();

    public static void init() throws DukeException{
        commandInit.add(new Bye());
        commandInit.add(new Clear());
        commandInit.add(new Deadline("init","by",LocalDate.MAX,LocalTime.MIDNIGHT));
        commandInit.add(new Delete(new String[]{"",""}));
        commandInit.add(new Done(new String[]{"",""}));
        commandInit.add(new Event("init","on",LocalDate.MAX,LocalTime.MIDNIGHT));
        commandInit.add(new Find("init"));
        commandInit.add(new Help(new String[]{"help"}));
        commandInit.add(new Ls());
        commandInit.add(new Load(new String[]{"",""}));
        commandInit.add(new ToDo("init"));
        commandInit.add(new Save());
    }
}
