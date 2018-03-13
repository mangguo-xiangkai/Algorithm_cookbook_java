
import os
import pandas as pd
import numpy as np
import math
import pickle
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor
from sklearn.ensemble import RandomForestRegressor,GradientBoostingRegressor
from sklearn.model_selection import train_test_split



def train_predict_loansum(trainpath ,trainData ,predictData,goalpath ,goalfile):

    trainData = pd.read_csv(trainpath+trainData)
    predictData=pd. read_csv(trainpath+predictData)

    X_feature = np.array(trainData.iloc[:, 1:4])
    X_goal = np.array(trainData.iloc[:, 4:5])
    y_train= X_goal
    y_train= y_train.reshape(X_goal.shape[0],) # print(y_train.shape)
    count=X_feature.shape[0]
    pre_feature=np.array( predictData.iloc[:,2:])
    preuid=np.array( predictData.iloc[:,0])

    maxdepth =500

    rng = np. random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)
    regRandomForest = RandomForestRegressor(max_depth=maxdepth, n_estimators=1000, random_state=rng)
    regGrad= GradientBoostingRegressor(loss="ls",n_estimators=1000, max_depth=maxdepth )

    regAdaBoost.fit(X_feature, y_train)
    regRandomForest.fit(X_feature, y_train)
    regGrad.fit(X_feature, y_train)
    mean=np.zeros((pre_feature.shape[0],1))
    for i in range(pre_feature.shape[0]):
        mean[i,0]=(pre_feature[i,0]+pre_feature[i,1]+pre_feature[i,2])/3

    y1 = regAdaBoost.predict(pre_feature)
    y2 = regRandomForest.predict(pre_feature)
    y3=regGrad.predict( pre_feature)

    pd1=pd.DataFrame(preuid, columns=["uid"])
    pd2=pd.DataFrame(y1, columns=["AdaBoost_pre"])
    pd3=pd.DataFrame(y2, columns=["RandomFrost_pre"])
    pd4=pd.DataFrame(y3, columns=["Grad"])
    pdm=pd.DataFrame(mean,columns=["mean"])
    pdd = pd.concat([pd1, pd2, pd3,pd4,pdm], axis=1)
    pdd.to_csv(goalpath+goalfile, index=False)
    return  count

def test_loansum_predict_0_1(trainpath ,trainData,goalpath ,goalfile):
    print(str(trainData))
    trainData = pd.read_csv(trainpath+trainData)

    X_feature = np.array(trainData.iloc[:, 0:4])
    X_goal = np.array(trainData.iloc[:, 4:5])

    x_train,x_test,y_train,y_test=train_test_split(X_feature,X_goal,train_size=0.7)
    y_train=y_train.reshape(y_train.shape[0],)
    maxdepth =500
    assert y_train.shape[0]==x_train.shape[0] ," 怎么搞的"
    rng = np. random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)
    regRandomForest = RandomForestRegressor(max_depth=maxdepth, n_estimators=1000, random_state=rng)
    regGrad= GradientBoostingRegressor(loss="ls",n_estimators=1000, max_depth=maxdepth )

    regAdaBoost.fit(x_train[:,1:4], y_train)
    regRandomForest.fit(x_train[:,1:4], y_train)
    regGrad.fit(x_train[:,1:4], y_train)

    y1 = regAdaBoost.predict(x_test[:,1:4])
    y2 = regRandomForest.predict(x_test[:,1:4])
    y3=regGrad.predict(x_test[:,1:4])

    y123=[y1,y2,y3]
    doc=["Ada","Ran","Grad"]

    for y,d in zip(y123,doc):
        ysum = 0
        for i in range(x_test.shape[0]):
            ysum=ysum+np.power(y[i]-y_test[i,0],2)
        print("{0}'s RMSE :{1}".format(d,math.sqrt(ysum/x_test.shape[0])))
    meansum = 0
    for i in range(x_test.shape[0]):
        meansum = meansum + np.power((x_test[i, 1] + x_test[i, 2] + x_test[i, 3]) / 3 - y_test[i, 0], 2)
    print("meansum's RMSE:{0}".format(math.sqrt(meansum/x_test.shape[0])))
    print()
    pduid=pd.DataFrame(x_test[:,0],columns=["uid"])
    pd1=pd.DataFrame(y_test, columns=["goal"])
    pd2=pd.DataFrame(y1, columns=["AdaBoost_pre"])
    pd3=pd.DataFrame(y2, columns=["RandomFrost_pre"])
    pd4=pd.DataFrame(y3, columns=["Grad"])
    pdd = pd.concat([pduid,pd1, pd2, pd3,pd4], axis=1)
    pdd.to_csv(goalpath+goalfile, index=False)
    # return  count


def repeat_test_loansum_predict_0_1(trainpath, trainData,):
    trainData = pd.read_csv(trainpath + trainData)

    X_feature = np.array(trainData.iloc[:, 0:4])
    X_goal = np.array(trainData.iloc[:, 4:5])

    x_train, x_test, y_train, y_test = train_test_split(X_feature, X_goal, train_size=0.7)
    y_train = y_train.reshape(y_train.shape[0], )
    maxdepth = 500
    assert y_train.shape[0] == x_train.shape[0], " 怎么搞的"
    rng = np.random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)
    regRandomForest = RandomForestRegressor(max_depth=maxdepth, n_estimators=1000, random_state=rng)
    regGrad = GradientBoostingRegressor(loss="ls", n_estimators=1000, max_depth=maxdepth)

    regAdaBoost.fit(x_train[:, 1:4], y_train)
    regRandomForest.fit(x_train[:, 1:4], y_train)
    regGrad.fit(x_train[:, 1:4], y_train)

    y1 = regAdaBoost.predict(x_test[:, 1:4])
    y2 = regRandomForest.predict(x_test[:, 1:4])
    y3 = regGrad.predict(x_test[:, 1:4])

    y123 = [y1, y2, y3]
    doc = ["Ada", "Ran", "Grad"]
    li = pd.DataFrame(np.zeros((1, 4)), columns=["Ada", "Ran", "Grad", "mean"])
    j=0
    for y, d in zip(y123, doc):
        ysum = 0
        for i in range(x_test.shape[0]):
            ysum = ysum + np.power(y[i] - y_test[i, 0], 2)
        li.iloc[0,j]=math.sqrt(ysum / x_test.shape[0])
        j=j+1
    meansum = 0
    for i in range(x_test.shape[0]):
        meansum = meansum + np.power((x_test[i, 1] + x_test[i, 2] + x_test[i, 3]) / 3 - y_test[i, 0], 2)
    li.iloc[0, j]=math.sqrt(meansum / x_test.shape[0])
    return li
    # print("meansum's RMSE:{0}".format(math.sqrt(meansum / x_test.shape[0])))
    # print()
    # pduid = pd.DataFrame(x_test[:, 0], columns=["uid"])
    # pd1 = pd.DataFrame(y_test, columns=["goal"])
    # pd2 = pd.DataFrame(y1, columns=["AdaBoost_pre"])
    # pd3 = pd.DataFrame(y2, columns=["RandomFrost_pre"])
    # pd4 = pd.DataFrame(y3, columns=["Grad"])
    # pdd = pd.concat([pduid, pd1, pd2, pd3, pd4], axis=1)
    # pdd.to_csv(goalpath + goalfile, index=False)
    # return  count


def train_predict_loansum_all():
    li = ["000", "001", "010", "100", "011", "101", "110", "111"]
    trainfile, predictfile, goalfile = [], [], []
    for i in li:
        trainfile.append("sumloan_{0}t.csv".format(i))
        predictfile.append("sumloan_x{0}.csv".format(i))
        goalfile.append("predictsumloan12_X{}.csv".format(i))

    trainpath = "./trainSumLoanData/"
    goalpath = "./predictSumLoanData/"
    countsum = 0
    for tfile, pfile, gfile in zip(trainfile, predictfile, goalfile):
        count = train_predict_loansum(trainpath, tfile, pfile, goalpath, gfile)
        print(str(pfile))
        countsum = countsum + count
    print(countsum)