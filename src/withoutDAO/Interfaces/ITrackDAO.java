package withoutDAO.Interfaces;

import withoutDAO.entetes.Track;

import java.util.ArrayList;

public interface ITrackDAO {
    ArrayList<Track> getAll();

    Track getById(int id);

    void add(Track track);

    void delete(int id);
}
