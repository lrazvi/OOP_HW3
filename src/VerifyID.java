//Command Pattern, ID verification
public class VerifyID implements Command{
    private Tree tree;

    public VerifyID(Tree tree){
        this.tree = tree;
    }

    @Override
    public void execute() {
        int dup = 0; //duplicate tracker

        //uniqueIDs + no spaces
        if (tree.getLeaves() != null) {
            for( Leaf l : tree.getLeaves()){
                for(Leaf e : tree.getLeaves()){
                    if (l.getID().equals(e.getID())) {
                        dup += 1;
                        if (dup > 1) {
                            System.out.println("IDs not valid, there are duplicates in the tree");
                            break;
                        }
                    }
                }
                if (dup > 1) {
                    break;
                }
                if (l.getID().contains(" ")) {
                    System.out.println("IDs not valid, IDs should not contain spaces");
                    break;
                }
                dup = 0;
            }
        }
        
    }

}
