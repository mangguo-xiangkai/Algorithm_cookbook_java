
import os
import pandas as pd
import numpy as np
import pickle

from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor,BaggingRegressor
from sklearn.model_selection import  train_test_split
from sklearn.metrics import mean_squared_error
from sklearn.feature_selection import SelectKBest,f_regression,mutual_info_regression
from sklearn.ensemble import RandomForestRegressor,GradientBoostingRegressor

import  xgboost as xgb
import math
from operator import pow,add
from functools import reduce
from loansumpredict import train_predict_loansum

trainpath="./trainData_loan/"
trainData="train_data_loan11_5.csv"

print(str(trainData))
trainData = pd.read_csv(trainpath + trainData)
uid="uid"
goal="loan_train11"
predictor=[x for x in trainData.columns if x not in [uid,goal]]

X_feature = np.array(trainData.iloc[:, 2:])
X_goal = np.array(trainData.iloc[:, 0:2])

x_train, x_test, y_train, y_test = train_test_split(X_feature, X_goal, train_size=0.7)
print(x_train.shape)
y_train = y_train[:,1].reshape(y_train.shape[0],)
maxdepth = 500
assert y_train.shape[0] == x_train.shape[0], " 怎么搞的"
rng = np.random.RandomState(1)
Classifier=[AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng),
            RandomForestRegressor(max_depth=maxdepth, n_estimators=1000, random_state=rng),
            GradientBoostingRegressor(loss="ls", n_estimators=1000, max_depth=maxdepth),
            xgb.XGBRegressor(max_depth=500,n_estimators=1000,random_state=1),
            BaggingRegressor(DecisionTreeRegressor(max_depth=maxdepth),n_estimators=1000, random_state=rng)]
doc = ["Ada", "Ran", "Grad","XGB","Bagg"]
pdy=[pd.DataFrame(y_test, columns=["uid","goal"])]
for d,cf in zip(doc,Classifier):
    cf.fit(x_train, y_train)
    y = cf.predict(x_test)
    # ysum = 0
    # for i in range(x_test.shape[0]):
    #     ysum = ysum + np.power(y[i] - y_test[i, 1], 2)
    ysum=reduce(add,(np.power(y[i]-y_test[i,1],2) for i in range(x_test.shape[0])))
    print("{0}'s RMSE :{1}".format(d, math.sqrt(ysum / x_test.shape[0])))
    py=pd.DataFrame(y,columns=[d])
    pdy.append(py)

# meansum = 0
# for i in range(x_test.shape[0]):
#     meansum = meansum + np.power((x_test[i, 1] + x_test[i, 2] + x_test[i, 3]) / 3 - y_test[i, 1], 2)
# print("meansum's RMSE:{0}".format(math.sqrt(meansum / x_test.shape[0])))

pdd = pd.concat(pdy, axis=1)
pdd.to_csv(trainpath+"xgbloan_shu_test.csv", index=False)



