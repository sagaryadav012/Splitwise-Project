package com.lld.splitwise.commands;

import com.lld.splitwise.exceptions.InvalidCommandException;

public interface Command {
    void execute(String input) throws InvalidCommandException;
}
