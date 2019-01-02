from mysql.connector import connection
from contrato import Contrato
from ativo import Ativo
import csv
from threading import Thread
from random import randint
from urllib.request import urlopen
import time

class Connection(Thread):

    def __init__(self):
        Thread.__init__(self)

    cnx = connection.MySQLConnection(user='root', password='anamarques23',
                                     host='127.0.0.1',
                                     database='otp_db')
    cnx.autocommit = True

    cursor = cnx.cursor(buffered=True)

    gold = 'http://download.finance.yahoo.com/d/quotes.csv?s=GC=F&f=l1c1p2v&e=.csv'
    silver = 'http://download.finance.yahoo.com/d/quotes.csv?s=SI=F&f=l1c1p2v&e=.csv'
    crude = 'http://download.finance.yahoo.com/d/quotes.csv?s=CL=F&f=l1c1p2v&e=.csv'
    google = 'http://download.finance.yahoo.com/d/quotes.csv?s=GOOG&f=l1c1p2v&e=.csv'
    apple = 'http://download.finance.yahoo.com/d/quotes.csv?s=AAPL&f=l1c1p2v&e=.csv'
    nvidia = 'http://download.finance.yahoo.com/d/quotes.csv?s=NVDA&f=l1c1p2v&e=.csv'
    alibaba = 'http://download.finance.yahoo.com/d/quotes.csv?s=BABA&f=l1c1p2v&e=.csv'
    ibm = 'http://download.finance.yahoo.com/d/quotes.csv?s=IBM&f=l1c1p2v&e=.csv'

    files = [gold, silver, crude, google, apple, nvidia, alibaba, ibm]

    def atualizaContrato(self):
        cursor=self.cnx.cursor();
        cursor.execute("SELECT idContrato FROM contrato;")
        for id in cursor:
            Contrato.verificaContrato(self, id)

    def run(self):
        while True:
            for url in Connection.files:
                id = 0
                response = urlopen(url)
                text = [line.decode('utf-8') for line in response]
                cr = csv.reader(text)
                if url == Connection.gold:
                    id = 1
                elif url == Connection.silver:
                    id = 2
                elif url == Connection.crude:
                    id = 3
                elif url == Connection.google:
                    id = 4
                elif url == Connection.apple:
                    id=5
                elif url == Connection.nvidia:
                    id=6
                elif url==Connection.alibaba:
                    id=7
                elif url == Connection.ibm:
                    id = 8
                else:
                    print("URL inválido");
                for row in cr:
                    lastPrice = float(row[0])
                    changeN = row[1]
                    changePercentage = row[2]
                    volume = float(row[3])
                Ativo.atualizaAtivo(self, id, lastPrice, changeN, changePercentage, volume)
                Connection.atualizaContrato(self)

            secondsToSleep = randint(1, 5)
            time.sleep(secondsToSleep)
        Connection.cnx.close()