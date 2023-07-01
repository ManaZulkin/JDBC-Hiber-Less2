package withoutDAO;

import withoutDAO.DAO.DAOFctory;
import withoutDAO.DAO.DiscDAO;
import withoutDAO.DAO.TrackDAO;
import withoutDAO.Interfaces.IDAOFactory;
import withoutDAO.Interfaces.IDiscDAO;
import withoutDAO.Interfaces.ITrackDAO;
import withoutDAO.entetes.DIsc;
import withoutDAO.entetes.Track;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DIsc dIsc = new DIsc("Test");
        Track track = null;
        ITrackDAO trackDAO = new TrackDAO();
        IDiscDAO discDAO = new DiscDAO();
        System.out.println("Time to write traks to your disc");
        writing(discDAO);

        //System.out.println(dIsc);

        System.out.println("All tracks \n" + trackDAO.getAll());

        System.out.println("Disc playlist \n" + discDAO.getAll());

        System.out.println("Track by id");
        System.out.println(trackDAO.getById(chuseID()));

        System.out.println("Set id for delete");
        trackDAO.delete(chuseID());
        System.out.println("This track is deleted!");


        System.out.println("Total time: " + discDAO.totalTime());

        System.out.println("Enter in witch time limit do yu want to find track");
        serch(discDAO);

        System.out.println("Now i will sort your track by style for your comfort");
        discDAO.sortByStile();
       // System.out.println(dIsc);


    }

    public static int chuseID(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = in.nextInt();
        in.nextLine();
        return id;
    }

    public static void writing(IDiscDAO discDAO){
        String name ;
        Scanner in = new Scanner(System.in);
        IDAOFactory daoFctory = DAOFctory.getFactory();
        ITrackDAO trackDAO = daoFctory.getTrackDAO();

        while (true){
            System.out.println("Enter name");
            name = in.nextLine();
            Track track = new Track();
            if (name.equalsIgnoreCase("end"))
                break;
            track.setName(name);
            System.out.println("Enter time of this track");
            track.setTime(in.nextDouble());
            in.nextLine();
            System.out.println("Enter Style");
            track.setStyle(in.nextLine());
            discDAO.write(track);
            trackDAO.add(track);
        }
        System.out.println("Writing complette!");

    }

    public static void serch(IDiscDAO discDAO){
        double min, max;
        Scanner in = new Scanner(System.in);
        System.out.println("Minimum:");
        min = in.nextDouble();
        System.out.println("Maximum:");
        max = in.nextDouble();
        discDAO.serchByTime(min, max);
    }
}
