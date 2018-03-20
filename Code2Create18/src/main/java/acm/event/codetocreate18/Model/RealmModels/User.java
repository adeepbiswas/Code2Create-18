package acm.event.codetocreate18.Model.RealmModels;



import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{
    @PrimaryKey
    public String email;
    public String name;
    public String gender;
    public boolean hasTeam;
    public String teamName;
    public boolean isLeader;
    public int noOfMembers;
    public RealmList<TeamMember> teamMembers;
}
