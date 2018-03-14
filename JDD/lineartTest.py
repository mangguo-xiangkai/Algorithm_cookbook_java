
import pandas as pd
import numpy as np
import os



from sklearn.linear_model import SGDRegressor


clf=SGDRegressor

trainData=np.array(pd.read_csv("./sumData/sumloanData.csv"))
x_train=np.array([8,9,10,11])
y_train=trainData[4,1:5]
print("y_train:{0}".format(y_train))

x_test=12

clf.fit(x_train,y_train)
y_pre=clf.predict(x_test)
print(y_pre)