from mysql.connector import connection

class Model:

    cnx = connection.MySQLConnection(user='root', password='password',
                                          host='127.0.0.1',
                                          database='otp_db')
    cnx.autocommit = True

    def __init__(self):
        pass



