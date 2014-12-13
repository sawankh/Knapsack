Knapsack
========

knapsack solver.
* version: 1.0
* Project for the subject "Complejidad Computacional", Universidad de La Laguna.

## [Colaborators](https://github.com/alu0100694765/Knapsack/graphs/contributors)
Sawan Jagdish Kapai Harpalani. Contact: <alu0100694765@ull.edu.es>

## [License](http://www.gnu.org/licenses/gpl-3.0.html) ![LICENSE](http://www.gnu.org/graphics/gplv3-88x31.png)
This project is under GNU license.

## Problem definition
> The knapsack problem or rucksack problem is a problem in combinatorial optimization: Given a set of items, each with a mass and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained by a fixed-size knapsack and must fill it with the most valuable items.

## Solutions applied.
* Greedy solution.
    > There are n items in a store. For i =1,2, . . . , n, item i has weight wi > 0 and worth vi > 0. Thief can carry a maximum weight of W pounds in a knapsack. In this version of a problem the items can be broken into smaller piece, so the thief may decide to carry only a fraction xi of object i, where 0 ≤ xi ≤ 1. Item i contributes xiwi to the total weight in the knapsack, and xivi to the value of the load. In Symbol, the fraction knapsack problem can be stated as follows.
maximize nSi=1 xivi subject to constraint nSi=1 xiwi ≤ W. It is clear that an optimal solution must fill the knapsack exactly, for otherwise we could add a fraction of one of the remaining objects and increase the value of the load. Thus in an optimal solution nSi=1 xiwi = W.

* Dynamic solution.
    > Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property). A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets. Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.

* Branch and Bound solution.
    > Branch and bound is a technique used only to solve optimization problems. It is an improvement over exhaustive search, because unlike it, branch and bound constructs candidate solutions one component at a time and evaluates the partly constructed solutions. If no potential values of the remaining components can lead to a solution, the remaining components are not generated at all. This approach makes it possible to solve some large instances of difficult combinatorial problems, though, in the worst case, it still has an exponential complexity.


## Programming language.
Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible. It is intended to let application developers "write once, run anywhere" (WORA), meaning that code that runs on one platform does not need to be recompiled to run on another. Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of computer architecture. Java is, as of 2014, one of the most popular programming languages in use, particularly for client-server web applications, with a reported 9 million developers.Java was originally developed by James Gosling at Sun Microsystems (which has since merged into Oracle Corporation) and released in 1995 as a core component of Sun Microsystems' Java platform. The language derives much of its syntax from C and C++, but it has fewer low-level facilities than either of them.