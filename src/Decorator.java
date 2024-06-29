//Decorator pattern abstract class
//Extended in UpdateTime.java & CreationTime.java
public abstract class Decorator implements UserGroupComponent {
    private UserGroupComponent groupOrUser;


    public Decorator(UserGroupComponent user){
        super();
        this.groupOrUser = user;
    }
    

    public UserGroupComponent getGroupOrUser() {
        return groupOrUser;
    }
    
    public void setGroupOrUser(UserGroupComponent groupOrUser) {
        this.groupOrUser = groupOrUser;
    }
 
}


//decorator
//or user&groups are components that implement interface (component)
//decorator implements component and references user/group
       //concrete decorator creationTime, lastUpdateTime?
 

//Command
//verify & lastUpdate command
//command interface w/ function execute()
    //command implementations reference tree?
        //lastupdate undo...record the actions; lastUpdateTime stack that can get userID?