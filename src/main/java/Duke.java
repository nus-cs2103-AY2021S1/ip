import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Duke {

    private Storage storage;
    private TaskList inputTasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        inputTasks = new TaskList(storage.readFile());
    }

    public void run() throws DukeException {
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
        if(ans.equals("bye")){
            ui.showGoodbye();
        }

    }

    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }
}
