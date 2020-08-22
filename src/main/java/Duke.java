import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage(taskList);
    public static Parser parser = new Parser(taskList, storage);
    public static Ui ui = new Ui(taskList, storage, parser);


    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }



    public static void main(String[] args) {

        greet();

        storage.init();

        ui.echo();

        exit();
    }
}
