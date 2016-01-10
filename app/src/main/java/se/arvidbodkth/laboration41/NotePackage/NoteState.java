package se.arvidbodkth.laboration41.NotePackage;

import java.io.Serializable;

/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * Class for the noteState.
 */
public class NoteState implements Serializable {

    private String id, state, title, date, body, imageName;

    /**
     * Constructor for the noteSaveState.
     * @param state the state.
     * @param id the id.
     * @param title the title.
     * @param date the date.
     * @param body the body.
     * @param imageName the image uri.
     */
    public NoteState(String state, String id, String title, String date, String body
            , String imageName){
        this.id = id;
        this.state = state;
        this.title = title;
        this.date = date;
        this.body = body;
        this.imageName = imageName;
    }

    /**
     * Returns the content of the saveState.
     * @return String, the content.
     */
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

    /**
     * Returns the id of the note.
     * @return String id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id od the note.
     * @param id String, id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the body of the note.
     * @return String with the body.
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the body of the note.
     * @param body String the body to set.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the date string for the note.
     * @return String with the date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the note.
     * @param date String with the date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the img uri from the note.
     * @return String with the uri.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Returns the state as a string.
     * @return String of state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the title of the note.
     * @return Strign with the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the note.
     * @param title The title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
