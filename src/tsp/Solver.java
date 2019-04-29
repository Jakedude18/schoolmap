package tsp;

import java.util.*;

public class Solver {
    private HashSet<Path> allHallPaths;
    private HashSet<Hallway> allHalls;
    private HashMap<Path, Integer> map;
    private HashSet<Path> allPaths;
    private Hallway deadEndHall = new Hallway("Dead_end",new HashSet<>(), 500);
    public Solver(HashMap map) {
        this.map = map;
    }
    public Solver(HashSet<Path> paths, HashSet<Hallway> halls){
        this.allHallPaths = paths;
        this.allHalls = halls;
    }

    void displayInfo() {
        System.out.println("this is the paths :");
        for (Path path : allHallPaths) {
            path.displayPath();
        }
        System.out.println("This is the map: " + map);
    }
    List<String> roomToRoomPath(String firstRoom,String secondRoom) throws RoomDoesNotExistException{
        List<String> easyToReadPath = new ArrayList<>();
        Hallway firtRoomHall = roomToHall(firstRoom);
        for(Hallway hall: easyToReadListPath(hallToHallPath(firtRoomHall,roomToHall(secondRoom),new HashSet<>()),firtRoomHall)){
            easyToReadPath.add(hall.getName());
        }
        return easyToReadPath;
    }
    private HashMap<Path, Integer> generateMapUsingHalls(HashSet<String> roomSchedule) {
        HashSet<Hallway> hallSchedule = scheduleToHallways(roomSchedule);
        HashMap<Path, Integer> TSPmap = new HashMap<>();
        HashSet<Hallway> subSchedule = new HashSet(hallSchedule);
        Iterator<Hallway> itr;
        Hallway secondHall;
        for (Hallway firstHall : hallSchedule) {
            subSchedule.remove(firstHall);
            itr = subSchedule.iterator();
            while(itr.hasNext()){
                secondHall = itr.next();
                TSPmap.put(new Path(hallWayToRoom(roomSchedule,firstHall),hallWayToRoom(roomSchedule,secondHall)), pathListValue(hallToHallPath(firstHall, secondHall, new HashSet<>()), firstHall));
            }
        }
        return TSPmap;
    }
    //This function would be for if someone wanted to find the best possible path starting from any point on a map
    List<String> bestPathStartingAnywhere(HashSet<String> schedule) throws NonHamiltonianTourPointsException {
        map = generateMapUsingHalls(schedule);
        allPaths = new HashSet(map.keySet());
        HashSet<String> allStartingPoints = new HashSet<>();
        Iterator<Path> pathsItr = map.keySet().iterator();
        while (pathsItr.hasNext()) {
            allStartingPoints.addAll(pathsItr.next().getPoints());
        }
        Iterator<String> startingPointsItr = allStartingPoints.iterator();
        String currentLowestStartingPosition = startingPointsItr.next();
        List<Path> currentLowestPath = dynamicBestPath(currentLowestStartingPosition, new HashSet<>());
        while (startingPointsItr.hasNext()) {
            String currentStartingPosition = startingPointsItr.next();
            List<Path> currentPath = dynamicBestPath(currentStartingPosition, new HashSet<>());
            if (pathListValue(currentPath) < pathListValue(currentLowestPath)) {
                currentLowestPath = currentPath;
                currentLowestStartingPosition = currentStartingPosition;
            }
        }
        return easyToReadListPath(currentLowestPath, currentLowestStartingPosition);
    }

    private List<String> easyToReadListPath(List<Path> paths, String startingPoint) {
        List<String> finalList = new ArrayList<>();
        finalList.add(startingPoint);
        String currentPoint = startingPoint;
        String nextPoint;
        for(Path nextPath:paths){
            nextPoint = nextPath.otherPoint(currentPoint);
            finalList.add(nextPoint);
            currentPoint = nextPoint;
        }
        return finalList;
    }
    private List<Hallway> easyToReadListPath(List<Path> paths, Hallway startingPoint){
        List<Hallway> finalList = new ArrayList<>();
        finalList.add(startingPoint);
        Hallway currentPoint = startingPoint;
        Hallway nextPoint;
            for (Path nextPath : paths) {
                nextPoint = nextPath.otherPoint(currentPoint);
                finalList.add(nextPoint);
                currentPoint = nextPoint;
            }
        return finalList;
    }
    //calculates the paths between two points for two reasons. One, to show a student how get from where he is to his class.
    //Two, to calculate how far classes are from each other to crate a map for the method dynamicBestPath()
    private List<Path> hallToHallPath(Hallway startingPosition, Hallway endPosition, HashSet<Hallway> notAllowedPoints) {
        HashSet<Hallway> alreadyUsedPoints = new HashSet<>(notAllowedPoints);
        HashSet<Path> nextPaths = possibleNextPlaces(startingPosition);
        Iterator<Path> removeItr = nextPaths.iterator();
        while (removeItr.hasNext()) {
            Path currentPath = removeItr.next();
            if (pathContainsObjectInSet(currentPath, alreadyUsedPoints)) removeItr.remove();
        }
        if (nextPaths.size() == 0) {
            List<Path> deadEnd = new ArrayList<>();
            deadEnd.add(new Path(startingPosition, deadEndHall));
            return deadEnd;

        }
        for (Path path : nextPaths) {
            //checks to see if end hall is one of the next halls
            if (path.otherPoint(startingPosition).equals(endPosition)) {
                List<Path> bestPath = new ArrayList<>();
                bestPath.add(path);
                return bestPath;
            }
        }
        alreadyUsedPoints.add(startingPosition);
        Iterator<Path> itr = nextPaths.iterator();
        Path currentLowestPath;
        List<Path> currentLowest;
        currentLowestPath = itr.next();
        currentLowest = hallToHallPath(currentLowestPath.otherPoint(startingPosition), endPosition, alreadyUsedPoints);
        currentLowest.add(0, currentLowestPath);
        while (itr.hasNext()) {
            Path currentPath;
            List<Path> currentList;
            currentPath = itr.next();
            currentList = hallToHallPath(currentPath.otherPoint(startingPosition), endPosition, alreadyUsedPoints);
            if (pathListValue(currentList, startingPosition) < pathListValue(currentLowest, startingPosition)) {
                currentLowest = currentList;
            }
            currentList.add(0, currentPath);
        }
        return currentLowest;
    }

    private List<Path> dynamicBestPath(String startingPosition, HashSet<String> notAllowedPoints) throws NonHamiltonianTourPointsException {
        HashSet<String> alreadyUsedPoints = new HashSet<>(notAllowedPoints);
        HashSet<Path> nextPlaces = possibleNextPlaces(startingPosition);
        Iterator<Path> removeItr = nextPlaces.iterator();
        while (removeItr.hasNext()) {
            Path currentPath = removeItr.next();
            if (pathContainsObjectInSet(currentPath, alreadyUsedPoints)) removeItr.remove();
        }
        if (nextPlaces.size() == 0) {
            throw new NonHamiltonianTourPointsException(startingPosition);
        }
        if (nextPlaces.size() == 1) {
            List<Path> bestPath = new ArrayList<>();
            bestPath.add(nextPlaces.iterator().next());
            return bestPath;
        } else {
            alreadyUsedPoints.add(startingPosition);
            Iterator<Path> itr = nextPlaces.iterator();
            Path currentLowestPath = itr.next();
            List<Path> currentLowest = dynamicBestPath(currentLowestPath.otherPoint(startingPosition), alreadyUsedPoints);
            currentLowest.add(0, currentLowestPath);
            Path currentPath;
            while (itr.hasNext()) {
                currentPath = itr.next();
                List<Path> currentList = dynamicBestPath(currentPath.otherPoint(startingPosition), alreadyUsedPoints);
                currentList.add(0, currentPath);
                if (pathListValue(currentList) < pathListValue(currentLowest)) currentLowest = currentList;
            }
            return currentLowest;
        }
    }


    private boolean pathContainsObjectInSet(Path path, HashSet notAllowedPoints) {
        for (Object point : notAllowedPoints) {
            if (path.getPoints().contains(point)) return true;
        }
        return false;
    }

    private int pathListValue(List<Path> paths) {
        int value = 0;
        for (Path path : paths) {
            value += map.get(path);
        }
        return value;
    }

    private int pathListValue(List<Path> paths,Hallway startingHall) {
        int value = 0;
        Hallway currentHall = startingHall;
        Hallway nextHall;
        for(Path nextPath: paths){
            nextHall = nextPath.otherPoint(currentHall);
            value += nextHall.getDistance();
            currentHall = nextHall;
        }
        return value;
    }


    private HashSet<Path> possibleNextPlaces(String index) {
        HashSet<Path> possiblePaths = new HashSet<>();
        for (Path path : allPaths) {
            if (path.getPoints().contains(index)) possiblePaths.add(path);
        }
        return possiblePaths;
    }

    private HashSet<Path> possibleNextPlaces(Hallway index) {
        HashSet<Path> possiblePaths = new HashSet<>();
        for (Path path : allHallPaths) {
            if(path.getPoints().contains(index)) possiblePaths.add(path);
        }
        return possiblePaths;
    }

    private HashSet<Hallway> scheduleToHallways(HashSet<String> schedule) {
        HashSet<Hallway> halls = new HashSet<>();
        for (String room : schedule) {
            subLoop:
            for (Hallway hall : allHalls) {
                if (hall.getRooms().contains(room)) {
                    halls.add(hall);
                    break subLoop;
                }
            }
        }
        return halls;
    }
        private Hallway roomToHall(String room) throws RoomDoesNotExistException{
            for (Hallway hall : allHalls) {
                if (hall.getRooms().contains(room)) {
                    return hall;
                }
            }
            throw new RoomDoesNotExistException(room);
    }

    private String hallWayToRoom(HashSet<String> schedule, Hallway hallway){
        for(String room: schedule){
            if(hallway.getRooms().contains(room)) return room;
        }
        return null;
    }
}
