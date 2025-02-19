package search;

import java.util.ArrayList;

import subway.Station;
import subway.SubwayMap;

/**
This code is adapted from search.py in the AIMA Python implementation, which is published with the license below:

	The MIT License (MIT)

	Copyright (c) 2016 aima-python contributors

	Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


**/

public class Search{
	/* DO NOT MODIFY THE HEADERS OF ANY OF THESE FUNCTIONS */
	
	// Uninformed Search algorithms
	
	public static Node breadthFirstSearch(Problem problem){
		//YOUR CODE HERE
		return null;
	}
	
	public static Node depthFirstSearch(Problem problem){
		//YOUR CODE HERE
		return null;
	}
	
	public static Node uniformCostSearch(Problem problem){
		//YOUR CODE HERE
		return null;
	}

	// Informed (Heuristic) Search
	
	public static Node aStarSearch(Problem problem){
		//YOUR CODE HERE
		return null;
	}
	
	// Main
	public static void main(String[] args){
		try {
            System.out.println("Starting Subway Navigation Test...");

            // Load a subway map (Boston or London)
            SubwayMap map = SubwayMap.buildBostonMap(); 
            System.out.println("Subway map loaded successfully.");
            System.out.println("Number of stations: " + map.numStations());

            // Choose a test station
            String startStationName = "Fenway";  
            String goalStationName = "South Station"; 

            Station startStation = map.getStationByName(startStationName);
            Station goalStation = map.getStationByName(goalStationName);

            if (startStation == null || goalStation == null) {
                System.out.println("Error: One or both stations do not exist. Please check station names.");
                return;
            }

            // Create a SubNavProblem instance
            SubNavProblem problem = new SubNavProblem(map, startStation, goalStation);
            System.out.println("Problem initialized with start station: " + startStation.name);

            // Print initial state
            System.out.println("Initial State: " + problem.getInitial());

            // Get successors of the initial state
            ArrayList<Tuple> successors = problem.successor(problem.getInitial());

            // Print successors
            System.out.println("Successors of " + startStation.name + ":");
            for (Tuple successor : successors) {
                System.out.println(successor);
            }

            System.out.println("SubNavProblem test completed successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred during execution:");
            e.printStackTrace();
        }
	}
}
