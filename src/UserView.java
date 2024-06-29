import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;


public class UserView {
    static JTextArea idInput;
    static JTextArea tweetInput;
    static JButton follow;
    static JButton postTweet;
    static JPanel followPan;
    static JList followList;
    static JPanel tweetPan;
    static DefaultListModel model;
    static JList obsFeed;
    static Stack<Command> lastUpdateStack = new Stack<>();

    static AdminControlPanel inst = AdminControlPanel.getInstance();

    public static void generateFrame(JLabelClick j){

        UserSubject currUser = inst.newTree.getUserFromTree(j.getName());   //current userview user



        idInput = new JTextArea("User ID"); tweetInput = new JTextArea();
        follow = new JButton("Follow User"); postTweet = new JButton("Post Tweet");
        followPan = new JPanel(); tweetPan = new JPanel(); 
        model = new DefaultListModel<>(); followList = new JList<>(model);

        DefaultListModel<String> fModel = new DefaultListModel<String>(); 
        obsFeed = new JList<String>(fModel);


        tweetInput.setToolTipText("add a tweet");
        
        JFrame frame = new JFrame(currUser.getID() + "'s User View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());

        JLabel fTitle = new JLabel("Current Following: ");

        followPan.setPreferredSize(new Dimension(400, 250));
        followPan.setLayout(new FlowLayout());
        followPan.setBackground(Color.PINK);
        followPan.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Dimension inputDimension = new Dimension(100, 30);
        Dimension labelDimension = new Dimension(380, 30);
        Dimension listDimension = new Dimension(400, 200);
        idInput.setPreferredSize(inputDimension);
        fTitle.setPreferredSize(labelDimension);
        followList.setPreferredSize(listDimension);

        follow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting user from idinput to follow
                UserSubject followSub = inst.newTree.getUserFromTree(idInput.getText());
                if (followSub != null) {    //if the id exists in the tree, follow that user
                    currUser.followSubject(followSub);
                    model.addElement(followSub.getID());
                }

                followPan.revalidate();
                followPan.repaint();
                followPan.updateUI();
            }
        });

        JLabel tweetFeed = new JLabel("Tweet Feed");
        tweetFeed.setPreferredSize(labelDimension);
        tweetInput.setPreferredSize(inputDimension);
        obsFeed.setPreferredSize(listDimension);

        postTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currUser.setTweeString(tweetInput.getText());         //post the tweet & notifyObservers
                
                currUser.setLastTimeUpdated(System.currentTimeMillis());
                UpdateTime updtTime = new UpdateTime(currUser);         //DECORATOR PATTERN IMPLEMENTATION ASSIGNMENT 3
                updtTime.timeAttribute();
                
                inst.lstUpdt = new FindLastUpdate(updtTime, inst.newTree);  //COMMAND PATTERN IMPLEMENTATION ASSIGNMENT 3
                
                
                
                // inst.tV = inst.newTree.accept(inst.totals);
                // System.out.println("TWEET VISITOR " + inst.newTree.accept(inst.totals));
                inst.tV += 1; //tweet visitor to be completed
                
                fModel.addElement(currUser.getTweeString());          //get the tweet to display on observers feed

                tweetPan.revalidate();
                tweetPan.repaint();
                tweetPan.updateUI();

            }
        });

        followPan.add(idInput); followPan.add(follow); followPan.add(fTitle);
        followPan.add(followList); 

        tweetPan.add(tweetInput); tweetPan.add(postTweet); tweetPan.add(tweetFeed);
        tweetPan.add(obsFeed);


        JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, followPan, tweetPan);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(400,500);
        frame.getContentPane().add(sl);
    }

}
