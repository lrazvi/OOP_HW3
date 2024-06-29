import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.awt.event.*;

public class AdminControlPanel implements ActionListener {
    JTextArea t, t2;
    JButton b, b2, b3, uTotal, gTotal, tTotal, posPercnt, idVer, userUpdt;
    JLabelClick root;
    JPanel pan, panel;
    ArrayList<String> IDs = new ArrayList<>();
    ArrayList<JLabelClick> clckGroup = new ArrayList<>();
    Tree newTree = new Tree();
    GroupUser rootGroup;
    ButtonGroup oneClick;
    String indent;
    int numFrames;
    DataVisitor totals;
    int uV, gV, tV;
    long crtdTime;
    FindLastUpdate lstUpdt;
    
    //Singleton Pattern for AdminControlPPanel
    private static AdminControlPanel instance;
    
    public static AdminControlPanel getInstance(){
        if (instance == null) {
            instance = new AdminControlPanel();
        }
        return instance;
    }

    private AdminControlPanel(){}

    public void initializeTree(){
        newTree.setID("Root");
    }

    public void renderWindow(){
        initializeTree();
        t = new JTextArea("User ID"); t2 = new JTextArea("Group ID");
        b = new JButton("Add User"); b2 = new JButton("Add Group");
        b3 = new JButton("Open User View");
        root = new JLabelClick(newTree.getID());
        uTotal = new JButton("User Total"); gTotal = new JButton("Group Total");
        tTotal = new JButton("Tweet Total"); posPercnt = new JButton("Positive %");
        idVer = new JButton("ID Verification"); userUpdt = new JButton("Last User Updt");

        JFrame frame = new JFrame("Admin Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());        
        
        panel = new JPanel();
        pan = new JPanel();
        pan.setLayout(new GridLayout(IDs.size(), 1));
 
        b3.setEnabled(false);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Observer Pattern
                numFrames++;
                UserView.generateFrame(JLabelClick.getSelectedGroup());
            }
        });
        b.addActionListener(this);
        b.setEnabled(false);
        b2.addActionListener(this);
        b2.setEnabled(false);

        //Visitor Pattern...

        totals = new CalcDataVisitor();
        uTotal.setToolTipText(Integer.toString(uV));
        gTotal.setToolTipText(Integer.toString(gV));
        tTotal.setToolTipText(Integer.toString(tV));

        idVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command verify = new VerifyID(newTree);
                verify.execute();                       //Command Pattern implementation (verify IDs)
            }
            
        });
        userUpdt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstUpdt != null) {
                    lstUpdt.execute();                       //Command Pattern implementation (Last updated User)

                }
            }
            
        });
        
        JSplitPane sl = new JSplitPane(SwingConstants.VERTICAL, panel, pan);
        sl.setOrientation(SwingConstants.VERTICAL); 
        sl.setDividerLocation(248);
        frame.add(pan);

        t.setPreferredSize(new Dimension(100, 30));
        t2.setPreferredSize(new Dimension(100, 30));
        panel.add(t); panel.add(b);
        panel.add(t2); panel.add(b2);
        panel.add(b3); panel.add(uTotal); panel.add(gTotal); panel.add(tTotal);
        panel.add(posPercnt); panel.add(idVer); panel.add(userUpdt);
        
        root.addMouseListener(root);
        pan.add(root, BorderLayout.PAGE_START);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,400);
        frame.getContentPane().add(sl);
    }

    public void createUser(String id){
        //creating new user to add to tree
        UserSubject newSubject = new UserSubject();

        newSubject.setID(id);
        newSubject.setFollowList(new ArrayList<>());

        newSubject.setTimeCreated(System.currentTimeMillis());
        Decorator crTime = new CreationTime(newSubject);        //DECORATOR PATTERN IMPLEMENTATION ASSIGNMENT 3 (time created)
        crTime.timeAttribute();
        

        // System.out.println("VISITOR " + newSubject.accept(totals));
        // uV = newSubject.accept(totals);

        if (newTree.getLeaves() == null) {
            newTree.setLeaves(new ArrayList<Leaf>());
        }
        
        //group has to be selected in order to add
        if (root.getBorder() != null) {
            newTree.addLeaf(newSubject);
            indent = "\t";
        }

        else{ //adding user to groups that aren't root
            for (Leaf l : newTree.getLeaves()){
                if (l instanceof GroupUser  && l.getID() != "Root") {
                    newTree.addLeaf(newSubject);
                    l.addLeaf(newSubject);
                    indent = "        ";
                    break;
                }
            }
        }

    }
    public void createGroup(String id){
        //creating new group to add to tree
        GroupUser newGroup = new GroupUser();
        newGroup.setID(id);
        // System.out.println("GROUP VISITOR " + newGroup.accept(totals));
        gV = newGroup.accept(totals);
        panel.revalidate();
        panel.updateUI();
        panel.repaint();

        newGroup.setTimeCreated(System.currentTimeMillis());
        Decorator crTime = new CreationTime(newGroup);        //DECORATOR PATTERN IMPLEMENTATION ASSIGNMENT 3 (time created)
        System.out.print(newGroup.getID() + " ");
        crTime.timeAttribute();

        if (newTree.getLeaves() == null) {
            newTree.setLeaves(new ArrayList<Leaf>());
        }
        if (root.getBorder() != null) {     //if root is selected
            newTree.addLeaf(newGroup);
            indent = "\t";
        }
        //adding group to group
        else{ 
            for (Leaf l : newTree.getLeaves()){
                if (l instanceof GroupUser && l.getID() != "Root") {
                    l.addLeaf(newGroup);
                    newTree.addLeaf(newGroup);
                    indent = "        ";
                    break;
                }
            }
        }
    }
        


    @Override
    public void actionPerformed(ActionEvent e) {
        JLabelClick la = new JLabelClick("newID");
        String sub;

        la.addMouseListener(la);
        

        if (e.getSource() == b) {
            la.setText(t.getText());
            createUser(la.getText());
            la.setName(la.getText());

            la.setText("\t - " + t.getText());
            la.setForeground(Color.LIGHT_GRAY);
        }
        else if (e.getSource() == b2) {
            la.setText(t2.getText());
            createGroup(la.getText());
            la.setText("\t - " + t2.getText());

            la.setForeground(Color.DARK_GRAY);
        }

        //calculate where we are in the hierarchy...keeping track of groups
        clckGroup.add(la); //list of clickable label ids

        //if root is clicked, default indent, else accumulate the indentation (for tree structure)
        if (root.getBorder() != null ) {
            la.setText(indent + la.getText());
        }
        else if (JLabelClick.getSelectedGroup().getBorder() != null){
            sub = JLabelClick.getSelectedGroup().getText();
            sub = sub.substring(sub.indexOf(" "), sub.indexOf("-"));
            la.setText(indent.concat(sub) + la.getText());
        }


        pan.add(la);
        pan.revalidate();
        pan.repaint();
    }
}





//creaate users & user groups
    //Methods: createUserID, getFollowers, getFollowings
        //createUserGroupID, setRoot?, numUsers, numGroups, numTweets, percentPosTweets
        //followUser, addTweet

//Composite Pattern for TreeView
    //interface Leaf? class tree Composite to compile all leaves(users&groups) 
    //initialize Root as first group
//Observer for newsfeed & following
//Singleton for controlpanel
//Visitor for user,group,tweets total & %posTweets



