import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Duke {

    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String hello = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(hello + "\n" + question);

        String ans = sc.nextLine();
<<<<<<< HEAD
        ArrayList<Task> tasks = new ArrayList<>();
=======
        ArrayList<Task> tasks = StoreData.readFile();
>>>>>>> branch-Level-7
        while (ans != null) {
            try {
                if (ans.equals("list")) {
                    System.out.println("Here are the tasks n your list:" + "\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        Integer listNum = i + 1;
                        System.out.println(listNum.toString() + "." + tasks.get(i).toString());
                    }
                } else if (ans.substring(0, 4).equals("done")) {
                    tasks.get(Integer.parseInt(ans.substring(5)) - 1).markAsDone();
                } else if (ans.substring(0, 4).equals("todo")) {
                    if (ans.length() == 4) {
                        DukeException ex = new DukeException(ans.substring(0, 4));
                        System.out.println(ex.toString());
                    } else {
                        Todo t = new Todo(ans.substring(5));
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:" + "\n" + " " + t.toString() + "\n"
                                + "Now you have " + tasks.size() + " tasks in the list");
                    }
                } else if (ans.substring(0, 8).equals("deadline")) {
                    if (ans.length() == 8) {
                        DukeException ex = new DukeException(ans.substring(0, 8));
                        System.out.println(ex.toString());
                    } else {
                        Deadline d = new Deadline(ans.substring(9, ans.indexOf('/') - 1),
<<<<<<< HEAD
                                LocalDate.parse(ans.substring(ans.indexOf('/') + 4)));
=======
                                ans.substring(ans.indexOf('/') + 4));
>>>>>>> branch-Level-7
                        tasks.add(d);
                        System.out.println("Got it. I've added this task:" + "\n" + " " + d.toString() + "\n"
                                + "Now you have " + tasks.size() + " tasks in the list");
                    }
                } else if (ans.substring(0, 5).equals("event")) {
                    if (ans.length() == 5) {
                        DukeException ex = new DukeException(ans.substring(0, 5));
                        System.out.println(ex.toString());
                    } else {
                        Event e = new Event(ans.substring(6, ans.indexOf('/') - 1),
<<<<<<< HEAD
                                LocalDate.parse(ans.substring(ans.indexOf('/') + 4)));
=======
                                ans.substring(ans.indexOf('/') + 4));
>>>>>>> branch-Level-7
                        tasks.add(e);
                        System.out.println("Got it. I've added this task:" + "\n" + " " + e.toString() + "\n"
                                + "Now you have " + tasks.size() + " tasks in the list");
                    }
                }else if (ans.substring(0, 6).equals("delete")){
                    int i = Integer.parseInt(ans.substring(7)) - 1;
                    Task r = tasks.get(i);
                    tasks.remove(i);
                    System.out.println("Noted. I've removed this task: " + "\n" +
                            r.toString() + "\n" + "Now you have " + tasks.size() + " tasks in the list.");

                }else{
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }catch (Exception ex){
                System.out.println(new DukeException().toString());
            }

            StoreData.writeToFile(tasks);
            ans = sc.nextLine();
            if (ans.equals("bye")) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
