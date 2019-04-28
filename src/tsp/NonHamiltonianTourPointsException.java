package tsp;

public class NonHamiltonianTourPointsException extends Exception{
    public NonHamiltonianTourPointsException(String startingPoint) {
        super("Choosing this path prevents you from visiting all point: " + startingPoint);
    }
}