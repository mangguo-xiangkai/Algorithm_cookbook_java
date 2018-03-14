import pandas as pd
import numpy as np

from collections import Counter
from operator import itemgetter
from sklearn.feature_selection import SelectKBest,mutual_info_regression
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor
from sklearn.model_selection import  train_test_split

from SelectFeature import feature_Select_del_similar


def run_feature_Select_del_similar():
    path = "./temporaryData/"
    filename = "train_data_loan11_21.csv"
    maxfeaturenum=20
    Datarray,Datauid=feature_Select_del_similar(path,filename=filename)
    columns=[]
    for num1 in range(maxfeaturenum):
        columns.append(str("loan_amount%d" % num1))
    pd1 = pd.DataFrame(Datarray, columns=columns)
    pdd = pd.concat([Datauid,pd1], axis=1)
    pdd.to_csv(path+"train_data_loan11_21_fs.csv", index=False)
# run_feature_Select_del_similar()



def test_my_select_feature_train21_23():
    filename = ("train_data_loan11_21.csv", "train_data_loan11_22.csv", "train_data_loan11_23.csv")
    maxfeaturenum = 20
    #
    trainData = np.zeros((1, maxfeaturenum + 2))
    print(trainData.shape)
    # print(trainData.shape)
    path = "./temporaryData/"
    filename21 = ("train_data_loan11_21.csv",)
    for fn in filename:
        Datarray, Datauid = feature_Select_del_similar(path, filename=fn)
        trainDatatem = np.array(np.concatenate([Datauid, Datarray], axis=1))
        trainData = np.concatenate([trainData, trainDatatem])
    trainData = np.delete(trainData, 0, 0)
    print(trainData.shape)

    X_train, X_test, y_train, y_test = train_test_split(trainData[:, 2:], trainData[:, 0:2], test_size=0.3)
    print(X_train.shape)
    print(y_test.shape)
    print(y_train[:, 1])
    maxdepth = 20
    rng = np.random.RandomState(1)
    reg2 = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=300, random_state=rng)
    reg2.fit(X_train, y_train[:, 1])

    y2 = reg2.predict(X_test)

    columns = []
    for num1 in range(maxfeaturenum):
        columns.append(str("loan_amount%d" % num1))

    pd1 = pd.DataFrame(y_test, columns=["uid", "goal"])
    pd2 = pd.DataFrame(y2, columns=["pre"])
    pdd = pd.concat([pd1, pd2], axis=1)
    pdd.to_csv(path + "train_data_loan11_21_fs.csv", index=False)

