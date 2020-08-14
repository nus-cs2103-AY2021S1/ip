// Command.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Optional;

public class Command {
    private final CommandType type;
    private final Optional<String> itemName;
    private final Optional<Integer> itemIndex;

    private Command(CommandType type) {
        this.type = type;
        this.itemName = Optional.empty();
        this.itemIndex = Optional.empty();
    }

    private Command(CommandType type, String name) {
        this.type = type;
        this.itemName = Optional.of(name);
        this.itemIndex = Optional.empty();
    }

    private Command(CommandType type, int idx) {
        this.type = type;
        this.itemIndex = Optional.of(idx);
        this.itemName = Optional.empty();
    }

    public CommandType getType() {
        return this.type;
    }

    public Optional<String> getItemName() {
        return this.itemName;
    }

    public Optional<Integer> getItemIndex() {
        return this.itemIndex;
    }


    public static Command quit() {
        return new Command(CommandType.Quit);
    }

    public static Command addItem(String item) {
        return new Command(CommandType.AddItem, item);
    }

    public static Command listItems() {
        return new Command(CommandType.ListItems);
    }

    public static Command markItemAsDone(int idx) {
        return new Command(CommandType.MarkItemAsDone, idx);
    }

    public static Command deleteItem(int idx) {
        return new Command(CommandType.DeleteItem, idx);
    }

    public enum CommandType {
        Quit,
        AddItem,
        ListItems,
        MarkItemAsDone,
        DeleteItem
    }
}
