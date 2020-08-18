import java.util.Scanner;

public class Duke {
    private Task[] list;
    private boolean running;
    private int ptr;
    private final String linebreaker;


    public Duke(String linebreaker){
        this.list = new Task[100];
        this.running = true;
        this.ptr = 0;
        this.linebreaker = linebreaker.repeat(50)+"\n";
    }

    /**
     * Parses the current list and prints the output
     */
    public void parseoutput() {
        StringBuilder sb = new StringBuilder(linebreaker);
        for (int i = 0; i< this.ptr; i++){
            sb.append("\t"+i+". "+this.list[i]+"\n");
        }
        sb.append(linebreaker);
        System.out.println(sb.toString());
    }

    /**
     * Manages all internal dataflow from Main or textual interaction
     * with the chatbot, by cleaning it
     * @param input
     */
    public void takeInput(String input) {

        String cmd = cleanInput(input);
        String[] words = cmd.split(" ");
        cmd = cmd.replace(" [sep]", "");
        System.out.println("word "+words[1]);
        if (cmd.contains("bye")) this.running = false;
        else if (cmd.contains("done")){
            checkList(words[1]);
        }
        else if (cmd.contains("list")) this.parseoutput();
        else {
            addToList(cmd);
        }
    }

    private void checkList(String index) {
        int i = Integer.parseInt(index)-1;//0 indexing
        System.out.println(index);
        this.list[i].doTask();
        System.out.print(this.linebreaker);
        System.out.println("Nice! I've marked this task as done: \n\t"+this.list[i]);
        System.out.println(this.linebreaker);
    }

    /**
     * inputs string, processes and cleans the text for the chatbot
     * via adding a ending token seperator
     * @param s
     * @return
     */
    private String cleanInput(String s){
        return s+" [sep]";
    }
    /**
     * Method that takes in any input given, echos and adds to the list
     * @param cmd
     */
    private void addToList(String cmd){
        this.list[this.ptr] = Task.makeTask(cmd);
        this.ptr++;
        this.echo(cmd);
    }

    private void echo(String s) {
        System.out.println(linebreaker+"\tadded: "+s+"\n"+linebreaker);
    }

    /**
     * Running state of the Duke Application
     * @return State of Duke running
     */
    public boolean running() {
        return this.running;
    }
}
