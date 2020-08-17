import java.util.Scanner;

public class Duke {
    private String[] list;
    private boolean running;
    private int ptr;
    private final String linebreaker;


    public Duke(String linebreaker){
        this.list = new String[100];
        this.running = true;
        this.ptr = 0;
        this.linebreaker = new String(new char[50]).replace("\0", linebreaker)+"\n";
    }

    /**
     * Parses the current list and prints the output
     */
    public void parseoutput() {
        StringBuilder sb = new StringBuilder(linebreaker);
        for (int i = 0; i< this.ptr; i++){
            sb.append("\t"+(i+1)+". ");
            sb.append(this.list[i]+"\n");
        }
        sb.append(linebreaker);
        System.out.println(sb.toString());
    }

    /**
     * Manages all internal dataflow from Main or textual interaction
     * with the chatbot
     * @param input
     */
    public void takeInput(String input) {
        if (input.equals("bye")) this.running = false;
        else if (input.equals("list")) this.parseoutput();
        else {
            this.list[this.ptr] = input;
            this.ptr++;
            this.echo(input);

        }
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
