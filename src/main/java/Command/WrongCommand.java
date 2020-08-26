package Command;

import UI.UserInterface;

public class WrongCommand extends Command {
    public static void execute(){
        UserInterface.wrongCommand();
    }
}
