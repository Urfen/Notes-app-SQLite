package se.arvidbodkth.laboration41.NotePackage;


/**
 * Created by arvidbodin on 27/12/15.
 *
 */
public class Note {

    private String id, title, body, date, imageName;

    public Note(String id, String title, String date, String body, String imageName){
        this.id = id;
        this.imageName = imageName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public Note(String title, String date, String body, String imageName){
        this.imageName = imageName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    @Override
    public String toString() {
        return id + "  :  " + title + "\nLast edit: " + date;
        /*return "Note{" +
                "body='" + body + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';*/
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
