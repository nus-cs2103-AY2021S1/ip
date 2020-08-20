import java.util.Scanner;

public class Duke {
    private final TaskManager list;
    private boolean running;
    private final String linebreaker;
    public Duke(String linebreaker){
        this.list = new TaskManager(linebreaker);
        this.running = true;
        this.linebreaker = linebreaker.repeat(50)+"\n";
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
            case "help":
                this.help();
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
                        badCommand(input);
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

    public void help(){
        StringBuilder b = new StringBuilder(linebreaker);
        b.append("\t Need some help huh?\n");
        b.append("\t Heres a list of my commands!\n");
        b.append("\t- 'bye' to close the application\n");
        b.append("\t- 'list' to list the current list of tasks and their statuses\n");
        b.append("\t- 'done' to set a task as done\n");
        b.append("\t- 'todo' to list a untimed task\n");
        b.append("\t- 'deadline' to list a timed deadline task, please structure with [deadline <task name> /by <time>]\n");
        b.append("\t- 'event' to list a timed event task, please structure with [event <task name> /at <time>\n");
        b.append("\t- 'help' to list these commands again\n");
        //eventually to add command help <command>
        b.append(linebreaker);
        System.out.println(b.toString());
    }

    /**
     * Running state of the Duke Application
     * @return State of Duke running
     */
    public boolean running() {
        return this.running;
    }

    /**
     * Handles bad command
     * @param cmd
     */
    private void badCommand(String cmd){
        StringBuilder b = new StringBuilder(linebreaker);
        b.append("\t Oops you used a invalid command! Not sure what you mean... by:\n");
        b.append("\t ").append(cmd).append("\n");
        b.append("\t Heres a tip, use the 'help' command to learn about my commands!\n");
        b.append(linebreaker);
        System.out.println(b.toString());
    }
}
