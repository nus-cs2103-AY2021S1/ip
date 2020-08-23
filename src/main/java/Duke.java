import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        String str = "   ____________________________________________________________"
                + "\n    Got it. I've added this task:"
                + "\n      " + task
                + "\n    Now you have " + tasks.size() + " task(s) in the list."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    private static final String path = "data/listOfTasks.txt";

    public static void readFile(ArrayList<Task> tasks) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] details = line.split(" \\| ");
                boolean isDone = details[1].equals("1") ? true : false;
                switch (details[0]) {
                case "T":
                    tasks.add(new ToDos(details[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadlines(details[2], details[3], isDone));
                    break;
                case "E":
                    tasks.add(new Events(details[2], details[3], isDone));
                    break;
                }
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println (e);
        }
    }

    public static void writeFile(ArrayList<Task> tasks) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(file, false);
            } else {
                fw = new FileWriter(file, true);
            }
            for (Task task : tasks) {
                fw.write(task.writeToFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println (e);
        }
    }


    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        readFile(tasks);

        CommandHandler.greeting();

        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                CommandHandler.bye();
                break;
            } else if (input.equals("help")) {
                CommandHandler.getListOfCommands();
            } else if (input.equals("list")) {
                Task.getListOfTasks(tasks);
            } else if (input.startsWith("todo")) {
                String task;
                try {
                    task = input.split("todo ")[1];
                    Task newTask = new ToDos(task);
                    addTask(newTask);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    try {
                        throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DukeCommandType.TODO);
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.split("deadline ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                    } else if (!input.contains("/by ")) {
                        if (input.equals("deadline /by ")) {
                            throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                        } else {
                            throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.DEADLINE);
                        }
                    } else if (input.split("/by ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                    } else {
                        try {
                            String task = input.split("deadline ")[1].split("/by ")[0];
                            String due = input.split("deadline ")[1].split("/by ")[1];
                            if (task.equals("") && due.equals("")) {
                                throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.DEADLINE);
                            } else if (task.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DukeCommandType.DEADLINE);
                            } else if (due.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.DEADLINE);
                            } else {
                                Task newTask = new Deadlines(task, due);
                                addTask(newTask);
                            }
                        } catch (DukeException e){
                            System.err.println(e);
                        }
                    }
                } catch (DukeException e) {
                    System.err.println(e);
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.split("event ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                    } else if (!input.contains("/at ")) {
                        if (input.equals("event /at ")) {
                            throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                        } else {
                            throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.EVENT);
                        }
                    } else if (input.split("/at ").length < 2) {
                        throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                    } else {
                        try {
                            String task = input.split("event ")[1].split("/at ")[0];
                            String due = input.split("event ")[1].split("/at ")[1];
                            if (task.equals("") && due.equals("")) {
                                throw new DukeException("", DukeExceptionType.WRONG_FORMAT, DukeCommandType.EVENT);
                            } else if (task.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_DESCRIPTION, DukeCommandType.DEADLINE);
                            } else if (due.equals("")) {
                                throw new DukeException("", DukeExceptionType.MISSING_TIMING, DukeCommandType.DEADLINE);
                            } else {
                                Task newTask = new Events(task, due);
                                addTask(newTask);
                            }
                        } catch (DukeException e){
                            System.err.println(e);
                        }
                    }
                } catch (DukeException e) {
                    System.err.println(e);
                }
            } else if (input.startsWith("done")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task.done(tasks, index);
                } catch (IndexOutOfBoundsException exception) {
                    try {
                        throw new DukeException("", DukeExceptionType.INVALID_INDEX, DukeCommandType.DONE);
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
                }
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task.delete(tasks, index);
                } catch (IndexOutOfBoundsException exception) {
                    try {
                        throw new DukeException("", DukeExceptionType.INVALID_INDEX, DukeCommandType.DELETE);
                    } catch (DukeException e) {
                        System.err.println(e);
                    }
                }
                input = sc.nextLine();
                continue;
            } else {
                try {
                    throw new DukeException("", DukeExceptionType.UNKNOWN);
                } catch (DukeException e) {
                    System.err.println(e);
                }
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
        writeFile(tasks);
    }
}
