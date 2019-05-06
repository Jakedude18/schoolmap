package tsp;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.lang.Integer;

import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class SolverTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private void addPath(HashMap map, String point1, String point2, Integer Distance) {
        Path addedPath = new Path(point1, point2);
        map.put(addedPath, Distance);
    }

    @Test
    public void twoPoints() throws NonHamiltonianTourPointsException {
        HashMap<Path, Integer> map = new HashMap<>();
        addPath(map, "1", "2", 10);
        Solver TSP = new Solver(map);
        TSP.displayInfo();
        //System.out.println("The final path: " + TSP.bestPathStartingAnywhere());
    }

    @Test
    public void fourHamiltonianTourPoints() throws NonHamiltonianTourPointsException {
        HashMap<Path, Integer> map = new HashMap<>();
        addPath(map, "1", "2", 10);
        addPath(map, "1", "3", 15);
        addPath(map, "1", "4", 20);
        addPath(map, "2", "3", 35);
        addPath(map, "2", "4", 25);
        addPath(map, "3", "4", 30);
        Solver TSP = new Solver(map);
        //System.out.println("The final path: " + TSP.bestPathStartingAnywhere());
    }

    @Test
    public void fourNonHamiltonianTourPoints() throws NonHamiltonianTourPointsException {
        HashMap<Path, Integer> map = new HashMap<>();
        addPath(map, "1", "2", 10);
        addPath(map, "1", "3", 15);
        addPath(map, "1", "4", 20);
        addPath(map, "2", "3", 35);
        addPath(map, "2", "4", 25);
        Solver TSP = new Solver(map);
        expectedException.expect(NonHamiltonianTourPointsException.class);
        //TSP.bestPathStartingAnywhere();
    }

    @Test
    public void LTHSMap() throws NonHamiltonianTourPointsException, RoomDoesNotExistException {
        HashSet<String> schedule = new HashSet();
        //schedule.add("B401");
        schedule.add("E207");
        schedule.add("C207");
        schedule.add("K207");
        System.out.println(SchoolDataSets.LTHS.bestPath(schedule));
    }

    @Test
    public void LTHSMapGenerate() throws NonHamiltonianTourPointsException, RoomDoesNotExistException{
        HashSet<String> schedule = new HashSet();
        schedule.add("B207");
        schedule.add("K205");
        schedule.add("B203");
        //schedule.add("E102");
        //schedule.add("D206");
        List<String> finalList = SchoolDataSets.LTHS.bestPath(schedule);
        System.out.println(finalList);
        //System.out.println(SchoolDataSets.LTHS.getSchoolData().roomToRoomPath("F201","M101"));
    }
}