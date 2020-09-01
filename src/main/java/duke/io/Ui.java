package duke.io;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * Manage interaction with the user.
 */
public class Ui {
    private final Layout layout;
    private final TaskList taskList;
    
    public Ui(TaskList taskList) {
        this.taskList = taskList;
        layout = new Layout();
    }
    
    public String greet() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm" + "\n" + logo + "\n\t" + "What can I do for you?";
        return layout.print(greeting);
    }

//    /**
//     * Listen to user input.
//     * Execute associated action for each command.
//     */
//    public void listen() {
//        greet();
//        
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNextLine()) {
//            String [] arr = sc.nextLine().split(" ");
//
//            taskList.readCommands(sc, arr);
//        }
//    }
}
