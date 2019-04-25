package tsp;

import java.util.*;
import java.lang.Integer;

public class Solver {
    private HashSet<Path> allPaths;
    private HashMap<Path, Integer> map;
    public Solver(){}
    public Solver(HashMap map) {
        this.map = map;
        this.allPaths = new HashSet<Path>(map.keySet());
    }
    public Solver(HashSet<Path> paths){
        this.allPaths = paths;
    }


    void displayInfo() {
        System.out.println("this is the paths :");
        for (Path path : allPaths) {
            path.displayPath();
        }
        System.out.println("This is the map: " + map);
    }

    HashMap<Path, Integer> generateMapUsingHalls(){
        HashMap<Path, Integer> TSPmap = new HashMap<>();
        Iterator<Hallway> itr;
        Hallway firstHall;
        Hallway secondHall;
        for(Path path: allPaths){
            itr = path.getPoints().iterator();
            firstHall = itr.next();
            secondHall = itr.next();
            TSPmap.put(new Path(firstHall,secondHall),pathListValue(hallToHallPath(firstHall,secondHall,new HashSet<>()),firstHall));
        }
        return TSPmap;
    }
    //This function would be for if someone wanted to find the best possible path starting from any point on a map
    List<String> bestPathStartingAnywhere() throws NonHamiltonianTourPointsException {
        HashSet<String> allStartingPoints = new HashSet<>();
        Iterator<Path> pathsItr = allPaths.iterator();
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

    List<String> easyToReadListPath(List<Path> paths, String startingPoint) {
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
    //calculates the paths between two points for two reasons. One, to show a student how get from where he is to his class.
    //Two, to calculate how far classes are from each other to crate a map for the method dynamicBestPath()
    List<Path> hallToHallPath(Hallway startingPosition, Hallway endPosition, HashSet<Hallway> notAllowedPoints) {
        HashSet<Hallway> alreadyUsedPoints = new HashSet<>(notAllowedPoints);
        HashSet<Path> nextPaths = possibleNextPlaces(startingPosition);
        Iterator<Path> removeItr = nextPaths.iterator();
        while (removeItr.hasNext()) {
            Path currentPath = removeItr.next();
            if (pathContainsObjectInSet(currentPath, alreadyUsedPoints)) removeItr.remove();
        }
        if (nextPaths.size() == 0) {
            List<Path> deadEnd = new ArrayList<>();
            deadEnd.add(new Path("Dead","End"));
            return deadEnd;
        }
        for(Path path: nextPaths){
            if(path.otherPoint(startingPosition).equals(endPosition)){
                List<Path> bestPath = new ArrayList<>();
                bestPath.add(path);
                return bestPath;
            }
        }
        alreadyUsedPoints.add(startingPosition);
        Iterator<Path> itr = nextPaths.iterator();
        Path currentLowestPath = itr.next();
        List<Path> currentLowest = hallToHallPath(currentLowestPath.otherPoint(startingPosition),endPosition, alreadyUsedPoints);
        currentLowest.add(0, currentLowestPath);
        while (itr.hasNext()) {
            Path currentPath = itr.next();
            List<Path> currentList = hallToHallPath(currentPath.otherPoint(startingPosition),endPosition, alreadyUsedPoints);
            currentList.add(0, currentPath);
            if (pathListValue(currentList,startingPosition) < pathListValue(currentLowest,startingPosition)) currentLowest = currentList;
            }
            return currentLowest;
        }

    List<Path> dynamicBestPath(String startingPosition, HashSet<String> notAllowedPoints) throws NonHamiltonianTourPointsException {
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
            value = nextHall.getDistance();
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
        for (Path path : allPaths) {
            if(path.getPoints().contains(index)) possiblePaths.add(path);
        }
        return possiblePaths;
    }

}
