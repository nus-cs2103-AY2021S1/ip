import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    public static final String PATH = "data/duke.txt";
            
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke" + "\nWhat can I do for you?");
        
        ArrayList<Task> lst = loadFile();
        //ArrayList<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            String[] strArr = str.split(" ");
            try {
                check(strArr);
                if (str.equals("bye")) {
                    System.out.println("_____________________________________________________\n" + 
                            "Bye. Hope to see you again soon!\n" +
                            "_____________________________________________________");
                    System.exit(0);
                } else if (str.equals("list")) {
                    System.out.println("_____________________________________________________\n" + 
                            "Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println((i + 1) + ". " + lst.get(i).toString());
                    }
                    System.out.println("_____________________________________________________");
                } else if (strArr[0].equals("done")) {
                    lst.get(Integer.parseInt(strArr[1]) - 1).markAsDone();
                    System.out.println("_____________________________________________________\n" + 
                            "Nice! I've marked this task as done: \n" + "  " +
                            lst.get(Integer.parseInt(strArr[1]) - 1).toString() +
                            "\n_____________________________________________________");
                    writeFile(lst);
                } else if (strArr[0].equals("delete")) {
                    int i = Integer.parseInt(strArr[1]) - 1;
                    Task t = lst.get(Integer.parseInt(strArr[1]) - 1);
                    lst.remove(i);
                    System.out.println("_____________________________________________________\n" + 
                            "Noted. I've removed this task:\n" + "  " +
                            t.toString() + "\n" +
                            "Now you have " + lst.size() + " tasks in the list.\n" +
                            "_____________________________________________________");
                    writeFile(lst);
                } else {
                    if (strArr[0].equals("todo")) {
                        String sd = "";
                        for (int i = 1; i < strArr.length; i++) {
                            sd += strArr[i] + " ";
                        }
                        Task task = new Todo(sd);
                        lst.add(task);
                        System.out.println("_____________________________________________________\n" + 
                                "Got it. I've added this task:\n" + "  " + task.toString() + "\n" +
                                "Now you have " + lst.size() + " tasks in the list.\n" +
                                "_____________________________________________________");
                        writeFile(lst);
                    } else if (strArr[0].equals("deadline")) {
                        String sd = "";
                        String sb = "";
                        boolean b = true;
                        for (int i = 1; i < strArr.length - 1; i++) {
                            if (strArr[i].equals("/by")) {
                                b = false;
                                sd = sd.substring(0, sd.length() - 1);
                            }
                            if (b) {
                                sd += strArr[i] + " ";
                            } else {
                                if(i == strArr.length - 2) {
                                    sb = sb + strArr[i + 1];
                                } else {
                                    sb = sb + strArr[i + 1] + " ";
                                }
                            }
                        }
                        Task task = new Deadline(sd, LocalDate.parse(sb));
                        lst.add(task);
                        System.out.println("_____________________________________________________\n" + 
                                "Got it. I've added this task:\n" + "  " + task.toString() + "\n" +
                                "Now you have " + lst.size() + " tasks in the list.\n" +
                                "_____________________________________________________");
                        writeFile(lst);
                    } else if (strArr[0].equals("event")) {
                        String sd = "";
                        String sa = "";
                        boolean b = true;
                        for (int i = 1; i < strArr.length - 1; i++) {
                            if (strArr[i].equals("/at")) {
                                b = false;
                                sd = sd.substring(0, sd.length() - 1);
                            }
                            if (b) {
                                sd += strArr[i] + " ";
                            } else {
                                if (i == strArr.length - 2) {
                                    sa = sa + strArr[i + 1];
                                } else {
                                    sa = sa + strArr[i + 1] + " ";
                                }
                            }
                        }
                        
                        Task task = new Event(sd, LocalDate.parse(sa));
                        lst.add(task);
                        System.out.println("_____________________________________________________\n" + 
                                "Got it. I've added this task:\n" + "  " + task.toString() + "\n" +
                                "Now you have " + lst.size() + " tasks in the list.\n" +
                                "_____________________________________________________");
                        writeFile(lst);
                    }
                }
            } catch (Exception e) {
                System.out.println("_____________________________________________________\n" + e.toString() +
                        "\n_____________________________________________________");
            }
        }
        sc.close();
    }

    public static void check(String[] arr) throws DukeException {
        if (arr.length == 1 && (arr[0].equals("todo") || arr[0].equals("deadline") || arr[0].equals("event") ||
                arr[0].equals("done") || arr[0].equals("delete"))) {
            throw new DukeException(arr[0]);
        } else if (!arr[0].equals("todo") && !arr[0].equals("deadline") && !arr[0].equals("event") &&
                !arr[0].equals("list") && !arr[0].equals("bye") && !arr[0].equals("done") && !arr[0].equals("delete")) {
            throw new DukeException("other");
        } 
    }
    
    public static ArrayList<Task> loadFile() throws IOException {
        File file = new File(PATH);
        ArrayList<Task> taskList = new ArrayList<>();
        if(file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine()) != null) {
                String[] strArr = line.split(" , ");
                switch(strArr[0]) { 
                    case "T":
                        taskList.add(new Todo(strArr[1], strArr[2]));
                        break; 
                    case "D":
                        taskList.add(new Deadline(strArr[1], strArr[2], LocalDate.parse(strArr[3])));
                        break;
                    case "E":
                        taskList.add(new Event(strArr[1], strArr[2], LocalDate.parse(strArr[3])));
                        break;
                }
            }
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }
    
    public static void writeFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(PATH);
        for(Task task : tasks) {
            writer.write(task.saveString() + "\n");
        }
        writer.close();
    }
}
