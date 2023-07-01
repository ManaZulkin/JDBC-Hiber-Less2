package withoutDAO.Interfaces;

import withoutDAO.DAO.DiscDAO;
import withoutDAO.DAO.TrackDAO;

public interface IDAOFactory {
    public DiscDAO getDiscDAO();
    public TrackDAO getTrackDAO();
}
