package tsp;

import java.util.HashSet;
import java.util.List;

public class Hallway {
    private String name;
    private HashSet<String> rooms;
    private int distance;

    Hallway(String name,HashSet<String> rooms, int distance){
        this.name = name;
        this.rooms = rooms;
        this.distance = distance;
    }

    int getDistance(){
        return distance;
    }

    HashSet<String> getRooms(){
        return rooms;
    }
}
