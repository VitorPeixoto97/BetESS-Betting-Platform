from abc import ABC, abstractmethod

class Subject(ABC):

    def __init__(self):
        pass

    @abstractmethod
    # quando valor se altera, notificar views
    def notifyObservers(self):
        pass

