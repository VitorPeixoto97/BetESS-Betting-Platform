from datetime import date

import Models
from Models.Contract import Contract

class Contract:
    def __init__(self):
        pass

    def getStatus(self, idContrato):
        if(idContrato!=0):
            return Models.Contract.Contract.getStatus(self, idContrato)
        else:
            return "-"

    def getAssetId(self, idContrato):
        if(idContrato!=0):
            return Models.Contract.Contract.getAssetId(self, idContrato)
        else:
            return 0

    def getSellerId(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getSellerId(self, idContrato)
        else:
            return "-"

    def getBuyerId(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getBuyerId(self, idContrato)
        else:
            return "-"

    def getNumberOfAssets(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getNumberOfAssets(self, idContrato)
        else:
            return "-"

    def getSellPrice(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getSellPrice(self, idContrato)
        else:
            return "-"

    def getBuyPrice(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getBuyPrice(self, idContrato)
        else:
            return "-"

    def getEndOfContract(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getEndOfContract(self, idContrato)
        else:
            return "-"

    def getTakeProfit(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getTakeProfit(self, idContrato)
        else:
            return "-"

    def getStopLoss(self, idContrato):
        if (idContrato != 0):
            return Models.Contract.Contract.getStopLoss(self, idContrato)
        else:
            return "-"

    def getAvailableContracts(self):
        return Models.Contract.Contract.getAvailableContracts(self)

    def isValidContract(self, asset, endOfContract, takeProfit, stopLoss, numberOfContracts):
        if((asset!='Gold') or (asset!='Silver') or (asset!='Crude Oil') or (asset!='Google') or
               (asset!='Apple') or(asset!='Nvidia') or (asset!='Alibaba') or (asset!='IBM')):
            y, m, d = endOfContract.split("-")
            if((int(y)>=2017) and (int(y)<=2018) and (int(m)>0) and (int(m)<13) and (int(d)>0) and (int(d)<32)):
                if(int(takeProfit)>0):
                    if(int(stopLoss)>0):
                        if(int(numberOfContracts)>0):
                            return True
                        else:
                            message = "Number of Contracts Invalid"
                    else:
                        message = "Stop Loss Invalid"
                else:
                    message = "Take Profit Invalid"
            else:
                message = "End Of Contract Invalid"
        else:
            message = "Asset Invalid"
        return message

    def createContract(self, assetId, sellerEmail, endOfContract, sellPrice, takeProfit, stopLoss, numberOfContracts):
        return Models.Contract.Contract.createContract(self, assetId, sellerEmail, endOfContract, sellPrice,
                                                       takeProfit, stopLoss, numberOfContracts)

    def getOpenAssetContracts(self, assetId):
        return Models.Contract.Contract.getOpenAssetContracts(self, assetId)

    def updateContract(self, contract, lastPrice, sellerId, buyerId, sellPrice, todayDate,
                       endOfContract, takeProfit, stopLoss, status, numberOfContracts):

        if(lastPrice>float(takeProfit)):
            if(buyerId=="-"):
                return False
            else:
                return  Models.Contract.Contract.closeContract(self, contract, sellPrice, takeProfit, sellerId, buyerId, numberOfContracts)
        if (lastPrice<float(stopLoss)):
            if (buyerId == "-"):
                return False
            else:
                return  Models.Contract.Contract.closeContract(self, contract, sellPrice, stopLoss, sellerId, buyerId, numberOfContracts)
        if endOfContract[0] > todayDate:
            if (buyerId == "-"):
                return Models.Contract.Contract.closeContractWithNoEffect(self, contract)
            else:
                return  Models.Contract.Contract.closeContract(self, contract, sellPrice, lastPrice, sellerId, buyerId, numberOfContracts)

    def addBuyer(self, contractId, buyerId, buyPrice):
        if((int(contractId)>0) and (len(buyerId)>0) and (float(buyPrice)>0)):
            return Models.Contract.Contract.addBuyer(self, contractId, buyerId, buyPrice)
        else:
            return "Not possible to buy. Check the contract Id"


