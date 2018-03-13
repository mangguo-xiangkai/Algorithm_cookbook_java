
import pandas as pd
from collections import Counter
from  Datadeal import build_train_loan11_2 ,t_loan_trans_month ,Counter_dict_look ,Counter_dict_store
import pickle
import numpy as np
import os


###  通用2 3 4  5  6  7  测试


def run():
    numstr = ["5"]
    for num in range(5,6):
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
        i=0
        for line in iter(f_t_loan):
            if (dict_out[line.iat[0, 0]] == num):
                if (i == 0):
                    train_data_loan11.iat[j, 0] = line.uid
                    train_data_loan11.iat[j, 2] = line.loan_amount
                    for line1 in iter(t_loan_sum):
                        if (line1.iat[0, 0] == line.iat[0, 0]):
                            train_data_loan11.iat[j, 1] = line1.loan_sum
                            break
                    i=i+1
                    continue
                if((i+2)!=(num+1)):
                    train_data_loan11.iat[j, i+2] = line.loan_amount
                    i=i+1
                    continue
                if((i+2)==(num+1)):
                    train_data_loan11.iat[j, i + 2] = line.loan_amount
                    i=0
                    j = j + 1
        path = "./temporaryData/"
        if not (os.path.exists(path)):
            os.makedirs(path)
        train_data_loan11.to_csv(path+"train_data_loan11_%s.csv"%numstr[0],columns=columns, index=False)

run()


