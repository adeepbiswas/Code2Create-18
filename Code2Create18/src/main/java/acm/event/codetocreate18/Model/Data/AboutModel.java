package acm.event.codetocreate18.Model.Data;


import android.content.SharedPreferences;

public class AboutModel {
    private String name;
    private String designation;
    private Boolean isContact = false;
    private int imageResource;
    SharedPreferences.Editor sharedPreferencesEditor1;
    int childpos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition(int childpos) { return childpos;}

    public void setPosition(int childpos) {

        this.childpos=childpos;
        /*SharedPreferences sharedPreferences = getSharedPreferences("acm.event.codetocreate18.View.Authentication", Context.MODE_PRIVATE);
        SharedPreferences.Editor share= sharedPreferences.edit();
        share.putString("childpos",childpos);
        share.commit();*/
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getContact() {
        return isContact;
    }

    public void setContact(Boolean contact) {
        isContact = contact;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }


    public int getImageResource() {
        return imageResource;
    }
}

