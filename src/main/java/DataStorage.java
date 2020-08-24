import java.util.ArrayList;

public class DataStorage {

    public static ArrayList<Task> taskList = new ArrayList<>();
    public static ArrayList<Command> commandInit = new ArrayList<>();

    public static void init() throws DukeException{
        commandInit.add(new Bye());
        commandInit.add(new Clear());
        commandInit.add(new Done(new String[]{"",""}));
        commandInit.add(new Deadline("init","by","startOfEpoch"));
        commandInit.add(new Event("init","on","startOfEpoch"));
        commandInit.add(new Help(new String[]{"help"}));
        commandInit.add(new Ls());
        commandInit.add(new ToDo("init"));
    }
}
