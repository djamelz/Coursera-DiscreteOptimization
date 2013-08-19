__author__ = 'Djamel'

import unittest
from solver import Node


class TestNode(unittest.TestCase):

    def test_getEdgesColors(self):
        n = Node(0, 1)
        n.addEdge(2)
        n.addEdge(3)
        graph = dict([(0, n), (1, Node(1, 3, 2)), (2, Node(2, 3, 1)), (3, Node(3, 3, 5)), (4, Node(4, 3, 7))])

        self.assertEqual(n.getEdgesColors(graph), set([2, 1, 5]))


    def test_getEdgesColorsByDegree(self):
        n = Node(0, 1)
        n.addEdge(2)
        n.addEdge(3)
        n.addEdge(5)
        n.addEdge(6)
        n.addEdge(7)
        graph = dict([(0, n), (1, Node(1, 3, 5)), (2, Node(2, 3, 1)), (3, Node(3, 3, 2)), (4, Node(4, 3, 7)), (5, Node(5, 3, 2)), (6, Node(6, 3, 2)), (7, Node(7, 3, 5))])

        self.assertEqual(n.getEdgesColorsByDegree(graph, [2, 5]), [(5, [1, 7]), (2, [3, 5, 6])])