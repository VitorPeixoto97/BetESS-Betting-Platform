from abc import ABC, abstractmethod

class Observer(ABC):

    def __init__(self):
        pass

    @abstractmethod
    def observerUpdate(self):
        pass
    #este método irá atualizar na view os valores dos ativos que se alteraram