package withoutDAO.DAO;

import withoutDAO.Interfaces.ITrackDAO;
import withoutDAO.entetes.Track;

import java.sql.*;
import java.util.ArrayList;

public class TrackDAO implements ITrackDAO {
    public static final String INSERT = "Insert into playlist (name, time, style, id) values (?, ?, ?,?)";
    public static final String SELECT_ALL = "Select * from playlist";
    public static final String SELECT_BY_ID = "select * from playlist where id = ?";
    public static final String DELETE_BY_ID = "delete from playlist where id = ? ";
    @Override
    public ArrayList<Track> getAll() {
        ArrayList<Track> result = new ArrayList<>();
        Connection connection = getConection();
        try{
            Statement select = connection.createStatement();
            ResultSet resultSet = select.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                result.add(new Track(resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getInt(1)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Track getById(int id) {
        Track track = new Track();
        try( Connection connection = getConection();
             PreparedStatement getById = connection.prepareStatement(SELECT_BY_ID)) {

            getById.setInt(1, id);
            ResultSet resultSet = getById.executeQuery();
            while (resultSet.next()) {
                track.setName(resultSet.getString(2));
                track.setStyle(resultSet.getString(4));
                track.setTime(resultSet.getDouble(3));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return track;
    }

    @Override
    public void add(Track track) {
        Connection connection ;
        connection = getConection();

        try(PreparedStatement insert = connection.prepareStatement(INSERT)){
            insert.setString(1, track.getName());
            insert.setString(3, track.getStyle());
            insert.setDouble(2,track.getTime());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from disk.playlist");
            int maxid = 0;
            while (resultSet.next()) {
                 maxid = resultSet.getInt(1) + 1;
            }
            insert.setInt(4, maxid);
            insert.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = getConection();
            PreparedStatement delete = connection.prepareStatement(DELETE_BY_ID)){
            delete.setInt(1,id);
            Track track = new Track();
            delete.execute();
            ResultSet resultSet = (connection.createStatement().executeQuery(SELECT_ALL));
            while (resultSet.next()){
                track.setName(resultSet.getString(2));
                track.setStyle(resultSet.getString(4));
                track.setTime(resultSet.getDouble(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private Connection getConection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/disk", "root", "2319A1923b12");
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
