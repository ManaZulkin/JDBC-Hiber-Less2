package withoutDAO.DAO;

import withoutDAO.Interfaces.IDAOFactory;

public class DAOFctory implements IDAOFactory {

    private static IDAOFactory factory;

    private DAOFctory(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getFactory(){
        if (factory == null){
            factory = new DAOFctory();
        }
        return factory;
    }
    @Override
    public DiscDAO getDiscDAO() {
        return new DiscDAO();
    }

    @Override
    public TrackDAO getTrackDAO() {
        return new TrackDAO();
    }
}
