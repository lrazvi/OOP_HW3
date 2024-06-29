import javax.swing.DefaultListModel;

//Class for observers to get updated for Observer Pattern
//Providing user ids since they're also users
public class FollowerObserver implements Observers, Leaf{
    String id;
    DefaultListModel<String> twtFeed;

    public DefaultListModel<String> getTwtFeed() {
        return twtFeed;
    }

    @Override
    public void update(Subject s) {
        if (s instanceof UserSubject) {
            //add to tweet feed.
            if (this.twtFeed  == null) {
                twtFeed = new DefaultListModel<>();
            }
            this.twtFeed.addElement(((UserSubject) s).getTweeString());
        }
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public void setID(String ID) {
        this.id = ID;
    }

    @Override
    public void addLeaf(Leaf leaf) {}

    @Override
    public void display() {}

}
