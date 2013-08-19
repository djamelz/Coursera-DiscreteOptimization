#!/usr/bin/python
# -*- coding: utf-8 -*-

from collections import deque
import copy
from itertools import groupby

def solveIt(inputData):
    # Modify this code to run your optimization algorithm

    #graph = createGraph(inputData)
    #colors, solution = solveWithBFS(graph)

    graph = createGraph(inputData)
    sortedList = sortGraph(graph)
    colors, solution = solveSortedGraph(graph, sortedList)

    # build a trivial solution
    # every Node has its own color
    #solution = range(0, nodeCount)

    # prepare the solution in the specified output format
    outputData = str(colors) + ' ' + str(0) + '\n'
    outputData += ' '.join(map(str, solution))

    return outputData

class Node:

    def __init__(self, id, edge=None, color=-1):
        self.id = id
        if edge is None:
            self.edges = []
        else:
            self.edges = [edge]
        self.color = color

    def addEdge(self,edge):
        self.edges.append(edge)

    def hasColor(self):
        if self.color == -1:
            return False
        else:
            return True

    def getEdgesColors(self, graph):
        result = set()
        for i in range(0,len(self.edges)):
            if graph[self.edges[i]].hasColor:
                result.add(graph[self.edges[i]].color)
        return result

    def getEdgesColorsByDegree(self, graph, colorPossible):
        result = []
        edgeColors = []
        map(lambda x: edgeColors.append((graph[x].color, x)), self.edges)
        edgeColors.sort( key=lambda x: x[0]) #sort because python sucks :-(
        for key, group in groupby(edgeColors, lambda x: x[0]):
            if key in colorPossible:
                result.append((key, map(lambda x: x[1], list(group))))

        return sorted(result, key=lambda x: len(x[1]))








def createGraph(inputData):
    lines = inputData.split('\n')
    firstLine = lines[0].split()
    nodeCount = int(firstLine[0])
    edgeCount = int(firstLine[1])


    print("Node: "+firstLine[0]+"edge: "+firstLine[1])
    graph = dict()

    for i in range(1,len(lines)):
        line = lines[i]
        if len(line) > 0:
            parts = line.split()
            node = int(parts[0])
            edge = int(parts[1])


            if graph.has_key(edge):
                graph[edge].addEdge(node)
            else:
                graph[edge] = Node(edge, node)
            if graph.has_key(node):
                graph[node].addEdge(edge)
            else:
                graph[node] = Node(node, edge)

    return graph

def sortGraph(graph):
    sortedList = deque()
    map(lambda node: sortedList.append(node.id), sorted(graph.values(), key=lambda x: len(x.edges), reverse=True))
    return sortedList


def solveWithBFS(graph):

    queue = deque([graph[len(graph) / 2]])
    colors = set()

    while len(queue) > 0:
        node = queue.popleft()
        if not node.hasColor():
            choice = colors.difference(node.getEdgesColors(graph))
            if len(choice) > 0:
                node.color = iter(choice).next()
            else:
                node.color = len(colors)
                colors.add(node.color)
            for i in range(0, len(node.edges)):
                if not graph[node.edges[i]].hasColor(): queue.append(graph[node.edges[i]])
            #map(lambda x: queue.append(graph[x]), node.edges)

    solution = []
    map(lambda x: solution.append(graph[x].color), range(0, len(graph)))

    return len(colors), solution

def solveSortedGraph(graph, sortedList):

    colors = set()

    for i in sortedList:
        node = graph[i]
        choice = colors.difference(node.getEdgesColors(graph))
        if len(choice) > 0:
            node.color = iter(choice).next()
        else:
            node.color = len(colors)
            colors.add(node.color)

    solution = []
    map(lambda x: solution.append(graph[x].color), range(0, len(graph)))

    return len(colors), solution

def changeColor(graph, sortedList, color, colors):
    for i in sortedList:
        node = graph[i]
        notColored = set()
        if node.color == color:
            queue = deque([(node, None)])
            notColored.add(node.id)
            while len(queue) > 0:
                node, newColor = queue.popleft()
                if newColor is None:
                    choice = colors.difference(node.getEdgesColors(graph))
                    if len(choice) > 0:
                        node.color = iter(choice).next()
                        notColored.remove(node.id)
                    else:
                        queue.append((node, node.getEdgesColorsByDegree(graph, color)))
                        notColored.add(node.id)
                else:
                    node.color = iter(color).next()





import sys

if __name__ == '__main__':
    if len(sys.argv) > 1:
        fileLocation = sys.argv[1].strip()
        inputDataFile = open(fileLocation, 'r')
        inputData = ''.join(inputDataFile.readlines())
        inputDataFile.close()
        print solveIt(inputData)
    else:
        print 'This test requires an input file.  Please select one from the data directory. (i.e. python solver.py ./data/gc_4_1)'

