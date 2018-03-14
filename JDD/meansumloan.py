import numpy as np
import pandas as pd




trainpath = "./sumData/"
trainfile="XGBsumloan_mean.csv"
trainData=pd.read_csv(trainpath+trainfile)
# trainData=trainData.drop(0)
trainData=np.array(trainData)
#  000, 001 ,010,011,100,101,110,111
array=np.zeros((trainData.shape[0],2))

for i in range(trainData.shape[0]):
    if(trainData[i,2]==0)&(trainData[i,3]==0)&(trainData[i,4]==0):
        array[i,0]=trainData[i,0]
        array[i,1]=(trainData[i,1]+trainData[i,2]+trainData[i,3]+trainData[i,4])/4

    if(trainData[i,4]==0)&(trainData[i,2]==0)&(trainData[i,3]>0):
        array[i, 0] = trainData[i, 0]
        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3)*0.71
    if(trainData[i,4]==0)&(trainData[i,2]>0)&(trainData[i,3]==0):
        array[i, 0] = trainData[i, 0]
        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3) * 0.79
    if(trainData[i,4]==0)&(trainData[i,2]>0)&(trainData[i,3]>0):
        array[i, 0] = trainData[i, 0]
        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3) * 0.68
    if(trainData[i,4]>0)&(trainData[i,2]==0)&(trainData[i,3]==0):
        array[i, 0] = trainData[i, 0]

        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3) * 0.85
    if(trainData[i,4]>0)&(trainData[i,2]==0)&(trainData[i,3]>0):
        array[i, 0] = trainData[i, 0]
        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3) * 0.825
    if(trainData[i,4]>0)&(trainData[i,2]>0)&(trainData[i,3]==0):
        array[i, 0] = trainData[i, 0]
        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3) * 0.73
    if(trainData[i,4]>0)&(trainData[i,2]>0)&(trainData[i,3]>0):
        array[i, 0] = trainData[i, 0]
        array[i, 1] = ((trainData[i, 2] + trainData[i, 3] + trainData[i, 4]) / 3) * 0.80



pdd=pd.DataFrame(array,columns=["uid",'dismean'])
# pdd=pdd.drop(0)
# pdd.columns=["",""]
pdd.to_csv(trainpath+"disXGBmean20171210.csv",index=False)

