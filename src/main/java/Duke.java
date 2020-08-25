import Exceptions.*;
import Tasks.Command;
import Tasks.TaskManager;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Backend Object Class for the Duke Chatbot Interface
 */
class Duke {
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
        //To prevent an Security Concern or Code Injection Cleaning of text is first performed and authenticated
        // by adding an ending token
        //TODO eventually to convert the input -> Command with getter for task, deadline(if applicable)
        String cleaned = cleanInput(input);
        // There is minimally a sep
        String[] words = cleaned.split(" "); 
        cleaned = cleaned.replace(" [sep]", "");
        // Take out command from the words
        String text_input = cleaned.replaceFirst(words[0],"");
        //Sep token is added to prevent index errors
        Command c = parseCommand(words[0]);

        try {
            switch (c) {
                case bye:
                    this.running = false;
                    break;
                case help:
                    print(this.help());
                    break;
                case done:
                    print(list.doTask(words[1]));
                    break;
                case delete:
                    print(list.deleteTask(words[1]));
                    break;
                case list:
                    print(list.parseoutput());
                    break;
                case todo:
                    print(this.list.addToDo(text_input));
                    break;
                case deadline:
                    print(this.list.addDeadline(text_input));
                    break;
                case event:
                    print(this.list.addEvent(text_input));
                    break;
                case blank:
                    throw new DukeBlankCommandException("''");
                case error:
                    throw new DukeCommandException(words[0]);
                default:
                    throw new DukeUnknownException(text_input);

            }
        }catch (DukeUnknownException e){
            print(e.toString());
            //Shut down the application as this error appears to be program breaking
            this.running = false;
        } catch (DukeException e){
            //an Expected exception that does not change the loop
            print(e.toString());
        } 
    }

    /**
     * Parse Command into the 
     * @param cmd
     * @return
     */
    
    public Command parseCommand(String cmd){
        String cleaned = cmd.toLowerCase();
        Command given = null;
        Command[] commandlst = Command.values();
        for (Command c : Command.values()){
            if (c.getCode().equals(cleaned)) {
                given = c;
                // if there is a match, there is no other command that would match
                break;
            }
        }
        given = given==null ? Command.error:given;
        return given;
    }

    /**
     * inputs string, processes and cleans the text for the chatbot
     * via adding a ending token seperator
     * TODO Refactor this into a seperate command class
     * @param s
     * @return
     */
    private String cleanInput(String s){
        return s.strip() +" [sep]";
    }

    public String help(){
        StringBuilder b = new StringBuilder();
        b.append("\t Need some help huh?\n");
        b.append("\t Heres a list of my commands!\n");
        b.append("\t- 'bye' to close the application\n");
        b.append("\t- 'list' to list the current list of tasks and their statuses\n");
        b.append("\t- 'done' to set a task as done\n");
        b.append("\t- 'todo' to list a untimed task\n");
        b.append("\t- 'deadline' to list a timed deadline task, please structure with [deadline <task name> /by dd-MM-yyyy]\n");
        b.append("\t- 'event' to list a timed event task, please structure with [event <task name> /at dd-MM-yyyy\n");
        b.append("\t- 'help' to list these commands again\n");
        //eventually to add command help <command>
        return b.toString();
    }

    /**
     * Running state of the Duke Application
     * @return State of Duke running
     */
    public boolean running() {
        return this.running;
    }
    
    /**
     * Wraps all text output and prints to the console
     * @param s
     */
    public void print(String s){
        System.out.println(String.format("%s%s%s",linebreaker,s,linebreaker));
    }
}
