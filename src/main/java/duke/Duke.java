package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;


import java.util.Scanner;
import java.io.File;

public class Duke {
    public final static String LINE = "*********************************************************";
    public static TaskList shelf;
    public static Storage storage;
    public static Parser parser;
    public static UI ui;

    public static void main(String[] args) {
        File f = new File("D:\\24092014\\Joven\\UNI STUFF\\CS2103\\IP\\task.txt");
        welcome();
        storage = new Storage(f);
        shelf = new TaskList(storage.loadFile());
        ui = new UI(shelf, storage);
        parser = new Parser(storage, shelf, ui);
        Scanner sc = new Scanner(System.in);
        handler(sc);
        sc.close();
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
        System.out.println("Hello from " + logo);
        System.out.println("What can I do for you pal? :D");
        System.out.println(LINE);
    }

    public static void handler(Scanner sc) {
        while(true){
            String response = sc.nextLine();
            parser.listen(response);
            System.out.println(LINE);
            if(parser.toStop(response)){
                break;
            }
        }
    }
}
