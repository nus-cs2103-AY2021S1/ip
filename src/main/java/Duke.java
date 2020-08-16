import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke, como estas mi amigo?";
    private final static String ACTION_LIST = "list";
    private final static String TERMINATION = "bye";
    private final static String TERMINATION_MESSAGE = "Adios, amigos!";
    private final static String TASK_COMPLETED = "done";
    private static ArrayList<Task> userInputsList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.next();
            switch (userInput) {
                case ACTION_LIST:
                    System.out.println("Estas son las tareas de su lista:");
                    for (Task task : userInputsList) {
                        System.out.println(task);
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
                    System.out.println("[✓] " + userInputsList.get(positionDone).getTaskDescription());
                    break;
                default:
                    userInput = userInput + sc.nextLine();
                    System.out.println("adicional: " + userInput);
                    int position = userInputsList.size() + 1;
                    userInputsList.add(new Task(position, userInput));
                    break;
            }
            if (userInput.equals(TERMINATION)) {
                sc.close();
                break;
            }
        }
    }
}
