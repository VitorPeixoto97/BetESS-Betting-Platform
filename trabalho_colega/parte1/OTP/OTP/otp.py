from connection import Connection
from sessao import Sessao
class OTP:

    def main(argv):
        pass

    if __name__ == "__main__":
        myThread = Connection()
        myThread2 = Sessao()
        myThread.start()
        myThread2.start()
        myThread.join()
        myThread2.join()
