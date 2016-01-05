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

    @Override
    public String toString() {
        return "Note{" +
                "body='" + body + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
