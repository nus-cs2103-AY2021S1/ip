import java.util.Scanner;

public class Duke {
    private TaskManager list;
    private boolean running;
    public Duke(String linebreaker){
        this.list = new TaskManager(linebreaker);
        this.running = true;
    }

    /**
     * Manages all internal dataflow from Main or textual interaction
     * with the chatbot, by cleaning it
     * @param input
     */
    public void takeInput(String input) {
        String cleaned = cleanInput(input);
        String[] words = cleaned.split(" ");
        cleaned = cleaned.replace(" [sep]", "");
        // Take out command from the word
        String cmd = cleaned.replaceFirst(words[0],"");
        //Sep token is added to prevent index errors
        switch (words[0]) {
            case "bye":
                this.running = false;
                break;
            case "done":
                this.list.doTask(words[1]);
                break;
            case "list":
                this.list.parseoutput();
                break;
            default:
                switch (words[0]) {
                    case "todo":
                        this.list.addToDo(cmd);
                        break;
                    case "deadline":
                        this.list.addDeadline(cmd);
                        break;
                    case "event":
                        this.list.addEvent(cmd);
                        break;
                    default:
                        System.out.println("Bad Command");
                        break;
                }
                break;
        }

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
     * Running state of the Duke Application
     * @return State of Duke running
     */
    public boolean running() {
        return this.running;
    }
}
