package Command;

import UI.UserInterface;

public class ExitCommand extends Command{
    public static void execute(){
        UserInterface.stop();
    }
}
