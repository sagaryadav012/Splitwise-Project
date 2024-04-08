package com.lld.splitwise.commands;

import com.lld.splitwise.controllers.SettleUpController;
import com.lld.splitwise.dtos.SettleGroupRequestDto;
import com.lld.splitwise.dtos.SettleGroupResponseDto;
import com.lld.splitwise.exceptions.InvalidCommandException;
import com.lld.splitwise.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpGroupCommand implements Command{
    private SettleUpController settleUpController;
    private static final String SETTLE_UP_GROUP_KEY = "SettleUpGroup";

    @Autowired
    public SettleUpGroupCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
        CommandRegistry.getInstance().register(SETTLE_UP_GROUP_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        // eg input : SettleUpGroup group_id
        String[] s = input.split(" ");
        if(s.length != 2){
            throw new InvalidCommandException("Register user command not in correct format");
        }
        int groupId = 0;
        try {
            groupId = Integer.parseInt(s[1]);
        }
        catch (Exception e){
            throw new InvalidCommandException("Group ID should be numeric");
        }
        SettleGroupRequestDto requestDto = new SettleGroupRequestDto();
        requestDto.setGroupId(groupId);
        SettleGroupResponseDto responseDto = settleUpController.settleGroup(requestDto);
        List<Transaction> transactions = responseDto.getTransactions();

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
