package se.arvidbodkth.laboration41;

import java.io.Serializable;

/**
 * Created by Arvid on 2016-01-08.
 *
 */
public class NoteState implements Serializable {

    private String id, state, title, date, body, imageName;

    public NoteState(String state, String id, String title, String date, String body
            , String imageName){
        this.id = id;
        this.state = state;
        this.title = title;
        this.date = date;
        this.body = body;
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "NoteState{" +
                "body='" + body + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
