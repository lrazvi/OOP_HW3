//Composite Pattern
public interface Leaf {
    public void addLeaf(Leaf leaf);
    String getID();
    public void setID(String ID);
    public void display();
}
