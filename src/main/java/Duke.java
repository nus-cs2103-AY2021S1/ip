package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        Loader loader = new Loader();
        ArrayList<Task> taskList;
        try{
            taskList = loader.load();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }

        CommandHandler commandHandler = new CommandHandler(taskList);
        commandHandler.handleCommand();
        try {
            commandHandler.updateFile();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("IOException from FileWriter!!");
            return;
        }
    }
}

