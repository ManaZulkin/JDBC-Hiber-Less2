package withoutDAO.Interfaces;

import withoutDAO.entetes.Track;

import java.util.ArrayList;

public interface IDisc {

    Track serchByTime(double min, double max);

    void sortByStyle();

    void writeTrack(Track track);

}
