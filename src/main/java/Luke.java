import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Luke {
    public static void main(String[] args) {
        File tlFile = new File("./data/", "luke.txt");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        readTasks(taskList, tlFile);
        System.out.printf("Luke:\n\tHey there! I'm Luke.\n\tPlease tell me what to add to your list.\nYou:\n");
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (Pattern.matches("^(todo) *.*$", input)) {
                try {
                    Todo newTodo = createTodo(input);
                    writeTasks(taskList, tlFile, newTodo);
                } catch (EmptyTodoException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (Pattern.matches("^(deadline) *.*$", input)){
                try {
                    Deadline newDeadline = createDeadline(input);
                    writeTasks(taskList, tlFile, newDeadline);
                } catch (EmptyDeadlineException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                } catch (InvalidDeadlineException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (Pattern.matches("^(event) *.*$", input)) {
                try {
                    Event newEvent = createEvent(input);
                    writeTasks(taskList, tlFile, newEvent);
                } catch (EmptyEventException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                } catch (InvalidEventException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (input.equals("list")) {
                if (taskList.size() < 1) {
                    System.out.printf("Luke:\n\tYou don't have any tasks in your list :(\nYou:\n");
                } else {
                    String todoSummary = "Luke:\n\tHere are the tasks in your list.";
                    for (int i = 0; i < taskList.size(); i++) {
                        Task current = taskList.get(i);
                        todoSummary += String.format("\n\t%d.%s", i + 1, current);
                    }
                    System.out.printf("%s\nYou:\n", todoSummary);
                }
            } else if (Pattern.matches("^(done) *[0-9]*$", input)) {
               try {
                   completeTask(input, taskList);
               } catch (InvalidDoneException e) {
                   System.out.printf("Luke:%s\nYou:\n", e.getMessage());
               } catch (DoneIndexOutofboundsException e) {
                   System.out.printf("Luke:%s\nYou:\n", e.getMessage());
               }
            } else if (Pattern.matches("^(delete) *[0-9]*$", input)) {
                try {
                    deleteTask(input, taskList, tlFile);
                } catch (InvalidDeleteException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                } catch (DeleteIndexOutofboundsException e) {
                    System.out.printf("Luke:%s\nYou:\n", e.getMessage());
                }
            } else if (input.equals("bye")) {
                System.out.println("Luke:\n\tOh, are you leaving? Hope to see you soon!");
                break;
            } else {
                System.out.printf("Luke:\n\tSorry I do not understand :( Please try another command.\nYou:\n");
            }
        }
    }

    private static void readTasks(ArrayList<Task> arrayList, File file) {
        // read tasks from hard disk (./data/luke.txt)
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String readLine = null;
            while((readLine = br.readLine()) != null){
                String[] readEach = readLine.split("\\|");
                Task task = null;
                if (readEach[0].equals("T")) {
                    task = new Todo(readEach[2]);
                } else if (readEach[0].equals("D")) {
                    task = new Deadline(readEach[2], readEach[3]);
                } else if (readEach[0].equals("E")) {
                    task = new Event(readEach[2], readEach[3]);
                }
                if (readEach[1].equals("1")) {
                    task.markAsDone();
                }
                arrayList.add(task);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void writeTasks(ArrayList<Task> arrayList, File file, Task task) {
        // add task to the list
        arrayList.add(task);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            // write task to hard disk (./data/luke.txt)
            String newTask = "";
            String stat = "";
            if (task.isDone) {
                stat = "1|";
            } else {
                stat += "0|";
            }
            if (task instanceof Todo) {
                newTask += "T|" + stat + task.getDescription();
            } else if (task instanceof Deadline) {
                newTask += "T|" + stat  + task.getDescription() + ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                newTask += "T|" + stat  + task.getDescription() + ((Event) task).getAt();
            }
            bw.newLine();
            bw.write(newTask);
            bw.flush();
            printAddSuccess(arrayList, task);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String countTasks(ArrayList<Task> arrayList) {
        int n = arrayList.size();
        return n <= 1
                ? String.format("%d task", n)
                : String.format("%d tasks", n);
    }

    private static void printAddSuccess(ArrayList<Task> arrayList, Task task) {
        String number = countTasks(arrayList);
        System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t\t%s\n\tNow you have %s in your list.\nYou:\n", task, number);
    }

    private static Todo createTodo(String input) throws EmptyTodoException {
        String todo = input.replaceAll("todo ", "");
        if (input.equals("todo") || input.equals("todo ")) {
            throw new EmptyTodoException("\n\tThe description of todo cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Todo(todo);
        }
    }

    private static Deadline createDeadline(String input) throws EmptyDeadlineException, InvalidDeadlineException {
        String[] deadline = input.split("deadline | /by ");
        if (input.equals("deadline") || input.equals("deadline ")) {
            throw new EmptyDeadlineException("\n\tThe description of deadline cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else if (deadline.length != 3) {
            throw new InvalidDeadlineException("\n\tYou have typed in an invalid deadline.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Deadline(deadline[1], deadline[2]);
        }
    }

    private static Event createEvent(String input) throws EmptyEventException, InvalidEventException {
        String[] event = input.split("event | /at ");
        if (input.equals("event") || input.equals("event ")) {
            throw new EmptyEventException("\n\tThe description of event cannot be empty.\n\tPlease make sure you follow the correct format.");
        } else if (event.length != 3) {
            throw new InvalidEventException("\n\tYou have typed in an invalid event.\n\tPlease make sure you follow the correct format.");
        } else {
            return new Event(event[1], event[2]);
        }
    }

    private static void completeTask(String input, ArrayList<Task> arrayList) throws InvalidDoneException, DoneIndexOutofboundsException {
        if (input.equals("done") || input.equals("done ")) {
            throw new InvalidDoneException("\n\tThe index of done cannot be empty.\n\tPlease make sure you follow the correct format.");
        }
        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
        int index = Integer.parseInt(numbering) - 1;
        if (index < 0 || index >= arrayList.size()) {
            throw new DoneIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
        } else {
            Task done = arrayList.get(index);
            done.markAsDone();
            System.out.printf("Luke:\n\tThe following task has successfully been marked as done!\n\t\t%s\nYou:\n", done);
        }
    }

    private static void deleteTask(String input, ArrayList<Task> arrayList, File file) throws InvalidDeleteException, DeleteIndexOutofboundsException {
        if (input.equals("delete") || input.equals("delete ")) {
            throw new InvalidDeleteException("\n\tThe index of delete cannot be empty.\n\tPlease make sure you follow the correct format.");
        }
        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
        int index = Integer.parseInt(numbering) - 1;
        if (index < 0 || index >= arrayList.size()) {
            throw new DeleteIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
        } else {
            Task delete = arrayList.get(index);
            arrayList.remove(index);
            File tempFile = new File("./data/", "temp.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                String readLine = "";
                int counter = 0;
                while((readLine = br.readLine()) != null) {
                    // remove task from hard disk (./data/luke.txt)
                    counter++;
                    if (counter == index + 1) {
                        continue;
                    }
                    bw.write(readLine);
                    bw.newLine();
                }
                bw.flush();
            } catch (IOException e) {
                System.out.println(e);
            }
            tempFile.renameTo(file);
            System.out.printf("Luke:\n\tThe following task has successfully been deleted!\n\t\t%s\nYou:\n", delete);
        }
    }
}