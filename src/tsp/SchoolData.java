package tsp;

import java.util.*;

public class SchoolData {
    private HashMap(Map.get)
    SchoolData(HashSet<Path> paths,HashSet<Hallway> halls){
        this.halls = halls;
        this.paths = paths;
    }
    List<String> bestPath(HashSet<String> schedule) throws NonHamiltonianTourPointsException {
        Solver solver = new Solver(updateMap(scheduleToZones(schedule)));
        //generate map
        HashMap<Path,Integer>();
        HashSet<Hallway> scheduleHallways = schedlueToHallways(schedule);
        Iterator<Hallway> mainItr = scheduleHallways.iterator();
        while (mainItr.hasNext()) {
            Iterator<Hallway> itr = scheduleHallways.iterator();
            itr.remove();
            while (itr.hasNext()) {
                solver.pointToPointPath();
            }
            scheduleHallways.remove(hallway);
        }
        return zonesToSchedule(solver.bestPathStartingAnywhere(), schedule);
    }
    HashSet<Hallway> schedlueToHallways(HashSet<String> schedule){
        HashSet<Hallway> scheduleHalls = new HashSet<>();
        for(String room: schedule){
            Iterator<Hallway> itr = halls.iterator();
            Hallway currentRoom = itr.next();
            while(itr.hasNext()){
                if(currentRoom.getRooms().contains(room)) scheduleHalls.add(currentRoom);
            }
            halls.remove(currentRoom);
        }
    }


    private HashSet<String> scheduleToZones(HashSet<String> schedule) {
        HashSet<String> Zones = new HashSet<>();
        for (String room : schedule) {
            Zones.add(room.split("")[0]);
        }
        return Zones;
    }

    private List<String> zonesToSchedule(List<String> zones,HashSet<String> schedule)
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
        for(Path path:new HashSet<>(paths.keySet())){
            if(schedule.containsAll(path.getPoints())) updatedMap.put(path, paths.get(path));
        }
        return updatedMap;
    }
}