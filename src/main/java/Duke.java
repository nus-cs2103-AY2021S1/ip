import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greeting = "Hello, I'm Duke, your personal assistant. \n What can I do for you?";
    public static String home = System.getProperty("user.home");//home = C:/Users/david
    public static void main(String[] args) throws IOException {
        boolean fileExists = new java.io.File(home + "/iP/File.txt").exists();
        File f = new File(home + "//iP//File.txt");
        f.createNewFile();
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        list = load(f);//load File.txt to the list      format: {T1bookhotel}, {E0bookhotel,tomorrow}
        while(sc.hasNextLine()) {
            String order = sc.nextLine();
            String reply = process(order, list, f);
            System.out.println(reply);
        }
    }

    private static List<Task> load(File f) throws FileNotFoundException {
        List<Task> temp = new ArrayList<>();
        Scanner scFile = new Scanner(f);
        while (scFile.hasNextLine()) {
            String data = scFile.nextLine();
            Task task = parse(data);
            temp.add(task);
        }
        return temp;
    }

    private static Task parse(String data) {
        char type = data.charAt(0);
        if (type == 'T') {
            return parseTodo(data);
        } else if (type == 'E') {
            return parseEvent(data);
        } else if (type == 'D') {
            return parseDeadline(data);
        } else {
            return new Task("something went wrong, the header is not T/D/E");
        }
    }

    private static Task parseDeadline(String data) {
        //format: {D1bookhotel,tomorrow}
        boolean done;
        String description;
        String due;
        int i = data.charAt(1);
        if (i == 1) {
            done = true;
        } else {
            done = false;
        }
        int indexOfComma = data.indexOf(',');
        description = data.substring(2, indexOfComma);
        due = data.substring(indexOfComma+1);
        return new Deadline(done, description, due);
    }

    private static Task parseEvent(String data) {
        //format: {D1bookhotel,tomorrow}
        boolean done;
        String description;
        String due;
        int i = data.charAt(1);
        if (i == 1) {
            done = true;
        } else {
            done = false;
        }
        int indexOfComma = data.indexOf(',');
        description = data.substring(2, indexOfComma);
        due = data.substring(indexOfComma+1);
        return new Event(done, description, due);
    }

    private static Task parseTodo(String data) {
        //format: {D1bookhotel,tomorrow}
        boolean done;
        String description;
        String due;
        int i = data.charAt(1);
        if (i == 1) {
            done = true;
        } else {
            done = false;
        }
        description = data.substring(2);
        return new Todo(done, description);
    }

    public static String process(String order, List<Task> list, File f) throws IOException {
        if (order.equals("bye")) {
            writeData(list, f);//write data into the new file before exiting
            return "    Bye-bye, see you next time!";
        } else if (order.equals("list")) {
            if (list.size() == 0) {
                return "    Well done! You've completed all your tasks.";
            } else {
                return printList(list);
            }
        } else if (order.length() >= 4 && order.substring(0, 4).equals("done")) {
            int index = Integer.parseInt(order.substring(5))-1;
            Task temp = list.get(index);
            temp.done();
            list.set(index, temp);
            writeData(list, f);
            return "    Great! I have marked this task as done:\n" + temp;
        } else if (order.length() >= 4 && order.substring(0, 4).equals("todo")) {
            if (order.length()>4) {
                String content = order.substring(5);
                list.add(new Todo(false, content));
                writeData(list, f);
                return "    added:" + content + "\n" + "    Now you have " + list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 8 && order.substring(0, 8).equals("deadline")) {
            if (order.length() > 8) {
                Integer indexOfSlash = order.indexOf('/');
                String content = order.substring(9, indexOfSlash);
                String due = order.substring(indexOfSlash + 1);
                list.add(new Deadline(false, content, due));
                writeData(list, f);
                return "    added:" + content + "\n" + "    Now you have " + list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 5 && order.substring(0, 5).equals("event")) {
            if (order.length() > 5) {
                int indexOfSlash = order.indexOf('/');
                String content = order.substring(6, indexOfSlash);
                String time = order.substring(indexOfSlash + 1);
                list.add(new Event(false, content, time));
                writeData(list, f);
                return "    added:" + content + "\n" + "    Now you have " + list.size() + " task(s) in the list";
            } else {
                return "    description cannot be empty~";
            }
        } else if (order.length() >= 6 && order.substring(0, 6).equals("delete")) {
            if (order.length() > 6) {
                Integer toBeDeleted = Integer.valueOf(order.substring(7));
                Task temp = list.get(toBeDeleted-1);
                list.remove(toBeDeleted-1);
                writeData(list, f);
                return "    I have removed this task: " + "\n" + temp;
            } else {
                return "    Sorry, you have not specified which task to be deleted.";
            }
        }
        else {
            return "    Sorry, I don't understand."; //handles all unexpected inputs
        }
    }

    private static void writeData(List<Task> list, File f) throws IOException {
        f.deleteOnExit();//delete the old file
        File file = new File(home + "/iP/File.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        for (Task task: list) {
            String temp = unparse(task);//convert Task into String
            fw.write(temp + System.lineSeparator());
        }
        fw.close();
    }

    private static String unparse(Task task) {
        return task.unparseMessage;
    }

    private static String printList(List<Task> list) {
        int size = list.size();
        String string = "";
        for (int i = 1; i <= size; i++) {
            string += "\n"+i+". "+list.get(i-1);
        }
        return string;
    }
}
