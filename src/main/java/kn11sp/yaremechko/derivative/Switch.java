package kn11sp.yaremechko.derivative;

import kn11sp.yaremechko.commands.Command;

import java.util.HashMap;
public class Switch {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
    }

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}