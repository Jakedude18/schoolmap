package tsp;

import java.util.HashSet;
import java.util.List;

public class PathFinder {
    public static void main(String rooms[]) throws NonHamiltonianTourPointsException, RoomDoesNotExistException{
        if(rooms.length < 2){
            System.out.println("Please enter more at least 2 rooms");
        }
        else if(rooms.length == 2){
            List bestPath = SchoolDataSets.LTHS.getSchoolData().roomToRoomPath(rooms[0],rooms[1]);
            System.out.printf("Here's how you get to the path you want %s%n", bestPath.toString());
        }else{
        HashSet schedule = new HashSet();
        for (String room : rooms){
            schedule.add(room);
        }
        List bestPath = SchoolDataSets.LTHS.getSchoolData().bestPathStartingAnywhere(schedule);
        System.out.printf("Best path is as follows %s%n", bestPath.toString());
        }
    }
}
