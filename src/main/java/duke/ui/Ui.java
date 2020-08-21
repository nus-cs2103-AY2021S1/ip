package duke.ui;

import java.util.Arrays;
import java.util.List;

public class Ui {
    public void say(List<String> strings) {
        final String indent = "  ";
        final String separator = indent + "_".repeat(68);

        System.out.println(separator);
        for (String s : strings) {
            System.out.println(indent + s);
        }
        System.out.println(separator);
        System.out.println();
    }

    public void say(String string) {
        say(Arrays.asList(string));
    }
}
