
import numpy as np
from my_BPR.load_data import load_data

# cc=
trainMatrix,ratings=load_data.mk_100K('u1.base')
trainMatrix[6,3]=5
print(trainMatrix.shape)
print(trainMatrix[0])
print(trainMatrix[6])
# print(trainMatrix[6,3])
print(trainMatrix[0,0])
print(trainMatrix[942,1329],'942,1329')

print(ratings[:5])
print(trainMatrix.getrowview(0).rows[0])
for i in range(trainMatrix.shape[0]):
    if trainMatrix.getrowview(i).sum()==0:
        print( "you 0" ,i)


# print(trainMatrix)