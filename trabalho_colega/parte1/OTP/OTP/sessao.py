from mysql.connector import connection
from threading import Thread
from trader import Trader

class Sessao(Thread):

    cnx = connection.MySQLConnection(user='root', password='anamarques23',
                                     host='127.0.0.1',
                                     database='otp_db')
    cnx.autocommit = True
    trader=Trader()

    def __init__(self):
        Thread.__init__(self)


# Login authorization
    def loginauth(email, password):
        cursor1 = Sessao.cnx.cursor()
        cursor1.execute("SELECT COUNT(*) FROM trader WHERE email = %s and password = %s;", (email, password))
        row = cursor1.fetchone()
        while row is not None:
            result = row
            row = cursor1.fetchone()
        if result!=(0,):
            print("Login successful")
            return True
        else:
            print("Login failed")
            return False


    def registerauth(emailU):
        cursor2 = Sessao.cnx.cursor()
        cursor2.execute("SELECT email FROM trader", (emailU))
        for email in cursor2:
            if email==emailU:
                print("Email invalido")
                return False
        return True


    def menuComSessao(self):
        menu=True
        while menu==True:
            option=input("1 - Ver todos os ativos\n"
                         "2 - Ver todos os contratos abertos\n"
                         "3 - Ver meus contratos\n"
                         "4 - Ver contrato\n"
                         "5 - Abrir contrato\n"
                         "6 - Ver contratos \"à venda\"\n"
                         "7 - Comprar contrato\n"
                         "8 - Adicionar plafond\n"
                         "9 - Terminar sessão\n")
            if option == '1':
                Sessao.trader.verTodosAtivos()
            if option == '2':
                Sessao.trader.verTodosContratos()
            if option == '3':
                Sessao.trader.listaContratos()
            if option == '4':
                Sessao.trader.verContrato()
            if option == '5':
                Sessao.trader.abrirContrato()
            if option == '6':
                Sessao.trader.verContratosÀVenda()
            if option == '7':
                Sessao.trader.comprarContrato()
            if option == '8':
                Sessao.trader.adicionarPlafond()
            if option == '9':
                self.trader = Trader()
                return True
            menu=False
            menuI = input("1 - Menu")
            if menuI == '1':
                menu = True

    #menu de utilizador
    def session(self, username):
        print("Welcome to your account " + username)
        return True


    # Login
    def login(self):
        while True:
            email = input("email: ")
            if not len(email) > 0:
                print("Email can't be blank")
            else:
                break
        while True:
            password = input("Password: ")
            if not len(password) > 0:
                print("Password can't be blank")
            else:
                break

        if Sessao.loginauth(str(email), str(password)):
            Sessao.trader.email=email
            Sessao.trader.password=password
            cursor1 = Sessao.cnx.cursor()
            cursor1.execute("SELECT nome, plafond FROM trader WHERE email='"+email+"';")
            for (nome, plafond) in cursor1:
                Sessao.trader.plafond=plafond
                Sessao.trader.nome=nome
            return Sessao.session(self, email)
        else:
            print("Invalid email or password")
            return False


    def criaConta(self, email, password, nome, plafond):
        cursor=Sessao.cnx.cursor()
        cursor.execute("INSERT INTO trader VALUES(\""+str(email)+"\", \"" +str(password)+"\", \"" +str(nome)+"\", " + str(plafond)+");")

    # Register
    def register(self):
        while True:
            email = input("Email: ")
            if not len(email) > 0:
                print("Email can't be blank")
                continue
            else:
                if Sessao.registerauth(email):
                    while True:
                        password = input("Password: ")
                        passwordConfirm = input("Confirm Password: ")
                        if password==passwordConfirm:
                            nome = input("Nome: ")
                            plafond = input("Plafond: ")
                            Sessao.trader.email=email
                            Sessao.trader.password=password
                            Sessao.trader.nome=nome
                            Sessao.trader.plafond=plafond
                            Sessao.criaConta(self, email, password, nome, plafond)
                            print("Conta criada")
                            return True
                        else:
                            print("Passwords não coincidem")
        return False

    #menu inicial
    def run(self):
        while True:
            option=input("Registar | Login : ")
            if option=='Registar':
                if Sessao.register(self)==True:
                    self.menuComSessao()
            if option=='Login':
                if Sessao.login(self)== True:
                    self.menuComSessao()
            else:
                print("Opção não existe")
