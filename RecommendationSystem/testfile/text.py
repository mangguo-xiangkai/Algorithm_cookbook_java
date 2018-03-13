# coding: utf-8

import sys, time
import numpy as np
import scipy.sparse as sp

from MFbpr import MFbpr


def load_data(ratingFile, testRatio=0.1):
    user_count = item_count = 0
    ratings = []
    for line in open(ratingFile):
        arr = line.strip().split()
        user_id = int(arr[0])
        item_id = int(arr[1])
        score = float(arr[2])
        timestamp = int(arr[3])   #  this is   long   function in python2.
        ratings.append((user_id, item_id, score, timestamp))
        user_count = max(user_count, user_id)
        item_count = max(item_count, item_id)
    user_count += 1
    item_count += 1

    ratings = sorted(ratings, key=lambda x: x[3])  # sort by timestamp

    test_count = int(len(ratings) * testRatio)
    count = 0
    trainMatrix = sp.lil_matrix((user_count, item_count))
    testRatings = []
    for rating in ratings:
        if count < len(ratings) - test_count:
            trainMatrix[rating[0], rating[1]] = 1
        else:
            testRatings.append(rating)
        count += 1

    newUsers = set([])
    newRatings = 0

    for u in range(user_count):
        if trainMatrix.getrowview(u).sum() == 0:
            newUsers.add(u)
    for rating in ratings:
        if rating[0] in newUsers:
            newRatings += 1

    sys.stderr.write("Data\t{}\n".format(ratingFile))
    sys.stderr.write("#Users\t{}, #newUser: {}\n".format(user_count, len(newUsers)))
    sys.stderr.write("#Items\t{}\n".format(item_count))
    sys.stderr.write(
        "#Ratings\t {} (train), {}(test), {}(#newTestRatings)\n".format(
            trainMatrix.sum(), len(testRatings), newRatings))

    return trainMatrix, testRatings





if __name__ == "__main__":
    # data
    trainMatrix, testRatings,ratings = load_data('test2.txt')
    print(trainMatrix.dtype)
    print(trainMatrix.shape)
    # print(trainMatrix)
    # print(trainMatrix[3])
    # print(trainMatrix[4])
    print('testRatings',testRatings)
    print(ratings)
    # print('testRatings',testRatings)




