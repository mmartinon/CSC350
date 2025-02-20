package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;

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
	
	public static Node uniformCostSearch(Problem problem) {
		System.out.println("UCS loading");
		PriorityQueue<Node> frontier = new PriorityQueue<>((a, b) -> Double.compare(a.getPathCost(), b.getPathCost()));
		HashSet<State> visited = new HashSet<>();
	
		// Start with the initial node
		Node startNode = new Node(problem.getInitial());
		frontier.add(startNode);
	
		int nodesVisited = 0; // Counter for visited nodes
		
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll(); // Get node with the lowest cost
			nodesVisited++;
	
			// Goal test
			if (problem.goalTest(currentNode.getState())) {
				System.out.println("Solution Found!");
				System.out.println("Total Path Cost: " + currentNode.getPathCost());
				System.out.println("Total Nodes Visited: " + nodesVisited);
	
				// Print path inline
				System.out.print("Solution Path: ");
				ArrayList<Node> path = currentNode.path();
				for (Node node : path) {
					System.out.print(node.getState().getName() + " -> ");
				}
				System.out.println();
				return currentNode;
			}
	
			// Mark the node as visited
			visited.add(currentNode.getState());
	
			// Expand node and add unvisited successors
			for (Node child : currentNode.expand(problem)) {
				if (!visited.contains(child.getState())) {
					frontier.add(child);
				}
			}
		}
	
		System.out.println("No solution found.");
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
			if (args.length < 4) {
				System.out.println("Usage: java search.Search <startStation> <goalStation> <city> <algorithm>");
				return;
			}

			System.out.println("Search Program is Running...");

			

			String city = args[0];
			if (!city.equals("Boston") && !city.equals("London")) {
				System.out.println("Invalid city. Please use 'Boston' or 'London'");
				return;
			}

			String algorithm = args[1];
			if (!algorithm.equals("bfs") && !algorithm.equals("dfs") && !algorithm.equals("ucs") && !algorithm.equals("astar")) {
				System.out.println("Invalid algorithm. Please use one of: bfs, dfs, ucs, astar");
				return;
			}

			String startStationName = args[2];
			String goalStationName = args[3];
			double distance = 0.0;

			if (args.length == 5) {
                try {
                    distance = Double.parseDouble(args[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid distance. Please provide a valid number.");
                    return;
                }
            }

			System.out.println("City: " + city);
            System.out.println("Algorithm: " + algorithm);
            System.out.println("Start: " + startStationName + " -> Goal: " + goalStationName);
			System.out.println("Distance: " + distance);

            // Load correct subway map
            SubwayMap map;

			System.out.println("Loading Subway map...");
			if (city.equals("Boston"))
            	map = SubwayMap.buildBostonMap();
			else if (city.equals("London"))
				map = SubwayMap.buildLondonMap();
			else
				map = null;

			System.out.println("Subway map loaded. Total Stations: " + map.numStations());

            // Get start and goal stations
            Station startStation = map.getStationByName(startStationName);
            Station goalStation = map.getStationByName(goalStationName);

            // Create the Subway Navigation Problem
            SubNavProblem problem = new SubNavProblem(map, startStation, goalStation);
            if (distance > 0) {
                problem = new SubNavProblem(map, startStation, goalStation, distance);
            } else {
                problem = new SubNavProblem(map, startStation, goalStation);
            }

			if (algorithm.equals("bfs"))
				breadthFirstSearch(problem);
			else if (algorithm.equals("dfs"))
				depthFirstSearch(problem);
			else if (algorithm.equals("ucs"))
				uniformCostSearch(problem);
			else if (algorithm.equals("astar"))
				aStarSearch(problem);

        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
}
