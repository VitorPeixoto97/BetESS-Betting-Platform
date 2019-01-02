from Models.Model import Model


class Asset(Model):

    def __init__(self):
        pass

    def getAssetType(self, idAtivo):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idTipo FROM ativo WHERE idAtivo='" + str(idAtivo) + "';")
        for idTipo in cursor:
            a, b = str(idTipo).split(",", 1)
            c, type = a.split("(", 1)
            return type
        return 0

    def getLastPrice(self, idAtivo):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT ultimoPreco FROM ativo WHERE idAtivo='" + str(idAtivo) + "';")
        for ultimoPreco in cursor:
            a, b = str(ultimoPreco).split(",", 1)
            c, price = a.split("(", 1)
            return price;
        return "-"

    def getChange(self, idAtivo):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT ativo.change FROM ativo WHERE idAtivo=" + str(idAtivo) + ";")
        for change in cursor:
            a, b = str(change).split(",", 1)
            c, d = a.split("(", 1)
            return d;
        return "-"

    def getChangePercentage(self, idAtivo):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT changePercentagem FROM ativo WHERE idAtivo='" + str(idAtivo) + "';")
        for changePercentagem in cursor:
            a, b = str(changePercentagem).split(",", 1)
            c, percentage = a.split("(", 1)
            return percentage;
        return "-"

    def getVolume(self, idAtivo):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT volume FROM ativo WHERE idAtivo='" + str(idAtivo) + "';")
        for volume in cursor:
            a, b = str(volume).split(",", 1)
            c, d= a.split("(", 1)
            return d;
        return "-"

    def updateAsset(self, idAtivo, lastPrice, change, changePercentage, volume):
        cursor = Model.cnx.cursor()
        cursor.execute("UPDATE ativo SET ultimoPreco=" + str(lastPrice) + ",  ativo.change="+str(change) +
                       ", changePercentagem=" + str(changePercentage) + ", volume=" + str(volume) +
                       " WHERE idAtivo='" + str(idAtivo) + "';")
        cursor.close()
        Model.cnx.commit()
        return True

