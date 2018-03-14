import pandas as pd
import numpy as np
import os

from collections import Counter
from operator import itemgetter
from sklearn.feature_selection import SelectKBest,mutual_info_regression
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor
from sklearn.model_selection import  train_test_split


def feature_Select_del_similar_for8910(path,filename,featurenum=20):
    trainDatafile= path+filename
    # print(trainDatafile)
    trainData= pd.read_csv(trainDatafile)
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    [row, col] = X_feature.shape
    # print(col)
    # print(X_feature.shape)
    featurearray = np.zeros((row, featurenum))
    for k in range(row):
        delrow = list(X_feature[k])
        count = Counter(X_feature[k])
        count = sorted(count.items(), key=itemgetter(1), reverse=True)
        for kk in range(1, len(count)):
            if not count[0][1] >= count[kk][1]:
                raise "排序有问题" + print(count)
        tem = []
        # print(count)
        # print(count[0][1])
        # print(str(filename)+"K："+str(k))
        if (col - featurenum) < count[0][1]:
            for j in range(col - featurenum):
                tem.append(count[0][0])
        else:
            raise "有问题，来检查" + print("filename:" + str(filename) + "  k:" + str(k), "   count[0][0]:" + str(count[0][0]))
        # if ((col - 20 - count[0][1]) > 0)&(len(count)>2):
        #     for j in range(min(col - featurenum - count[0][1], count[1][1])):
        #         tem.append(count[1][0])
        # elif ((col - 20 - count[0][1] - count[1][1]) > 0)&(len(count)>3):
        #     for j in range(min(col - 20 - count[0][1] - count[1][1], count[2][1])):
        #         tem.append(count[2][0])
        #
        # elif (len(count)>2)|(col - 20 - count[0][1] - count[1][1]) > 0:
        #     raise "取得数太多了"
        # print(len(tem))
        for m in range(len(tem)):
            delrow.remove(tem[m])
        featurearray[k] = np.array(delrow)
    return featurearray,X_uidsum

def feature_Select_del_similar_for11(trainData,featurenum):
    X_feature = np.array(trainData.iloc[:, 2:])
    X_uidsum = np.array(trainData.iloc[:, 0:2])
    [row, col] = X_feature.shape
    # print(col)
    # print(X_feature.shape)
    featurearray = np.zeros((row, featurenum))
    for k in range(row):
        delrow = list(X_feature[k])
        count = Counter(X_feature[k])
        count = sorted(count.items(), key=itemgetter(1), reverse=True)
        for kk in range(1, len(count)):
            if not count[0][1] >= count[kk][1]:
                raise "排序有问题" + print(count)
        tem = []
        # print(count)
        # print(count[0][1])
        # print(str(filename)+"K："+str(k))
        if (col - featurenum) < count[0][1]:
            for j in range(col - featurenum):
                tem.append(count[0][0])
        else:
            raise "有问题，来检查" + print("filename:" + str(trainData.iloc[0,0]) + "  k:" + str(k), "   count[0][0]:" + str(count[0][0]))
        # if ((col - 20 - count[0][1]) > 0)&(len(count)>2):
        #     for j in range(min(col - featurenum - count[0][1], count[1][1])):
        #         tem.append(count[1][0])
        # elif ((col - 20 - count[0][1] - count[1][1]) > 0)&(len(count)>3):
        #     for j in range(min(col - 20 - count[0][1] - count[1][1], count[2][1])):
        #         tem.append(count[2][0])
        #
        # elif (len(count)>2)|(col - 20 - count[0][1] - count[1][1]) > 0:
        #     raise "取得数太多了"
        # print(len(tem))
        for m in range(len(tem)):
            delrow.remove(tem[m])
        featurearray[k] = np.array(delrow)
    return featurearray, X_uidsum




def feature_Select_KBest(path,filename,maxfeaturenum=20):
    Xsum_select = np.zeros((1, maxfeaturenum))
    Xsum_uidsum=np.zeros((1,2))
    for file in filename:
        trainData = pd.read_csv(path+file)
        X_feature = np.array(trainData.iloc[:, 2:])
        X_uidsum = np.array(trainData.iloc[:, 0:2])
        tem_select = SelectKBest(mutual_info_regression, k=maxfeaturenum).fit_transform(X_feature, X_uidsum[:, 1])
        Xsum_select= np.concatenate([Xsum_select, tem_select])
        Xsum_uidsum=np.concatenate([Xsum_uidsum,X_uidsum])
    Xsum_select = np.delete(Xsum_select, 0, 0)
    Xsum_uidsum = np.delete(Xsum_uidsum, 0, 0)
    return Xsum_select,Xsum_uidsum


def feature_Select_loanfor11(trainpath,trainfile,maxfeaturenum):
    Xsum_select = np.zeros((1, maxfeaturenum))
    Xsum_uidsum = np.zeros((1, 2))
    for trfile in trainfile:
        if os.path.exists(trainpath + trfile):
            # print("kkk")
            trainData = pd.read_csv(trainpath + trfile)
            if (trainData.shape[0] > 3):
                X_feature = np.array(trainData.iloc[:, 2:])
                X_uidsum = np.array(trainData.iloc[:, 0:2])
                trainselect_feature = SelectKBest(mutual_info_regression, k=maxfeaturenum).fit_transform(X_feature,
                                                                                                         X_uidsum[:, 1])
            elif (trainData.shape[0] > 0):
                trainselect_feature, X_uidsum = feature_Select_del_similar_for11(trainData,featurenum=maxfeaturenum)
                # print("执行了"+str(trfile))
            else:
                continue

            Xsum_select = np.concatenate([Xsum_select, trainselect_feature])
            # print(Xsum_select.shape)
            Xsum_uidsum = np.concatenate([Xsum_uidsum, X_uidsum])
    Xsum_select = np.delete(Xsum_select, 0, 0)
    Xsum_uidsum = np.delete(Xsum_uidsum, 0, 0)

    return Xsum_select, Xsum_uidsum