import exceptions.*;
import tasks.Command;
import tasks.TaskManager;
import tasks.TextParser;

import java.util.Scanner;

/**
 * Backend Object Class for the Duke Chatbot Interface
 */
class Duke {
    private final TaskManager list;
    private final String linebreaker;
    private final TextParser textParser;
    public Duke(String linebreaker, String path){
        TaskManager list1;
        try{
            list1 = new TaskManager(path);    
        }catch (DukeIOException e){
            print(e.toString());
            list1 = new TaskManager(path,true);
        }
        this.textParser = new TextParser();
        this.list = list1;
        this.linebreaker = linebreaker.repeat(50)+"\n";
    }


    /**
     * Manages all internal dataflow from Main or textual interaction
     * with the chatbot, by cleaning it
     * @param input
     */
    public String takeInput(String input) throws DukeUnknownException {
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
        Command c = textParser.parseCommand(words[0]);

        try {
            switch (c) {
                case bye:
                    return Boolean.TRUE.toString();
                case help:
                    return this.help();
                case done:
                    return list.doTask(words[1]);
                case delete:
                    return list.deleteTask(words[1]);
                case list:
                    return list.parseoutput();
                case todo:
                    return this.list.addToDo(text_input);
                case deadline:
                    return this.list.addDeadline(text_input);
                case event:
                    return this.list.addEvent(text_input);
                case blank:
                    throw new DukeBlankCommandException("''");
                case error:
                    throw new DukeCommandException(words[0]);
                default:
                    throw new DukeUnknownException(text_input);

            }
        }catch (DukeUnknownException e){
            throw e;
            //Shut down the application as this error appears to be program breaking
        } catch (DukeException e){
            //an Expected exception that does not change the loop
            print(e.toString());
        }
        return cleaned;
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
     * Greeting from Duke Bot
     * @return Sends a greeting from dukebot to the user
     */
    String greeting(String name){
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

    String goodbye(String name){
        return "Bye " + name +"! Hope to see you again soon!";
    }
    /**
     * Wraps all text output and prints to the console
     * @param s
     */
    private void print(String s){
        System.out.printf("%s%s\n%s%n",linebreaker,s,linebreaker);
    }
    
    void saveTasks() throws DukeIOException {
        this.list.saveTasks();
    }
}
