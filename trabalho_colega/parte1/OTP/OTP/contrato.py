from mysql.connector import connection
import time
from datetime import date
class Contrato:

    def __init__(self):
        self.precoCompra=0
        self.precoVenda=0
        self.vendedor=''
        self.comprador=''
        self.fechoContrato=''
        self.stopLoss=0
        self.takeProfit=0

    cnx = connection.MySQLConnection(user='root', password='anamarques23',
                                     host='127.0.0.1',
                                     database='otp_db')
    cnx.autocommit = True

    def adicionaComprador(self, comprador, idContrato, numeroContratos):
        cursor = Contrato.cnx.cursor()
        cursor.execute("UPDATE contrato SET idComprador='"+comprador+"' WHERE idContrato='"+idContrato+"';")
        cursor.execute("UPDATE contrato SET numeroContratos=" + numeroContratos + " WHERE idContrato='" + idContrato + "';")

    def changePlafondVendedor(self, idVendedor, diferenca):
        cursor = Contrato.cnx.cursor()
        cursor.execute("SELECT plafond from trader WHERE email='"+idVendedor+"';")
        for plafond in cursor:
            plafondAtual=plafond
        plafondNovo=plafondAtual-diferenca
        cursor.execute("UPDATE trader SET plafond="+plafondNovo+"' WHERE email='"+idVendedor+"';")

    def changePlafondComprador(self, idComprador, diferenca):
        cursor = Contrato.cnx.cursor()
        cursor.execute("SELECT plafond from trader WHERE email='" + idComprador + "';")
        for plafond in cursor:
            plafondAtual = plafond
        plafondNovo = plafondAtual+diferenca
        cursor.execute("UPDATE trader SET plafond=" + plafondNovo + "' WHERE email='" + idComprador + "';")

    def encerraContrato(self, id, idComprador, idVendedor, precoVenda, ultimoPreco, contratos):
        cursor = Contrato.cnx.cursor()
        # atualiza preco de compra
        cursor.execute("UPDATE contrato SET precoCompra="+ultimoPreco+"WHERE idAtivo='"+id+"';")
        #altera estado
        cursor.execute("UPDATE contrato SET estado='fechado' WHERE idAtivo='" + id + "';")
        diferenca= (ultimoPreco - precoVenda)*contratos
        # adiciona diferença ao vendedor
        # retira diferença a comprador (se diferença negativa, comprador é que paga)
        Contrato.changePlafondVendedor(idVendedor, diferenca)
        Contrato.changePlafondComprador(idComprador, diferenca)

    def encerraContratoSemEfeito(self, id):
        cursor = Contrato.cnx.cursor()
        cursor.execute("UPDATE contrato SET estado='fechado' WHERE idAtivo='" + id + "';")

    def verificaContrato(self, id):
        cursor = Contrato.cnx.cursor()
        cursor.execute("SELECT ultimoPreco, dataFechoContrato, takeProfit, stopLoss, idComprador, idVendedor, numeroContratos from ativo AS A "
                       "INNER JOIN contrato AS C ON A.idAtivo=C.idAtivo "
                       "WHERE A.idAtivo='"+str(id)+"';")
        for (precoVenda, ultimoPreco, dataFechoContrato, takeProfit, stopLoss, idComprador, idVendedor, numeroContratos) in cursor:
            today = date.fromtimestamp(time.time())
            year, month, day = dataFechoContrato.split('-', 1)
            contratos=numeroContratos
            if idComprador =="NULL":
                Contrato.encerraContratoSemEfeito(id)
            if ultimoPreco>takeProfit:
                Contrato.encerraContrato(id, idVendedor, idComprador, precoVenda, takeProfit, contratos)
            if ultimoPreco<stopLoss:
                Contrato.encerraContrato(id, idVendedor, idComprador, precoVenda, stopLoss, contratos)
            if date(year, month, day) > today:
                Contrato.encerraContrato(id, idVendedor, idComprador, precoVenda, ultimoPreco, contratos)
