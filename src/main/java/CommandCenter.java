import java.util.ArrayList;

public class CommandCenter {

    public static ArrayList<Command> commandList= new ArrayList<>();

    CommandCenter(){

    }

    public static void addCommand(Command command){
        commandList.add(command);
    }
}
