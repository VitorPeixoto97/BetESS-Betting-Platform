from Models.Model import Model

class Contract(Model):
    def __init__(self):
        pass

    def getStatus(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT estado FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for estado in cursor:
            return estado
        return "-"

    def getAssetId(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idAtivo FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for idAtivo in cursor:
            a, b = str(idAtivo).split(",", 1)
            c, asset = a.split("(", 1)
            return asset
        return 0

    def getSellerId(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idVendedor FROM contrato WHERE idContrato='"+str(idContrato)+"' AND idVendedor IS NOT NULL;")
        for idVendedor in cursor:
            a, b = str(idVendedor).split(",", 1)
            c, seller = a.split("(", 1)
            return seller
        return 0

    def getBuyerId(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idComprador FROM contrato WHERE idContrato='"+str(idContrato)+"' AND idComprador IS NOT NULL;")
        for idComprador in cursor:
            a, b = str(idComprador).split(",", 1)
            c, buyer = a.split("(", 1)
            return buyer
        return 0

    def getNumberOfAssets(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT numeroContratos FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for numeroContratos in cursor:
            a, b = str(numeroContratos).split(",", 1)
            c, contracts = a.split("(", 1)
            return contracts

    def getSellPrice(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT precoVenda FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for precoVenda in cursor:
            a, b = str(precoVenda).split(",", 1)
            c, preco = a.split("(", 1)
            return preco
        return 0

    def getBuyPrice(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT precoCompra FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for precoCompra in cursor:
            a, b = str(precoCompra).split(",", 1)
            c, preco = a.split("(", 1)
            return preco
        return 0

    def getEndOfContract(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT dataFechoContrato FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for dataFechoContrato in cursor:
            if (dataFechoContrato != "NULL"):
                return dataFechoContrato
        return 0

    def getTakeProfit(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT takeProfit FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for takeProfit in cursor:
            a, b = str(takeProfit).split(",", 1)
            c, profit = a.split("(", 1)
            return profit
        cursor.close()
        return 0

    def getStopLoss(self, idContrato):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT stopLoss FROM contrato WHERE idContrato='"+str(idContrato)+"';")
        for stopLoss in cursor:
            a, b = str(stopLoss).split(",", 1)
            c, loss = a.split("(", 1)
            return loss
        cursor.close()
        return 0

    def getAvailableContracts(self):
        contracts = []
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idContrato FROM contrato where idVendedor IS NULL OR idComprador IS NULL;")
        for idContrato in cursor:
            a, b = str(idContrato).split(",", 1)
            c, d = a.split("(", 1)
            contracts.append(d)
        cursor.close()
        return contracts

    def createContract(self, assetId, sellerEmail, endOfContract, sellPrice, takeProfit, stopLoss, numberOfContracts):
        cursor = Model.cnx.cursor()
        cursor.execute("INSERT INTO contrato (idAtivo, idVendedor, precoVenda, dataFechoContrato, takeProfit,"
                       " stopLoss, numeroContratos, estado) VALUES("+str(assetId)+", \""+str(sellerEmail)+"\", \""+str(sellPrice)+
                       "\", \""+str(endOfContract)+"\", "+str(takeProfit)+", "+str(stopLoss)+", "+str(numberOfContracts)+", \"aberto\");")
        cursor.close()
        return True

    def getOpenAssetContracts(self, assetId):
        contracts = []
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idContrato FROM contrato WHERE idAtivo='"+str(assetId)+"' AND estado='aberto';")
        for idContrato in cursor:
            a, b = str(idContrato).split(",", 1)
            c, d = a.split("(", 1)
            contracts.append(d)
        cursor.close()
        return contracts

    def closeContractWithNoEffect(self, contractId):
        cursor = Model.cnx.cursor()
        cursor.execute("UPDATE contrato SET estado='fechado' WHERE idContrato='" + str(contractId) + "';")
        cursor.close()
        return True

    def closeContract(self, contractId, sellPrice, buyPrice, sellerId, buyerId, numberOfContracts):
        cursor = Model.cnx.cursor()
        cursor.execute("UPDATE contrato SET precoCompra=" + buyPrice + " WHERE idContrato='" + contractId + "';")
        # altera estado
        cursor.execute("UPDATE contrato SET estado='fechado' WHERE idContrato='" + contractId + "';")
        diff = (float(buyPrice) - float(sellPrice)) * numberOfContracts
        cursor.close()
        # adiciona diferença ao vendedor
        # retira diferença a comprador (se diferença negativa, comprador é que paga)
        self.changeSellerPlafond(sellerId, diff)
        self.changeBuyerPlafond(buyerId, diff)
        return True

    def changeSellerPlafond(self, sellerId, diff):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT plafond from trader WHERE email='"+str(sellerId)+"';")
        for plafond in cursor:
            plafondNow=plafond
            newPlafond=plafondNow-diff
        cursor.execute("UPDATE trader SET plafond="+ str(newPlafond) +"' WHERE email='"+str(sellerId)+"';")
        cursor.close()
        return True

    def changeBuyerPlafond(self, idComprador, diff):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT plafond from trader WHERE email='" + idComprador + "';")
        for plafond in cursor:
            plafondNow = plafond
        newPlafond = plafondNow+diff
        cursor.execute("UPDATE trader SET plafond=" + str(newPlafond) + "' WHERE email='" + idComprador + "';")
        cursor.close()
        return True

    def addBuyer(self, contractId, buyerId, buyPrice):
        cursor = Model.cnx.cursor()
        cursor.execute("UPDATE contrato SET idComprador='"+str(buyerId)+"', precoCompra="+str(buyPrice)+
                       " WHERE idContrato='"+str(contractId)+"';")
        cursor.close()
        return True

