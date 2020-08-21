package parser;

import exceptions.InvalidCommandException;
import utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Flag {
    BY("/by"),
    AT("/at"),
    NONFLAG(""),
    ENDFLAG("/end");

    private final String value;
    Flag(String value) {
        this.value = value;
    }

    public static boolean hasFormOfFlag(String s) {
        return s.length() > 0 && s.charAt(0) == '/';
    }

    private static Optional<Flag> getFlags(String token) {
        if (token.length() == 0) {
            return  Optional.empty();
        }
        for (Flag flag: Flag.values()) {
            if (token.equals(flag.value )) {
                return Optional.of(flag);
            }
        }
        return Optional.empty();
    }

    public static Map<Flag, String> parseFlags(String[] tokens) throws InvalidCommandException {
        StringBuilder sb = new StringBuilder();
        Flag currentFlag = NONFLAG;
        Map<Flag, String> result = new HashMap<>();
        for (String token : tokens) {
            if (hasFormOfFlag(token)) {
                Optional<Flag> flag = getFlags(token);
                if (flag.isEmpty()) {
                    throw new InvalidCommandException("Invalid flag");
                }
                if (sb.length() > 0) {
                    result.put(currentFlag, sb.toString());
                }
                currentFlag = flag.get();
                sb = new StringBuilder();
            } else {
                if (sb.length() > 0) sb.append(" ");
                sb.append(token);
            }
        }
        if (sb.length() > 0) {
            result.put(currentFlag, sb.toString());
        }
        return result;
    }
}
