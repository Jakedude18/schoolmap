package tsp;

import java.util.HashSet;
import java.util.List;

public class PathFinder {
    public static void main(String rooms[]) throws NonHamiltonianTourPointsException, RoomDoesNotExistException{
        if(rooms.length < 2){
            System.out.println("Please enter more at least 2 rooms");
        }
        else if(rooms.length == 2){
            try {
                List<String> bestPath = SchoolDataSets.LTHS.getSchoolData().roomToRoomPath(rooms[0], rooms[1]);
                System.out.printf("Here's how you get too " + rooms[0] + " from " + rooms[1] + "%s%n", bestPath.toString());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
        HashSet schedule = new HashSet();
        for (String room : rooms){
            schedule.add(room);
        }
        try {
            List<String> bestPath = SchoolDataSets.LTHS.getSchoolData().bestPathStartingAnywhere(schedule);
            System.out.printf("Best path is as follows %s%n", bestPath.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
            }
        }
    }
}
