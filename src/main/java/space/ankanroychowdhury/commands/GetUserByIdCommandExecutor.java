package space.ankanroychowdhury.commands;

import space.ankanroychowdhury.OutputPrinter;
import space.ankanroychowdhury.models.Command;
import space.ankanroychowdhury.models.User;
import space.ankanroychowdhury.services.UserService;

public class GetUserByIdCommandExecutor extends UserCommandExecutor {
    public static String COMMAND_NAME = "GETUSER";

    public GetUserByIdCommandExecutor(final UserService userService, final OutputPrinter outputPrinter) {
        super(userService, outputPrinter);
    }


    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final String userId = command.getParams().get(0);
        User user = userService.getOrThrow(userId);
        outputPrinter.printWithNewLine("<"+user.getId()+", "+user.getName()+", "+user.getWallet()+">");
    }
}
