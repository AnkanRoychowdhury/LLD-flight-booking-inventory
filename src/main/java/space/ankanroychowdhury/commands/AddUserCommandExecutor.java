package space.ankanroychowdhury.commands;

import space.ankanroychowdhury.OutputPrinter;
import space.ankanroychowdhury.models.Command;
import space.ankanroychowdhury.models.User;
import space.ankanroychowdhury.services.UserService;
import space.ankanroychowdhury.validator.DoubleValidator;

import java.util.List;

public class AddUserCommandExecutor extends UserCommandExecutor {
    public static String COMMAND_NAME = "ADDUSER";

    public AddUserCommandExecutor(final UserService userService, final OutputPrinter outputPrinter) {
        super(userService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommandName().equals(COMMAND_NAME)){
            return false;
        }
        final List<String> params = command.getParams();
        if(params.size() != 3){
            return false;
        }
        return DoubleValidator.isDouble(params.get(2));
    }

    @Override
    public void execute(Command command) {
        final String userId = command.getParams().get(0);
        final String userName = command.getParams().get(1);
        final Double userWallet = Double.parseDouble(command.getParams().get(2));
        User user = userService.addUser(userId, userName, userWallet);
        outputPrinter.printWithNewLine("<"+user.getId()+", "+user.getName()+", "+user.getWallet()+">");
    }
}
