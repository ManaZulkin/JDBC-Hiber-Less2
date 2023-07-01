package withoutDAO.DAO;

import withoutDAO.Interfaces.IDiscDAO;
import withoutDAO.entetes.Track;

import java.sql.*;
import java.util.ArrayList;

public class DiscDAO implements IDiscDAO {
    private final String GET_ALL = "select id, name, time, style from disk join playlist where disk.trackid = playlist.id";
    private final String WRITE_ON_DISC = "Insert into disk(trackid, discId) values (?,?)";
    private final String SORT_BY_STYLE ="select * from playlist join disk  where disk.trackId = playlist.id  order by style ASC ";
    private final String SERCH_BY_TIME = "SELECT * from disk join playlist where disk.disk.trackId = playlist.id and playlist.time between ? and ?";
    private final String TOTAL_TIME = "select  time from disk join playlist where disk.trackId = playlist.id";

    @Override
    public ArrayList<Track> getAll() {
        ArrayList<Track> result = new ArrayList<>();
        try(Connection connection = getConection();
            PreparedStatement getAll = connection.prepareStatement(GET_ALL)){
            ResultSet resultSet = getAll.executeQuery();
            while (resultSet.next()){
                result.add(new Track(resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getInt(1)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void write(Track track) {
        try {
            Connection connection = getConection();
            PreparedStatement write = connection.prepareStatement(WRITE_ON_DISC);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from disk.playlist");
            int maxid = 0;
            while (resultSet.next()) {
                maxid = resultSet.getInt(1) ;
            }
            write.setInt(1, maxid);
            write.setInt(2, maxid);
            write.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortByStile() {
        try(Connection connection = getConection();
            PreparedStatement sort = connection.prepareStatement(SORT_BY_STYLE)){
            ResultSet resultSet = sort.executeQuery();
            ArrayList<Track> result = new ArrayList<>();
            while (resultSet.next()){
                result.add(new Track(resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getInt(1)));
            }
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void serchByTime(double min, double max) {
        try(Connection connection = getConection();
            PreparedStatement serch = connection.prepareStatement(SERCH_BY_TIME)) {
            serch.setDouble(1,min);
            serch.setDouble(2, max);
            ResultSet resultSet = serch.executeQuery();
            while (resultSet.next()){
                System.out.println(new Track(resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getInt(1)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public double totalTime() {
        double totalTime = 0;
        try(Connection connection = getConection();
            PreparedStatement timeSelect = connection.prepareStatement(TOTAL_TIME)){
            ResultSet resultSet = timeSelect.executeQuery();
            while (resultSet.next()){
                totalTime += resultSet.getDouble(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return totalTime;
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
