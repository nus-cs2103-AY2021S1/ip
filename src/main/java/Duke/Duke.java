package Duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() throws IOException{
        ui = new Ui();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.drawLine();
        storage = new Storage();
        taskList = new TaskList(storage.loadFile());

    }


    public static void main(String[] args) throws  IOException{
        new Duke().bot();
    }

    public void bot() throws IOException {
        Scanner sc = new Scanner(System.in);
        try {
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String first = input.split(" ")[0];
                if (input.equals("bye")) {
                    ui.bye();
                    break;
                } else if (input.equals("list")) {
                    ui.printList(taskList.getList());
                    ui.drawLine();
                } else if (input.split(" ")[0].equals("done")) {
                    ui.doneTask(taskList.done(Integer.parseInt(input.split(" ")[1])));
                    ui.listCount(taskList.countList());
                    ui.drawLine();
                    storage.saveFile(taskList.getList());
                } else if (first.equals("todo")|| first.equals("deadline") || first.equals("event")) {
                    ui.addTask(taskList.add(input));
                    ui.listCount(taskList.countList());
                    ui.drawLine();
                    storage.saveFile(taskList.getList());
                } else if (first.equals("delete")){
                    ui.deleteTask(taskList.delete(input));
                    ui.listCount(taskList.countList());
                    ui.drawLine();
                    storage.saveFile(taskList.getList());
                } else {
                    throw new DukeException("Sorry I don't know what you mean");
                }
            }
        }
        catch (DukeException ex){
            System.out.println(ex.getMessage());
        }
    }



}

