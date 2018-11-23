from mysql.connector import connection


class Ativo():

    def __init__(self):
        self.id=0
        self.nome=''
        self.ultimoPreco=0
        self.change=''
        self.changePercentage=''
        self.volume=0

    cnx = connection.MySQLConnection(user='root', password='anamarques23',
                                     host='127.0.0.1',
                                     database='otp_db')
    cnx.autocommit = True

    def atualizaAtivo(self, id, lastPrice, changeN, changePercentage, volume):
        self.id=id
        self.ultimoPreco=lastPrice
        self.change=changeN
        self.changePercentage=changePercentage
        self.volume=volume

        cursor=self.cnx.cursor()

        cursor.execute("UPDATE ativo SET ultimoPreco="+str(lastPrice)+" WHERE idAtivo='"+str(id)+"';")
#        cursor.execute("UPDATE ativo SET change='0' WHERE idAtivo='"+str(id)+"';")
        cursor.execute("UPDATE ativo SET changePercentagem='"+ str(changePercentage)+"' WHERE idAtivo='"+str(id)+"';")
        cursor.execute("UPDATE ativo SET volume="+str(volume)+" WHERE idAtivo='"+str(id)+"';")
