
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static SimpleDateFormat readformatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static SimpleDateFormat writeformatter = new SimpleDateFormat ("MMM dd yyyy hh:mm a");
    public static ArrayList<Task> tasks = new ArrayList<Task>(100);
    public static String fileName = "data.txt";
    public static void main(String[] args) {
        LoadData();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        boolean hasNext = true;
        while(hasNext) {
            try {
                hasNext = processLine(sc);
            }  catch(DukeException ex) {
                System.out.println(ex);
            }
            System.out.println("-----------------");

        }
    }

    public static boolean processLine(Scanner sc) throws DukeException {
        String str;
        str = sc.nextLine();
        System.out.println("-----------------");
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(str.split(" ")));
        if (tokens.size()==0||tokens.get(0).equals("")){
            throw new EmptyCommandException();
        }
        boolean bl;
        DukeCommand command = DukeCommand.getCommand(tokens.get(0));
        if (command == null) {
            throw new InvalidCommandException("I do not recognise this command!");
        }

        if (command == DukeCommand.BYE){
            System.out.println("Bye. Hope to see you again!");
            bl = false;
        } else {
            if (command == DukeCommand.DONE) {
                if (tokens.size() < 2) {
                    throw new MissingArgumentException("Must provide number after done!");
                }
                int ind;
                try{
                    ind = Integer.parseInt(tokens.get(1))-1;
                } catch(Exception ex) {
                    throw new InvalidCommandException(tokens.get(1)+" is not a number!");
                }
                if (tasks.size()<=ind || ind < 0) {
                    throw new DukeException("Task "+ tokens.get(1) +" does not exist!");
                }
                tasks.get(ind).completeTask();
                Save();
                System.out.println(tasks.get(ind).toString());
            } else if (command == DukeCommand.DELETE) {
                if (tokens.size() < 2) {
                    throw new MissingArgumentException("Must provide number after delete!");
                }
                int ind;
                try{
                    ind = Integer.parseInt(tokens.get(1)) - 1;
                } catch(Exception ex) {
                    throw new InvalidCommandException(tokens.get(1) + " is not a number!");
                }
                if (tasks.size()<=ind || ind < 0) {
                    throw new DukeException("Task " + tokens.get(1) + " does not exist!");
                }
                Task task = tasks.remove(ind);
                Save();
                System.out.println("I have removed this task");
                System.out.println(task.toString());
                System.out.println("You now have " + tasks.size() + " tasks left!");
            }else if (command == DukeCommand.LIST) {
                System.out.println("Here are the tasks in your list:");
                int i=0;
                for(Task task:tasks){
                    System.out.println(String.format("%d.%s", ++i, task));
                }
            } else {
                Task task = addNewTask(command, tokens);
                tasks.add(task);
                Save();
                System.out.println(
                        String.format("I have added this task: \n\t%s\nYou now have %d tasks in the list",
                                task.toString(), tasks.size()));
            }
            bl = true;
        }
        return bl;

    }

    public static Task addNewTask(DukeCommand command, ArrayList<String> tokens) throws DukeException {
        if (command == DukeCommand.TODO) {
            if (tokens.size() < 2) {
                throw new MissingArgumentException("todo cannot be empty!");
            }
            return new ToDo(stringCombiner(tokens, 1, tokens.size()));
        } else if (command == DukeCommand.DEADLINE) {

            int ind = 0;
            boolean found= false;
            while (!found && ind < tokens.size()-1) {
                ind++;
                if (tokens.get(ind).equals("/by")) {
                    found = true;
                }
            }
            if (!found) {
                throw new MissingArgumentException("Deadline need an /by time!");
            }
            if (ind == 1) {
                throw new MissingArgumentException("Deadline needs a name!");
            }
            if (ind == tokens.size()-1) {
                throw new MissingArgumentException("Deadline needs a deadline time!");
            }
            Date date;
            try{
                date = readformatter.parse(stringCombiner(tokens,ind+1, tokens.size()));
            } catch(Exception ex) {
                throw new InvalidCommandException("Provide a proper date and time!");
            }

            return new Deadline(stringCombiner(tokens, 1, ind),
                    date);
        } else {
            int ind = 0;
            boolean found= false;
            while (!found && ind < tokens.size()-1) {
                ind++;
                if (tokens.get(ind).equals("/at")) {
                    found = true;
                }
            }
            if (!found) {
                throw new MissingArgumentException("Event need an /at time!");
            }
            if (ind == 1) {
                throw new MissingArgumentException("Event needs a name!");
            }
            if (ind == tokens.size()-1) {
                throw new MissingArgumentException("Event needs a event time!");
            }
            Date date;
            try{
                date = readformatter.parse(stringCombiner(tokens,ind+1, tokens.size()));
            } catch(Exception ex) {
                throw new InvalidCommandException("Provide a proper date and time!");
            }
            return new Event(stringCombiner(tokens, 1, ind),
                    date);
        }
    }

    public static String stringCombiner(ArrayList<String> tokens, int start, int end) {
        StringBuilder str = new StringBuilder();
        for (int i = start; i < end; i++) {
            str.append(tokens.get(i));
            if (i != end - 1) {
                str.append(" ");
            }
        }
        return str.toString();
    }

    public static void Save() {
        try{
            FileWriter writer = new FileWriter(fileName);
            StringBuilder str = new StringBuilder();
            for(Task task : tasks) {
                str.append(task.toFileString());
                str.append("\n");
            }
            writer.write(str.toString());
            writer.flush();
            writer.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }
    public static void LoadData() {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String type = sc.nextLine();
                    String done = sc.nextLine();
                    String desc = sc.nextLine();
                    Task t;
                    System.out.println(type);
                    if (type.equals("T")) {
                        t = new ToDo(desc);
                    } else if (type.equals("E")) {
                        t = new Event(desc, writeformatter.parse(sc.nextLine()));
                    } else {
                        t = new Deadline(desc, writeformatter.parse(sc.nextLine()));
                    }
                    if (done.equals("T")) {
                        t.completeTask();
                    }
                    sc.nextLine();
                    tasks.add(t);
                }
            } catch (FileNotFoundException | ParseException e) {
                System.out.println(e);
            }
        }
    }
}
