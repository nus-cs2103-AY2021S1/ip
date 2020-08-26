package Command;

import Tasks.TaskManager;

public class ListCommand extends Command{
    public static void execute(){
        TaskManager.listing();
    }
}
