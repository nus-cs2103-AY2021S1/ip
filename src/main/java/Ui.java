import java.util.Arrays;
import java.util.List;

public class Ui {
    public void greet() {
        print("Hello! I'm Duke", "What can I do for you?");
    }

    public void showLoadingError() {
        print("Oops! I wasn't able to load past tasks properly :(");
    }

    public void print(List<String> strings) {
        final String INDENT = "\t";
        final String SEPARATOR = "_".repeat(69);

        System.out.println(INDENT + SEPARATOR);
        for(String s: strings) {
            System.out.println(INDENT + INDENT + s);
        }
        System.out.println(INDENT + SEPARATOR + "\n");
    }

    public void print(String ...strings) {
        print(Arrays.asList(strings));
    }
}
