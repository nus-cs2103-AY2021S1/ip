package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Duke {

    public enum Command {
        TERMINATE ("bye"),
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        HELP("--help"),
        DELETE("delete");

        private String command;

        Command(String command){
            this.command = command;
        }

        @Override
        public String toString() {
            return this.command;
        }

    }
    private List<Task> list = new ArrayList<>();

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    
    static final String LINE = "     ___________________________________________________________________________\n";
    static final String DOUBLE_TAB = "      ";

    static final String PATH = "data/data.txt";


    public void mainProgram() {
        greet();

        Scanner sc = new Scanner(System.in);

        createDirectory();
        createFile();

        try {
            readFile();
        } catch(FileNotFoundException e) {
            printException(new DukeFileNotFoundException());
        }

        System.out.print("Enter a command: ");

        while (sc.hasNext()) {
            String input = sc.nextLine().toLowerCase();
            if (input.equals(Command.TERMINATE.toString())) {
                bye();
                break;
            } else if (input.equals(Command.LIST.toString())) {
                runList();
            } else if (input.contains(Command.DONE.toString())) {
                try {
                    done(input);
                } catch (IndexOutOfBoundsException e){
                    printException(new DoneException());
                }
            } else if (input.contains(Command.TODO.toString())) {
                try {
                    todoTask(input);
                } catch (IndexOutOfBoundsException e) {
                    printException(new TodoException());
                }
            } else if (input.contains(Command.DEADLINE.toString())) {
                try {
                    deadlineTask(input);
                } catch (IndexOutOfBoundsException e) {
                    printException(new DeadlineException());
                }
            } else if (input.contains(Command.EVENT.toString())) {
                try {
                    eventTask(input);
                } catch(IndexOutOfBoundsException e) {
                   printException(new EventException());
                }
            } else if (input.equals(Command.HELP.toString())){
                help();
            } else if (input.contains(Command.DELETE.toString())){
                try {
                    delete(input);
                } catch (IndexOutOfBoundsException e) {
                    printException(new DeleteException());
                }
            } else {
                printException(new AnonymousException(input));
            }
            System.out.print("Enter a command: ");
        }
    }

    private void updateToFile() {
        try {
            FileWriter fw = new FileWriter(PATH);
            StringBuilder txtToAdd = new StringBuilder();

            for(Task task : list) {
                if (task instanceof TodoTask) {
                    TodoTask todoTask = (TodoTask) task;
                    if (todoTask.isDone()) {
                        txtToAdd.append("T").append(" | ").append("1").append(" | ");
                    } else {
                        txtToAdd.append("T").append(" | ").append("0").append(" | ");
                    }
                    txtToAdd.append(todoTask.getDescription()).append("\n");
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    if (eventTask.isDone()) {
                        txtToAdd.append("E").append(" | ").append("1").append(" | ");
                    } else {
                        txtToAdd.append("E").append(" | ").append("0").append(" | ");
                    }
                    txtToAdd.append(eventTask.getDescription().replace('/', '|')).append("\n");
                } else if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    if (deadlineTask.isDone()) {
                        txtToAdd.append("D").append(" | ").append("1").append(" | ");
                    } else {
                        txtToAdd.append("D").append(" | ").append("0").append(" | ");
                    }
                    txtToAdd.append(deadlineTask.getDescription().replace('/', '|')).append("\n");
                }
            }
            fw.write(txtToAdd.toString());
            fw.close();
        } catch(IOException e) {
            printException(new DukeCreateFileException());
        }
    }


    private void readFile() throws FileNotFoundException {
        File file =  new File(PATH);
        Scanner s = new Scanner(file);

        while(s.hasNext()){
            String string = s.nextLine();
            String[] word = string.split("\\|",3);
            processString(word);
        }
    }

    private void processString(String[] word) {
        String taskType = word[0].trim();
        switch(taskType) {
        case "T":
            TodoTask todoTask = new TodoTask();
            if (word[1].trim().equals("1")) {
                todoTask.setStatus(true);
            } else {
                todoTask.setStatus(false);
            }
            todoTask.setDescription(word[2].trim());
            list.add(todoTask);
            break;
        case "D" :
            DeadlineTask deadlineTask = new DeadlineTask();
            if (word[1].trim().equals("1")) {
                deadlineTask.setStatus(true);
            } else {
               deadlineTask.setStatus(false);
            }
            deadlineTask.setDescription(word[2].trim().replace('|','/'));
            list.add(deadlineTask);
            break;
        case "E" :
            EventTask eventTask = new EventTask();
            if(word[1].trim().equals("1")) {
                eventTask.setStatus(true);
            } else {
                eventTask.setStatus(false);
            }
            eventTask.setDescription(word[2].trim().replace('|','/'));
            list.add(eventTask);
            break;
        default :
            Task task = new Task();
        }
    }

    private void createFile() {
        Path dataPath = Paths.get("data", "data.txt");
        File datafile = new File(dataPath.toString());
        if(!datafile.exists()) {
            try {
                datafile.createNewFile();
            } catch (IOException e) {
                printException(new DukeCreateFileException());
                System.exit(-1);
            }
        }
    }

    private void createDirectory() {
        Path directory = Paths.get("data");
        File file = new File(directory.toString());
        if(!file.exists()) {
            file.mkdir();
        }
    }

    private void delete(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        Task removedTask = list.remove(index-1);
        printMessage("Noted. I've removed this task:\n"
                +format( removedTask.toString()) + "\n"
                + format("Now you have " + list.size() + " tasks in the list"));
        updateToFile();
    }

    private void help() {
        printCommandList("COMMAND","FORMAT");
        printCommandList("todo","todo <TASK_NAME>");
        printCommandList("event","event <EVENT_NAME> / <EVENT_TIME>");
        printCommandList("deadline", "deadline <DEADLINE_NAME> / <DEADLINE_TIME>");
        printCommandList("delete", "delete <TASK_NUMBER>");
        printCommandList("done", "done <TASK_NUMBER>");
    }

    private void printCommandList(String command, String format) {
        String indentation = "%-20s%s%n" ;
        System.out.printf(indentation,command,format);
    }


    private void runList() {
        printMessage("Here are the tasks in your list:\n" + showList());
    }

    private void printException(Exception e) {
        System.out.print(LINE);
        System.out.println(format(e.toString()));
        System.out.println(LINE);
    }

    private String format(String text) {
        return DOUBLE_TAB + text;
    }

    private void printMessage(String text) {
        System.out.print(LINE);
        System.out.println(format(text));
        System.out.println(LINE);
    }

    private void deadlineTask(String input) {
        String taskDetails = input.split("\\s", 2)[1];
        String[] splitTaskDetails = taskDetails.split("/");
        String taskDescription = splitTaskDetails[0];
        String taskTime = splitTaskDetails[1];
        final String BY = taskTime.split("\\s",2)[0];
        final String TIME = taskTime.split("\\s",2)[1];
        DeadlineTask deadlineTask = new DeadlineTask(taskDetails);
        list.add(deadlineTask);
        printMessage("Got it. I've added this task:\n" +
                format(deadlineTask + " (" + BY + ": " + TIME + ")\n")
                + format("Now you have " + list.size() + " tasks in the list") );

        updateToFile();
    }

    private void eventTask(String input) {
        String taskDetails = input.split("\\s", 2)[1];
        String taskDescription = taskDetails.split("/", 2)[0];
        String taskTime = taskDetails.split("/", 2)[1];
        final String AT = taskTime.split("\\s", 2)[0];
        final String TIME = taskTime.split("\\s", 2)[1];
        EventTask eventTask = new EventTask(taskDetails);
        list.add(eventTask);
        printMessage("Got it. I've added this task:\n" +
                format(eventTask + " (" + AT + ": " + TIME + ")\n")
        + format("Now you have " + list.size() + " tasks in the list" ));

        updateToFile();
    }


    private void todoTask(String input) {
        String taskDescription = input.split("\\s", 2)[1];
        TodoTask todoTask = new TodoTask(taskDescription);
        list.add(todoTask);
        printMessage("Got it. I've added this task :\n" + format(todoTask +"\n" +
                format("Now you have " + list.size() + " tasks in the list")));

        updateToFile();
    }

    private void done(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        Task task = list.get(index-1);
        task.setStatus(true);
        printMessage("Nice! I've marked this task as done:\n" + DOUBLE_TAB + task);

        updateToFile();
    }

    private String showList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++) {
            if (i != list.size()-1) {
                sb.append(DOUBLE_TAB + (i + 1) + ". " + list.get(i) + "\n");
            } else {
                sb.append(DOUBLE_TAB + (i + 1) + ". " + list.get(i));
            }
        }
        return sb.toString();
    }

    private void greet() {
        System.out.println(LINE + DOUBLE_TAB + "Hello! I'm Rich.\n" + DOUBLE_TAB  + "What can I do for you?\n" + LINE);
    }


    public void bye() {
        printMessage("Bye.Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.mainProgram();
    }
}
