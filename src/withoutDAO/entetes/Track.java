package withoutDAO.entetes;

public class Track {
    int id = 0;
    double time;
    String name;
    String style;

    public double getTime() {
        return time;
    }

    public Track() {
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + this.name + " - " + this.time + " sek" + ", Style " + this.style;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Track( String name, double time, String style, int id) {
        this.time = time;
        this.name = name;
        this.style = style;
        this.id = id;
    }
}
