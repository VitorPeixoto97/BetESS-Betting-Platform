from mysql.connector import connection
from contrato import Contrato

class Trader:
    cnx = connection.MySQLConnection(user='root', password='anamarques23',
                                 host='127.0.0.1',
                                 database='otp_db')
    cnx.autocommit = True

    def __init__(self):
        self.email=''
        self.password=''
        self.nome=''
        self.plafond=0

    #option 3
    def listaContratos(self):
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT * FROM contrato WHERE idVendedor='"+self.email+"' OR idComprador='"+self.email+"';")
        sum=0
        for row in cursor:
            sum+=1
            print(row)
        if sum==0:
            print("Não tem contratos")

    #option 4
    def verContrato(self):
        id = input("Id do contrato: ")
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT * FROM contrato WHERE idContrato="+id+";")
        for r in cursor:
            print(r)

    #option 1
    def verTodosAtivos(self):
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT * FROM ativo;")
        for r in cursor:
            print(r)

    #option 2
    def verTodosContratos(self):
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT * FROM contrato WHERE estado='aberto';")
        for r in cursor:
            print(r)

    #option 6
    def verContratosÀVenda(self):
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT * FROM contrato WHERE idComprador IS NULL;")
        for r in cursor:
            print(r)

    #option 7
    def comprarContrato(self):
        id = input("ID do contrato: ")
        numeroContratos = input("Nº de contratos que quer comprar: ")
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT * FROM contrato WHERE idContrato='"+id+"' AND idComprador IS NULL;")
        sum=0
        for row in cursor:
            sum+=1;
        if sum==1:
            Contrato.adicionaComprador(self, self.email, id, numeroContratos)
            print("Aceitou o contrato")
        else:
            print("Contrato não existe ou já tem comprador")

    #option 5
    def abrirContrato(self):
        id = input("ID do ativo: ")
        fim = input("Data de fim (yyyy-mm-dd): ")
        takeProfit = input("Take Profit: ")
        stopLoss = input("Stop Loss: ")
        cursor = Trader.cnx.cursor()
        cursor.execute("SELECT ultimoPreco FROM ativo WHERE idAtivo='"+id+"';")
        for ultimoPreco in cursor:
            precoVenda=ultimoPreco
            a,b=str(precoVenda).split(",", 1)
            c,d=a.split("(", 1)
        cursor.execute("INSERT INTO contrato (idAtivo, idVendedor, precoVenda, dataFechoContrato, takeProfit, stopLoss, estado) VALUES("+
                       id+", \""+self.email+"\", "+str(d)+", \""+str(fim)+"\", "+str(takeProfit)+", "+str(stopLoss)+", \"aberto\");")
        print("Contrato aberto")

    def adicionarPlafond(self):
        plafondNovo = input("Quanto pretende adicionar?")
        cursor = Contrato.cnx.cursor()
        cursor.execute("SELECT plafond from trader WHERE email='" + self.email + "';")
        for plafond in cursor:
            plafondAtual = plafond
        plafondNovo+=plafondAtual
        cursor.execute("UPDATE trader SET plafond="+plafondNovo+" WHERE email='"+self.email+"';")