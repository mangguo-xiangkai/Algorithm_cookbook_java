
import pandas as pd
import numpy as np
import os
import math
import xgboost as xgb

import pickle
from collections import Counter
from operator import itemgetter,add
from functools import reduce
from SelectFeature import  feature_Select_del_similar_for8910,feature_Select_loanfor11
from specificSelectfeature import del_feature_similar_laon11_33,del_feature_similar_loan8_40,del_feature_similar_loan09_39


from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import AdaBoostClassifier,RandomForestClassifier,BaggingClassifier
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor,GradientBoostingRegressor,RandomForestRegressor,BaggingRegressor
from sklearn.model_selection import  train_test_split
from sklearn.preprocessing import StandardScaler
import tensorflow as tf

from Datadeal import build_train_loan11_2
from train_model import train_predict_loan11_2_20

from sklearn.naive_bayes import BernoulliNB
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPRegressor


trainpath="./trainData_loan/"
trainData="train_data_loan11_19.csv"

# print(str(trainData))
trainData = pd.read_csv(trainpath + trainData)
# trainData.drop(0)
uid="uid"
goal="loan_sum"
mean="mean"
predictor=[x for x in trainData.columns if x not in [uid,goal,mean]]

X_feature=np.array(trainData[predictor])
print("X_feature.shape:{0}".format(X_feature.shape))
X_goal=np.array(trainData[goal])

x_train,x_test,y_train,y_test=train_test_split(X_feature,X_goal,train_size=0.7)
#
maxdepth=500
rng = np.random.RandomState(1)
Classifier=[AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng),
            RandomForestRegressor(max_depth=maxdepth, n_estimators=1000, random_state=rng),
            GradientBoostingRegressor(loss="ls", n_estimators=1000, max_depth=maxdepth),
            xgb.XGBRegressor(max_depth=maxdepth,n_estimators=1000,random_state=1),
            BaggingRegressor(DecisionTreeRegressor(max_depth=maxdepth),n_estimators=1000, random_state=rng)
            ]
doc = ["Ada", "Rand","Grad","XGB","Bagg"]
for d ,cf,in zip(doc,Classifier):
    cf.fit(x_train,y_train)
    y=cf.predict(x_test)
    # ysum = 0
    # for i in range(x_test.shape[0]):
    #     ysum = ysum + np.power(y[i] - y_test[i], 2)
    ysum=0
    ysum=reduce(add,(np.power(y[i] - y_test[i], 2) for i in range(x_test.shape[0])))
    print("{0}'s RMSE :{1}".format(d, np.sqrt(ysum / x_test.shape[0])))
    #
    # for i in range(x_test.shape[0]):
    #     if (x_test[i,0]==0)&(x_test[i,1]==0)&(x_test[i,2]==0):
    #         y[i]=0
    #     if (x_test[i, 2] == 0) & (x_test[i, 0] == 0) & (x_test[i, 1] > 0):
    #         y[i] =  y[i]* 0.71
    #     if (x_test[i, 2] == 0) & (x_test[i, 0] > 0) & (x_test[i, 1] ==0):
    #         y[i] =  y[i]* 0.79
    #     if (x_test[i, 2] == 0) & (x_test[i, 0] >0) & (x_test[i, 1] > 0):
    #         y[i] =  y[i]* 0.68
    #     if (x_test[i, 2] >0) & (x_test[i, 0] == 0) & (x_test[i, 1] == 0):
    #         y[i] =  y[i]* 0.85
    #     if (x_test[i, 2] > 0) & (x_test[i, 0] == 0) & (x_test[i, 1] > 0):
    #         y[i] =  y[i]* 0.825
    #     if (x_test[i, 2] > 0) & (x_test[i, 0] > 0) & (x_test[i, 1] == 0):
    #         y[i] =  y[i]* 0.73
    #     if (x_test[i, 2] >0) & (x_test[i, 0] > 0) & (x_test[i, 1] > 0):
    #         y[i] =  y[i]* 0.80

    # y=map(y[i]=0,for i in range(x_test.shape[0]) if (x_test[i,0]==0)&(x_test[i,1]==0)&(x_test[i,2]==0))
    # for i in range(x_test.shape[0]):
    #     ysum = ysum + np.power(y[i] - y_test[i], 2)




    # ysum1=0
    # ysum1=reduce(add,(np.power(y[i] - y_test[i], 2) for i in range(y_test.shape[0])))
    # print("{0}'s tuning  RMSE :{1}".format(d, np.sqrt(ysum1 / x_test.shape[0])))

# summean=0
# for i in range(x_test.shape[0]):
    # summean=summean+np.power((x_test[i, 0] + x_test[i, 1] + x_test[i, 2]) / 3 - y_test[i],2)

# summean=reduce(add,(np.power((x_test[i, 0] + x_test[i, 1] + x_test[i, 2]) / 3 - y_test[i],2) for i in range(x_test.shape[0])))
# print("mean's RMSE :{0}".format(np.sqrt(summean/x_test.shape[0])))
