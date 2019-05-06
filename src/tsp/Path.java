package tsp;

import java.util.HashSet;

public class Path<T> {
    private HashSet<T> points = new HashSet<>(2);
    Path(T point1,T point2){
        points.add(point1);
        points.add(point2);
    }

    public void displayPath(){
        String message = "2 points:";
        for(Object place:points){
            message += " " + place;
        }
        System.out.println(message);
    }
    public String otherPoint(String notPoint) {
        for(T point: points){
            if(!point.equals(notPoint)){
                return (String) point;
            }
        }
        return null;
    }
    public Hallway otherPoint(Hallway notHallway){
        for(T point: points){
            if(!point.equals(notHallway)){
                return (Hallway) point;
            }
        }
        return notHallway;
    }
    public HashSet<T> getPoints() {
        HashSet<T> points = new HashSet<>();
        for (Object obj : this.points) {
            points.add((T) obj);
        }
        return points;
    }
}
