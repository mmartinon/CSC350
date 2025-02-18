package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
	
	public static Node breadthFirstSearch(Problem problem) {
		Queue<Node> frontier = new LinkedList<>(); // FIFO
		HashSet<State> visited = new HashSet<>(); // Track visited states

		// Start with the initial node
		Node startNode = new Node(problem.getInitial());
		frontier.add(startNode);

		int nodesVisited = 0; // Counter for visited nodes

		while (!frontier.isEmpty()) 
		{
			Node currentNode = frontier.poll(); // Remove the first-added node
			nodesVisited++;

			// Goal test
			if (problem.goalTest(currentNode.getState())) 
			{
				System.out.println("Solution Found!");
				System.out.println("Total Path Cost: " + currentNode.getPathCost());
				System.out.println("Total Nodes Visited: " + nodesVisited);

				// Print path inline
				System.out.print("Solution Path: ");
				ArrayList<Node> path = currentNode.path();
				for (Node node : path) 
				{
					System.out.print(node.getState().getName() + " -> ");
				}
				System.out.println(); // Move to next line
				return currentNode;
			}

			// Mark node as visited
			visited.add(currentNode.getState());

			// Expand node and add unvisited successors to the queue
			for (Node child : currentNode.expand(problem)) 
			{
				if (!visited.contains(child.getState())) 
				{
					frontier.add(child);
					visited.add(child.getState()); // Avoid duplicate expansions
				}
			}
		}

		System.out.println("No solution found.");
		return null;
	}
	
	public static Node depthFirstSearch(Problem problem)
	{
		Stack<Node> frontier = new Stack<>(); //stack
        HashSet<State> visited = new HashSet<>();  // Track visited states

        // Start with the initial node
        Node startNode = new Node(problem.getInitial());
        frontier.push(startNode);

        int nodesVisited = 0;  // Counter for visited nodes

        while (!frontier.isEmpty()) 
		{
            Node currentNode = frontier.pop();  // Get the last node
            nodesVisited++;

            // Goal test
            if (problem.goalTest(currentNode.getState())) 
			{
                System.out.println("Solution Found!");
                System.out.println("Total Path Cost: " + currentNode.getPathCost());
                System.out.println("Total Nodes Visited: " + nodesVisited);

                // Print path inline
                System.out.print("Solution Path: ");
                ArrayList<Node> path = currentNode.path();
                for (Node node : path) 
				{
                    System.out.print(node.getState().getName() + " ");
                }
                System.out.println(); // Move to next line
                return currentNode;
            }

            // Mark node as visited
            visited.add(currentNode.getState());

            // Expand node and add unvisited successors to stack
            for (Node child : currentNode.expand(problem)) 
			{
                if (!visited.contains(child.getState())) 
				{
                    frontier.push(child);
                }
            }
		}

		System.out.println("No solution found.");
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
			System.out.println("Search Program is Running...");

            String city = "boston";
            String algorithm = "bfs";
            String startStationName = "Fenway";
            String goalStationName = "South Station";

			System.out.println("City: " + city);
            System.out.println("Algorithm: " + algorithm);
            System.out.println("Start: " + startStationName + " -> Goal: " + goalStationName);

            // Load correct subway map
            SubwayMap map;

			System.out.println("Loading Boston subway map...");
            map = SubwayMap.buildBostonMap();
			System.out.println("Subway map loaded. Total Stations: " + map.numStations());

            // Get start and goal stations
            Station startStation = map.getStationByName(startStationName);
            Station goalStation = map.getStationByName(goalStationName);

            // Create the Subway Navigation Problem
            SubNavProblem problem = new SubNavProblem(map, startStation, goalStation);
            
            breadthFirstSearch(problem);
            

        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
}
