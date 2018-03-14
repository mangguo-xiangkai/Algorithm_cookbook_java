

import numpy as np
import scipy.sparse as sp


class load_data(object):    # def __init__(self,file_name):
        # self.file_name=file_name


    def mk_100K(file_name):
        ratings=[];user_count=0;item_count=0;
        for line in open(file_name):
            arr = line.strip().split()
            user_id = int(arr[0])
            item_id = int(arr[1])
            score = float(arr[2])
            timestamp = int(arr[3])  #
            ratings.append((user_id, item_id, score, timestamp))
            user_count = max(user_count, user_id)
            item_count = max(item_count, item_id)
        # user_count -= 1
        # item_count -= 1

        Matrix = sp.lil_matrix((user_count, item_count))   #  matrix is sparse matrix of lil_matrix format
        for rating in ratings:
            Matrix[rating[0]-1, rating[1]-1] = 1
        return Matrix,ratings
