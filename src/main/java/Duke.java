import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> myTasks = new ArrayList<>();
    static String horizontalLine = "_________________________________________________________________";

    public static void greet(){
        String greeting = "\n   " + horizontalLine
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   " + horizontalLine;
        System.out.println(greeting);
        try{
            readFile();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void readFile() throws IOException{
        File file = new File("./data\\duke.txt");
        if(!file.exists()){
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null){
            String[] splitLine = line.split(" \\| ");
            switch (splitLine[0]){
                case "E":
                    myTasks.add(new EventTask(splitLine[2], Boolean.parseBoolean(splitLine[1]),splitLine[3]));
                    break;
                case "T":
                    myTasks.add(new TodoTask(splitLine[2], Boolean.parseBoolean(splitLine[1])));
                    break;
                case "D":
                    myTasks.add(new DeadlineTask(splitLine[2], Boolean.parseBoolean(splitLine[1]),splitLine[3]));
                    break;
            }

            line = reader.readLine();
        }

    }

    public static void writeFile() throws IOException{
        String line = "";
        File file = new File("./data\\duke.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(Task task : myTasks){
            String type = task.getType();
            switch (type){
                case "E":
                case "D":
                    line = type+" | "+ task.isDone()+" | " + task.getName() + " | " + task.getTime();
                    break;
                case "T":
                    line = type+" | "+ task.isDone()+" | " + task.getName();
                    break;
            }
            writer.write(line);
            writer.newLine();
            line="";
        }
        writer.close();

    }


    public static void addTask(Task task) throws IOException {
        myTasks.add(task);
        String output = "   " + horizontalLine
                        + "\n   Got it. I've added this task:"
                        + "\n       " + task
                        + String.format("\n   Now you have %d task(s) in the list.", myTasks.size())
                        + "\n   " + horizontalLine;
        System.out.println(output);
        writeFile();
    }

    public static void listTasks(){
        if(myTasks.size() == 0){
            System.out.println("   " + horizontalLine
                    + "\n   " + "You have no tasks"
                    + "\n   " + horizontalLine);
        } else {
            StringBuilder output = new StringBuilder("   " + horizontalLine);
            for (int i = 0; i < myTasks.size(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(myTasks.get(i));
            }
            output.append("\n   " + horizontalLine);
            System.out.println(output);
        }
    }

    public static void bye(){
        String output = "   " + horizontalLine
                + "\n   " + "Bye. Hope to see you again soon!"
                + "\n   " + horizontalLine;
        System.out.println(output);
    }

    private static void markAsDone(int i) throws IOException {
        Task task = myTasks.get(i-1);
        task.markDone();
        String output = "   " + horizontalLine
                + "\n   " + "Nice! I've marked this task as done:"
                + "\n   " + task
                + "\n   " + horizontalLine;
        System.out.println(output);
        writeFile();
    }

    public static void deleteTask(int i) throws IOException {
        Task task = myTasks.get(i-1);
        myTasks.remove(i-1);
        String output = "   " + horizontalLine
                + "\n   " + "Noted. I've removed this task: "
                + "\n   " + task
                + String.format("\n   Now you have %d task(s) in the list.", myTasks.size())
                + "\n   " + horizontalLine;
        System.out.println(output);
        writeFile();
    }

    public static boolean isNumeric(String string){
        if(string == null){
            return true;
        }
        int a;
        try {
            a = Integer.parseInt(string);
        } catch (NumberFormatException e){
            return true;
        }
        return (a <= 0 || a > myTasks.size());
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                String input = scanner.nextLine();
                if (input.equals("bye") || input.equals("Bye")) {
                    bye();
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("done")) {
                    try {
                        if(input.split("done ").length < 2) {
                            throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DONE);
                        } else if(isNumeric(input.split("done ")[1])){
                            throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DONE);
                        } else {
                            String[] inputSplit = input.split("done ");
                            int index = Integer.parseInt(inputSplit[1]);
                            markAsDone(index);
                        }
                    } catch(DukeException e){
                        System.err.println(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (input.startsWith("todo")) {
                    try {
                        if (input.split("todo ").length < 2) {
                            throw new DukeException("todo needs description", DukeExceptionType.NO_DESCRIPTION, Commands.TODO);
                        } else {
                            String[] inputSplit = input.split("todo ");
                            Task task = new TodoTask(inputSplit[1], false);
                            addTask(task);
                        }
                    } catch (DukeException e){
                        System.err.println(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (input.startsWith("deadline")) {
                    try {
                        if(input.split("deadline ").length < 2) {
                            throw new DukeException("deadline needs description", DukeExceptionType.NO_DESCRIPTION, Commands.DEADLINE);
                        } else if(!input.contains("/by")) {
                            throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DEADLINE);
                        }  else if(input.split("/by ").length < 2) {
                            throw new DukeException("", DukeExceptionType.EMPTY_TIME, Commands.DEADLINE);
                        } else {
                            String[] inputSplit = input.split("/by ");
                            try {
                                if(inputSplit[0].split("deadline ").length < 2) {
                                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DEADLINE);
                                } else{
                                    String name = inputSplit[0].split("deadline ")[1];
                                    String time = inputSplit[1];
                                    Task task = new DeadlineTask(name,false, time);
                                    addTask(task);
                                }
                            } catch(DukeException e){
                                System.err.println(e);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DateTimeParseException e){
                                System.err.println(e.getMessage());
                            }
                        }
                    } catch(DukeException e){
                        System.err.println(e);
                    }
                } else if (input.startsWith("event")) {
                    try{
                        if (input.split("event ").length<2){
                            throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.EVENT);
                        } else if(!input.contains("/at")) {
                            throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.EVENT);
                        }  else if(input.split("/at ").length < 2) {
                            throw new DukeException("", DukeExceptionType.EMPTY_TIME, Commands.EVENT);
                        }
                        else {
                            String[] inputSplit = input.split("/at ");
                            try {
                                if(inputSplit[0].split("event ").length < 2) {
                                    throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.EVENT);
                                } else{
                                    String name = inputSplit[0].split("event ")[1];
                                    String time = inputSplit[1];
                                    Task task = new EventTask(name,false, time);
                                    addTask(task);
                                }
                            } catch(DukeException e){
                                System.err.println(e);
                            }
                        }
                    } catch(DukeException | IOException e){
                        System.err.println(e);
                    }
                } else if (input.startsWith("delete")) {
                    try{
                        if (input.split("delete ").length<2){
                            throw new DukeException("", DukeExceptionType.NO_DESCRIPTION, Commands.DELETE);
                        } else if(isNumeric(input.split("delete ")[1])){
                            throw new DukeException("", DukeExceptionType.WRONG_DESCRIPTION, Commands.DELETE);
                        } else {
                            String[] inputSplit = input.split("delete ");
                            int index = Integer.parseInt(inputSplit[1]);
                            deleteTask(index);
                        }
                    } catch(DukeException e){
                        System.err.println(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    throw new DukeException("", DukeExceptionType.INVALID_TASK);
                }
            } catch(DukeException e) {
                System.err.println(e);
            }
        }

    }

}
