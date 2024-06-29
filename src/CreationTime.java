//Decorator Pattern, User/Group Creation time (concrete implementation of Decorator/UserComponent)
public class CreationTime extends Decorator{

    public CreationTime(UserGroupComponent c) {
        super(c);
    }

    @Override
    public void timeAttribute(){
        System.out.println("TIME CREATED: " + getGroupOrUser().getTimeCreated());
    }


    //unused
    @Override
    public long getTimeCreated() {return 0;}
    @Override
    public long getLastTimeUpdated() {return 0;}


}
