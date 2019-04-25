package tsp;

import java.util.*;

public class SchoolData {
    private HashMap<Path, Integer> map;
    private HashSet<Path> paths;
    SchoolData(HashMap<Path, Integer> map){
        this.map = map;
        paths = new HashSet(map.keySet());
    }
    List<String> bestPath(HashSet<String> schedule) throws NonHamiltonianTourPointsException{
        Solver solver = new Solver(updateMap(scheduleToHallways(schedule)));
        return hallwaysToSchedule(solver.bestPathStartingAnywhere(),schedule);
    }

    private HashSet<Hallway> scheduleToHallways(HashSet<String> schedule) {
        HashSet<Hallway> halls = new HashSet<>();
        for (String room : schedule) {
            subLoop: for(Path path: paths){
                for(Object hall:path.getPoints()){
                    if(((Hallway)hall).getName().contains(room.split("")[0]))
                        break subLoop;
                }
            }
        }
        return halls;
    }

    private List<String> hallwaysToSchedule(List<String> zones, HashSet<String> schedule)
    {
        List<String> bestSchedule = new ArrayList<>();
        for (String roomZone : zones) {
            for (String room : schedule) {
                if (room.contains(roomZone)) {
                    bestSchedule.add(room);
                }
            }
        }
        return bestSchedule;
    }
    private HashMap updateMap(HashSet<String> schedule){
        HashMap<Path,Integer> updatedMap = new HashMap<>();
        for(Path path:new HashSet<>(map.keySet())){
            if(schedule.containsAll(path.getPoints())) updatedMap.put(path, map.get(path));
        }
        return updatedMap;
    }
}