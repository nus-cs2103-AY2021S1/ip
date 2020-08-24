package main.java;

import main.java.Commands.Command;
import main.java.DukeException.DukeException;
import main.java.Task.Task;
import main.java.Task.TaskList;

import java.util.ArrayList;
import java.io.IOException;

public class Duke {

    public Storage storage;
    public TaskList tasklist;
    public Ui ui;

    public Duke (String filePath){
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(storage.arr);
        this.ui = new Ui();
    }

    public static void main(String[] args) throws DukeException, IOException {
        boolean isExit = false;
        Duke duke = new Duke("duke.txt");
        ArrayList<Task> arrList = duke.tasklist.list;
        Parser parser = new Parser(duke);

        duke.ui.greetingMessage();
        String userinput = "";

        while(!isExit) {
            duke.ui.prompt();
            userinput = duke.ui.readCommand();
            Command command = parser.parse(userinput,duke.tasklist);
            command.execute();
            isExit = command.isExit;
        }
        duke.storage.saveTasks(arrList);
        System.out.println("Bye. Hope to see you again soon!");
    }


}
