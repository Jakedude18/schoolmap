import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.lang.Integer;

public class MainTest {
    private void addPath(HashMap map, HashSet set, String point1, String point2, Integer Distance){
        Path addedPath = new Path(point1, point2);
        map.put(addedPath,Distance);
        set.add(addedPath);
    }
    @Test
    public void twoPoints()  throws BothPointsAreTheNotPointException, NonHamiltonianTourPointsException {
        HashSet<Path> paths = new HashSet<>();
        HashMap<Path,Integer> map = new HashMap<>();
        addPath(map,paths,"1","2",10);
        Main TSP = new Main(paths,map);
        TSP.displayInfo();
        System.out.println("The final path: " + TSP.bestPathStartingAnywhere());
    }
    @Test
    public void fourHamiltonianTourPoints()  throws BothPointsAreTheNotPointException, NonHamiltonianTourPointsException {
        HashSet<Path> paths = new HashSet<>();
        HashMap<Path,Integer> map = new HashMap<>();
        addPath(map,paths,"1","2",10);
        addPath(map,paths,"1","3",15);
        addPath(map,paths,"1","4",20);
        addPath(map,paths,"2","3",35);
        addPath(map,paths,"2","4",25);
        addPath(map,paths,"3","4",30);
        Main TSP = new Main(paths,map);
        TSP.displayInfo();
        System.out.println("The final path: " + TSP.bestPathStartingAnywhere());
    }
    @Test
    public void fourNonHamiltonianTourPoints()  throws BothPointsAreTheNotPointException, NonHamiltonianTourPointsException {
        HashSet<Path> paths = new HashSet<>();
        HashMap<Path,Integer> map = new HashMap<>();
        addPath(map,paths,"1","2",10);
        addPath(map,paths,"1","3",15);
        addPath(map,paths,"1","4",20);
        addPath(map,paths,"2","3",35);
        addPath(map,paths,"2","4",25);
        Main TSP = new Main(paths,map);
        TSP.displayInfo();
        System.out.println("The final path: " + TSP.bestPathStartingAnywhere());
    }
}