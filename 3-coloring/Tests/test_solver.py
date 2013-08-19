__author__ = 'Djamel'

import unittest
import solver
from solver import Node

class TestSolver(unittest.TestCase):

    def test_solveWithBFS(self):
        n0 = Node(0, 1)
        n1 = Node(1, 2)
        n1.addEdge(0)
        n1.addEdge(3)
        n2 = Node(2, 1)
        n3 = Node(3, 1)
        graph = dict([(0, n0), (1, n1), (2, n2), (3, n3)])

        self.assertEqual(solver.solveWithBFS(graph), (2, [0, 1, 0, 0]))


    def test_solveIt(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/3-coloring/data/gc_50_3").read(-1)
        self.assertEqual(solver.solveIt(input_data), "")