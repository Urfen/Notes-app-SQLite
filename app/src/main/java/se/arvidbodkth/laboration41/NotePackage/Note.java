package se.arvidbodkth.laboration41.NotePackage;


/**
 * Created by Arvid Bodin and Mattias Grehnik on 2016-01-03.
 *
 * This Class is a note with a id, title, date, body and an
 * URI for a image.
 */
public class Note {

    private String id, title, body, date, imageName;

    /**
     * Constructor for the note.
     * @param id note ID
     * @param title note Title
     * @param date note Date
     * @param body the body of the Note
     * @param imageName imgURI
     */
    public Note(String id, String title, String date, String body, String imageName){
        this.id = id;
        this.imageName = imageName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    /**
     * Constructor for note with no ID.
     * @param title note Title
     * @param date note Date
     * @param body the body of the Note
     * @param imageName imgURI
     */
    public Note(String title, String date, String body, String imageName){
        this.imageName = imageName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    /**
     * Get the note as a string.
     * @return
     */
    @Override
    public String toString() {
        return title + "\n" + date;
        /*return "Note{" +
                "body='" + body + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';*/
    }

    /**
     * Returns the body of the note.
     * @return String body.
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the date of the note.
     * @return String date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the id of the note.
     * @return String id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the notes id.
     * @param id String id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the imgURI from the note
     * @return String imgURI.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Returns the title of the note.
     * @return String title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the note.
     * @param title String title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
