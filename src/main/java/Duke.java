//public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
//}
import dukeclass.Parser;
import dukeclass.Storage;
import dukeclass.Task;
import dukeclass.TaskList;
import dukeclass.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Encapsulates a chat robot that you can chat with to set tasks for yourself.
 */
public class Duke {

    public static void startDuke() {
        TaskList inputList = new TaskList();

        File f = Storage.createFile();

        System.out.println(Ui.welcomeMessage());

        try {
            Storage.readFileContents(f, inputList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Loading done");


        try {
            inputList = Parser.parseCommands(inputList);

        } catch (Exception e) {
            System.out.println(Ui.unknownInputErrorMessage(e));
        }


        try {
            Storage.writeToFile(f, inputList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println(Ui.endMessage());


    }

    public static void main(String[] args) throws FileNotFoundException {

        Duke.startDuke();

    }
}
