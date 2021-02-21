package interpreter.command;

import java.util.ArrayList;

public class BlocksCommand extends Command{
    private ArrayList<Command> cmds;

    public BlocksCommand(int line){
        super(line);

        cmds = new ArrayList<Command>();
    }

    public void addCommand(Command cmd){
        cmds.add(cmd);
    }

    @Override
    public void execute(){
        for(Command cmd : this.cmds){
            cmd.execute();
        }
    }
}
