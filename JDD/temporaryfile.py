
import pandas as pd
import numpy as np
import os
from sklearn.feature_selection import SelectKBest,f_regression,mutual_info_regression
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor
from sklearn.model_selection import  train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error

from SelectFeature import feature_Select_KBest,feature_Select_del_similar_for8910


def train_loan11_20_100(path,filename,maxfeaturenum=20):
    path = "./trainData_loan/"

    filename = []
    for filenum in range(30, 70):
        tem=path+"train_data_loan11_%d.csv" % filenum
        if(os.path.exists(path)):
            filename.append("train_data_loan11_%d.csv" % filenum)
    maxfeaturenum=20
    Xsum_select, Xsum_uidsum = feature_Select_KBest(path,filename,maxfeaturenum)
    # print(Xsum_uidsum[0])
    print(Xsum_select.shape)
    X_train, X_test, y_train, y_test = train_test_split(Xsum_select, Xsum_uidsum, test_size=0.3)
    maxdepth = 30
    rng = np.random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=300, random_state=rng)
    regRandomForest = RandomForestRegressor(max_features=maxfeaturenum, max_depth=maxdepth, n_estimators=300,
                                            random_state=rng)

    regAdaBoost.fit(X_train, y_train[:, 1])
    regRandomForest.fit(X_train, y_train[:, 1])

    ypreAda = regAdaBoost.predict(X_test)
    ypreRandom = regRandomForest.predict(X_test)
    print(type(ypreAda))
    print(ypreAda.shape)
    columns = []
    for num1 in range(maxfeaturenum):
        columns.append(str("loan_amount%d" % num1))

    pd1 = pd.DataFrame(y_test, columns=["uid", "goal"])
    pd2 = pd.DataFrame(ypreAda, columns=["Adapre"])
    pd3 = pd.DataFrame(ypreRandom, columns=["Randompre"])
    # print("AdaBoosting_mean_squared_error:" + mean_squared_error(y_test[:, 1], list(ypreAda)))
    # print("RandonForest_mean_squared_error:" + mean_squared_error(y_test[:, 1], list(ypreRandom)))

    pdd = pd.concat([pd1, pd2, pd3], axis=1)
    pdd.to_csv(path + "test_data_loan11_21_27.csv", index=False)



def test_my_select_feature_train21_23():
    filename = ("train_data_loan11_21.csv", "train_data_loan11_22.csv", "train_data_loan11_23.csv",
                "train_data_loan11_24.csv", "train_data_loan11_25.csv", "train_data_loan11_26.csv",
                "train_data_loan11_27.csv")
    maxfeaturenum = 20
    #
    trainData = np.zeros((1, maxfeaturenum + 2))
    print(trainData.shape)
    # print(trainData.shape)
    path = "./temporaryData/"
    # filename21 = ("train_data_loan11_21.csv",)
    for fn in filename:
        Datarray, Datauid = feature_Select_del_similar_for8910(path, filename=fn)
        trainDatatem = np.array(np.concatenate([Datauid, Datarray], axis=1))
        trainData = np.concatenate([trainData, trainDatatem])
    trainData = np.delete(trainData, 0, 0)
    print(trainData.shape)

    X_train, X_test, y_train, y_test = train_test_split(trainData[:, 2:], trainData[:, 0:2], test_size=0.3)
    print(X_train.shape)
    print(y_test.shape)
    print(y_train[:, 1])
    maxdepth = 30
    rng = np.random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=300, random_state=rng)
    regRandomForest=RandomForestRegressor(max_features=maxfeaturenum,max_depth=maxdepth,n_estimators=300,random_state=rng)

    regAdaBoost.fit(X_train, y_train[:, 1])
    regRandomForest.fit(X_train,y_train[:,1])

    ypreAda= regAdaBoost.predict(X_test)
    ypreRandom=regRandomForest.predict(X_test)


    columns = []
    for num1 in range(maxfeaturenum):
        columns.append(str("loan_amount%d" % num1))

    pd1 = pd.DataFrame(y_test, columns=["uid", "goal"])
    pd2 = pd.DataFrame(ypreAda, columns=["Adapre"])
    pd3=pd.DataFrame(ypreRandom,columns=["Randompre"])
    print("AdaBoosting_mean_squared_error:"+mean_squared_error(y_test[:,1],ypreAda))
    print("RandonForest_mean_squared_error:" + mean_squared_error(y_test[:, 1], ypreRandom))

    pdd = pd.concat([pd1, pd2,pd3], axis=1)
    pdd.to_csv(path + "test_data_loan11_21_27.csv", index=False)

