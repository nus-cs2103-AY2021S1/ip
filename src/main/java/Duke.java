/**
 * Main class
 */

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList inputTasks;
    private Ui ui;

    /**
     * Duke constructor
     * @param filePath the file for storage of tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        inputTasks = new TaskList(storage.readFile());
    }

    /**
     * runs Duke
     */
    public void run(){
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine();
        while(!ans.equals("bye")){
            try{
                Command c = Parser.parse(ans);
                c.execute(this.inputTasks, this.storage);
            } catch (DukeException e){
                System.out.println(e.getMessage());
            } finally{
                ans = sc.nextLine();
            }
        }
        if(ans.equals("bye")) {
            ui.showGoodbye();
        }

    }

    /**
     * Start of the program
     * @param args command-line arguments for execution
     */
    public static void main(String[] args){
        new Duke("./data/duke.txt").run();
    }
}
