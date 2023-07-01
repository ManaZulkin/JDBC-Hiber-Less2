package withoutDAO.Interfaces;

import withoutDAO.entetes.Track;

import java.util.ArrayList;

public interface IDiscDAO {
    ArrayList<Track> getAll();
    void write(Track track);
    void sortByStile();
    void serchByTime(double min, double max);
    double totalTime();
}
