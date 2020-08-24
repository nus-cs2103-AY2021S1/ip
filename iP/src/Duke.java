import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(
            "Hello! I'm Duke\n" +
            "What can I do for you?");

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String user_input = "";
        // load saved data
        try{
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }

        // main task
        while (true) {
            user_input = scanner.nextLine();  // Read user input
            try {
                user_input_handler(user_input, false);
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    static ArrayList<Task> taskList = new ArrayList<Task>();

    static void user_input_handler(String user_input, boolean loading) throws DukeException, IOException {
        if (user_input.equals("bye")) {
            // quit
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (user_input.equals("list")) {
            // list task
            System.out.println("Here are the tasks in your list:");
            int index = 1;
            for (Task task : taskList) {
                System.out.println(String.format("%s. %s", index, task));
                index += 1;
            }


        } else if (user_input.split(" ")[0].equals("done")) {
            // mark done
            int index = Integer.parseInt(user_input.split(" ")[1]) - 1;
            Task chosenTask = taskList.get(index);
            chosenTask.markAsDone();
            if (!loading){
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(chosenTask);
            }

        } else if (user_input.split(" ")[0].equals("delete")) {
            //delete task
            int index = Integer.parseInt(user_input.split(" ")[1]) - 1;
            Task chosenTask = taskList.get(index);
            taskList.remove(index);
            if (!loading){
                System.out.println("Noted. I've removed this task: ");
                System.out.println(chosenTask);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
            }

        } else if (user_input.split(" ")[0].equals("todo")) {
            // make to do
            if (user_input.split(" ", 2).length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = user_input.split(" ", 2)[1];
            Task todo = new Todo(description);
            taskList.add(todo);
            if (!loading){
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
            }

        } else if (user_input.split(" ")[0].equals("deadline")) {
            // make deadline
            // check if input is valid
            if (user_input.split(" ", 2).length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String task = user_input.split(" ", 2)[1];
            if (task.split(" /by ", 2).length < 2) {
                throw new DukeException("☹ OOPS!!! The description and time is required for deadline");
            }
            // get description
            String description = task.split(" /by ", 2)[0];
            // get time
            String time = task.split(" /by ")[1];
            LocalDate l_time = LocalDate.parse(time);
            // add deadline
            Task deadline = new Deadline(description, l_time);
            taskList.add(deadline);
            if (!loading) {
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
            }

        } else if (user_input.split(" ")[0].equals("event")){
            // make event
            if (user_input.split(" ", 2).length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            String task = user_input.split(" ", 2)[1];
            if (task.split(" /at ", 2).length < 2) {
                throw new DukeException("☹ OOPS!!! The description and time is required for event");
            }

            String description = user_input.split(" /at ", 2)[0];
            String time = task.split(" /at ")[1];
            LocalDate l_time = LocalDate.parse(time);
            // add event
            Task event = new Event(description, l_time);
            taskList.add(event);
            if (!loading){
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
            }

        } else {
            // invalid input
            if (!loading){
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        // save Data for every user input
        saveData(taskList);
    }

    static void loadData() throws DukeException, IOException {
        // read file
        try {
            File file = new File("./data/duke.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String user_input = reader.nextLine();
                user_input_handler(user_input, true);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // check if folder exists
            File path = new File("./data/");
            if (!path.isDirectory()){
                // data folder doesn't exist so create it
                path.mkdir();
            }
            File file = new File("./data/duke.txt");
            file.createNewFile(); // create file
            // Load again
            loadData();
        }
    }

    static void saveData(ArrayList<Task> taskList) throws IOException {
        File file = new File("./data/duke.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        int index = 1;
        for (Task task: taskList) {
            // convert task into instruction(user input)
            String taskInst = "";
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                taskInst = String.format("todo %s\n", todo.description);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                taskInst = String.format("event %s /at %s\n", event.description, event.eventTime);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                taskInst = String.format("deadline %s /by %s\n", task.description, deadline.deadline);
            }
            //write instruction to text file
            fileWriter.write(taskInst);
            // add done instruction if task is done
            if (task.isDone) {
                fileWriter.write(String.format("done %s\n", index));
            }
            index += 1;
        }
        fileWriter.close();
    }

}
