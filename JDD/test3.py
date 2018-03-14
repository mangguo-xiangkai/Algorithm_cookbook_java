


import pandas as pd
import numpy as np
import pickle
import xgboost as xgb
from collections import Counter
from operator import itemgetter,add
from functools import reduce


from train_model import train_predict_loan8910_2_20,train_predict_loan8910_21_24,train_predict_loan8910_25_40,train_predict_loan8910_40_100

# train_predict_loan8910_2_20()
# train_predict_loan8910_21_24()
#
# train_predict_loan8910_25_40()
# train_predict_loan8910_40_100()
#



from sklearn.model_selection import GridSearchCV
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split
# basestimater=
# trainpath="./trainData_loan/"
# trainData="train_data_loan11_17.csv"
#
# print(str(trainData))
# trainData = pd.read_csv(trainpath + trainData)
#
# X_feature = np.array(trainData.iloc[:, 2:])
# X_goal = np.array(trainData.iloc[:, 0:2])
#
# x_train, x_test, y_train, y_test = train_test_split(X_feature, X_goal, train_size=0.7)
# print(x_train.shape)
# y_train = y_train[:,1:2].reshape(y_train.shape[0],)
#
# param_test={
#     "max_depth":range(20,200,10),
#     "min_child_weight":range(1,5,1)
#
# }
# parasum=[(a,b) for a in param_test["max_depth"]
#                for b in param_test["min_child_weight"]]
#
#
# for a, b in parasum:
#     regXGB=xgb.XGBRegressor(max_depth=a, n_estimators=1000, random_state=1,scoring="roc_auc",min_child_weight=b)
#     regXGB.fit(x_train, y_train)
#     y=regXGB.predict(x_test)
#     RESM=reduce(add,(np.power(y[i]-y_test[i],2) for i in range(x_test.shape[0])))
#
#     print(param_test["max_depth"])





