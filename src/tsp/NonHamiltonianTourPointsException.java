package tsp;

public class NonHamiltonianTourPointsException extends Exception{
    public NonHamiltonianTourPointsException(String satrtingPoint) {
        super("Choosing this path prevents you from visiting all point: " + satrtingPoint);
    }
}