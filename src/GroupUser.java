import java.util.ArrayList;

//User Groups for Composite Pattern
public class GroupUser implements Leaf, Node, UserGroupComponent{
    private String groupId;
    private ArrayList<Leaf> usersInGroup = new ArrayList<Leaf>();
    private long timeCreated;
    
    public ArrayList<Leaf> getUsersInGroup() {
        return usersInGroup;
    }

    @Override
    public String getID() {
        return groupId;
    }

    @Override
    public void setID(String ID) {
        this.groupId = ID;
    }

    @Override
    public void addLeaf(Leaf leaf) {
        this.usersInGroup.add(leaf);
    }

    @Override
    public void display() {
        System.out.println(" ");
        System.out.println("Displaying group: " + groupId);
        for(Leaf leaf : usersInGroup){
            leaf.display();
            //total group
        }
        System.out.println(" ");
    }

    //Visitor
    @Override
    public int accept(DataVisitor visitor) {
        return visitor.visit(this);
    }


    //For Decorator Pattern
    @Override
    public void timeAttribute() {}

    public void setTimeCreated(long time) {
        this.timeCreated = time;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public long getLastTimeUpdated() {return 0;}

}
