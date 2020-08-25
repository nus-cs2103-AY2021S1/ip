package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {
    public static String parseMainCommand(String input) {
        return input.split(" ")[0].strip();
    }

    public static HashMap<String, String> parseParameters(String input) {
        input = input.replace(parseMainCommand(input), "").strip();

        HashMap<String, String> parameters = new HashMap<>();

        if (input.equals("")) {
            return parameters;
        }

        List<String> split = Arrays.asList(input.split("/"));
        parameters.put("argument", split.get(0).strip());

        for (String pair : split.subList(1, split.size())) {
            String key = pair.split(" ")[0];
            parameters.put(key, pair.replace(key, "").strip());
        }

        return parameters;
    }
}
