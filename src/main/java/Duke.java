import Commands.AddInput;
import Parser.Parser;
import Storage.StorageCommands;

public class Duke {



    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Parser.lineFormatter("Hello!!!! I'm Duke\nWhat can I do for you?!?!?!" );
        StorageCommands.loadTasks();
        AddInput.add_input();

    }

}
