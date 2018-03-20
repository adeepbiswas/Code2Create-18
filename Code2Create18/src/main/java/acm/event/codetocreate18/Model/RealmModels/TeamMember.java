package acm.event.codetocreate18.Model.RealmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



public class TeamMember extends RealmObject {
    @PrimaryKey
    public String email;
    public String name;
    public boolean isLeader;
}
