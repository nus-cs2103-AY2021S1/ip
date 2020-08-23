import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> initTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(".\\data.txt");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = ".\\data.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            //process the line
            Task task;
            char taskType = line.charAt(1);


            if (taskType == 'T') {
                String description = line.substring(7);
                task = new ToDo(description);

            } else if (taskType == 'E') {
                String[] temp = line.substring(7).split(" \\(at: ");
                String description = temp[0];
                String[] dateTime = temp[1].substring(0, temp[1].length() - 1).split("-");
                LocalDate by = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                task = new Events(description, by);

            } else {
                String[] temp = line.substring(7).split(" \\(by: ");
                String description = temp[0];
                String[] dateTime = temp[1].substring(0, temp[1].length() - 1).split("-");
                LocalDate at = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                task = new Deadline(description, at);

            }

            if (line.charAt(4) == '\u2713') {
                task.doTask();
            }
            tasks.add(task);


        }

        return tasks;

    }

    public static void main(String[] args) throws IOException {

        System.out.println("Hello from Bikini Bottom!");
        System.out.println("____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?\n"
            + "____________________________________________________________");

        ArrayList<Task> tasks = initTaskList();; //Initialise Task List
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            try {

                if (str.equals("list")) {
                    int counter = 1;
                    System.out.println("Here are the tasks in your list: \n");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            System.out.println(counter + ". " + tasks.get(i));

                            counter++;
                        } else {
                            break;
                        }
                    }
                } else if (str.startsWith("delete ")) {
                    int temp = Integer.parseInt(str.substring(7));
                    Task task = tasks.get(temp - 1);
                    tasks.remove(temp - 1);
                    System.out.println("Noted. I've removed the task: \n"
                        + task
                        + "\nNow you have " + tasks.size() + " tasks in the list.");

                } else if (str.startsWith("done ")) {
                    int temp = Integer.parseInt(str.substring(5));
                    tasks.get(temp-1).doTask();

                } else if (str.startsWith("todo ")) {
                    if (str.length() <= 5) {
                        throw new DescriptionException("todo");
                    }
                    str = str.substring(5);
                    tasks.add(new ToDo(str));

                    System.out.println("Got it. I've added this task: \n"
                        + tasks.get(tasks.size()-1)
                        + "\nNow you have " + tasks.size() + " task(s) in the list.");

                } else if (str.startsWith("deadline ")) {

                    if (str.length() <= 9) {
                        throw new DescriptionException("deadline");
                    }
                    str = str.substring(9);
                    if (!str.contains(" /by ")) {
                        throw new TrackingException("deadline");
                    }
                    String[] temp = str.split(" /by ");
                    String desc = temp[0];
                    String deadlineString = temp[1];

                    if (desc.length() == 0) {
                        throw new DescriptionException("deadline");
                    }
                    if (deadlineString.length() == 0) {
                        throw new TrackingException("deadline");
                    }
                    String[] dateTime = deadlineString.split("-");
                    if (dateTime.length != 3 ||
                        dateTime[0].length() != 4 && dateTime[1].length() != 2 && dateTime[2].length() != 2) {
                        throw new DateTimeException();
                    } // check if the date and time are in the correct format.
                    LocalDate by = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                        Integer.parseInt(dateTime[2]));


                    tasks.add(new Deadline(desc, by));

                    System.out.println("Got it. I've added this task: \n"
                        + tasks.get(tasks.size() - 1)
                        + "\nNow you have " + tasks.size() + " task(s) in the list.");

                } else if (str.startsWith("event ")) {
                    if (str.length() <= 6) {
                        throw new DescriptionException("event");
                    }
                    str = str.substring(6);
                    if (!str.contains(" /at ")) {
                        throw new TrackingException("event");
                    }
                    String[] temp = str.split(" /at ");
                    String desc = temp[0];
                    String atString = temp[1];

                    if (desc.length() == 0) {
                        throw new DescriptionException("event");
                    }
                    if (atString.length() == 0) {
                        throw new TrackingException("event");
                    }


                    String[] dateTime = atString.split("-");
                    if (dateTime.length != 3 ||
                        dateTime[0].length() != 4 && dateTime[1].length() != 2 && dateTime[2].length() != 2 ) {
                        throw new DateTimeException();
                    } // check if the date and time are in the correct format.
                    LocalDate at = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                        Integer.parseInt(dateTime[2]));
                    tasks.add(new Events(desc, at));

                    System.out.println("Got it. I've added this task: \n"
                        + tasks.get(tasks.size() - 1)
                        + "\nNow you have " + tasks.size() + " task(s) in the list.");


                } else if (str.equals("event") || str.equals("deadline") || str.equals("todo") ||
                str.equals("done")) {
                    throw new DescriptionException(str);
                } else {
                    throw new CommandException(str);
                }
            } catch (DukeException ex) {
                System.out.println(ex);
            }

            System.out.println("____________________________________________________________\n");

            str = sc.nextLine();

        }

        try { //write the list to file
            FileWriter myWriter = new FileWriter(".\\data.txt");
            for (int i = 0; i < tasks.size(); i++) {
                myWriter.write(tasks.get(i)+ "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }

        System.out.println("____________________________________________________________\n"
            + "Bye. Hope to see you again soon! Bahahahaha!\n"
            + "____________________________________________________________\n");



    }
}
