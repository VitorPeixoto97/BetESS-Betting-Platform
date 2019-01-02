import Models
from Models.AssetType import AssetType

class AssetType:
    def __init__(self):
        pass

    def getTypeName(self, idTipo):
        if(idTipo!=0):
            return Models.AssetType.AssetType.getTypeName(self, idTipo)
        else:
            return "-"

    def getTypeId(self, typeName):
        return Models.AssetType.AssetType.getTypeId(self, typeName)