import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static List<Task> tasks = new ArrayList<>(100);
    public static int numberOfTasks = 0;

    public static List<String> getResponse(String s, String prefix, String[] suffix) throws DukeException {
        List<String> response = new ArrayList<>();

        if (prefix.equals("bye")) {
            if (suffix.length != 0) {
                throw new DukeException("the 'bye' command shouldn't have anything behind.");
            } else {
                response.add("Bye. Hope to see you again soon!");
            }
        } else if (prefix.equals("list")) {
            if (suffix.length != 0) {
                throw new DukeException("the 'list' command shouldn't have anything behind.");
            } else {
                response.add("Here are the tasks in your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    Task t = tasks.get(i);
                    response.add(String.format("%d.%s", i + 1, t.toString()));
                }
            }
        } else if (prefix.equals("todo")) {
            if (suffix.length == 0) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                String[] arr = s.split(" ", 2);
                String description = arr[1];
                Task t = new Todo(description);
                tasks.add(t);
                numberOfTasks++;
                response.add("Got it. I've added this task:");
                response.add("  " + t.toString());
                response.add("Now you have " + numberOfTasks + " tasks in the list.");
            }
        } else if (prefix.equals("deadline")) {
            boolean hasIdentifier = false;
            int index = -1;
            for (int i = 0; i < suffix.length; i++) {
                if (suffix[i].equals("/by")) {
                    index = i;
                    hasIdentifier = true;
                    break;
                }
            }
            if (hasIdentifier && index > 0 && suffix.length - 1 > index) {
                StringBuilder description = new StringBuilder();
                for (int i = 0; i < index; i++) {
                    if (i == 0) {
                        description.append(suffix[i]);
                    } else {
                        description.append(" ").append(suffix[i]);
                    }
                }
                StringBuilder doBy = new StringBuilder();
                for (int j = index + 1; j < suffix.length; j++) {
                    if (j == index + 1) {
                        doBy.append(suffix[j]);
                    } else {
                        doBy.append(" ").append(suffix[j]);
                    }
                }
                Task t = new Deadline(description.toString(), doBy.toString());
                tasks.add(t);
                numberOfTasks++;
                response.add("Got it. I've added this task:");
                response.add("  " + t.toString());
                response.add("Now you have " + numberOfTasks + " tasks in the list.");
            } else {
                throw new DukeException("Ensure that deadlines have correct description.");
            }
        } else if (prefix.equals("event")) {
            boolean hasIdentifier = false;
            int index = -1;
            for (int i = 0; i < suffix.length; i++) {
                if (suffix[i].equals("/at")) {
                    index = i;
                    hasIdentifier = true;
                    break;
                }
            }
            if (hasIdentifier && index > 0 && suffix.length - 1 > index) {
                StringBuilder description = new StringBuilder();
                for (int i = 0; i < index; i++) {
                    if (i == 0) {
                        description.append(suffix[i]);
                    } else {
                        description.append(" ").append(suffix[i]);
                    }
                }
                StringBuilder when = new StringBuilder();
                for (int j = index + 1; j < suffix.length; j++) {
                    if (j == index + 1) {
                        when.append(suffix[j]);
                    } else {
                        when.append(" ").append(suffix[j]);
                    }
                }
                Task t = new Event(description.toString(), when.toString());
                tasks.add(t);
                numberOfTasks++;
                response.add("Got it. I've added this task:");
                response.add("  " + t.toString());
                response.add("Now you have " + numberOfTasks + " tasks in the list.");
            } else {
                throw new DukeException("Ensure that events have correct description.");
            }
        } else if (prefix.equals("done")) {
            if (suffix.length != 1) {
                throw new DukeException("done should be followed by a single task number.");
            } else {
                int taskIndex =Integer.parseInt(suffix[0]) - 1;
                if (taskIndex < 0 || taskIndex > numberOfTasks - 1) {
                    throw new DukeException("Task does not exist/invalid task number.");
                } else {
                    Task t = tasks.get(taskIndex);
                    t.markAsDone();
                    response.add("Nice! I've marked this task as done:");
                    response.add("  " + t.toString());
                }
            }
        } else if (prefix.equals("delete")) {
            if (suffix.length != 1) {
                throw new DukeException("delete should be followed by a single task number.");
            } else {
                int deleteIndex = Integer.parseInt(suffix[0]) - 1;
                if (deleteIndex < 0 || deleteIndex > numberOfTasks - 1) {
                    throw new DukeException("Task does not exist/invalid task number.");
                } else {
                    Task t = tasks.get(deleteIndex);
                    tasks.remove(deleteIndex);
                    numberOfTasks--;

                    assert numberOfTasks == tasks.size();

                    response.add("Noted. I've removed this task:") ;
                    response.add("  " + t.toString());
                    response.add("Now you have " + numberOfTasks + " tasks in the list.");
                }
            }
        } else {
            throw new DukeException();
        }
        return response;
    }

    private static void save() throws IOException {
        FileWriter fw = new FileWriter(".\\data\\duke.txt");
        StringBuilder textToWrite = new StringBuilder();
        for (Task t : tasks) {
            String entry = t.getStringType() + " / " + t.isDoneToString() + " / " +
                    t.getDescription() + t.getDate().map(result -> " / " + result).orElse("");
            textToWrite.append(entry).append("\n");
        }
        fw.write(String.valueOf(textToWrite));
        fw.close();
    }

    private static void read(File taskData) throws FileNotFoundException {
        Scanner sc = new Scanner(taskData);
        while (sc.hasNext()) {
            String entry = sc.nextLine();
            String[] entryData = entry.split(" / ", 4);
            String type = entryData[0];
            boolean isDone = entryData[1].equals("1");
            String description = entryData[2];

            Task t = null;
            if (type.equals("T")) {
                t = new Todo(description);
                if (isDone) { t.markAsDone(); }
            } else if (type.equals("D")) {
                String by = entryData[3];
                t = new Deadline(description, by);
            } else if (type.equals("E")) {
                String at = entryData[3];
                t = new Event(description, at);
            } else {
                System.out.println("Invalid taskData entry");
            }

            if (t != null) { tasks.add(t); numberOfTasks++; }
        }
    }

    public static void main(String[] args) {
        boolean running = true;

        String logo =
                "#############################################################\n" +
                "###|░██╗░░░░░░░██╗██╗░░░██╗███████╗|###############   #######\n" +
                "###|░██║░░██╗░░██║██║░░░██║██╔════╝|###########   /~\\   #####\n" +
                "###|░╚██╗████╗██╔╝██║░░░██║█████╗░░|########   _- `~~~', ####\n" +
                "###|░░████╔═████║░██║░░░██║██╔══╝░░|######  _-~       )  ####\n" +
                "###|░░╚██╔╝░╚██╔╝░╚██████╔╝██║░░░░░|###  _-~          |  ####\n" +
                "###|░░░╚═╝░░░╚═╝░░░╚═════╝░╚═╝░░░░░#  _-~            ;  #####\n" +
                "##########################  __---___-~              |   #####\n" +
                "#######################   _~   ,,                  ;  `,,  ##\n" +
                "#####################  _-~    ;'                  |  ,'  ; ##\n" +
                "###################  _~      '                    `~'   ; ###\n" +
                "############   __---;                                 ,' ####\n" +
                "########   __~~  ___                                ,' ######\n" +
                "#####  _-~~   -~~ _                               ,' ########\n" +
                "##### `-_         _                              ; ##########\n" +
                "#######  ~~----~~~   ;                          ; ###########\n" +
                "#########  /          ;                        ; ############\n" +
                "#######  /             ;                      ; #############\n" +
                "#####  /                `                    ; ##############\n" +
                "###  /                                      ; ###############\n" +
                "#                                            ################";
        System.out.println(logo);

        System.out.println(
                "#############################################################\n" +
                "#     Henlo! I am WUF,\n" +
                "#     here to track yowr tasks!\n" +
                "#############################################################\n");

        try {
            String filePathString = ".\\data\\duke.txt";
            File taskData = new File(filePathString);
            if (taskData.exists()) {
                read(taskData);
            } else {
                //System.out.println(new File(".").getCanonicalPath());
                File dir = new File(".\\data");
                dir.mkdir();
                boolean created = taskData.createNewFile();
                assert created;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (running) {
            String input = sc.nextLine();

            String[] arr = input.split(" ", 2);
            String prefix = arr[0];
            String[] suffix = new String[0];
            if (arr.length > 1) {
                suffix = arr[1].split(" ", 0);
            }

            try {
                List<String> response = getResponse(input, prefix, suffix);
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < response.size(); i++) {
                    str.append("#    ").append(response.get(i)).append("\n");
                }
                String reply = "#############################################################\n" +
                        str.toString() +
                        "#############################################################";
                System.out.println(reply);
                if (prefix.equals("bye")) {
                    running = false;
                }

                // Save current list of tasks
                save();

            } catch (DukeException e) {
                String reply = "#############################################################\n" +
                        "#    " + e.toString() + "\n" +
                        "#############################################################";
                System.out.println(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
