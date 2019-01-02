from Models.Model import Model

class AssetType(Model):
    def __init__(self):
        pass

    def getTypeName(self, idTipo):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT nome FROM tipo WHERE idTipo='" + str(idTipo) + "';")
        for nome in cursor:
            return nome;
        return "-"

    def getTypeId(self, typeName):
        cursor = Model.cnx.cursor()
        cursor.execute("SELECT idTipo FROM tipo WHERE nome='" + str(typeName) + "';")
        for idTipo in cursor:
            a, b = str(idTipo).split(",", 1)
            c, d = a.split("(", 1)
            return d
        return "-"