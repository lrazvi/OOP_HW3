import java.util.ArrayList;
//Tree for Composite Pattern
public class Tree implements Leaf {
    private ArrayList<Leaf> leaves;
    String treeRoot;
    
    public ArrayList<Leaf> getLeaves() {
        return leaves;
    }

    public void setLeaves(ArrayList<Leaf> leaves) {
        this.leaves = leaves;
    }

    public void addLeaf(Leaf leaf){
        leaves.add(leaf);
    }

    public UserSubject getUserFromTree(String id){
        
        if (this.getLeaves() != null) {
            for( Leaf l : this.getLeaves()){
                if (l.getID().equals(id) && l instanceof UserSubject) {
                    return (UserSubject) l;
                }
            }
        }
                
        return null;
    }

    @Override
    public String getID() {
        return treeRoot;
    }

    @Override
    public void setID(String ID) {
        this.treeRoot = ID;
    }

    @Override
    public void display() {
        System.out.println("Displaying TreeView: " + treeRoot);
        for(Leaf leaf : this.getLeaves()){
            leaf.display();
        }
    }

    // @Override
    // public int accept(DataVisitor visitor) {
    //     return visitor.visit(this);
    // }

    //Visitor pattern - accept from interface Node

}

