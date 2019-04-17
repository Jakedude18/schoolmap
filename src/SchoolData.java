import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SchoolData {
    private HashMap<Path,Integer> map;
    SchoolData(HashMap map){
        this.map = map;
    }
    List<String> bestPath(HashSet<String> schedule) throws NonHamiltonianTourPointsException{
        return zonesToSchedule(new Main(updateMap(scheduleToZones(schedule))).bestPathStartingAnywhere(),schedule);
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
        for(Path path:new HashSet<>(map.keySet())){
            if(schedule.containsAll(path.getPoints())) updatedMap.put(path, map.get(path));
        }
        return updatedMap;
    }
}