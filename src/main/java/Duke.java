import exceptions.*;
import tasks.Command;
import tasks.TaskManager;

import java.util.Scanner;

/**
 * Backend Object Class for the Duke Chatbot Interface
 */
class Duke {
    private final TaskManager list;
    private boolean running;
    private final String linebreaker;
    public Duke(String linebreaker, String path){
        TaskManager list1;
        try{
            list1 = new TaskManager(path);    
        }catch (DukeIOException e){
            print(e.toString());
            list1 = new TaskManager(path,true);
        }

        this.list = list1;
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
                    end();
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

    private void end() throws DukeIOException{
        this.running = false;
        this.list.saveTasks();
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
     * @param s
     * @return
     */
    private String cleanInput(String s){
        return s+" [sep]";
    }

    public String help(){
        StringBuilder b = new StringBuilder();
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
        return b.toString();
    }

    /**
     * Running state of the Duke Application
     * @return State of Duke running
     */
    private boolean running() {
        return this.running;
    }

    /**
     * Internal Main Loop of Duke program
     * @param sc
     */
    public void dukeLoop(Scanner sc){
        print("Please Enter your name");
        String name = sc.nextLine();
        print(greeting(name));
        String in = "";
        while (this.running()){
            in = sc.nextLine();
            takeInput(in);
        }
        try {
            this.list.saveTasks();
        } catch (DukeIOException e) {
            print(e.toString());
        }
        print(goodbye(name));
    }

    /**
     * Greeting from Duke Bot
     * @return
     */
    private String greeting(String name){
        StringBuilder logo = new StringBuilder("\tHello from\n")
                                        .append(" ____        _        \n")
                                        .append("|  _ \\ _   _| | _____ \n")
                                        .append("| | | | | | | |/ / _ \\\n")
                                        .append("| |_| | |_| |   <  __/\n")
                                        .append("|____/ \\__,_|_|\\_\\___|\n")
                                        .append("\tHello! I'm Duke\n\tWhat can I do for you ")
                                        .append(name)
                                        .append("\n");
                
        return logo.toString();
    }

    private String goodbye(String name){
        return "Bye " + name +"! Hope to see you again soon!";
    }
    /**
     * Wraps all text output and prints to the console
     * @param s
     */
    private void print(String s){
        System.out.printf("%s%s\n%s%n",linebreaker,s,linebreaker);
    }
}
