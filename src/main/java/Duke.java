import java.util.Scanner;
import java.io.File;

public class Duke {
    TaskList tasks;
    
    public Duke() {
        this.tasks = new TaskList();
    }

    public void op() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        
        while (!end) {
            String input = Ui.get_input(sc);
            String output_msg = "";
            if (Parser.isBye(input)) {
                end = true;
            } else if (Parser.isList(input)) {
                output_msg = tasks.summarize();
            } else if (Parser.isDone(input)) {
                output_msg = tasks.mark_done(Parser.getIndex(input)); 
            } else if (Parser.isDelete(input)) {
                output_msg = tasks.deleteTask(Parser.getIndex(input)); 
            } else if (Parser.isFind(input)) {
                output_msg = tasks.findTasksWith(Parser.getKeyword(input));
            } else {
                Task taskInput;
                try {
                    taskInput = Parser.parseTask(input); // catch duke exception from getTask(input)
                } catch (Exception e) {
                    System.out.println(Formatter.format_response(e.getMessage()));
                    continue;
                }
                output_msg = tasks.addTask(taskInput);
            }
            if (!end) {
                FormatPrinter.print(output_msg);
            }
        }
        System.out.println(Formatter.format_response("Bye. Hope to see you again soon!\n"));
        sc.close();
    }
    
    public static void main(String[] args) {
        String logo =
"   __ _____   __  ___  ___  ___  ___\n" +
"  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n" +
" / _  / __ |/ /__\\_, / // / // / // /\n" +
"/_//_/_/ |_/____/___/\\___/\\___/\\___/\n";
                                     
        // Intro message
        System.out.println(logo);
        FormatPrinter.print(
            "Hello! I'm Hal9000\nWhat can I do for you?\n"
        );

        Duke hal9000 = new Duke();
        File prev_tasks = FileOpener.openFile("prev_tasks.txt");
        TaskLoader.load_tasks(prev_tasks, hal9000.tasks);
        hal9000.op();
        TaskStorage.save_task(prev_tasks, hal9000.tasks);
    }
}
