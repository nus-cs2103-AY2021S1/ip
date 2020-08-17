import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> myTasks = new ArrayList<>();

    public static void greet(){
        String greeting = "\n   _________________________________________________________________"
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   _________________________________________________________________";
        System.out.println(greeting);
    }


    public static void addTask(Task task){
        myTasks.add(task);
        String output = "   ______________________________________________________________"
                        + "\n   Got it. I've added this task:"
                        + "\n       " + task
                        + String.format("\n   Now you have %d task(s) in the list.", myTasks.size())
                        + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void listTasks(){
        if(myTasks.size() == 0){
            System.out.println("   ______________________________________________________________"
                    + "\n   " + "You have no tasks"
                    + "\n   ______________________________________________________________");
        } else {
            StringBuilder output = new StringBuilder("   ______________________________________________________________");
            for (int i = 0; i < myTasks.size(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(myTasks.get(i));
            }
            output.append("\n   ______________________________________________________________");
            System.out.println(output);
        }
    }

    public static void bye(){
        String output = "   ______________________________________________________________"
                + "\n   " + "Bye. Hope to see you again soon!"
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    private static void markAsDone(int i) {
        Task task = myTasks.get(i-1);
        task.markDone();
        String output = "   ______________________________________________________________"
                + "\n   " + "Nice! I've marked this task as done:"
                + "\n   " + task
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void deleteTask(int i){
        Task task = myTasks.get(i-1);
        myTasks.remove(i-1);
        String output = "   ______________________________________________________________"
                + "\n   " + "Noted. I've removed this task: "
                + "\n   " + task
                + String.format("\n   Now you have %d task(s) in the list.", myTasks.size())
                + "\n   ______________________________________________________________";
        System.out.println(output);
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
                    }
                } else if (input.startsWith("todo")) {
                    try {
                        if (input.split("todo ").length < 2) {
                            throw new DukeException("todo needs description", DukeExceptionType.NO_DESCRIPTION, Commands.TODO);
                        } else {
                            String[] inputSplit = input.split("todo ");
                            Task task = new TodoTask(inputSplit[1]);
                            addTask(task);
                        }
                    } catch (DukeException e){
                        System.err.println(e);
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
                                    Task task = new DeadlineTask(name, time);
                                    addTask(task);
                                }
                            } catch(DukeException e){
                                System.err.println(e);
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
                                    Task task = new EventTask(name, time);
                                    addTask(task);
                                }
                            } catch(DukeException e){
                                System.err.println(e);
                            }
                        }
                    } catch(DukeException e){
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
