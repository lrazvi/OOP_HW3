//Visitor Pattern methods
public class CalcDataVisitor implements DataVisitor{
    int userTotal = 0;
    int groupTotal, tweetsTotal, posPercnt = 0;

    @Override
    public int visit(UserSubject s) {
        userTotal += 1;
        
        return (userTotal/ 2) + 1;  //adding /2 +1 due to function miscalculating
    }

    @Override
    public int visit(GroupUser g) {
        groupTotal += 1;
        return (groupTotal/ 2) + 1;
    }

    //Positive percentage and Total Tweets to be completed...
    //will possibly make separate class for tweets
    @Override
    public int visit(Tree tree) {
            tweetsTotal += 1;
            return (tweetsTotal/ 2) + 1;
    }


    // @Override
    // public int visitMessages(Tree tree) {
    //     for(Leaf l : tree.getLeaves()){
    //         if (l instanceof UserSubject) {
    //             //get total user tweets
    //             ((UserSubject) l).getUserTwtTotal();
    //             for( String twt : ((UserSubject) l).userTwtTotal){
    //                 if (twt.contains("cool") || twt.contains("nice") || twt.contains("awesome") ) {
    //                     posPercnt++;
    //                 }
    //             }
    //         }
    //     }
    //     return (posPercnt/visitTweets(tree)) * 100;
    // }



}
