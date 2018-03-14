
import pandas as pd
from collections import Counter
import pickle
import os
import numpy as np
def t_loan_trans_month():
    d = {"uid": [0], "loan_time": [0], "loan_amount": [0], "plannum": [0]}
    fp11= pd.DataFrame(data=d)
    fp10=pd.DataFrame(data=d)
    fp09=pd.DataFrame(data=d)
    fp08=pd.DataFrame(data=d)
    f_t_loan = pd.read_csv("./sourceData/t_loan.csv", chunksize=1, iterator=True)
    for line in iter(f_t_loan):
        if("2016-08" in str(line.loan_time)):
            fp08=fp08.append(pd.DataFrame(line))
        elif ("2016-11" in str(line.loan_time)):
            fp11=fp11.append(pd.DataFrame(line))
        elif("2016-09" in str(line.loan_time)):
            fp09=fp09.append(pd.DataFrame(line))
        elif("2016-10" in str(line.loan_time)):
            fp10= fp10.append(pd.DataFrame(line))
    fp11=fp11.drop(0)
    fp10=fp10.drop(0)
    fp09=fp09.drop(0)
    fp08=fp08.drop(0)
    fp08=fp08.sort_values(["uid","loan_time"])
    fp09= fp09.sort_values(["uid", "loan_time"])
    fp10= fp10.sort_values(["uid", "loan_time"])
    fp11 = fp11.sort_values(["uid", "loan_time"])
    columns=["uid", "loan_time", "loan_amount", "plannum"]
    fp08.to_csv("./loan_Data/t_loan08.csv",columns=["uid","loan_time","loan_amount","plannum"],index=False)
    fp09.to_csv("./loan_Data/t_loan09.csv",columns=columns,index=False)
    fp10.to_csv("./loan_Data/t_loan10.csv",columns=columns,index=False)
    fp11.to_csv("./loan_Data/t_loan11.csv",columns=columns,index=False)

def Counter_dict_store():

    t_loan=("t_loan08.csv","t_loan09.csv","t_loan10.csv","t_loan11.csv")
    t_dict=("t_loan_dict08.dat","t_loan_dict09.dat","t_loan_dict10.dat","t_loan_dict11.dat")
    t_dict_dict = ("t_loan_dict_dict08.dat", "t_loan_dict_dict09.dat", "t_loan_dict_dict10.dat", "t_loan_dict_dict11.dat")
    f_name=("t_loan08","t_loan09","t_loan10","t_loan11")
    for t_loan,t_dict,t_dict_dict in zip(t_loan,t_dict,t_dict_dict):
        f_t_loan=pd.read_csv("./loan_Data/"+t_loan)
        t_loan_dict = Counter(f_t_loan.uid)
        # t_loan_dict=sorted(t_loan_dict.keys())
        t_loan_dict_dict = Counter(t_loan_dict.values())
        # t_loan_dict_dict=sorted(t_loan_dict_dict.items(),key=lambda item:item[0])
        if(os.path.exists("./dict_Data/")):
            pass
        else: os.makedirs("./dict_Data/")
        with open('./dict_Data/'+t_dict, "wb") as fp:
            pickle.dump(t_loan_dict, fp)
        with open('./dict_Data/'+t_dict_dict, "wb") as dd:
            pickle.dump(t_loan_dict_dict, dd)

def Counter_dict_look():
    # t_loan = ("t_loan08.csv", "t_loan09.csv", "t_loan10.csv", "t_loan10.csv")
    t_dict = ("t_loan_dict08.dat", "t_loan_dict09.dat", "t_loan_dict10.dat", "t_loan_dict11.dat")
    t_dict_dict = ("t_loan_dict_dict08.dat", "t_loan_dict_dict09.dat", "t_loan_dict_dict10.dat", "t_loan_dict_dict11.dat")
    for t_dict,t_dict_dict in zip(t_dict,t_dict_dict):
        with open("./dict_Data/"+t_dict, "rb") as dd:
            print(t_dict)
            out = pickle.load(dd)
            print(out)
        with open("./dict_Data/"+t_dict_dict,"rb")as dd:
            print(t_dict_dict)
            out=pickle.load(dd)
            print(out)

def build_train_loan11_2():
    f_t_loan = pd.read_csv("./loan_Data/t_loan11.csv", chunksize=1, iterator=True)
    with open("./dict_Data/t_loan_dict11.dat", "rb") as fp:
        dict_out = pickle.load(fp)
    with open("./dict_Data/t_loan_dict_dict11.dat", "rb") as dd:
        dict_dict_out = pickle.load(dd)
    t_loan_sum = pd.read_csv("./sourceData/t_loan_sum.csv",chunksize=1, iterator=True)
    i ,j =1, 0
    num=2
    # tup=[]
    # for num1 in range(num):
    #     tup.append(str("loan_amount%d" % num1))
    columns = ["uid", "loan_sum"] + ["loan_amount{0}".format(n) for n in range(num)]
    train_data_loan11_2 = pd.DataFrame(np.zeros((dict_dict_out[2], 4)), columns=columns)
    for line in iter(f_t_loan):
        if (dict_out[line.iat[0,0]] == 2):
            if (i == 1):
                train_data_loan11_2.iat[j, 0] = line.uid
                train_data_loan11_2.iat[j, 2] = line.loan_amount
                for line1 in iter(t_loan_sum):
                    if(line1.iat[0,0]==line.iat[0,0]):
                        train_data_loan11_2.iat[j,1]=line1.loan_sum
                        break
                i = 2
                continue
            if (i == 2):
                train_data_loan11_2.iat[j, 3] = line.loan_amount
                i = 1
                j = j + 1
    if not(os.path.exists("./trainData_loan/")):
        os.makedirs("./trainData_loan/")
    train_data_loan11_2.to_csv("./trainData_loan/train_data_loan11_2.csv",columns=columns,index=False)
def build_train_loan11_3():
    f_t_loan = pd.read_csv("./trainData/t_loan11.csv", chunksize=1, iterator=True)
    with open("./dict_Data/t_loan_dict11.dat", "rb") as fp:
        dict_out = pickle.load(fp)
    with open("./dict_Data/t_loan_dict_dict11.dat", "rb") as dd:
        dict_dict_out = pickle.load(dd)
    t_loan_sum = pd.read_csv("./sourceData/t_loan_sum.csv", chunksize=1, iterator=True)
    i, j, tup = 1, 0, []
    num = 3  ##  更改项
    for num1 in range(num):
        tup.append(str("loan_amount%d" % num1))
    columns = ["uid", "loan_sum"] + tup
    # num=3
    train_data_loan11_3 = pd.DataFrame(np.zeros((dict_dict_out[num], num + 2)), columns=columns)
    for line in iter(f_t_loan):
        if (dict_out[line.iat[0, 0]] == num):
            if (i == 1):
                train_data_loan11_3.iat[j, 0] = line.uid
                train_data_loan11_3.iat[j, 2] = line.loan_amount
                for line1 in iter(t_loan_sum):
                    if (line1.iat[0, 0] == line.iat[0, 0]):
                        train_data_loan11_3.iat[j, 1] = line1.loan_sum
                        break
                i = 2
                continue
            if (i == 2):
                train_data_loan11_3.iat[j, 3] = line.loan_amount
                i = 3
                continue
            if (i == 3):
                train_data_loan11_3.iat[j, 4] = line.loan_amount
                i = 1
                j = j + 1
    if not (os.path.exists("./trainData_loan/")):
        os.makedirs("./trainData_loan/")
    train_data_loan11_3.to_csv("./trainData_loan/train_data_loan11_3.csv", columns=columns, index=False)
def build_train_loan11_4():
    f_t_loan = pd.read_csv("./trainData/t_loan11.csv", chunksize=1, iterator=True)
    with open("./dict_Data/t_loan_dict11.dat", "rb") as fp:
        dict_out = pickle.load(fp)
    with open("./dict_Data/t_loan_dict_dict11.dat", "rb") as dd:
        dict_dict_out = pickle.load(dd)
    t_loan_sum = pd.read_csv("t_loan_sum.csv", chunksize=1, iterator=True)
    i, j, tup = 1, 0, []
    num = 4 ##  更改项
    for num1 in range(num):
        tup.append(str("loan_amount%d" % num1))
    columns = ["uid", "loan_sum"] + tup
    # num=3
    train_data_loan11 = pd.DataFrame(np.zeros((dict_dict_out[num], num + 2)), columns=columns)
    for line in iter(f_t_loan):
        if (dict_out[line.iat[0, 0]] == num):
            if (i == 1):
                train_data_loan11.iat[j, 0] = line.uid
                train_data_loan11.iat[j, 2] = line.loan_amount
                for line1 in iter(t_loan_sum):
                    if (line1.iat[0, 0] == line.iat[0, 0]):
                        train_data_loan11.iat[j, 1] = line1.loan_sum
                        break
                i = 2
                continue
            if (i == 2):
                train_data_loan11.iat[j, 3] = line.loan_amount
                i = 3
                continue
            if (i == 3):
                train_data_loan11.iat[j, 4] = line.loan_amount
                i = 4
                continue
            if(i==4):
                train_data_loan11.iat[j, 5] = line.loan_amount
                i=1
                j = j + 1
    if not (os.path.exists("./trainData_loan/")):
        os.makedirs("./trainData_loan/")
    train_data_loan11.to_csv("./trainData_loan/train_data_loan11_2.csv", columns=columns, index=False)


def general_loanData11_2_20():
    # numstr = ("2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
              # "16", "17", "18", "19", "20")
    # numstr = list(map(str, range(2, 51)))
    numstr=[str(n) for n in range(2,51)]
    for num in range(8, 21):
        j = 0
        f_t_loan = pd.read_csv("./loan_Data/t_loan11.csv", chunksize=1, iterator=True)
        with open("./dict_Data/t_loan_dict11.dat", "rb") as fp:
            dict_out = pickle.load(fp)
        with open("./dict_Data/t_loan_dict_dict11.dat", "rb") as dd:
            dict_dict_out = pickle.load(dd)
        t_loan_sum = pd.read_csv("./sourceData/t_loan_sum.csv", chunksize=1, iterator=True)
        tup = []
        for num1 in range(num):
            tup.append(str("loan_amount%d" % num1))
        columns = ["uid", "loan_sum"] + tup
        train_data_loan11 = pd.DataFrame(np.zeros((dict_dict_out[num], num + 2)), columns=columns)
        i = 0
        for line in iter(f_t_loan):
            if (dict_out[line.iat[0, 0]] == num):
                if (i == 0):
                    train_data_loan11.iat[j, 0] = line.uid
                    train_data_loan11.iat[j, 2] = line.loan_amount
                    for line1 in iter(t_loan_sum):
                        if (line1.iat[0, 0] == line.iat[0, 0]):
                            train_data_loan11.iat[j, 1] = line1.loan_sum
                            break
                    i = i + 1
                    continue
                if ((i + 2) != (num + 1)):
                    train_data_loan11.iat[j, i + 2] = line.loan_amount
                    i = i + 1
                    continue
                if ((i + 2) == (num + 1)):
                    train_data_loan11.iat[j, i + 2] = line.loan_amount
                    i = 0
                    j = j + 1
        path="./trainData_loan/"
        if not (os.path.exists(path)):
            os.makedirs(path)
        train_data_loan11.to_csv(path+"train_data_loan11_%s.csv" % numstr[num - 2], columns=columns,
                                 index=False)



def general_loanData8910_2_50(loan_data,dict_data,dict_dict_data,filename,path):
    numstr = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
              "14", "15","16", "17", "18", "19", "20","21","22","23","24","25",
              "26","27","28","29","30","31","32","33","34","35","36","37","38",
              "39","40","41","42","43","44","45","46","47","48","49","50"]
    # numstr = list(map(str, range(2, 51)))
    for num in range(2, 51):
        j = 0
        f_t_loan = pd.read_csv(loan_data, chunksize=1, iterator=True)
        with open(dict_data, "rb") as fp:
            dict_out = pickle.load(fp)
        with open(dict_dict_data, "rb") as dd:
            dict_dict_out = pickle.load(dd)
        t_loan_sum = pd.read_csv("./sourceData/t_loan_sum.csv", chunksize=1, iterator=True)
        # tup = []
        # for num1 in range(num):
        #     tup.append(str("loan_amount%d" % num1))
        columns = ["uid", "loan_sum"] + ["loan_amount{}".format(n) for n in range(num)]
        train_data_loan11 = pd.DataFrame(np.zeros((dict_dict_out[num], num + 2)), columns=columns)
        i = 0
        for line in iter(f_t_loan):
            if (dict_out[line.iat[0, 0]] == num):
                if (i == 0):
                    train_data_loan11.iat[j, 0] = line.uid
                    train_data_loan11.iat[j, 2] = line.loan_amount
                    for line1 in iter(t_loan_sum):
                        if (line1.iat[0, 0] == line.iat[0, 0]):
                            # train_data_loan11.iat[j, 1] = line1.loan_sum    c错误  删除
                            break
                    i = i + 1
                    continue
                if ((i + 2) != (num + 1)):
                    train_data_loan11.iat[j, i + 2] = line.loan_amount
                    i = i + 1
                    continue
                if ((i + 2) == (num + 1)):
                    train_data_loan11.iat[j, i + 2] = line.loan_amount
                    i = 0
                    j = j + 1
        # path=path  没用
        if not (os.path.exists(path)):
            os.makedirs(path)
        train_data_loan11.to_csv(path+filename+"%s.csv" % numstr[num - 2], columns=columns,
                                 index=False)
def run_loan_data8910():
    abc=("./loan_Data/t_loan08.csv","./loan_Data/t_loan09.csv","./loan_Data/t_loan10.csv")
    dct=("./dict_Data/t_loan_dict08.dat","./dict_Data/t_loan_dict09.dat","./dict_Data/t_loan_dict10.dat")
    dct_dct=("./dict_Data/t_loan_dict_dict08.dat","./dict_Data/t_loan_dict_dict09.dat","./dict_Data/t_loan_dict_dict10.dat")
    filename=("test_loan08_","test_loan09_","test_loan10_")
    path=("./testLoanData08/","./testLoanData09/","./testLoanData10/")
    for abc,dct,dct_dct,filename,path in zip(abc,dct,dct_dct,filename,path):
        general_loanData8910_2_50(abc,dct,dct_dct,filename,path=path)



def del_loansum8910():
    numstr = ["02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
              "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
              "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
              "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"]
    # numstr=list(map(str, range(2,51)))
    # numstr = []
    # for n in range(2, 21):
    #     numstr.append(str("%d" % n))  参考

    # numstr=[str(n) for n in range(2,51)]
    filename = ("test_loan08_", "test_loan09_", "test_loan10_")
    path = ("./testLoanData08/", "./testLoanData09/", "./testLoanData10/")
    for path, filename in zip(path, filename):
        for num in range(2, 51):
            tup=[]
            for num1 in range(num):
                tup.append(str("loan_amount%d" % num1))
            columns = ["uid"] + tup
            train_data_loan11=pd.read_csv(path+filename+"%s.csv" % numstr[num-2])
            train_data_loan11 = train_data_loan11.drop(["loan_sum"], axis=1)
            train_data_loan11.to_csv(path + filename+"%s.csv" % numstr[num-2], columns=columns,index=False)



def general_loanData8910_specific(loan_data,dict_data,dict_dict_data,filename,path,listf):

    # listfilename = [[64, 69, 100], [52, 73, 128], [52, 57, 58, 60]]
    for num in listf:
        j = 0
        f_t_loan = pd.read_csv(loan_data, chunksize=1, iterator=True)
        with open(dict_data, "rb") as fp:
            dict_out = pickle.load(fp)
        with open(dict_dict_data, "rb") as dd:
            dict_dict_out = pickle.load(dd)

        columns = ["uid"] + ["loan_amount{}".format(n) for n in range(num)]
        train_data_loan11 = pd.DataFrame(np.zeros((dict_dict_out[num], num + 1)), columns=columns)
        i = 0
        for line in iter(f_t_loan):
            if (dict_out[line.iat[0, 0]] == num):
                if (i == 0):
                    train_data_loan11.iat[j, 0] = line.uid
                    train_data_loan11.iat[j, 1] = line.loan_amount
                    i = i + 1
                    continue
                if ((i + 1) != (num )):
                    train_data_loan11.iat[j, i + 1] = line.loan_amount
                    i = i + 1
                    continue
                if ((i + 1) == (num )):
                    train_data_loan11.iat[j, i + 1] = line.loan_amount
                    i = 0
                    j = j + 1

        if not (os.path.exists(path)):
            os.makedirs(path)
        train_data_loan11.to_csv(path+filename+"{0}.csv".format(num), columns=columns,
                                 index=False)
def run_loan_data8910_specific():
    abc=("./loan_Data/t_loan08.csv","./loan_Data/t_loan09.csv","./loan_Data/t_loan10.csv")
    dct=("./dict_Data/t_loan_dict08.dat","./dict_Data/t_loan_dict09.dat","./dict_Data/t_loan_dict10.dat")
    dct_dct=("./dict_Data/t_loan_dict_dict08.dat","./dict_Data/t_loan_dict_dict09.dat","./dict_Data/t_loan_dict_dict10.dat")
    filename=("test_loan08_","test_loan09_","test_loan10_")
    path=("./testLoanData08/","./testLoanData09/","./testLoanData10/")
    listfilename = [[64, 69, 100], [52, 73, 128], [52, 57, 58, 60]]
    # listfilename1=[[],[],[52, 57, 58, 60]]
    for abc,dct,dct_dct,filename,path ,listfile in zip(abc,dct,dct_dct,filename,path,listfilename):
        general_loanData8910_specific(abc,dct,dct_dct,filename,path,listfile)