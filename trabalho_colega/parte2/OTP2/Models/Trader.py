from Models.Model import Model

class Trader(Model):
    def __init__(self):
        super().__init__()

    def isLoginValid(self, email, password):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT COUNT(*) FROM trader WHERE email = %s and password = %s;", (email, password))
        row = cursor.fetchone()
        while row is not None:
            result = row
            row = cursor.fetchone()
        if result!=(0,):
            return True
        else:
            return False

    def isRegisterValid(self, name, newEmail, password, plafond):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT email FROM trader")
        for email in cursor:
            a, b = str(email).split(",", 1)
            c, d = a.split("(", 1)
            if d==("'"+newEmail+"'"):
                return False
        Trader.createAccount(self, name, newEmail, password, plafond)
        return True

    def createAccount(self, name, email, password, plafond):
        cursor = Model.cnx.cursor()
        cursor.execute("INSERT INTO trader VALUES(\"" + str(email) + "\", \"" + str(password) + "\", \"" + str(
            name) + "\", " + str(plafond) + ");")

    def getContracts(self, email):
        contracts = []
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idContrato FROM contrato where idVendedor='"+email+"' OR idComprador='"+email+"';")
        for idContrato in cursor:
            a, b = str(idContrato).split(",", 1)
            c, d = a.split("(", 1)
            contracts.append(d)
        return contracts

    def getName(self, idTrader):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT nome FROM trader where idTrader='"+idTrader+"';")
        for nome in cursor:
            return nome;
        return "-"

    def addPlafond(self, email, newPlafond):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT plafond FROM trader where idTrader='" + email + "';")
        for plafond in cursor:
            a, b = str(plafond).split(",", 1)
            c, lastPlafond = a.split("(", 1)
        newPlafond+=lastPlafond
        cursor.execute("UPDATE trader SET plafond='"+newPlafond+"' WHERE email='"+email+"';")
        return True