import time
from threading import Thread
from random import randint, random

from datetime import date

import Controllers
from Controllers.Contract import Contract
from Controllers.Asset import Asset

class DBUpdater(Thread):

    def __init__(self, email, tkframe):
        self.tkframe = tkframe
        self.user = email
        Thread.__init__(self)

    def randPrice(self, lastPrice):
        value = float(lastPrice) + (float(lastPrice) * (randint(-3, 3)/100))
        return value

    def randChange(self, changeN):
        value = float(changeN) + (float(changeN) * (randint(-3, 3)/100))
        return value

    def randPercentage(self, changePercentage):
        value = float(changePercentage) + (float(changePercentage) * (randint(-3, 3)/100))
        return value

    def randVolume(self, volume):
        value = float(volume) + (float(volume) * (randint(-3, 3)/100))
        return value

    def run(self):
        while True:
            newLastPrice = 0
            for x in range(1, 9):
                lastPrice = Asset.getLastPrice(self, x)
                newLastPrice = DBUpdater.randPrice(self, lastPrice)
                change = Asset.getChange(self, x)
                newChange = DBUpdater.randChange(self, change)
                changePercentage = Asset.getChangePercentage(self, x)
                newChangePercentage = DBUpdater.randPercentage(self, changePercentage)
                volume = Asset.getVolume(self, x)
                newVolume = DBUpdater.randVolume(self, volume)
                Asset.updateAsset(self, x, newLastPrice, newChange, newChangePercentage, newVolume)
                Asset.notifyObservers(self, self.user, self.tkframe)

            contracts = Contract.getOpenAssetContracts(self, x)
            for contract in contracts:
                todayDate = date.fromtimestamp(time.time())
                sellPrice = Contract.getSellPrice(self, contract)
                buyPrice = Contract.getBuyPrice(self, contract)
                endOfContract = Contract.getEndOfContract(self, contract)
                takeProfit = Contract.getTakeProfit(self, contract)
                stopLoss = Contract.getStopLoss(self, contract)
                sellerId = Contract.getSellerId(self, contract)
                buyerId = Contract.getBuyerId(self, contract)
                status = Contract.getStatus(self, contract)
                numberOfContracts = Contract.getNumberOfAssets(self, contract)
                Contract.updateContract(self, contract, newLastPrice, sellerId, buyerId, sellPrice,
                                        todayDate, endOfContract, takeProfit, stopLoss, status, numberOfContracts)

            secondsToSleep = randint(1, 5)
            time.sleep(secondsToSleep)