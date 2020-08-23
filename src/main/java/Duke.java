import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static String line = "________________________________________________";
    private static String end = "bye";
    private static String done = "done";
    private static String delete = "delete";
    private static String listing = "list";
    private static FileWriter fw;
    private static PrintWriter pw;
    private static FileReader fr;
    private static BufferedReader br;

    public static void openFile() {
        try {
            fr = new FileReader("data/duke.txt");
            br = new BufferedReader(fr);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void closeFile() {
        pw.close();
    }

    public static void display(String text) {
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void printWelcomeMessage() {
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);
    }

    public static Task assignTask(String type, String name) throws NoTaskException, InvalidCommandException, NoDateException {

        try {
            if (type.equals("todo")) {
                try {
                    return new Todo(name.substring(5), false);
                } catch (IndexOutOfBoundsException e) {
                    throw new NoTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else {
                int indexOfCommand = name.indexOf("/");
                String deadline = name.substring(indexOfCommand + 4);
                    if (type.equals("deadline")) {
                        try {
                            String currname = name.substring(9);
                            if (indexOfCommand > -1 ) {
                                return new Deadline(name.substring(9, indexOfCommand - 1), false, deadline);
                            } else {
                                throw new NoDateException("☹ OOPS!!! Please specify the deadline!");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                    } else {
                        try {
                            String currname = name.substring(6);
                            if (indexOfCommand > -1 ) {
                                return new Deadline(name.substring(6, indexOfCommand - 1), false, deadline);
                            } else {
                                throw new NoDateException("☹ OOPS!!! Please specify when the event is going to be held!");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new NoTaskException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                    }
            }
        } catch (NullPointerException e) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static int decideIndexDelete(String word) throws MissingSpecifiedDeleteError {
        int index = 0;
        try {
            index = Integer.parseInt(word.substring(7));
        } catch (IndexOutOfBoundsException e) {
            throw new MissingSpecifiedDeleteError("☹ OOPS!!! Please specify which task you want to delete.");
        }
        return index;
    }

    public static Task deletedTask(int index, ArrayList<Task> tasks) throws WrongDeleteIndexError {
        Task curr = null;
        try {
            curr = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongDeleteIndexError("☹ OOPS!!! You only have " + tasks.size() + " tasks in your list. " +
                    "Please select a valid task to be deleted.");
        }
        return curr;
    }

    public static ArrayList<Task> insertTasks(){
        String task;
        ArrayList<Task> temp = new ArrayList<>();
        try {
            while ((task = br.readLine()) != null) {
                String type = task.substring(0, 1);
                String name = task.substring(8);
                int isDone = Integer.parseInt(task.substring(4, 5));
                boolean isTaskDone = isDone == 1;
                switch (type) {
                    case "T":
                        temp.add(new Todo(name, isTaskDone));
                        break;
                    case "D":
                        int indexOfLine = name.indexOf("|");
                        temp.add(new Deadline(name.substring(0, indexOfLine - 1), isTaskDone, name.substring(indexOfLine + 1)));
                        break;
                    case "E":
                        indexOfLine = name.indexOf("|");
                        temp.add(new Event(name.substring(0, indexOfLine - 1), isTaskDone, name.substring(indexOfLine + 1)));
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }

        return temp;
    }

    public static void putToDatabase(ArrayList<Task> tasks) {

        try {
            fw = new FileWriter("data/duke.txt");
            pw = new PrintWriter(fw);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    pw.println(task.getType() + " | " + task.isDone() + " | " + task.getName());
                } else {
                    pw.println(task.getType() + " | " + task.isDone() + " | " + task.getName() + " |" + task.getEnd());
                }
            }
            pw.close();
        } catch (IOException e) {
            System.out.println(line);
            System.out.println(e.getMessage());
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        openFile();
        Scanner scan = new Scanner(System.in);

        ArrayList<Task> tasks = insertTasks();
        String echo = scan.nextLine();
        while (!echo.equals(end)) {
            String displayText = "";
            if (echo.equals(listing)) {
                int i = 1;
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task item :tasks) {
                    System.out.println(i + ". " + item);
                    i++;
                }
                System.out.println(line);
            } else if (echo.toLowerCase().contains(done)) {
                int num = Integer.parseInt(echo.substring(5));
                Task curr = tasks.get(num - 1).setToTrue();
                tasks.set(num - 1, curr);
                curr = tasks.get(num - 1);
                displayText = "Nice! I've marked this task as done: \n"
                        + curr;
                System.out.println(line);
                System.out.println(curr);
                System.out.println(line);
            } else if (echo.toLowerCase().contains(delete)) {
                int num = 0;
                try {
                    num = decideIndexDelete(echo);
                    try {
                        Task removed = deletedTask(num,tasks);
                        tasks.remove(num - 1);
                        System.out.println(line);
                        System.out.println("Noted. I've removed this task: \n" +
                                "  " + removed + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                    } catch (WrongDeleteIndexError e) {
                        System.out.println(line);
                        System.out.println(e.getMessage());
                        System.out.println(line);
                    }
                } catch (MissingSpecifiedDeleteError e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }


            } else {

                String firstWord = echo.toLowerCase().contains("todo") ? "todo"
                        : echo.toLowerCase().contains("deadline") ? "deadline"
                        : echo.toLowerCase().contains("event") ? "event"
                        : null;

                Task curr = null;
                try {
                    curr = assignTask(firstWord, echo);
                    tasks.add(curr);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task: \n"
                            + " " + curr + "\n"
                            + "Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } catch (NoTaskException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (InvalidCommandException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                } catch (NoDateException e){
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
            }
            echo = scan.nextLine();
        }
        putToDatabase(tasks);
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
