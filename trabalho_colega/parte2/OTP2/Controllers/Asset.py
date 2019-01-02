import Models
from Controllers.Subject import Subject
from Models.Asset import Asset


class Asset(Subject):

    def __init__(self):
        pass

    def getAssetType(self, idAtivo):
        if(idAtivo!=0):
            return Models.Asset.Asset.getAssetType(self, idAtivo)
        else:
            return 0

    def getLastPrice(self, idAtivo):
        if(idAtivo!=0):
            return Models.Asset.Asset.getLastPrice(self, idAtivo)
        else:
            return "-"

    def getChange(self, idAtivo):
        if (idAtivo != 0):
            return Models.Asset.Asset.getChange(self, idAtivo)
        else:
            return "-"

    def getChangePercentage(self, idAtivo):
        if (idAtivo != 0):
            return Models.Asset.Asset.getChangePercentage(self, idAtivo)
        else:
            return "-"

    def getVolume(self, idAtivo):
        if (idAtivo != 0):
            return Models.Asset.Asset.getVolume(self, idAtivo)
        else:
            return "-"

    def updateAsset(self, idAtivo, lastPrice, change, changePercentage, volume):
        if(idAtivo>0):
            return Models.Asset.Asset.updateAsset(self, idAtivo, lastPrice, change, changePercentage, volume)
        else:
            return False

    def notifyObservers(self, email, tkframe):
        tkframe.observerUpdate()