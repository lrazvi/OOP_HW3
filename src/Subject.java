import java.util.ArrayList;

//Observer Pattern abstract Subject class
public abstract class Subject {
    protected ArrayList<UserSubject> followings = new ArrayList<>(); 
    protected ArrayList<FollowerObserver> observers = new ArrayList<>(); 


    public ArrayList<FollowerObserver> getObserverList() {
        return observers;
    }

    public void setObserverList(ArrayList<FollowerObserver> observers) {
        this.observers = observers;
    }

    public void addObserver(FollowerObserver f){
        observers.add(f);
    }

    public void notifyTweets() {
        for (FollowerObserver o : observers) {
            System.out.println(o.getID() + " has been notified");
            o.update(this);
        }
    }
}
