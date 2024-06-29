import java.util.Stack;

//Decorator Pattern, User Last update time (concrete implementation of Decorator/UserComponent)
public class UpdateTime extends Decorator{
    private Stack<UserGroupComponent> lastID = new Stack<>(); //stack to track last updated user
    UserSubject curr;

    public UpdateTime(UserGroupComponent c){
        super(c);
    }


    public Stack<UserGroupComponent> getLastID() {
        return lastID;
    }

    public void setLastID(Stack<UserGroupComponent> lastID) {
        this.lastID = lastID;
    }


    @Override
    public void timeAttribute(){
        System.out.println("TIME UPDATED: " + getGroupOrUser().getLastTimeUpdated());
        lastID.push(getGroupOrUser());              //push user to stack when updated
    }


    //unused
    @Override
    public long getTimeCreated() {return 0;}
    @Override
    public long getLastTimeUpdated() {return 0;}

}
