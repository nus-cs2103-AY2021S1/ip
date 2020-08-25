import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Duke {
    static ArrayList<Task> list = new ArrayList<>();
    private static FileProcessor fp;

    static void initialise() {
        try {
            list = new ArrayList<>();
            String dir = System.getProperty("user.dir") + "/data";
            File path = new File(dir);
            if(!path.exists()) {
                path.mkdir();
            }
            File f = new File(path + "/duke.txt");
            boolean result = f.createNewFile();
            if(result) {
                System.out.println("file created "+f.getCanonicalPath());
            } else {
                System.out.println("file exists at: "+f.getCanonicalPath());
                System.out.println("reading... ");
                fp = new FileProcessor(f);
                fp.readAll(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isNum(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static Task createTask(String s) throws DukeException {
        String[] task = s.split(" ");
        Task.Type type;
        if (task.length > 0) {
            if (task[0].equals("todo")) {
                type = Task.Type.TODO;
            } else if (task[0].equals("deadline")) {
                type = Task.Type.DEADLINE;
            } else if (task[0].equals("event")) {
                type = Task.Type.EVENT;
            } else {
                throw new DukeException(DukeException.IGNORE);
            }
        } else {
            throw new DukeException(DukeException.EMPTY);
        }
        switch (type) {
            case DEADLINE:
                if ((task = s.split(" /by ")).length != 2) {
                    throw new DukeException(DukeException.WRONG_DEADLINE);
                }
                return new Deadline(task[0].replaceFirst("deadline ", ""), task[1]);
            case EVENT:
                if ((task = s.split(" /at ")).length != 2) {
                    throw new DukeException(DukeException.WRONG_EVENT);
                }
                return new Event(task[0].replaceFirst("event ", ""), task[1]);
            default:
                if ((s.split(" ")).length < 2) {
                    throw new DukeException(DukeException.EMPTY_TODO);
                }
                return new Todo(s.replaceFirst("todo ", ""));
        }
    }

    static void print(String s) {
        System.out.println("\t____________________________________________________________\n" +
                s +
                "\t____________________________________________________________\n");
    }

    public static void main(String[] args) {
        initialise();
        Scanner scanner = new Scanner(System.in);
        String s;
        print("\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n");
        while(!(s = scanner.nextLine()).equals("bye")) {
            String[] done = s.split(" ");
            if (done.length == 2 && (done[0].equals("done") || done[0].equals("delete")) && isNum(done[1])
                    && Integer.parseInt(done[1]) <= list.size() && Integer.parseInt(done[1]) > 0) {
                if(done[0].equals("done")) {
                    list.get(Integer.parseInt(done[1]) - 1).setCompleted();
//                    fp.setTaskCompleted(Integer.parseInt(done[1]));
                    //test
                    print("\tNice! I've marked this task as done:\n" +
                            "\t" + list.get(Integer.parseInt(done[1]) - 1) + "\n");
                } else {
                    Task deleted = list.get(Integer.parseInt(done[1]) - 1);
                    list.remove(Integer.parseInt(done[1]) - 1);
                    print("\tNoted. I've removed this task:\n" +
                            "\t" + deleted + "\n" +
                            "\tNow you have " + list.size() + " tasks in the list.\n");
                }
                fp.reset();
                fp.addAll(list);
            } else if(s.equals("list")) {
                String temp = "";
                for(int i = 0; i < list.size(); i++) {
                    temp += "\t" + (i+1) + ". " + list.get(i) + "\n";
                }
                print("\tHere are the tasks in your list:\n" + temp);
            } else {
                try {
                    Task task = createTask(s);
                    list.add(task);
                    fp.addTask(task);
                    print("\tGot it. I've added this task: \n" +
                        "\t" + list.get(list.size()-1) + "\n" +
                        "\tNow you have " + list.size() + " tasks in the list.\n");
                } catch (DukeException e) {
                    if (e.getMessage().equals(DukeException.IGNORE)) {
                        print("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    } else if (e.getMessage().equals(DukeException.EMPTY_TODO)) {
                        print("\t☹ OOPS!!! The description of a todo cannot be empty.\n");
                    }
                }
            }
        }

        print("\tBye. Hope to see you again soon!\n");

    }
}
