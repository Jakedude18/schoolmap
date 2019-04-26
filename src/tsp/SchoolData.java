package tsp;

import java.util.*;

public class SchoolData {
    private HashSet<Path> paths;
    private HashSet<Hallway> halls;
    SchoolData(HashSet<Path> paths, HashSet<Hallway> halls){
        this.paths = paths;
        this.halls = halls;
    }
    List<String> bestPath(List<String> schedule) throws NonHamiltonianTourPointsException{
        Solver solver = new Solver(paths,halls);
        return solver.bestPathStartingAnywhere(schedule);
    }
}