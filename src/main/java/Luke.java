import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> todo = new ArrayList<>();
        System.out.printf("Luke : Hey there! I'm Luke.\nPlease tell me what to add to your to-do list.\nYou : ");
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (input.equals("list")) {
                String list = "";
                for (int i = 0; i < todo.size(); i++) {
                    if (i == 0) {
                        list += String.format("%d. %s", i + 1, todo.get(i));
                    } else {
                        list += String.format("\n%d. %s", i + 1, todo.get(i));
                    }
                }
                System.out.printf("%s\nYou : ", list);
            } else if (input.equals("bye")) {
                System.out.println("Luke : Oh, are you leaving? Hope to see you soon!");
                break;
            } else {
                todo.add(input);
                System.out.printf("Luke : Added '%s' to your to-do list.\nYou : ", input);
            }
        }
    }
}
