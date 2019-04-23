package tsp;

import java.util.List;

public class Hallway {
    private String name;
    private List<String> rooms;
    private int distance;

    Hallway(String name,List<String> rooms, int distance){
        this.name = name;
        this.rooms = rooms;
        this.distance = distance;
    }

    int getDistance(){
        return distance;
    }
}
