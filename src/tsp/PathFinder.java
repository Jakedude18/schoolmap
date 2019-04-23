package tsp;

import java.util.HashSet;
import java.util.List;

public class PathFinder {
    public static void main(String rooms[]) throws NonHamiltonianTourPointsException {
        HashSet schedule = new HashSet();
        for (String room : rooms){
            schedule.add(room);
        }
        List bestPath = SchoolDataSets.LTHS.getSchoolData().bestPath(schedule);
        System.out.printf("Best path is as follows %s%n", bestPath.toString());
    }
}
