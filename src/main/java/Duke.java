import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("hi! im conundrum boy :)");
        List<String> things = new ArrayList<String>();
        Scanner input = new Scanner(System.in);

        String inn;
        label:
        while (true) {
            inn = input.nextLine();
            switch (inn) {
                case "bye":
                    break label;
                case "":
                    continue;
                case "list":
                    for (int i = 1; i <= things.size(); i++) {
                        System.out.println(i + ". " + things.get(i - 1));
                    }
                    break;
                default:
                    System.out.println("added: " + inn);
                    things.add(inn);
                    break;
            }

        }
        System.out.println("bye bye!");

    }
}
