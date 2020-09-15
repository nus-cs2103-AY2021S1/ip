package com.siawsam.duke;

import java.util.Arrays;
import java.util.List;

/**
 * A parser for user inputs.
 */
public class Parser {
    /**
     * An array of valid parameterized Duke commands.
     */
    private static final List<String> PARAMETERIZED_COMMANDS = Arrays.asList(
            "done",
            "delete",
            "find",
            "tag",
            "untag"
    );
    private final TaskList userTaskList;
    private final TagList tagList;
    private final Storage storage;
    
    /**
     * Constructs a Parser when no saved data exists.
     *
     * @param storage A Storage instance to use when the parser needs to save to disk.
     */
    public Parser(Storage storage) {
        userTaskList = new TaskList();
        tagList = new TagList();
        this.storage = storage;
    }
    
    /**
     * Constructs a Parser when saved data exists.
     *
     * @param storage      A Storage instance to use when the parser needs to save to disk.
     * @param userTaskList A TaskList instance that represents the existing save.
     * @param tagList      A TagList instance that represents an existing save.
     */
    Parser(Storage storage, TaskList userTaskList, TagList tagList) {
        this.userTaskList = userTaskList;
        this.tagList = tagList;
        this.storage = storage;
    }
    
    /**
     * Parses a string literal and executes the associated operation.
     *
     * @param literal The string literal to parse and execute.
     * @return The {@link Response response} of the executed operation.
     */
    public Response parseAndExecute(String literal) {
        String command = literal.split(" ")[0];
        boolean isValidCommand = PARAMETERIZED_COMMANDS.contains(command);
        boolean hasArgument = literal.split(" ").length > 1;
    
        if (isValidCommand && hasArgument) {
            // handle commands with >1 argument
            return parseParameterizedCommand(command, literal);
        } else {
            switch (literal) {
            case "bye":
                return Response.terminatingResponse(Ui.showGoodbyeMessage());
            case "list":
                return new Response(Ui.printList(userTaskList));
            case "tags":
                return new Response(Ui.printTags(tagList));
            case "save":
                return storage.save(userTaskList, tagList);
            default:
                return parseAddCommand(literal);
            }
        }
    }
    
    private Response parseParameterizedCommand(String command, String literal) {
        assert command.length() > 0 : "empty command string";
        
        switch (command) {
        case "done":
            return parseDoneCommand(literal);
        case "delete":
            return parseDeleteCommand(literal);
        case "find":
            return parseFindCommand(literal);
        case "tag":
            return parseTagCommand(literal);
        case "untag":
            return parseUntagCommand(literal);
        default:
            assert !PARAMETERIZED_COMMANDS.contains(command) : "invalid parameterized command";
            return Response.emptyResponse();
        }
    }
    
    private Response parseDoneCommand(String literal) {
        assert literal.length() > 0 : "empty command literal";
        
        try {
            return userTaskList.markAsDone(
                    Integer.parseInt(literal.split(" ")[1])
            );
        } catch (IllegalArgumentException ex) {
            return new Response(Ui.showErrorMessage(ex));
        }
    }
    
    private Response parseDeleteCommand(String literal) {
        assert literal.length() > 0 : "empty command literal";
        
        try {
            return userTaskList.removeItem(
                    Integer.parseInt(literal.split(" ")[1])
            );
        } catch (IllegalArgumentException ex) {
            return new Response(Ui.showErrorMessage(ex));
        }
    }
    
    private Response parseFindCommand(String literal) {
        assert literal.length() > 0 : "empty command literal";
        
        TaskSearcher searcher = new TaskSearcher(userTaskList);
        return searcher.searchAndDisplay(literal.split("find")[1].trim());
    }
    
    private Response parseAddCommand(String literal) {
        assert literal.length() > 0 : "empty command literal";
        
        try {
            Task addedTask = userTaskList.addItem(literal);
            return new Response(Ui.showSuccessfulAdd(addedTask));
        } catch (DukeException ex) {
            return new Response(Ui.showErrorMessage(ex));
        }
    }
    
    private Response parseTagCommand(String literal) {
        String parameters = literal.split("tag")[1].trim();
        try {
            String itemIndex = parameters.split(" ")[0];
            boolean isTagNameAbsent = parameters.split(itemIndex).length == 0;
    
            if (isTagNameAbsent) {
                throw new DukeException("No tag name specified.");
            }
    
            String tagName = parameters.split(itemIndex)[1].trim();
            return userTaskList.tagItem(Integer.parseInt(itemIndex), tagName, tagList);
        } catch (NumberFormatException | DukeException exception) {
            String errorMsg = Ui.showErrorMessage(new DukeException("Invalid tag command."));
            return new Response(errorMsg);
        } catch (IllegalArgumentException exception) {
            return new Response(Ui.showErrorMessage(exception));
        }
    }
    
    private Response parseUntagCommand(String literal) {
        try {
            return userTaskList.untagItem(
                    Integer.parseInt(literal.split(" ")[1]),
                    tagList
            );
        } catch (NumberFormatException ex) {
            return new Response("Invalid untag command.");
        } catch (IllegalArgumentException ex) {
            return new Response(Ui.showErrorMessage(ex));
        }
    }
}
