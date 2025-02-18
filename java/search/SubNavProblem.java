package search;

import subway.*;
import java.util.*;

public class SubNavProblem extends Problem {
    private SubwayMap map;
    private Station startStation;
    private Station goalStation;

    public SubNavProblem(SubwayMap map, Station startStation, Station goalStation) {
        super(new State(startStation.name), new State(goalStation.name));
        this.map = map;
        this.startStation = startStation;
        this.goalStation = goalStation;
    }

    public State initialState() {
        return new State(startStation.name);
    }
    public State goalState() {
        return new State(goalStation.name);
    }

    public ArrayList<Tuple> successor(State state) {
        ArrayList<Tuple> successors = new ArrayList<>();
        Station currentStation = map.getStationByName(state.getName());

        ArrayList<Station> adjacentStations = map.adjacentStations(currentStation);

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

    public double pathCost(double pathCost, State state, Action action, State nextState) {

        Station currentStation = map.getStationByName(state.getName());
        Station nextStation = map.getStationByName(nextState.getName());

        ArrayList<Link> links = map.getLinksBetween(currentStation, nextStation);
        for (Link link : links) {
            if (link.line.equals(action.getName())) {
                return pathCost + link.getDistance();
            }
        }
        return pathCost; 
    }    
    
}
