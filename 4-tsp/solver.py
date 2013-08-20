#!/usr/bin/python
# -*- coding: utf-8 -*-

import os
from subprocess import Popen, PIPE

def solveIt(inputData):

    # Writes the inputData to a temporay file

    tmpFileName = 'tmp.data'
    tmpFile = open(tmpFileName, 'w')
    tmpFile.write(inputData)
    tmpFile.close()

    #scala /projects/Coursera-DiscreteOptimization/DiscreteOptimization/target/scala-2.10/coursera-discreteoptimization_2.10-1.0.jar coloring data/gc_50_1

    process = Popen(['scala', '/projects/Coursera-DiscreteOptimization/DiscreteOptimization/target/scala-2.10/coursera-discreteoptimization_2.10-1.0.jar', 'traveling', tmpFileName],
                    stdout=PIPE)
    (stdout, stderr) = process.communicate()

    # removes the temporay file

    os.remove(tmpFileName)

    return stdout.strip()


import sys

if __name__ == '__main__':
    if len(sys.argv) > 1:
        fileLocation = sys.argv[1].strip()
        inputDataFile = open(fileLocation, 'r')
        inputData = ''.join(inputDataFile.readlines())
        inputDataFile.close()
        print solveIt(inputData)
    else:
        print 'This test requires an input file.  Please select one from the data directory. (i.e. python solver.py ./data/tsp_51_1)'

