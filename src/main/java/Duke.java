import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    static char line = '*';
    static List<Task> tasks = new ArrayList<>();
    private static FileWriter fw;
    private static File file;

    static void loopMethod() {
        //gets input and displays it
        Scanner sc = new Scanner(System.in);
        String word = "";
        while (sc.hasNext()) {
            word = sc.nextLine();

            if (word.equals("bye")) {
                printTopLine();
                System.out.println("Bye. Hope to see you again soon!");
                printBottomLine();
                return;
            } else if (word.equals("list")) {
                printTopLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                printBottomLine();
            } else if (word.startsWith("done")) {
                try {
                    int index = Character.getNumericValue(word.charAt(5));
                    Task originalTask = tasks.get(index);
                    Task nextTask = originalTask.setTaskAsCompleted();
                    tasks.set(index, nextTask);
                    printTopLine();
                    System.out.println("Nice! I've marked this task as done: \n" + nextTask);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new InvalidArgumentException("Sorry, the task cannot be found!"));
                    continue;
                }
            } else if (word.startsWith("todo")) {
                try {
                    int index = word.indexOf(" ");
                    if (index == -1) {
                        System.out.println(new EmptyArgumentException("No task name given"));
                        continue;
                    }
                    word = word.substring(index + 1);
                    Task toDo = new Todo(word);
                    tasks.add(toDo);
                    printTopLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(toDo);
                    String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                    System.out.println(numberTasks);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new EmptyArgumentException("No task name given"));
                    continue;
                }
            } else if (word.startsWith("deadline") || word.startsWith("event")) {
                int index = word.indexOf(' ');
                boolean isDeadline = word.startsWith("deadline");
                if (index == -1 || index == word.length() - 1) {

                    System.out.println(new EmptyArgumentException("No task name given"));
                    continue;
                }
                //get stuff after the space if there are still characters after the space
                String content = word.substring(word.indexOf(' ') + 1);
                index = content.indexOf('/');
                if (index == -1 || index == content.length() - 1) {
                    //nothing after the slash
                    System.out.println(new InvalidArgumentException("No deadline given!"));
                    continue;
                }
                //if there are words after the slash
                String taskName = content.substring(0, index);
                String keyword = content.substring(index + 1, index + 3);
                boolean matches = isDeadline && keyword.equals("by") || !isDeadline && keyword.equals("at");
                if (!matches) {
                    System.out.println(new EmptyArgumentException("Task name does not start with proper arguments"));
                    continue;
                }
                try {
                    String time = content.substring(index + 4);
                    Task newTask = isDeadline ? new Deadline(taskName, time) : new Event(taskName, time);
                    tasks.add(newTask);
                    printTopLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                    System.out.println(numberTasks);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new EmptyArgumentException("No task name given"));
                    continue;
                }
            } else if (word.startsWith("delete")) {
                int index;
                try {
                    index = Integer.parseInt(word.substring(7));
                } catch (IndexOutOfBoundsException e) {
                    //no proper number given
                    System.out.println(new EmptyArgumentException("Sorry! Duke could not find the task number"));
                    continue;
                }
                try {
                    Task toBeDeleted = tasks.get(index);
                    tasks.remove(index);
                    printTopLine();
                    System.out.println("Noted. I've removed this task:\n" + toBeDeleted);
                    String numberTasks = String.format("Now you have %s %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                    System.out.println(numberTasks);
                    printBottomLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new InvalidArgumentException("Sorry! Duke could not find a task at the specified index"));
                    continue;
                }
            } else {
                System.out.println(new InvalidArgumentException());
                continue;
            }
        }
    }

    //Prints the top line, e.g. *******************
    static void printTopLine() {
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    //Prints the bottom line e.g. ****************
    static void printBottomLine() {
        System.out.println();
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    //Restarts program after an exception is thrown
    //Asks for input again
    static void restartAfterException() {
        try {
            loopMethod();
        } catch (Exception e) {
            System.out.println(e);
            restartAfterException();
        }
    }

    static void createFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.close();
    }

    static void loadFile(String filePath) {
        file = new File(filePath);
    }

    static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String task = s.nextLine();
            String taskName = "";
            if (task.startsWith("TN")) {
                //Todo, not yet done
                taskName = getStringAfterSpace(task);
                if (taskName.length() != 0) {
                    tasks.add(new Todo(taskName));
                }
            } else if (task.startsWith("TD")) {
                taskName = getStringAfterSpace(task);
                if (taskName.length() != 0) {
                    Task toDo = new Todo(taskName,true);
                    tasks.add(toDo);
                }
            } else if (task.startsWith("DN")) {
                //Deadline, not done
                String removeSpace = getStringAfterSpace(task); //get everything after "DN"
                String time = getStringBeforeSpace(removeSpace); //get time
                taskName = getStringAfterSpace(removeSpace);
                if (time.length() != 0 && taskName.length() != 0) {
                    Task deadline = new Deadline(taskName,time);
                    tasks.add(deadline);
                }
            } else if (task.startsWith("DD")) {
                //deadline, done
                String removeSpace = getStringAfterSpace(task); //get everything after "DD"
                String time = getStringBeforeSpace(removeSpace); //get time
                taskName = getStringAfterSpace(removeSpace);
                if (time.length() != 0 && taskName.length() != 0) {
                    Task deadline = new Deadline(taskName,time,true);
                    tasks.add(deadline);
                }
            } else if (task.startsWith("EN")) {
                //event, not done
                String removeSpace = getStringAfterSpace(task); //get everything after "EN"
                String time = getStringBeforeSpace(removeSpace); //get time
                taskName = getStringAfterSpace(removeSpace);
                if (time.length() != 0 && taskName.length() != 0) {
                    Task event = new Event(taskName,time);
                    tasks.add(event);
                }
            } else if (task.startsWith("ED")) {
                //event, done
                String removeSpace = getStringAfterSpace(task); //get everything after "ED"
                String time = getStringBeforeSpace(removeSpace); //get time
                taskName = getStringAfterSpace(removeSpace);
                if (time.length() != 0 && taskName.length() != 0) {
                    Task event = new Event(taskName,time,true);
                    tasks.add(event);
                }
            }
        }
    }

    static String getStringAfterSpace(String task) {
        int index = task.indexOf(" ");
        if (index == -1 || index == task.length() - 1) {
            //index not found, or index is last char in string
            return "";
        } else {
            //get everything after the space
            String taskName = task.substring(index + 1);
            return taskName;
        }
    }

    static String getStringBeforeSpace(String task) {
        //gets a string before the first space
        //e.g. if input is "Apple juice", method returns "Apple"
        int index = task.indexOf(" ");
        if (index == -1 || index == 0) {
            //space not found, or index is first char in String
            return "";
        } else {
            return task.substring(0,index);
        }
    }


    static void loadFileWriter(String filePath) throws IOException {
        fw = new FileWriter(filePath, true);
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String filePath = home + "/tasks.txt";
        loadFile(filePath);
        boolean fileExists = file.exists();
        if (fileExists) {
            try {
                readFile(filePath);
            } catch (FileNotFoundException e) {
                return;
            }
        }
        if (!fileExists) {
            try {
                createFile(filePath);
            } catch (IOException e) {
                return;
            }
        }

        try {
            loadFileWriter(filePath);
        } catch (IOException e) {
            return;
        }
        printTopLine();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        printBottomLine();
        loopMethod();
    }
}
