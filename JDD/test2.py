
import pandas as pd
from collections import Counter
from functools import reduce
from  Datadeal import build_train_loan11_2,t_loan_trans_month,Counter_dict_look,Counter_dict_store
import pickle
import numpy as np
import os
from operator import  itemgetter

from Datadeal import  build_train_loan11_4
from Datadeal import  general_loanData8910_2_50,del_loansum8910
from temporaryfile import train_loan11_20_100


from sklearn.tree import DecisionTreeRegressor,DecisionTreeClassifier
from sklearn.ensemble import GradientBoostingRegressor,AdaBoostRegressor,RandomForestRegressor
from sklearn.feature_selection import SelectKBest,mutual_info_regression
from sklearn.model_selection import train_test_split
from sklearn.ensemble import GradientBoostingClassifier,AdaBoostClassifier,RandomForestClassifier,BaggingClassifier
import xgboost as xgb


def run():
    # t_loan_trans_month()
    Counter_dict_store()
    Counter_dict_look()
    # build_train_loan11_2()
    # build_train_loan11_4()
# def build_train_loan11_3():
#      del_loansum8910()

# run()

trainpath="./sumData/"
trainfile="XGBsumloanData.csv"

trainData=np.array(pd.read_csv(trainpath+trainfile))
print(trainData.shape)
array=np.zeros((90994,1))
for i in range(trainData.shape[0]):
     array[i,0]=(trainData[i,1]+trainData[i,2]+trainData[i,3]+trainData[i,4])/4
# array=np.delete(array,0,0)

columns="uid,loan_pre08,loan_pre09,loan_pre10,loan_train11".split(",")
pd1=pd.DataFrame(array,columns=['mean'])
pd2=pd.DataFrame(trainData,columns=columns)
pdd=pd.concat([pd2,pd1],axis=1)
pdd.to_csv(trainpath+"XGBsumloan_mean.csv",index=False)

# testarray=reduce(np.concatenate,(trainData[i,:].reshape(1,6) for i in range(trainData.shape[0])
#                 if (trainData[i,1]>0)|(trainData[i,2]>0)|(trainData[i,3]>0)|(trainData[i,4]>0)))
# pdd1=pd.DataFrame(testarray,columns=columns)
# pdd1.to_csv(trainpath+"testsumloan0_11.csv",index=False)