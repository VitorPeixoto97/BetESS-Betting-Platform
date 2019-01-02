import Models
from Models.Trader import Trader
class Trader:

    def __init__(self):
        pass

    def isLoginValid(self, email, password):
        if(len(password)>0):
            return Models.Trader.Trader.isLoginValid(self, email, password)

    def isRegisterValid(self, name, email, password, plafond):
        if(len(name)>0) and (len(email)>0 and (len(password)>0) and (int(plafond))>=0):
            return Models.Trader.Trader.isRegisterValid(self, name, email, password, plafond)

    def getContracts(self, email):
        return Models.Trader.Trader.getContracts(self, email)

    def getName(self, idTrader):
        return Models.Trader.Trader.getName(self, idTrader)

    def addPlafond(self, email, plafond):
        if(len(plafond)==0):
            return False
        else:
            return Models.Trader.Trader.addPlafond(self, email, plafond)
