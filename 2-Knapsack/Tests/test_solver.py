__author__ = 'Djamel'

import unittest
import solver


class TestSolver(unittest.TestCase):

    def test_BranchAndBound(self):
        # lines = [(-8, solver.Item(8, 4)), (-10, solver.Item(10, 5)), (-15, solver.Item(15, 8)), (-4, solver.Item(4, 3))]
        # heapq.heapify(lines)
        lines = [solver.Item(15, 8, 2),  solver.Item(10, 5, 1), solver.Item(8, 4, 0), solver.Item(4, 3, 3)]
        self.assertEqual(solver.BranchAndBound(lines, 11, 37), (19, [0, 0, 1, 1]))

    def test_dynamic_programming(self):
        lines = [solver.Item(8, 4), solver.Item(10, 5), solver.Item(15, 8), solver.Item(4, 3)]
        self.assertEqual(solver.dynamicProgramming(lines, 11), (19, [0, 0, 1, 1]))

    def test_create_list(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_4_0").read(-1)
        self.assertEqual(solver.create_list(input_data), ([solver.Item(8, 4), solver.Item(10, 5), solver.Item(15, 8), solver.Item(4, 3)], 11))

    def test_create_sorted_list(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_4_0").read(-1)
        self.assertEqual(solver.create_sorted_list(input_data), ([solver.Item(8, 4), solver.Item(10, 5), solver.Item(15, 8), solver.Item(4, 3)], 11))

    def test_solveIt(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_4_0").read(-1)
        self.assertEqual(solver.solveIt(input_data), "19 0\n0 0 1 1")

    def test_solveIt2(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_45_0").read(-1)
        self.assertEqual(solver.solveIt(input_data), "23974 0\n0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0")

    def test_solveIt3(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_200_0").read(-1)
        self.assertEqual(solver.solveIt(input_data), solver.solveIt2(input_data))

    def test_solveIt4(self):
        input_data = open("//projects/Coursera-DiscreteOptimization/2-Knapsack/data/ks_400_0").read(-1)
        self.assertEqual(solver.solveIt(input_data), solver.solveIt2(input_data))

    def test_estimator(self):
        lines = [solver.Item(8, 4), solver.Item(10, 5), solver.Item(15, 8), solver.Item(4, 3)]
        self.assertEqual(solver.estimator(lines, 0, 11), 21.75)
