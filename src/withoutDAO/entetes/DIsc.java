package withoutDAO.entetes;

import withoutDAO.Interfaces.IDisc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class DIsc implements IDisc {
    ArrayList<Track> playlist = new ArrayList<>();
    String name;

    public DIsc(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return playlist.toString();
    }

    public ArrayList<Track> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Track> playlist) {
        this.playlist = playlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeTrack(Track track){
        playlist.add(track);
    }

    public double totalTime(){
        double time = 0;
        ListIterator iterator = playlist.listIterator();
        while (iterator.hasNext()){
            time += playlist.get(iterator.nextIndex()).getTime();
            iterator.next();
        }
        return time;
    }

    public Track serchByTime(double min, double max){
        ListIterator iterator = playlist.listIterator();
        Track emptyTrack = new Track("Don't Exist", 0, "Unknown" , 0);
        ArrayList<Track> result = new ArrayList<>();
        while (iterator.hasNext()){
            if (min <= playlist.get(iterator.nextIndex()).getTime()){
                if (playlist.get(iterator.nextIndex()).getTime() <= max){
                    return playlist.get(iterator.nextIndex());
                }
            }
        }
        iterator.next();
        return emptyTrack;
    }

    public void sortByStyle(){
        ListIterator iterator = playlist.listIterator();
        Comparator compareByStyle = new SortByStyle();
        playlist.sort(compareByStyle);

    }
    public DIsc() {
    }
}

class SortByStyle implements Comparator<Track>{

    @Override
    public int compare(Track t1, Track t2) {
        return t1.getStyle().compareTo(t2.getStyle());
    }
}