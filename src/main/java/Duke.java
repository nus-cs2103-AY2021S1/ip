import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> storage;
    private Path filePath = Path.of("/data","data.txt");
    //TODO: Hard code filePath in Duke() only.
    public Duke() {
        storage = new ArrayList<>();
        try {
            Files.createFile(this.filePath);
        } catch (IOException e) {
            try {
                Files.createDirectory(Path.of("/data"));
            } catch (IOException e2) {
                //System.out.println(e2.toString());
            }
        }
    }

    public Duke(Path filePath) {
        storage = new ArrayList<>();
        this.filePath = filePath;

        //initializes storage with save data
        try {
            /*
            File saveFile = new File("/data/data.txt");
            Scanner sc = new Scanner(saveFile);
            SaveManager sm = new SaveManager();
            while (sc.hasNext()) {
                storage.add(sm.loadTaskFromSave(sc.nextLine()));
            }
            */
            BufferedReader br = Files.newBufferedReader(filePath); //new BufferedReader(new FileReader("/data/data22.txt"));
            SaveManager sm = new SaveManager();
            String currLine = br.readLine();
            while (currLine != null) {
                storage.add(sm.loadTaskFromSave(currLine));
                currLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            try {
                Files.createFile(filePath);
            } catch (IOException e2) {
                //System.out.println("Unable to create new Data File");
                //System.out.println(e2.toString());
                try {
                    Files.createDirectory(filePath.getParent());
                    Files.createFile(filePath);
                } catch (IOException e3) {
                    System.out.println("Unable to create new Data File Again");
                    System.out.println(e2.toString());
                    System.out.println(e3.toString());
                }
            }
        } catch (DukeSaveDataException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public String saveDataGenerator() {
        SaveManager sm = new SaveManager();
        final String[] saveData = {""};

        this.storage.forEach(task -> saveData[0] += sm.toSaveFormat(task.convertToHashMap()));
        return saveData[0];
    }

    public void bye() throws DukeInputException{
        try {
            BufferedWriter bw = Files.newBufferedWriter(this.filePath);
            bw.write(this.saveDataGenerator());
            bw.close();
        } catch (IOException e) {
            throw new DukeInputException("Unable to write to file <" + this.filePath + "> when exiting");
        } finally {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    public void store(Task t) {
        this.storage.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t.toString());
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
    }

    public void addToDo(String input) throws DukeInputException{
        if (input.equals("")) {
            throw new DukeInputException("'todo' requires parameters.\n" +
                    "Use case: todo <name>");
        }
        ToDo newToDo = new ToDo(input);
        this.store(newToDo);
    }

    public void addDeadline(String input) throws DukeInputException{
        if (input.equals("")) {
            throw new DukeInputException("'deadline' requires parameters.\n" +
                    "Use case: deadline <name> /by <deadline>");
        }

        String[] params = input.split("/by ", 2);
        if (params.length != 2) {
            throw new DukeInputException("<" + input + "> is not valid for the 'deadline' command.\n" +
                    "Please add a /by deadline to the task.");
        }
        Deadline newDeadline = new Deadline(params[0], params[1]);
        this.store(newDeadline);
    }

    public void addEvent(String input) throws DukeInputException{
        if (input.equals("")) {
            throw new DukeInputException("'event' requires parameters.\n" +
                    "Use case: event <name> /at <timing>");
        }

        String[] params = input.split("/at ", 2);
        if (params.length != 2) {
            throw new DukeInputException("<" + input + "> is not valid for the 'event' command.\n" +
                    "Please add a /at timing to the task.");
        }
        Event newEvent = new Event(params[0], params[1]);
        this.store(newEvent);
    }

    public Task getTask(int i) {
        return this.storage.get(i-1);
    }

    public Task removeTask(int i) {
        return this.storage.remove(i-1);
    }

    public void doTask(String params) throws DukeInputException {
        if (params.equals("")) {
            throw new DukeInputException("'done' requires parameters.\n" +
                    "Use case: done <task number>");
        }
        int i;
        try {
            i = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <" + params + "> after a 'done' command!");
        }

        Task temp = this.getTask(i);
        temp.doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + temp.toString());
    }

    public void printList() {
        for (int i = 0; i < this.storage.size(); i++) {
            String printText = (i + 1) + ". " + this.storage.get(i).toString();
            System.out.println(printText);
        }
    }

    public void deleteTask(String params) throws DukeInputException{
        if (params.equals("")) {
            throw new DukeInputException("'delete' requires parameters.\n" +
                    "Use case: delete <task number>");
        }
        int i;
        try {
            i = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <" + params + "> after a 'delete' command!");
        }

        //TODO: Throw DukeInputException for wrong out of bounds index
        Task temp = this.removeTask(i);
        System.out.println("Alright. I've removed this task:");
        System.out.println("\t" + temp.toString());
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        //initialize Duke with save data and send welcome message
        //TODO: Find where is this file???? Cannot find this file anywhere.
        Duke duke = new Duke(Path.of("data/data.txt"));
        duke.greet();

        //input loop
        Scanner sc = new Scanner(System.in);

        while(true) {
            String s = sc.nextLine();
            String[] inputs = s.split(" ", 2);
            String command = inputs[0];
            String params = "";
            if (inputs.length == 2) {
                params = inputs[1];
            }

            //TODO: Capture the command switch as a method of Duke
            try {
                if (s.equals("bye")) {
                    break;
                } else if (s.equals("list")) {
                    duke.printList();
                } else if (command.equals("done")) {
                    duke.doTask(params);
                } else if (command.equals("todo")) {
                    duke.addToDo(params);
                } else if (command.equals("deadline")) {
                    duke.addDeadline(params);
                } else if (command.equals("event")) {
                    duke.addEvent(params);
                } else if (command.equals("delete")) {
                    duke.deleteTask(params);
                } else {
                    throw new DukeInputException("Invalid command <" + s + "> given.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        //send exit message
        try {
            duke.bye();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        /*testing code to check if printout to savefile worked
        try {
            Scanner sc2 = new Scanner(new File("/data/data.txt"));
            while (sc2.hasNext()) {
                System.out.println(sc2.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

         */

    }
}
