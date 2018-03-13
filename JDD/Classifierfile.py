
import pandas as pd
import numpy as np
import os







def count_loanAmont891011():
    sourcepath= "./loan_Data/"

    goalpath="./classifierFeature/"

    filename=[ "t_loan08.csv", "t_loan09.csv", "t_loan10.csv", "t_loan11.csv"]
    sumloanData=pd. read_csv("./sumData/sumloanData.csv")

    sumloanData=np. array(sumloanData)

    print(sumloanData[0,0])
    array = np.zeros((90994, 9))
    for i, fn in enumerate(filename):
        trainData=pd. read_csv(sourcepath+fn, chunksize =1,iterator= True)
        for line in iter(trainData):
            tem=int(line . iat[0,0])
            array[tem,0]=tem
            array[tem,2*i+1]= sumloanData[ tem,i+1]
            array[tem,2*i+2]=array[ tem, 2 * i+ 2 ]+line.iat [ 0 , 3 ]
    for i in range(90994):
        array[i,0]=i
    columns=[ "uid" ,"loan8", "loan8_amount","loan9" ,"loan9_amount" , "loan10", "loan10_amount", "loan11", "loan11_amount"]
    pdd=pd.DataFrame(array,columns = columns)
    pdd. to_csv("./classifierData/sumloanAmount891011.csv",index=False)