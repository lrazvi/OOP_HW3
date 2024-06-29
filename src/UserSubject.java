import java.util.ArrayList;

//Single users class for Composite Pattern
//Subject and Observer for Observer Pattern
public class UserSubject extends Subject implements Observers, Leaf, Node, UserGroupComponent{
    private String ID;
    private String tweeString;
    static Tree tree = new Tree();
    FollowerObserver subjectObsInstance;
    ArrayList<String> userTwtTotal = new ArrayList<>();
    private long timeCreated;
    private long timeUpdated;


    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public void setID(String iD) {
        this.ID = iD;

        subjectObsInstance = new FollowerObserver();       //create observer instance of subject
        subjectObsInstance.setID(iD);
    }

    public FollowerObserver getObserverInstance(){
        return this.subjectObsInstance;
    }
    
    public void setFollowList(ArrayList<UserSubject> o) {
        this.followings = o;
    }

    public ArrayList<UserSubject> getFollowings() {
        return followings;
    }


    public void followSubject(UserSubject s1) {       //when this user follows another user (s1)
        boolean in = false;
        if (this.getFollowings() == null) {
            this.setFollowList(new ArrayList<>());
        }
        followings.add(s1);
        
        if (s1.getObserverList() == null) {
            s1.setObserverList(new ArrayList<>());
        }
        else {
            for(FollowerObserver o : s1.getObserverList()){
                if (o.getID() == this.ID) {
                    System.out.println(this.ID + " Already follows " + s1.getID());
                    in = true;
                    break;
                }
            }
        }

        if (!in) {
            s1.addObserver(this.getObserverInstance());
            System.out.println("OBSCENE: " + s1.getObserverList());     
        }
    }

    public String getTweeString() {
        return tweeString;
    }

    public void setTweeString(String tweeString) {
        this.tweeString = tweeString;
        this.userTwtTotal.add(tweeString);
        //notify observers and update feed
        notifyTweets();
    }

    public ArrayList<String> getUserTwtTotal() {
        return userTwtTotal;
    }
    

    @Override
    public void update(Subject s) {}
    @Override
    public void addLeaf(Leaf leaf) {}
    @Override
    public void display() {}

    //Visitor accept
    @Override
    public int accept(DataVisitor visitor) {
        return visitor.visit(this);
    }



    //For Decorator/Command Patterns
    @Override
    public void timeAttribute() { }

    public void setTimeCreated(long time) {
        this.timeCreated = time;
    }

    @Override
    public long getTimeCreated() {
        return timeCreated;
    }

    public void setLastTimeUpdated(long time) {
        this.timeUpdated = time;
    }

    @Override
    public long getLastTimeUpdated() {
        return timeUpdated;
    }


}
