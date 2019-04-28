package tsp;

import java.util.HashSet;
import java.util.List;

public class PathFinder {
    public static void main(String rooms[]) throws NonHamiltonianTourPointsException {
        HashSet schedule = new HashSet();
        for (String room : rooms){
            schedule.add(room);
        }
        List bestPath = SchoolDataSets.LTHS.getSchoolData().bestPathStartingAnywhere(schedule);
        System.out.printf("Best path is as follows %s%n", bestPath.toString());
    }
    public static void main(String startingPosition, String endPositon) {
        List bestPath = SchoolDataSets.LTHS.getSchoolData().roomToRoomPath(startingPosition,endPositon);
        System.out.printf("Here's how you get to the path you want %s%n", bestPath.toString());
    }
}
