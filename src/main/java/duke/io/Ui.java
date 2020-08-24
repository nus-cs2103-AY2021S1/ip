package duke.io;

import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Layout layout = new Layout();
    private final TaskList tasks;
    
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }
    
    private void greet() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm" + "\n" + logo + "\n\t" + "What can I do for you?";
        layout.print(greeting);
    }
    
    public void listen() {
        greet();
        
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String [] arr = sc.nextLine().split(" ");
            
            Parser parser = new Parser();
            parser.readCommands(sc, arr, tasks);
        }
    }
}
