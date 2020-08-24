package main.java;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<String> parse(String cmd) {
        String[] splitted = cmd.split(" /");
        List<String> out = new ArrayList<>();
        for (String token : splitted) {
            String[] splitted2 = token.split(" ", 2);
            for (String tokenn : splitted2) out.add(tokenn);
        }
        if (out.get(0).equals("todo") || out.get(0).equals("deadline") || out.get(0).equals("event")) {
            if (out.size() >= 3) out.remove(2);
            if (out.size() <= 2) out.add("null");
            out.add("0");
        }
        return out;
    }
}
