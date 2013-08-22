#!/usr/bin/python
# -*- coding: utf-8 -*-

from Numberjack import *

def solveIt(inputData):
    # Modify this code to run your optimization algorithm

    # parse the input
    lines = inputData.split('\n')

    parts = lines[0].split()
    warehouseCount = int(parts[0])
    customerCount = int(parts[1])

    warehouses = []
    for i in range(1, warehouseCount+1):
        line = lines[i]
        parts = line.split()
        warehouses.append((int(parts[0]), float(parts[1])))

    customerSizes = []
    customerCosts = []

    lineIndex = warehouseCount+1
    for i in range(0, customerCount):
        customerSize = int(lines[lineIndex+2*i])
        customerCost = map(float, lines[lineIndex+2*i+1].split())
        customerSizes.append(customerSize)
        customerCosts.append(customerCost)

    # build a trivial solution
    # pack the warehouses one by one until all the customers are served

    solution = [-1] * customerCount
    capacityRemaining = [w[0] for w in warehouses]

    warehouseIndex = 0
    for c in range(0, customerCount):
        if capacityRemaining[warehouseIndex] >= customerSizes[c]:
            solution[c] = warehouseIndex
            capacityRemaining[warehouseIndex] -= customerSizes[c]
        else:
            warehouseIndex += 1
            assert capacityRemaining[warehouseIndex] >= customerSizes[c]
            solution[c] = warehouseIndex
            capacityRemaining[warehouseIndex] -= customerSizes[c]

    used = [0]*warehouseCount
    for wa in solution:
        used[wa] = 1

    # calculate the cost of the solution
    obj = sum([warehouses[x][1]*used[x] for x in range(0,warehouseCount)])
    for c in range(0, customerCount):
        obj += customerCosts[c][solution[c]]

    # prepare the solution in the specified output format
    outputData = str(obj) + ' ' + str(0) + '\n'
    outputData += ' '.join(map(str, solution))

    return outputData



def model_warehouse_planning(data):
    WareHouseOpen = VarArray(data.NumberOfWarehouses)

    ShopSupplied = Matrix(data.NumberOfWarehouses,
                          data.NumberOfShops)

    # Cost of running warehouses
    warehouseCost = Sum(WareHouseOpen, data.WareHouseCosts)

    # Cost of shops using warehouses
    transpCost = Sum([ Sum(varRow, costRow)
                       for (varRow, costRow) in zip(ShopSupplied, data.SupplyCost)])

    obj = warehouseCost + transpCost

    model = Model(
        # Objective function
        Minimise(obj),
        # Channel from store opening to store supply matrix
        [[var <= store for var in col]
         for (col, store) in zip(ShopSupplied.col, WareHouseOpen)],
        # Make sure every shop if supplied by one store
        [Sum(row) == 1 for row in ShopSupplied.row],
        # Make sure that each store does not exceed it's supply capacity
        [Sum(col) <= cap
         for (col, cap) in zip(ShopSupplied.col, data.Capacity)]
    )

    return (obj, WareHouseOpen, ShopSupplied, model)

def solve_warehouse_planning(data, param):
    (obj, WareHouseOpen, ShopSupplied, model) = model_warehouse_planning(data)
    solver = model.load(param['solver'])
    solver.setVerbosity(1)
    solver.solve()
    print obj.get_value()
    print "",WareHouseOpen
    print ShopSupplied

class WareHouseData:
    def __init__(self):
        self.NumberOfWarehouses = 5
        self.NumberOfShops = 10
        self.FixedCost = 30
        self.WareHouseCosts = [30, 30, 30, 30, 30]
        self.Capacity = [1,4,2,1,3]
        self.SupplyCost = supplyCost = [
            [ 20, 24, 11, 25, 30 ],
            [ 28, 27, 82, 83, 74 ],
            [ 74, 97, 71, 96, 70 ],
            [ 2, 55, 73, 69, 61 ],
            [ 46, 96, 59, 83, 4 ],
            [ 42, 22, 29, 67, 59 ],
            [ 1, 5, 73, 59, 56 ],
            [ 10, 73, 13, 43, 96 ],
            [ 93, 35, 63, 85, 46 ],
            [ 47, 65, 55, 71, 95 ]
        ]

solve_warehouse_planning(WareHouseData(), input({'solver':'SCIP'}))

import sys

if __name__ == '__main__':
    if len(sys.argv) > 1:
        fileLocation = sys.argv[1].strip()
        inputDataFile = open(fileLocation, 'r')
        inputData = ''.join(inputDataFile.readlines())
        inputDataFile.close()
        print 'Solving:', fileLocation
        print solveIt(inputData)
    else:
        print 'This test requires an input file.  Please select one from the data directory. (i.e. python solver.py ./data/wl_16_1)'

