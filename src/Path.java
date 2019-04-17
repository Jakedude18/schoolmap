
import java.util.HashSet;

public class Path {
    private HashSet<String> points = new HashSet<>(2);
    Path(String point1,String point2){
        points.add(point1);
        points.add(point2);
    }

     void displayPath(){
        String message = "2 points:";
        for(String place:points){
            message += " " + place;
        }
        System.out.println(message);
    }
    String otherPoint(String notPoint) {
        for(String point: points){
            if(!point.equals(notPoint)){
                return point;
            }
        }
        return null;
    }
    HashSet<String> getPoints(){
        return points;
     }
}
