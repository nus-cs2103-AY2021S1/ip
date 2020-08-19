import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke, como estas mi amigo?";
    private final static String ACTION_LIST = "list";
    private final static String TERMINATION = "bye";
    private final static String TERMINATION_MESSAGE = "Adios, amigos!";
    private final static String TASK_COMPLETED = "done";
    private final static String TASK_TODO = "todo";
    private final static String TASK_DEADLINE = "deadline";
    private final static String TASK_EVENT = "event";
    private final static String DEADLINE_DATE = "/by";
    private final static String EVENT_DATE = "/at";
    private static ArrayList<Task> userInputsList = new ArrayList<>();
    //throw errors when the output wrong but other than that no errors

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.next();
            switch (userInput) {
                case ACTION_LIST:
                    System.out.println("Estas son las tareas de su lista:");
                    for (int i = 0; i < userInputsList.size(); i++) {
                        System.out.println((i + 1) + ". " + userInputsList.get(i).toString());
                    }
                    break;
                case TERMINATION:
                    System.out.println(TERMINATION_MESSAGE);
                    userInputsList.clear();
                    break;
                case TASK_COMPLETED:
                    System.out.println("Agradable! He marcado esta tarea como hecha:");
                    int positionDone = sc.nextInt() - 1;
                    userInputsList.get(positionDone).completeTask();
                    System.out.println(userInputsList.get(positionDone));
                    break;
                case TASK_TODO:
                    userInput = sc.nextLine();
                    int position = userInputsList.size() + 1;
                    Todo newTodo = new Todo(position, userInput);
                    userInputsList.add(newTodo);
                    System.out.println("Entendido. He agregado esta tarea:\n" +
                             newTodo +
                            "\nAhora tienes " + userInputsList.size() +" tareas en la lista. ");
                    break;
                case TASK_DEADLINE:
                    userInput = sc.nextLine();
                    int byPosition = userInput.indexOf(DEADLINE_DATE);
                    position = userInputsList.size() + 1;
                    String taskDescription = userInput.substring(0, byPosition);
                    String dateDescription = userInput.substring(byPosition + 3);
                    Deadline newDeadline = new Deadline(position, taskDescription);
                    newDeadline.setTime(dateDescription);
                    userInputsList.add(newDeadline);
                    System.out.println("Entendido. He agregado esta tarea:\n" +
                            newDeadline +
                            "\nAhora tienes " + userInputsList.size() +" tareas en la lista. ");
                    break;
                case TASK_EVENT:
                    userInput = sc.nextLine();
                    int atPosition = userInput.indexOf(EVENT_DATE);
                    position = userInputsList.size() + 1;
                    taskDescription = userInput.substring(0, atPosition);
                    dateDescription = userInput.substring(atPosition + 3);
                    Event newEvent = new Event(position, taskDescription);
                    newEvent.setTime(dateDescription);
                    userInputsList.add(newEvent);
                    System.out.println("Entendido. He agregado esta tarea:\n" +
                            newEvent +
                            "\nAhora tienes " + userInputsList.size() +" tareas en la lista. ");
                    break;
            }
            if (userInput.equals(TERMINATION)) {
                sc.close();
                break;
            }
        }
    }
}
