import java.util.*;
import java.lang.Integer;

public class Main {
    private HashSet<Path> allPaths;
    private HashMap<Path, Integer> map;

    Main(HashSet paths, HashMap map) {
        this.allPaths = paths;
        this.map = map;
    }

    void displayInfo() {
        System.out.println("this is the paths :");
        for (Path path : allPaths) {
            path.displayPath();
        }
        System.out.println("This is the map: " + map);
    }

    //This function would be for if someone wanted to find the best possible path starting from any point on a map
    List<String> bestPathStartingAnywhere() throws BothPointsAreTheNotPointException, NonHamiltonianTourPointsException {
        HashSet<String> allStartingPoints = new HashSet<>();
        Iterator<Path> pathsItr = allPaths.iterator();
        while (pathsItr.hasNext()) {
            allStartingPoints.addAll(pathsItr.next().getPoints());
        }
        Iterator<String> startingPointsItr = allStartingPoints.iterator();
        String currentLowestStartingPosition = startingPointsItr.next();
        List<Path> currentLowestPath = dynamicBestPath(currentLowestStartingPosition, new HashSet<>());
        while (startingPointsItr.hasNext()) {
            String currentStartingPosition = (startingPointsItr.next());
            List<Path> currentPath = dynamicBestPath(currentStartingPosition, new HashSet<>());
            if (pathListValue(currentPath) < pathListValue(currentLowestPath)) {
                currentLowestPath = currentPath;
                currentLowestStartingPosition = currentStartingPosition;
            }
        }
        return easyToReadListPath(currentLowestPath, currentLowestStartingPosition);
    }

    List<String> easyToReadListPath(List<Path> paths, String startingPoint) throws BothPointsAreTheNotPointException {
        List<String> finalList = new ArrayList<>();
        finalList.add(startingPoint);
        Iterator<Path> itr = paths.iterator();
        String currentPoint = startingPoint;
        while (itr.hasNext()) {
            String nextPoint = itr.next().otherPoint(currentPoint);
            finalList.add(nextPoint);
            currentPoint = nextPoint;
        }
        return finalList;
    }

    List<Path> dynamicBestPath(String startingPosition, HashSet<String> notAllowedPoints) throws BothPointsAreTheNotPointException, NonHamiltonianTourPointsException {
        HashSet<Path> nextPlaces = possibleNextPlaces(startingPosition);
        Iterator<Path> removeItr = nextPlaces.iterator();
        while (removeItr.hasNext()) {
            Path currentPath = removeItr.next();
            if (pathContainsStringInList(currentPath, notAllowedPoints)) removeItr.remove();
        }
        if (nextPlaces.size() == 0) {
            throw new NonHamiltonianTourPointsException("This point has dose not lead to all the other points :" + startingPosition);
        }
        if (nextPlaces.size() == 1) {
            List<Path> bestPath = new ArrayList<>();
            bestPath.add(nextPlaces.iterator().next());
            return bestPath;
        } else {
            notAllowedPoints.add(startingPosition);
            Iterator<Path> itr = nextPlaces.iterator();
            Path currentLowestPath = itr.next();
            List<Path> currentLowest;
            try{
                currentLowest = dynamicBestPath(currentLowestPath.otherPoint(startingPosition), notAllowedPoints);

            }catch (NonHamiltonianTourPointsException ex){
                currentLowestPath = itr.next(); //skips the invalid path
                currentLowest = dynamicBestPath(currentLowestPath.otherPoint(startingPosition), notAllowedPoints);
            }
            currentLowest.add(0, currentLowestPath);
            while (itr.hasNext()) {
                Path currentPath = itr.next();
                List<Path> currentList;
                try {
                    currentList = dynamicBestPath(currentPath.otherPoint(startingPosition), notAllowedPoints);
                }catch (NonHamiltonianTourPointsException ex){
                    break;
                }
                currentList.add(0, currentPath);
                if (pathListValue(currentList) < pathListValue(currentLowest)) currentLowest = currentList;
            }
            return currentLowest;
        }
    }

    private boolean pathContainsStringInList(Path path, HashSet<String> notAllowedPoints) {
        for (String point : notAllowedPoints) {
            if (path.getPoints().contains(point)) return true;
        }
        return false;
    }

    private int pathListValue(List<Path> paths) {
        int value = 0;
        for (Path path : paths) {
            value = +map.get(path);
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
}
