package com.lld.splitwise.commands;

import com.lld.splitwise.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, Command> map;
    private CommandRegistry(){
        map = new HashMap<>();
    }
    private static final CommandRegistry INSTANCE = new CommandRegistry();

    public static CommandRegistry getInstance(){
        return INSTANCE;
    }

    public void register(String key, Command command){
        map.put(key, command);
    }
    public Command getCommand(String input) throws InvalidCommandException {
        for (Map.Entry<String, Command> entry : map.entrySet()) {
            String key = entry.getKey();
            if(input.contains(key)) return entry.getValue();
        }
        throw new InvalidCommandException("We do not support this command");
    }
}
