package search;

import subway.*;
import java.util.*;

public class SubNavProblem extends Problem {
    private SubwayMap map;
    private Station startStation;
    private Station goalStation;
    private double distance;

    /**
     * Constructor to initialize the subway navigation problem.
     * @param map The subway map containing all stations and links.
     * @param startStation The starting station in the problem.
     * @param goalStation The goal station in the problem.
     */
    public SubNavProblem(SubwayMap map, Station startStation, Station goalStation) {
        super(new State(startStation.name), new State(goalStation.name));
        this.map = map;
        this.startStation = startStation;
        this.goalStation = goalStation;
        this.distance = 0.0;
    }

    /**
     * Constructor to initialize the subway navigation problem.
     * @param map The subway map containing all stations and links.
     * @param startStation The starting station in the problem.
     * @param goalStation The goal station in the problem.
     * @param distance distance in kilometers to consider as goal.
     */
    public SubNavProblem(SubwayMap map, Station startStation, Station goalStation, double distance) {
        super(new State(startStation.name), new State(goalStation.name));
        this.map = map;
        this.startStation = startStation;
        this.goalStation = goalStation;
        this.distance = distance;
    }


    

    /**
     * Returns the initial state of the problem.
     * @return The initial state.
     */
    @Override
    public State getInitial() {
        return new State(startStation.name);
    }

    /**
     * Returns the goal state of the problem.
     * @return The goal state.
     */
    public State getGoal() {
        return new State(goalStation.name);
    }

    /**
     * Generates the successors of a given state.
     * @param state The current state.
     * @return A list of tuples containing actions and resulting states.
     */
    @Override
    public ArrayList<Tuple> successor(State state) {
        ArrayList<Tuple> successors = new ArrayList<>();
        Station currentStation = map.getStationByName(state.getName());

        // Get adjacent stations to the current station
        ArrayList<Station> adjacentStations = map.adjacentStations(currentStation);

        // Iterate through adjacent stations and create successor states
        for (Station adjacentStation : adjacentStations) {
            ArrayList<Link> links = map.getLinksBetween(currentStation, adjacentStation);
            for (Link link : links) {
                Action action = new Action(link.line);
                State nextState = new State(adjacentStation.name);
                successors.add(new Tuple(action, nextState));
            }
        }

        return successors;
    }


    /**
     * Calculates the path cost from the current state to the next state.
     * @param pathCost The current path cost.
     * @param state The current state.
     * @param action The action taken.
     * @param nextState The resulting state.
     * @return The updated path cost.
     */
    @Override
    public double pathCost(double pathCost, State state, Action action, State nextState) {

        Station currentStation = map.getStationByName(state.getName());
        Station nextStation = map.getStationByName(nextState.getName());

        // Get links between the current station and the next station
        ArrayList<Link> links = map.getLinksBetween(currentStation, nextStation);
        for (Link link : links) {
            if (link.line.equals(action.getName())) {
                return pathCost + link.getDistance();
            }
        }
        return pathCost; 
    }    

    /**
     * Tests whether the given state is a goal state.
     * @param state The state to test.
     * @return True if the state is a goal state, false otherwise.
     */
    @Override
    public boolean goalTest(State state) {
        Station currentStation = map.getStationByName(state.getName());
        Station goalStation = map.getStationByName(this.goalStation.name);

        // Check if the current station is within the distance d of the goal station
        return currentStation.equals(goalStation) || SubwayMap.straightLineDistance(currentStation, goalStation) <= distance;
    }
    
}
