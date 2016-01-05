package se.arvidbodkth.laboration41;


/**
 * Created by arvidbodin on 27/12/15.
 *
 */
public class Note {

    private int id;
    private String title, body, date, imageName;

    public Note(String id, String title, String date, String body, String imageName){
        this.imageName = imageName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
