import pandas as pd
import numpy as np
from collections import Counter
from operator import itemgetter
from sklearn.cluster import FeatureAgglomeration

from SelectFeature import feature_Select_del_similar_for11

def del_feature_similar_laon11_33():
    #11月,33选25: 4.7230168221: 8, 4.5443733959000001: 5, 4.5015352815999998: 3,    1选5 2 选2  3 选1
    trainData=pd.read_csv("./specificLoanFile/loan11/train_data_loan11_33.csv")
    X_feature = np.array(trainData.iloc[:, 2:])
    X_uidsum = np.array(trainData.iloc[:, 0:2])
    delrow = list(X_feature[0])
    count= Counter(list(X_feature[0]))
    count = sorted(count.items(), key=itemgetter(1), reverse=True)
    li=[5,2,1]
    for k,i in enumerate(li):
        for j in range(i):
            delrow.remove(count[k][0])
    featurearray = np.array(delrow).reshape(1,25)
    return featurearray,X_uidsum

def del_feature_similar_loan11_58():
    trainData = pd.read_csv("./trainData_loan/train_data_loan11_58.csv")
    X_feature, X_uidsum = feature_Select_del_similar_for11(trainData, 40)
    # X_feature.reshape(1,40)
    return X_feature,X_uidsum

def del_feature_similar_loan11_68():
    trainData = pd.read_csv("./trainData_loan/train_data_loan11_68.csv")
    X_feature, X_uidsum = feature_Select_del_similar_for11(trainData, 40)
    # X_feature.reshape(1,40)

    return X_feature, X_uidsum

def del_feature_similar_loan8_40():
    # 8月,40选25: k=1:(3.9756705649000001, 11), (4.2926506987000002, 8), (4.2272554939999996, 5), (4.5443733959000001, 4),
    #  1选6 ； 2 选4 ； 3 选2 ；4 选1 ； 5 选1 ；6 选1
    trainData = pd.read_csv("./specificLoanFile/loan8/test_loan08_40.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    [row, col] = X_feature.shape

    delrow1 = list(X_feature[0])
    count1 = Counter(X_feature[0])
    count1 = sorted(count1.items(), key=itemgetter(1), reverse=True)
    for k in range(col-25):
        delrow1.remove(count1[0][0])

    delrow2 = list(X_feature[1])
    count2 = Counter(X_feature[1])
    count2 = sorted(count2.items(), key=itemgetter(1), reverse=True)
    li = [6, 4, 2,1,1,1]
    for k, i in enumerate(li):
        for j in range(i):
            delrow2.remove(count2[k][0])

    array1= np.array(delrow1).reshape(1, 25)
    array2 = np.array(delrow2).reshape(1, 25)
    featurearray=np.concatenate([array1,array2])
    return featurearray, X_uidsum

def del_feature_similar_loan08_50():
    # 50 选 40  k=0: 3.8625945449999999: 10, 4.7230168221: 6, 4.5443733959000001: 5, 4.4058299903: 4, 4.9748429457999999: 4,
    #  1 选 5 ；2 选 3 ; 3选 1 4 选1
    trainData = pd.read_csv("./specificLoanFile/loan8/test_loan08_50.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li = [5,3,1,1]
    array = del_feature_similar_loan_handwork(X_feature[0], li, featurenum=40)
    return array, X_uidsum

def del_feature_similar_loan08_64():

    # 8月,64选40: k=0:{4.2926506987000002: 25, 4.1541589739999996: 15, 4.0713020591999998: 8, 3.8625945449999999: 7, 3.9756705649000001: 4,
    #  1选13 ； 2 选5； 3 选2 ；4 选2； 5 选1；
    trainData = pd.read_csv("./testLoanData08/test_loan08_64.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    delrow = list(X_feature[0])
    count = Counter(X_feature[0])
    count = sorted(count.items(), key=itemgetter(1), reverse=True)
    li = [13, 5, 3, 2, 1]
    for k, i in enumerate(li):
        for j in range(i):
            delrow.remove(count[k][0])

    array1 = np.array(delrow).reshape(1, 40)
    return array1,X_uidsum

def del_feature_similar_loan08_69():
    # 8月,69选40: k=0:4.2926506987000002: 64, 4.3518138118999996: 2, 4.0713020591999998: 1,
    #  1选29
    trainData = pd.read_csv("./testLoanData08/test_loan08_69.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li=[29]
    array=del_feature_similar_loan_handwork(X_feature[0],li,featurenum=40)
    return  array,X_uidsum
def del_feature_similar_loan08_100():
    # 8月,100选40: k=0:3.8625945449999999: 34, 4.2926506987000002: 20, 4.7230168221: 6, 3.9756705649000001: 5, 4.4058299903: 5, 4.1541589739999996: 3, 5.3513621782999996: 3,
    # 特殊选择 值得注意
    trainData = pd.read_csv("./testLoanData08/test_loan08_100.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    array = X_feature[0]
    abc = np.sort(np.absolute(array - np.mean(array)))
    ar = []
    i = 1
    for x in array:
        if (np.absolute(x - np.mean(array)) <= abc[40]) & (i <= 40):
            ar.append(x)
            i = i + 1
    ar = np.array(ar).reshape(1, 40)
    return ar,X_uidsum

def del_feature_similar_loan_handwork(X_feature,li,featurenum):
    delrow = list(X_feature)
    count = Counter(X_feature)
    count = sorted(count.items(), key=itemgetter(1), reverse=True)
    print(count)
    for k, i in enumerate(li):
        print("k:{0}".format(k)+"i:{0}".format(i))
        for j in range(i):

            delrow.remove(count[k][0])
    array= np.array(delrow).reshape(1, featurenum)
    return array

def del_feature_similar_loan09_39():
    # 9月,39选25: k=0:(4.7230168221, 5), (4.9748429457999999, 4), (4.6575871533999997, 4), (4.8616016004000002, 4), (5.0880911903000001, 3), (4.2926506987000002, 2),
    #  特殊选择，值得注意
      #           k=1   ({3.8625945449999999: 39})
    trainData = pd.read_csv("./specificLoanFile/loan9/test_loan09_39.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    # [row, col] = X_feature.shape
    # delrow1 = list(X_feature[0])
    array = X_feature[0]
    abc = np.sort(np.absolute(array - np.mean(array)))
    ar = []
    for x in array:
        if np.absolute(x - np.mean(array)) < abc[25]:
            ar.append(x)
    ar=np.array(ar).reshape(1,25)
    de = X_feature[1, :25]
    de=de.reshape(1,25)

    arde=np.concatenate([ar,de])
    return arde, X_uidsum

def del_feature_similar_loan09_52():
    #9月,52选40: k=0:4.7230168221: 16, 4.2926506987000002: 14, 3.8625945449999999: 5, 4.9748429457999999: 4,
    #  1 选7，2 选5,
    trainData = pd.read_csv("./testLoanData09/test_loan09_52.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li = [7,5]
    array = del_feature_similar_loan_handwork(X_feature[0], li, featurenum=40)
    return array, X_uidsum

def del_feature_similar_loan09_73():
    # 9月,73选40: k=0:  {4.2926506987000002: 33, 3.8625945449999999: 8, 4.0713020591999998: 8, 4.1541589739999996: 8, 3.9756705649000001: 4,
    #  1 选23；2 选3； 3选3 ;4选3  5选1
    trainData = pd.read_csv("./testLoanData09/test_loan09_73.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li = [23,3,3,3,1]
    array = del_feature_similar_loan_handwork(X_feature[0], li, featurenum=40)
    return array, X_uidsum

def del_feature_similar_loan09_128():
    # 9月,128选40: k=0:  {3.8625945449999999: 100, 3.9756705649000001: 13, 4.2926506987000002: 10, 4.1541589739999996: 3,
    #  1 选79；2 选5； 3选4;
    trainData = pd.read_csv("./testLoanData09/test_loan09_128.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li = [79,5,4]
    array = del_feature_similar_loan_handwork(X_feature[0], li, featurenum=40)
    return array, X_uidsum

def del_feature_similar_loan10_52():
    # 10月,52选40: k=0:  {4.0713020591999998: 23, 4.1541589739999996: 17, 3.9756705649000001: 7, 4.2272554939999996: 4,,
    #  1 选8；2 选7；
    #            k=1: ({3.8625945449999999: 52})
    # 1 选12
    trainData = pd.read_csv("./testLoanData10/test_loan10_52.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li1 = [8,7]

    array1 = del_feature_similar_loan_handwork(X_feature[0], li1, featurenum=40)
    li2=[12]
    array2 = del_feature_similar_loan_handwork(X_feature[1], li2, featurenum=40)
    array = np.concatenate([array1, array2])
    return array, X_uidsum

def del_feature_similar_loan10_57():
    # 10月,57选40: k=0:  3.8625945449999999: 45, 4.2926506987000002: 3, 3.9756705649000001: 2, 4.0713020591999998: 2, 5.4739682592999994: 2,
    #  1 选17；
    trainData = pd.read_csv("./testLoanData10/test_loan10_57.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li = [17]
    array = del_feature_similar_loan_handwork(X_feature[0], li, featurenum=40)
    return array, X_uidsum

def del_feature_similar_loan10_58():
    # 10月,58选40: k=0: {4.7230168221: 7, 4.2926506987000002: 7, 3.8625945449999999: 6, 4.6575871533999997: 5, 4.8616016004000002: 4, 4.1541589739999996: 3, 4.9319900530999998: 3
   #  特殊选择  值得注意
    trainData = pd.read_csv("./testLoanData10/test_loan10_58.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    array = X_feature[0]
    abc = np.sort(np.absolute(array - np.mean(array)))
    ar = []
    i = 1
    for x in array:
        if (np.absolute(x - np.mean(array)) <= abc[40]) & (i <= 40):
            ar.append(x)
            i = i + 1
    ar = np.array(ar).reshape(1, 40)
    return ar,X_uidsum

def del_feature_similar_loan10_60():
    # 10月,57选40: k=0: ({3.8625945449999999: 60})
    #  1 选；
    trainData = pd.read_csv("./testLoanData10/test_loan10_60.csv")
    X_feature = np.array(trainData.iloc[:, 1:])
    X_uidsum = np.array(trainData.iloc[:, 0:1])
    li = [20]
    array = del_feature_similar_loan_handwork(X_feature[0], li, featurenum=40)
    return array, X_uidsum

