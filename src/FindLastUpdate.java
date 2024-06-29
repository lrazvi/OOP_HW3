import java.util.Stack;

//Command Pattern for last user update
public class FindLastUpdate implements Command{
    private Tree tree;
    Stack<Command> uStack = new Stack<>();
    UpdateTime updt;

    public FindLastUpdate(UpdateTime u, Tree tree){
        super();
        this.updt = u;
        this.tree = tree;
    }

    @Override
    public void execute() {

        for(Leaf l : tree.getLeaves()){
            if (l.equals(updt.getLastID().peek())) {
                System.out.println("LAST UPDATED USER: " + l.getID());
                break;
            }
        }
    }

}
