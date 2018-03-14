import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os
import xgboost as xgb
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import AdaBoostRegressor
from sklearn.model_selection import  train_test_split
from sklearn.metrics import mean_squared_error
from sklearn.feature_selection import SelectKBest,f_regression,mutual_info_regression
from sklearn.ensemble import RandomForestRegressor,BaggingRegressor

from SelectFeature import feature_Select_KBest,feature_Select_del_similar_for8910,feature_Select_loanfor11

from specificSelectfeature import del_feature_similar_loan8_40,del_feature_similar_loan09_39
from specificSelectfeature import del_feature_similar_laon11_33,del_feature_similar_loan11_58,del_feature_similar_loan11_68
from specificSelectfeature import del_feature_similar_loan08_69,del_feature_similar_loan08_64,del_feature_similar_loan08_50,del_feature_similar_loan08_100
from specificSelectfeature import del_feature_similar_loan09_73,del_feature_similar_loan09_128
from specificSelectfeature import del_feature_similar_loan10_57,del_feature_similar_loan10_58,del_feature_similar_loan10_60

def train_predict_loan11_2_20(trainData,predictData,maxdepth=50,goaladdress=None):
    trainData= pd.read_csv(trainData)
    X_feature = np.array(trainData.iloc[:, 2:])
    X_uidsum = np.array(trainData.iloc[:, 0:2])
    # X_train, X_test, y_train, y_test = train_test_split(X_feature,X_uidsum, test_size=0.3)
    X_train=X_feature
    y_train=X_uidsum[:,1]

    rng = np.random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)
    regRandomForest = RandomForestRegressor( max_depth=maxdepth, n_estimators=1000,random_state=rng)
    regXGB=xgb.XGBRegressor(max_depth=maxdepth,n_estimators=1000,random_state=1)
    regBagg = BaggingRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)

    regAdaBoost.fit(X_train, y_train)
    regRandomForest.fit(X_train,y_train)
    regXGB.fit(X_train,y_train)
    regBagg.fit(X_train,y_train)

    predictData= pd.read_csv(predictData)
    X_test_feature = np.array(predictData.iloc[:, 1:])
    X_test_uidsum= np.array(predictData.iloc[:, 0:1])

    y1 = regAdaBoost.predict(X_test_feature)
    y2 = regRandomForest.predict(X_test_feature)
    y3=regXGB.predict(X_test_feature)
    y4=regBagg.predict(X_test_feature)

    pd1 = pd.DataFrame(X_test_uidsum, columns=["uid"])
    pd2 = pd.DataFrame(y1, columns=["AdaBoost_pre"])
    pd3 = pd.DataFrame(y2, columns=["RandomFrost_pre"])
    pd4=pd.DataFrame(y3,columns=["XGB"])
    pd5=pd.DataFrame(y4,columns=["Bagg"])
    pdd = pd.concat([pd1, pd2,pd3,pd4,pd5], axis=1)
    # pdd.to_csv(goaladdress, index=False)
    return  pdd

def train_predict_loan8910_2_20():
    testtem = ["./testLoanData08/test_loan08_", "./testLoanData09/test_loan09_", "./testLoanData10/test_loan10_"]
    prepath = ["./predict_loan08/", "./predict_loan09/", "./predict_loan10/"]
    predict_loan = ["predict_loan08_", "predict_loan09_", "predict_loan10_"]
    testfile08, testfile09, testfile10, prefile08, prefile09, prefile10 = [], [], [], [], [], []
    testfile = [testfile08, testfile09, testfile10]
    prefile = [prefile08, prefile09, prefile10]
    trainfile = []
    for filenum in range(2, 21):
        for k in range(3):
            testfile[k].append(testtem[k] + "{0}.csv".format(filenum))
            prefile[k].append(predict_loan[k] + "%d.csv" % filenum)
        trainfile.append("train_data_loan11_%d.csv" % filenum)

    trainpath = "./trainData_loan/"
    for tfile, pfile, ppath in zip(testfile, prefile, prepath):
        for testf, trainf, pf in zip(tfile, trainfile, pfile):
            pdd = train_predict_loan11_2_20(trainpath + trainf, testf, maxdepth=500)
            if not os.path.exists(ppath):
                os.makedirs(ppath)
            pdd.to_csv(ppath + pf, index=False)

def train_predict_loan8910_21_24_100(trainData_feature,trainDatauid ,predictData_feature,predictDatauid, maxfeature=21):
    """

    :param trainData_feature:
    :param trainDatauid:
    :param predictData_feature:
    :param predictDatauid:
    :param maxfeature:
    :return:   pdd   pandas.DataFrame 文件

    """
    maxfeaturenum=maxfeature
    maxdepth =500
    rng = np.random.RandomState(1)
    regAdaBoost = AdaBoostRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)
    regRandomForest = RandomForestRegressor(max_features=maxfeaturenum, max_depth=maxdepth, n_estimators=1000,
                                            random_state=rng)
    regXGB=xgb.XGBRegressor(max_depth=maxdepth,n_estimators=1000,random_state=2)
    regBagg=BaggingRegressor(DecisionTreeRegressor(max_depth=maxdepth), n_estimators=1000, random_state=rng)

    regAdaBoost.fit(trainData_feature, trainDatauid[:, 1])
    regRandomForest.fit(trainData_feature, trainDatauid[:, 1])
    regXGB.fit(trainData_feature, trainDatauid[:, 1])
    regBagg.fit(trainData_feature, trainDatauid[:, 1])

    ypreAda = regAdaBoost.predict(predictData_feature)
    ypreRandom = regRandomForest.predict(predictData_feature)
    ypreXGB=regXGB.predict(predictData_feature)
    ypreBagg=regBagg.predict(predictData_feature)

    pd1 = pd.DataFrame(predictDatauid, columns=["uid"])
    pd2 = pd.DataFrame(ypreAda, columns=["Adapre"])
    pd3 = pd.DataFrame(ypreRandom, columns=["Randompre"])
    pd4=pd.DataFrame(ypreXGB,columns=["XGB"])
    pd5=pd.DataFrame(ypreBagg,columns=["Bagg"])
    pdd = pd.concat([pd1, pd2, pd3,pd4,pd5], axis=1)
    return pdd

def train_predict_loan8910_21_24():
    trainfile = ["train_data_loan11_21.csv", "train_data_loan11_22.csv", "train_data_loan11_23.csv",
                "train_data_loan11_24.csv"]
    # trainfile=["train_data_loan11_{0}".format(i) for i in range(21,25)]
    maxfeaturenum = 21
    trainpath="./trainData_loan/"
    predictpath=["./testLoanData08/","./testLoanData09/","./testLoanData10/"]
    goalpath=["./predict_loan08/","./predict_loan09/","./predict_loan10/"]

    predictfile08=["test_loan08_21.csv","test_loan08_22.csv","test_loan08_23.csv","test_loan08_24.csv"]
    predictfile09= ["test_loan09_21.csv", "test_loan09_22.csv", "test_loan09_23.csv", "test_loan09_24.csv"]
    predictfile10 = ["test_loan10_21.csv", "test_loan10_22.csv", "test_loan10_23.csv", "test_loan10_24.csv"]
    # predictfile=[["test_loan08_{0}".format(i) for i in range(21,25)],
    #           ["test_loan09_{0}".format(i) for i in range(21,25)],
    #           ["test_loan10_{0}".format(i) for i in range(21,25)]]

    goalfile08 = ["predict_loan08_21.csv", "predict_loan08_22.csv", "predict_loan08_23.csv", "predict_loan08_24.csv"]
    goalfile09 = ["predict_loan09_21.csv", "predict_loan09_22.csv", "predict_loan09_23.csv", "predict_loan09_24.csv"]
    goalfile10 = ["predict_loan10_21.csv", "predict_loan10_22.csv", "predict_loan10_23.csv", "predict_loan10_24.csv"]
    # goalfile=[["predict_loan08_{0}".format(i) for i in range(21,25)],
    #           ["predict_loan09_{0}".format(i) for i in range(21,25)],
    #           ["predict_loan10_{0}".format(i) for i in range(21,25)]]

    predictfile=[predictfile08,predictfile09,predictfile10]
    goalfile=[goalfile08,goalfile09,goalfile10]

    trainData_feature, trainDatauid = feature_Select_KBest(trainpath, filename=trainfile,maxfeaturenum=maxfeaturenum)

    for prepath,filename,gpath,gfile in zip(predictpath,predictfile ,goalpath,goalfile):
        for fn,gf in zip(filename,gfile):
            trainData = pd.read_csv(prepath + fn)
            if(trainData.shape[0]>3):
                X_feature = np.array(trainData.iloc[:, 1:])
                X_uidsum = np.array(trainData.iloc[:, 0:1])

                preselect_feature = SelectKBest(mutual_info_regression, k=maxfeaturenum).fit_transform(X_feature, X_uidsum[:, 0])

                pdd = train_predict_loan8910_21_24_100(trainData_feature,trainDatauid,preselect_feature, X_uidsum,
                                               maxfeature = maxfeaturenum)
            elif(trainData.shape[0]>0):
                preselect_feature, X_uidsum=feature_Select_del_similar_for8910(prepath,fn,featurenum=maxfeaturenum)
                pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, preselect_feature, X_uidsum,
                                                       maxfeature=maxfeaturenum)
            else:
                continue
            pdd.to_csv(gpath+gf, index=False)

def train_predict_loan8910_25_40():
    #  预测  8 9 10  月 25-41 的 loan,,去除  8月40  以及  9 月39
    maxfeaturenum = 25
    trainpath="./trainData_loan/"
    predictpath=["./testLoanData08/","./testLoanData09/","./testLoanData10/"]
    goalpath=["./predict_loan08/","./predict_loan09/","./predict_loan10/"]
    trainfile = []
    goalfile, predictfile = [[], [], []], [[], [], []]
    for i in range(25, 41):
        for k in range(8, 11):
            if (k <= 9):
                goalfile[k - 8].append("predict_loan0{0}_{1}.csv".format(k, i))
                predictfile[k - 8].append("test_loan0{0}_{1}.csv".format(k, i))
            else:
                goalfile[k - 8].append("predict_loan{0}_{1}.csv".format(k, i))
                predictfile[k - 8].append("test_loan{0}_{1}.csv".format(k, i))
        trainfile.append("train_data_loan11_{0}.csv".format(i))

    Xsum_select, Xsum_uidsum=feature_Select_loanfor11(trainpath, trainfile, maxfeaturenum)
    print("size:"+str(Xsum_select.shape))
    # print(Xsum_uidsum)
    X33_select, X33_uid = del_feature_similar_laon11_33()
    trainData_feature = np.concatenate([Xsum_select, X33_select])
    trainDatauid = np.concatenate([Xsum_uidsum, X33_uid])


    for prepath,filename,gpath,gfile in zip(predictpath,predictfile ,goalpath,goalfile):
        for fn,gf in zip(filename,gfile):
            if os.path.exists(prepath + fn):
                trainData = pd.read_csv(prepath + fn)
                if(trainData.shape[0]>3):
                    X_feature = np.array(trainData.iloc[:, 1:])
                    X_uidsum = np.array(trainData.iloc[:, 0:1])
                    preselect_feature = SelectKBest(mutual_info_regression, k=maxfeaturenum).fit_transform(X_feature, X_uidsum[:, 0])
                elif(trainData.shape[0]>0):
                    preselect_feature, X_uidsum=feature_Select_del_similar_for8910(prepath,fn,featurenum=maxfeaturenum)
                else:
                    continue
                pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, preselect_feature, X_uidsum,
                                                       maxfeature=maxfeaturenum)
                pdd.to_csv(gpath+gf, index=False)


    #  下面 处理 8 月  40  的特征选择和预测情形
    feature840, X_uidsum840=del_feature_similar_loan8_40()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid,feature840, X_uidsum840,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan08/predict_loan08_40.csv", index=False)
    # 下面 处理 9 月 39的情况
    feature939, X_uidsum939 = del_feature_similar_loan09_39()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature939, X_uidsum939,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan09/predict_loan09_39.csv", index=False)



def train_predict_loan8910_40_100():
    #  预测  8 9 10  月 25-41 的 loan,,去除  8月40  以及  9 月39
    maxfeaturenum = 40
    trainpath="./trainData_loan/"
    predictpath=["./testLoanData08/","./testLoanData09/","./testLoanData10/"]
    goalpath=["./predict_loan08/","./predict_loan09/","./predict_loan10/"]
    trainfile = []
    goalfile, predictfile = [[], [], []], [[], [], []]
    for i in range(41, 53):
        for k in range(8, 11):
            if (k <= 9):
                goalfile[k - 8].append("predict_loan0{0}_{1}.csv".format(k, i))
                predictfile[k - 8].append("test_loan0{0}_{1}.csv".format(k, i))
            else:
                goalfile[k - 8].append("predict_loan{0}_{1}.csv".format(k, i))
                predictfile[k - 8].append("test_loan{0}_{1}.csv".format(k, i))
        trainfile.append("train_data_loan11_{0}.csv".format(i))

    print(trainfile)
    # for fi in trainfile:
    Xsum_select, Xsum_uidsum=feature_Select_loanfor11(trainpath, trainfile, maxfeaturenum)


    print("训练集  特征选择 完成")
    # print("size:"+str(Xsum_select.shape))

    X1158_select, X1158_uid = del_feature_similar_loan11_58()
    X1168_select,X1168_uid= del_feature_similar_loan11_68()

    trainData_feature = np.concatenate([Xsum_select, X1158_select,X1168_select])
    # trainData_feature=np.concatenate([Xsum_select,])
    trainDatauid = np.concatenate([Xsum_uidsum, X1158_uid,X1168_uid])
    # trainDatauid=np.concatenate([Xsum_select,X1168_uid])
    print(trainData_feature.shape)
    print(trainDatauid.shape)


    for prepath,filename,gpath,gfile in zip(predictpath,predictfile ,goalpath,goalfile):
        for fn,gf in zip(filename,gfile):
            if os.path.exists(prepath + fn):
                trainData = pd.read_csv(prepath + fn)
                if(trainData.shape[0]>3):
                    X_feature = np.array(trainData.iloc[:, 1:])
                    X_uidsum = np.array(trainData.iloc[:, 0:1])
                    preselect_feature = SelectKBest(mutual_info_regression, k=maxfeaturenum).fit_transform(X_feature, X_uidsum[:, 0])
                elif(trainData.shape[0]>0):
                    preselect_feature, X_uidsum=feature_Select_del_similar_for8910(prepath,fn,featurenum=maxfeaturenum)
                else:
                    continue
                pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, preselect_feature, X_uidsum,
                                                       maxfeature=maxfeaturenum)
                pdd.to_csv(gpath+gf, index=False)
    # 8 月50
    feature850, X_uidsum850 = del_feature_similar_loan08_50()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature850, X_uidsum850,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan08/predict_loan08_50.csv", index=False)

    # 8 月69
    feature869, X_uidsum869 = del_feature_similar_loan08_69()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature869, X_uidsum869,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan08/predict_loan08_69.csv", index=False)
    # 8 月 64
    feature864, X_uidsum864= del_feature_similar_loan08_64()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature864, X_uidsum864,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan08/predict_loan08_64.csv", index=False)
    # 8 月 100
    feature8100, X_uidsum8100 = del_feature_similar_loan08_100()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature8100, X_uidsum8100,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan08/predict_loan08_100.csv", index=False)

    # 9 月 73
    feature973, X_uidsum973 = del_feature_similar_loan09_73()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature973, X_uidsum973,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan09/predict_loan09_73.csv", index=False)

    # 9 月128
    feature9128, X_uidsum9128= del_feature_similar_loan09_128()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature9128, X_uidsum9128,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan09/predict_loan09_128.csv", index=False)

    # 10月57
    feature1057, X_uidsum1057 = del_feature_similar_loan10_57()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature1057, X_uidsum1057,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan10/predict_loan10_57.csv", index=False)

    # 10月58
    feature1058, X_uidsum1058 = del_feature_similar_loan10_58()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature1058, X_uidsum1058,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan10/predict_loan10_58.csv", index=False)

    #10 月60
    feature1060, X_uidsum1060 = del_feature_similar_loan10_60()
    pdd = train_predict_loan8910_21_24_100(trainData_feature, trainDatauid, feature1060, X_uidsum1060,
                                           maxfeature=maxfeaturenum)
    pdd.to_csv("./predict_loan10/predict_loan10_60.csv", index=False)





