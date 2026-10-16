# CS2210 - Data Structures and Algorithms
Coursework completed for CS2210 at Western University.
This repository contains four major assignments that progressively develop skills in algorithm analysis, custom data structure implementation, graph traversal, recursion, state-space search, and Abstract Data Type (ADT) design. The focus of these projects is on implementing core data structures and algorithms from scratch rather than relying on built-in library implementations.


## Technologies
- Java
- Object-Oriented Programming (OOP)
- Abstract Data Types (ADTs)
- Algorithm Analysis
- Hash Tables
- Binary Search Trees
- Graphs
- Recursion
- Depth-First Search (DFS)
- Backtracking
- Java Swing
- File I/O
- Custom Data Structures
- Exception Handling


## Assignment 1: Worst-Case Search Analysis
A programming component accompanying a written assignment on algorithm analysis and Big O complexity. The implementation was used to experimentally evaluate how different complexity classes behave under worst-case conditions.

### My Contributions
- Implemented Linear Search, O(n)
- Implemented Quadratic Search, O(n²)
- Implemented Factorial Search, O(n!)
- Generated worst-case test inputs
- Benchmarked algorithm runtime using large datasets
- Collected empirical performance measurements
- Compared theoretical and observed complexity growth

### Skills Demonstrated
- Big O Analysis
- Time Complexity Evaluation
- Algorithm Benchmarking
- Worst-Case Analysis
- Experimental Performance Testing
- Recursive Algorithms
- Algorithm Design

### Key Concepts Explored
- O(n) Linear Complexity
- O(n²) Quadratic Complexity
- O(n!) Factorial Complexity
- Scalability Analysis
- Growth Rate Comparison
- Theoretical vs. Practical Performance

### Core Files
- `Search.java`
- `SearchTimes.txt`


## Assignment 2: Bored Game
Implemented the core game-state management and board-storage infrastructure for a board-game AI. My work focused on designing the board ADT, implementing a custom hash-table dictionary, and developing the board evaluation system used for game-state analysis.

### My Contributions
- Designed and implemented the Board ADT
- Developed board-state storage and retrieval functionality
- Implemented board-layout serialization
- Built win-condition detection algorithms
- Implemented draw-state evaluation logic
- Created a custom hash-table dictionary
- Implemented separate chaining collision resolution
- Developed polynomial hashing for board-state indexing
- Implemented dictionary insertion, lookup, and deletion operations
- Created data classes used to store board layouts and evaluation scores
- Implemented custom exception handling for dictionary operations

### Skills Demonstrated
- Hash Tables
- Separate Chaining
- Polynomial Hashing
- Custom ADT Design
- Data Structure Implementation
- Board-State Evaluation
- Object-Oriented Programming
- Exception Handling
- Software Testing

### Core Files
- `Board.java`
- `HashDictionary.java`
- `Layout.java`
- `DictionaryException.java`

---

## Assignment 4: Multimedia Dictionary
Implemented an ordered dictionary using a Binary Search Tree (BST) to efficiently store and manage multimedia records. My work focused on designing the BST, implementing ordered dictionary operations, and developing the command-processing functionality used by the application.

### My Contributions
- Implemented a Binary Search Tree from scratch
- Designed BST node structures and tree organization logic
- Implemented insertion, search, and deletion operations
- Developed successor and predecessor retrieval algorithms
- Implemented smallest and largest key retrieval operations
- Created multimedia data storage structures
- Designed dictionary entry and multimedia record models
- Implemented ordered dictionary functionality
- Developed command-processing logic for dictionary queries
- Built file-loading and data-parsing functionality
- Implemented support for multiple multimedia entries per key
- Developed custom exception handling for dictionary operations

### Skills Demonstrated
- Binary Search Trees
- Ordered Dictionaries
- Tree Traversal
- Recursive Algorithms
- Data Modeling
- File Processing
- Command-Line Interfaces
- Object-Oriented Design
- Software Testing

### Core Files
- `BSTOrderedDictionary.java`
- `BSTNode.java`
- `Data.java`
- `MultimediaItem.java`
- `Query.java`
- `DictionaryException.java`
- `MultimediaException.java`


## Assignment 5: Labyrinth Escape
Implemented a graph-based labyrinth solver that models maze layouts as undirected graphs and uses recursive depth-first search with backtracking to find valid escape paths while managing limited traversal resources.

### My Contributions
- Designed and implemented an Undirected Graph ADT
- Built an adjacency-list graph representation
- Created custom Node and Edge classes
- Implemented graph construction from labyrinth input files
- Developed graph edge insertion and adjacency operations
- Implemented graph traversal and incident-edge retrieval methods
- Built custom graph exception handling
- Developed a recursive Depth-First Search (DFS) pathfinding algorithm
- Implemented recursive backtracking and state restoration
- Designed resource-constrained traversal logic
- Added support for multiple obstacle and wall types
- Built graph testing and validation utilities

### Skills Demonstrated
- Graph Theory
- Graph ADTs
- Adjacency Lists
- Depth-First Search (DFS)
- Recursive Backtracking
- Pathfinding Algorithms
- State Management
- Algorithm Design
- Data Structure Testing
- Object-Oriented Programming

### Core Files
- `UndirectedGraph.java`
- `Node.java`
- `Edge.java`
- `Solve.java`
- `GraphException.java`
- `LabyrinthException.java`


# Key Learning Outcomes
Throughout these assignments I gained experience with:
- Algorithm Analysis and Benchmarking
- Big O Complexity Evaluation
- Abstract Data Type (ADT) Design
- Custom Data Structure Implementation
- Hash Tables and Collision Resolution
- Binary Search Trees
- Ordered Dictionaries
- Graph Data Structures
- Depth-First Search (DFS)
- Recursive Backtracking
- State-Space Search
- File Processing
- Object-Oriented Software Development
- Software Testing and Debugging
- Technical Documentation

## Data Structures Implemented
- Hash Tables
- Separate-Chaining Dictionaries
- Binary Search Trees
- Ordered Dictionaries
- Graphs
- Adjacency Lists
- Tree-Based Data Structures
- Custom Search Structures

## Algorithms Implemented
- Linear Search
- Quadratic Search
- Factorial Search
- Depth-First Search (DFS)
- Recursive Backtracking
- Graph Traversal
- Hash-Based State Lookup
- Successor and Predecessor Tree Operations
- Board-State Evaluation Algorithms
- Worst-Case Runtime Benchmarking

## Future Improvements
Potential future enhancements include:
- Breadth-First Search (BFS)
- Shortest Path Algorithms (Dijkstra's, A*)
- Balanced Search Trees (AVL Trees, Red-Black Trees)
- Priority Queues and Heaps
- Additional Graph Algorithms
- Improved Game AI Evaluation Heuristics
- JUnit Test Suites
- JavaFX User Interfaces
- Persistent Storage Systems
- Database Integration
- Design Pattern Implementation
