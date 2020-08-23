import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Duke {
    static char line = '*';
    static List<Task> tasks = new ArrayList<>();
    private static File file;
    private static Storage storage;

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
                    updateStorage(tasks);
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
                    updateStorage(tasks);
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
                    //nothing after the slash, or slash is not found
                    System.out.println(new InvalidArgumentException("No deadline given!"));
                    continue;
                }
                //if there are words after the slash
                String taskName = content.substring(0, index - 1);
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
                    updateStorage(tasks);
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
                    updateStorage(tasks);
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

    static void updateStorage(List<Task> tasks) {
        List<String> taskStrings = tasks.stream().map(Task :: toString).collect(Collectors.toList());
        storage.updateFile(taskStrings);
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


    static void loadFile(String filePath) {
        file = new File(filePath);
    }

    static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        List<String> sentences = storage.readFile();
        for (String sentence : sentences) {
            String taskName = "";
            int index;
            if (sentence.startsWith("[T]")) {
                //Sentence is in the form [T][<Status of Task> ]  <Task Name>
                //find out whether task is done
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 1) {
                    continue;
                }
                //remove [T]
                sentence = sentence.substring(index + 1);
                boolean isCompleted = sentence.startsWith("[DONE]");
                //get index after closing bracket ]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 3) {
                    continue;
                }

                //get task name
                taskName = sentence.substring(index + 3);
                Task toBeAdded = isCompleted ? new Todo(taskName, true) : new Todo(taskName);
                tasks.add(toBeAdded);
            } else if (sentence.startsWith("[D]")) {
                //Sentence is in the form [D][<Status of Task>]  <Task Name>  (<Deadline>)
                //remove [D]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 1) {
                    continue;
                }
                sentence = sentence.substring(index + 1);


                //check if task is completed
                boolean isCompleted = sentence.startsWith("[DONE]");


                //get index after closing bracket ]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 3) {
                    continue;
                }

                //get task name, i.e. everything before opening bracket
                sentence = sentence.substring(index + 3);
                taskName = getStringBeforeCharacter(sentence, '(');
                if (taskName.length() == 0) {
                    //corrupted entry
                    continue;
                }

                //get task deadline
                String deadline = getStringAfterCharacter(sentence, ' ');
                if (deadline.length() == 0) {
                    //corrupted entry
                    continue;
                }
                //remove () brackets and by
                index = deadline.indexOf(":");
                if (index == -1 || index >= deadline.length() - 1) {
                    continue;
                }

                deadline = deadline.substring(index + 2);
                //remove ) closing bracket
                deadline = deadline.substring(0, deadline.length() - 1);
                //add task
                Task toBeAdded = new Deadline(taskName, deadline, isCompleted);
                tasks.add(toBeAdded);
            } else if (sentence.startsWith("[E")) {
                //Sentence is in the form [E][<Status of Task>]   (at: tmr)

                //remove [E]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 1) {
                    continue;
                }
                sentence = sentence.substring(index + 1);


                //check if task is completed
                boolean isCompleted = sentence.startsWith("[DONE]");


                //get index after closing bracket ]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 3) {
                    continue;
                }

                //get task name
                sentence = sentence.substring(index + 3);
                taskName = getStringBeforeCharacter(sentence, '(');
                if (taskName.length() == 0) {
                    //corrupted entry
                    continue;
                }

                //get task deadline
                String time = getStringAfterCharacter(sentence, ' ');
                if (time.length() == 0) {
                    //corrupted entry
                    continue;
                }

                //remove () brackets and at
                index = time.indexOf(":");
                if (index == -1 || index >= time.length() - 1) {
                    continue;
                }
                time = time.substring(index + 2);

                //remove ) closing bracket
                time = time.substring(0, time.length() - 1);

                //add task
                Task toBeAdded = new Event(taskName, time, isCompleted);
                tasks.add(toBeAdded);
            }
        }
    }


    static String getStringAfterCharacter(String task, Character character) {
        //gets String after character, e.g. if input is "Apple Juice" and character is " ", method returns "Juice"
        int index = task.indexOf(character);
        if (index == -1 || index == task.length() - 1) {
            //index not found, or index is last char in string
            return "";
        } else {
            //get everything after the space
            String taskName = task.substring(index + 1);
            return taskName;
        }
    }

    static String getStringBeforeCharacter(String task, char character) {
        //gets a string before the first character
        //e.g. if input is "Apple juice", and character is " ", method returns "Apple"
        int index = task.indexOf(character);
        if (index == -1 || index == 0) {
            //space not found, or index is first char in String
            return "";
        } else {
            return task.substring(0, index);
        }
    }


    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String filePath = home + "/tasks.txt";
        loadFile(filePath);
        boolean fileExists = file.exists();
        storage = new Storage(filePath);
        if (fileExists) {
            try {
                readFile(filePath);
            } catch (FileNotFoundException e) {
                System.out.println(e);
                return;
            }
        }
        printTopLine();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        printBottomLine();
        loopMethod();
    }
}
