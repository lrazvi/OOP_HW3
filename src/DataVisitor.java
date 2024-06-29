//Visitor Pattern
public interface DataVisitor {
    public int visit(UserSubject subject);
    public int visit(GroupUser groupUser);
    public int visit(Tree tree);
}
