
import os
import pickle
import pandas as pd
import numpy as np


def sumloanData891011_1_128():
    trainfile=[]
    predictfile = [[], [], [],trainfile]
    for i in range(2, 53):
        for k in range(8, 12): # 8 9 10 11
            if (k <= 9):
                predictfile[k - 8].append("predict_loan0{0}_{1}.csv".format(k, i))
            elif(k==10):
                predictfile[k - 8].append("predict_loan{0}_{1}.csv".format(k, i))
            else:
                predictfile[k - 8].append("train_data_loan{0}_{1}.csv".format(k, i))

    listfilename = [[64, 69, 100], [73, 128], [ 57, 58, 60], [58, 68]]
    for k, v in enumerate(listfilename):
        if k <= 1:
            for i in v:
                predictfile[k].append("predict_loan0{0}_{1}.csv".format(k + 8, i))
        elif(k==2):
            for i in v:
                predictfile[k].append("predict_loan{0}_{1}.csv".format(k + 8, i))
        else:
            for i in v:
                predictfile[k].append("train_data_loan{0}_{1}.csv".format(k + 8, i))

    prepath = ["./predict_loan08/", "./predict_loan09/", "./predict_loan10/","./trainData_loan/"]
    # predict_loan = ["predict_loan08_", "predict_loan09_", "predict_loan10_"]
    # prefile08, prefile09, prefile10 = [], [], []
    # trainfile = []
    # prefile = [prefile08, prefile09, prefile10,trainfile]
    #
    # for filenum in range(2, 52):
    #     for k in range(3):
    #         prefile[k].append(predict_loan[k] + "%d.csv" % filenum)
    #     trainfile.append("train_data_loan11_%d.csv" % filenum)
    # print(prefile08)
    t_dict = ["t_loan_dict08.dat", "t_loan_dict09.dat", "t_loan_dict10.dat", "t_loan_dict11.dat"]
    t_loan = ["t_loan08.csv", "t_loan09.csv", "t_loan10.csv", "t_loan11.csv"]
    array=np.zeros((90994,4))
    # for i in range(4):
    # # for pfile, ppath in zip(prefile, prepath):
    #     for ppf in predictfile[i]:
    for i ,prefile in enumerate(predictfile):
        for ppf in prefile:
            if os.path.exists(prepath[i]+ppf):
                if(pd.read_csv(prepath[i]+ppf).shape[0]>0):
                    data=pd.read_csv(prepath[i]+ppf,iterator=True,chunksize=1)
                    for da in iter(data):
                        if(i<=2):
                            if( array[int(da.iat[0,0]),i]==0):
                                array[int(da.iat[0, 0]), i]=da.iat[0, 3]  # 选择 8910 XGB的  值
                            else:
                                raise "可能被重复赋值"+print(str(da.iat[0,0])+"   :::"+str(ppf))
                        else:
                            if (array[int(da.iat[0, 0]), i] == 0):
                                array[int(da.iat[0, 0]), i] = da.iat[0, 1]  #  选择 11 月  loan_sum
                            else:
                                raise "可能被重复赋值" + print(str(da.iat[0, 0]) + "   :::" + str(ppf))
        with open("./dict_Data/" + t_dict[i], "rb") as dd:
            dict_out = pickle.load(dd)
            LoanD= pd.read_csv("./loan_Data/"+t_loan[i], iterator=True, chunksize=1)
            for LD in iter(LoanD):
                if (dict_out[int(LD.iat[0,0])] == 1):
                    if (array[int(LD.iat[0, 0]), i] == 0):
                        array[int(LD.iat[0,0]),i]=LD.iat[0,2]
                    else:
                        raise "可能被重复赋值" + print(str(LD.iat[0, 0]) + "   :::" + str(LoanD))
    goalpath="./sumData/"
    if not os.path.exists(goalpath):
        os.makedirs(goalpath)
    pdd =pd.DataFrame(array,columns=["loan_pre08","loan_pre09","loan_pre10","loan_train11"])
    pdd.to_csv(goalpath+"XGBsumloanData.csv")

# sumloanData891011_1_128()
def loansumData0_1excision():
    li = ["000", "001", "010", "100", "011", "101", "110", "111"]
    trainfile, predictfile = [], []
    for i in li:
        trainfile.append("sumloan_{0}t.csv".format(i))
        predictfile.append("sumloan_x{0}.csv".format(i))

    trainpath = "./trainSumLoanData/"
    trainData = "./sumData/sumloanData.csv"
    trainData = pd.read_csv(trainData)
    X_feature = np.array(trainData)
    columns = ["uid", "loan_pre08", "loan_pre09", "loan_pre10", "loan_train11"]
    def soggre(array, array1, i, tfile):
        array = np.delete(array, 0, 0)
        array1 = np.delete(array1, 0, 0)
        pd1 = pd.DataFrame(array, columns=columns)
        pd2 = pd.DataFrame(array1, columns=columns)
        pd1.to_csv(trainpath + tfile, index=False)
        pd2.to_csv(trainpath + predictfile[i], index=False)

    for i, tfile in enumerate(trainfile):
        array = np.zeros((1, 5))
        array1 = np.zeros((1, 5))
        if i == 0:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1,X_feature[j,:] ])
            soggre(array, array1, i, tfile)
        elif i == 1:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 2:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 3:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 4:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 5:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 6:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 7:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, X_feature[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, X_feature[j, :]])
            soggre(array, array1, i, tfile)


def classifierloansumData0_1excision():
    li = ["000", "001", "010", "100", "011", "101", "110", "111"]
    trainfile, predictfile = [], []
    for i in li:
        trainfile.append("classifierSumloan_{0}t.csv".format(i))
        predictfile.append("classifierSumloan_x{0}.csv".format(i))

    copyfile = pd.read_csv("./classifierData/sumloanAmount891011.csv")
    copyfile = np.array(copyfile)

    trainpath = "./classifierSumLoan/"
    trainData = "./sumData/sumloanData.csv"
    trainData = pd.read_csv(trainData)
    X_feature = np.array(trainData)
    columns = ["uid", "loan8", "loan8_amount", "loan9", "loan9_amount", "loan10", "loan10_amount", "loan11",
               "loan11_amount"]

    def soggre(array, array1, i, tfile):
        array = np.delete(array, 0, 0)
        array1 = np.delete(array1, 0, 0)
        pd1 = pd.DataFrame(array, columns=columns)
        pd2 = pd.DataFrame(array1, columns=columns)
        if not os.path.exists(trainpath):
            os.makedirs(trainpath)
        pd1.to_csv(trainpath + tfile, index=False)
        pd2.to_csv(trainpath + predictfile[i], index=False)

    for i, tfile in enumerate(trainfile):
        array = np.zeros((1, 9))
        array1 = np.zeros((1, 9))
        if i == 0:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 1:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 2:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 3:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 4:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) == 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 5:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) == 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 6:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) == 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) == 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
        elif i == 7:
            for j in range(X_feature.shape[0]):
                if (int(X_feature[j, 1]) > 0) & (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0):
                    array = np.concatenate([array, copyfile[j, :]])
                if (int(X_feature[j, 2]) > 0) & (int(X_feature[j, 3]) > 0) & (int(X_feature[j, 4]) > 0):
                    array1 = np.concatenate([array1, copyfile[j, :]])
            soggre(array, array1, i, tfile)
