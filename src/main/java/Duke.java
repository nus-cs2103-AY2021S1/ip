import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


public class Duke {
    public static void main(String[] args) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" + logo +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(hello);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> task_list = new ArrayList<Task>();

        try {
            task_list = loadFile();
        }

        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        Boolean bye = false;

        while (sc.hasNextLine() && !bye) {
            String command = sc.nextLine();
                try {
                    Duke.TaskIdentifier(command, task_list);
                    createFile(task_list);
                }

                catch (DukeInvalidCommandException e) {
                    System.out.println(e.toString());
                }

                catch (DukeIncompleteCommandException e) {
                    System.out.println(e.toString());
                }

                catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            }
        }

    public static void TaskIdentifier(String command, ArrayList<Task> task_list) throws DukeInvalidCommandException, DukeIncompleteCommandException{
            String task_type = command.split(" ")[0];
            String task_details = "";
            String task_info = "";

            if (!task_type.equals("todo") && !task_type.equals("event") && !task_type.equals("deadline")
                    && !task_type.equals("list") && !task_type.equals("bye") && !task_type.equals("done")
                    && !task_type.equals("delete")) {
                throw new DukeInvalidCommandException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________");
            }

            try {

                if (!task_type.equals("list") && !task_type.equals("bye")) {
                    task_details = command.split(task_type + " ")[1]; //Includes task task_info and date/time if applicable
                    task_info = task_details.split(" /")[0];
                }

                switch (task_type) {

                    case "list":
                        String tasks = "";
                        for (int i = 0; i < task_list.size(); i++) {
                            tasks += String.format("     %d." + task_list.get(i) + "\n", i + 1);
                        }
                        System.out.println("    ____________________________________________________________\n" +
                                "     Here are the tasks in your list:\n" +
                                tasks +
                                "    ____________________________________________________________\n");
                        break;

                    case "bye":
                        System.out.println("    ____________________________________________________________\n" +
                                "     Bye. Hope to see you again soon!\n" +
                                "    ____________________________________________________________\n");
                        System.exit(0);
                        break;

                    case "todo":
                        Task todo = new ToDo(task_info);
                        task_list.add(todo);
                        PrintTask(task_list, todo);
                        break;

                    case "deadline":
                        String date_n_time_d[] = task_details.split("by ")[1].split(" ");
                        String str_date_d = date_n_time_d[0];
                        LocalDate date_d = LocalDate.parse(str_date_d);
                        String time_d = date_n_time_d[1];
                        Task deadline = new Deadline(task_info, date_d, time_d);
                        task_list.add(deadline);
                        PrintTask(task_list, deadline);
                        break;

                    case "event":
                        String date_n_time_e[] = task_details.split("at ")[1].split(" ");
                        String str_date_e = date_n_time_e[0];
                        LocalDate date_e = LocalDate.parse(str_date_e);
                        String time_e = date_n_time_e[1];
                        Task event = new Event(task_info, date_e, time_e);
                        task_list.add(event);
                        PrintTask(task_list, event);
                        break;

                    case "done":
                        Task temp = task_list.get(Integer.parseInt(task_details) - 1);
                        temp.completeTask();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done: \n" +
                                "       " + temp + "\n" +
                                "    ____________________________________________________________");
                        break;

                    case "delete":
                        Task temp2 = task_list.remove(Integer.parseInt(task_details) - 1);
                        System.out.println(
                                String.format("    ____________________________________________________________\n" +
                                "     Noted. I've removed this task: \n" +
                                "       %s\n" +
                                "     Now you have %d tasks in the list.\n" +
                                "    ____________________________________________________________", temp2.toString(), task_list.size()));

                    default:
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeIncompleteCommandException(String.format(
                        "    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The task_info of a %s cannot be empty.\n" +
                        "    ____________________________________________________________\n", task_type));
            }
    }


    public static void PrintTask(ArrayList<Task> task_list, Task t) {
        System.out.println(
                String.format("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________\n", t.toString(), task_list.size()));

    }

    public static void createFile(ArrayList<Task> task_list) throws IOException {
        File file = getFile();
        FileWriter writer = new FileWriter(file);
        for (Task task : task_list) {
            if (task instanceof ToDo) {
                writer.write("T|" + task.task_completion + "|" + task.task_info + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D|" + task.task_completion + "|" + task.task_info + "|by " + ((Deadline) task).date + ((Deadline) task).time + "\n");
            } else if (task instanceof Event) {
                writer.write("E|" + task.task_completion + "|" + task.task_info + "|at " + ((Event) task).date + ((Event) task).time + "\n");
            }
        }
        writer.close();
    }



    public static File getFile() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        return new File(path.toString());
    }

    public static ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> task_list = new ArrayList<Task>();
        File file = getFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] taskContent = sc.nextLine().split("\\|");
            String taskType = taskContent[0];
            String status = taskContent[1];
            String task_info = taskContent[2];
            switch (taskType) {
                case "T":
                    Task toDo = new ToDo(task_info);
                    if (status.equals("true")) {
                        toDo.task_completion = true;
                    }
                    task_list.add(toDo);
                    break;
                case "D":
                    Task deadLine = new Deadline(task_info, getDate(taskContent[3]), getTime(taskContent[3]));
                    if (status.equals("true")) {
                        deadLine.task_completion = true;
                    }
                    task_list.add(deadLine);
                    break;
                case "E":
                    Task event = new Event(task_info, getDate(taskContent[3]), getTime(taskContent[3]));
                    if (status.equals("true")) {
                        event.task_completion = true;
                    }
                    task_list.add(event);
                    break;
                default:
                    break;
            }
        }
        return task_list;
    }

    public static String getTime(String s) {
        return s.substring(14);
    }

    public static LocalDate getDate(String s) {
        return LocalDate.parse(s.substring(3,13));
    }
}

