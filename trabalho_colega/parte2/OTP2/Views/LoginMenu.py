import tkinter

import time

from Utils.DBUpdater import DBUpdater
from Views.AddPlafondMenu import AddPlafondMenu
from Views.BuyContractMenu import BuyContractMenu
from Views.LogoutMenu import LogoutMenu
from Views.Observer import Observer
from Views.SeeAssetsMenu import SeeAssetsMenu
from Views.SeeContractsMenu import SeeContractsMenu
from Views.SellContractMenu import SellContractMenu

try:
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *

from Controllers.Trader import Trader
from Controllers.Contract import Contract
from Controllers.Asset import Asset
from Controllers.AssetType import AssetType

class LoginMenu(Observer):

    def __init__(self, email):
        self.user = email
        self.sessionMenu = tkinter.Tk()

        self.W = Label(self.sessionMenu, text="OTP")
        self.L = Label(self.sessionMenu, text="Your Contracts:")
        self.W.grid(row=1, columnspan=2)
        self.L.grid(row=2, columnspan=2)

        self.createTable()
        self.tableContents = Trader.getContracts(self, self.user)

        seeAssets = Button(self.sessionMenu, text="See Assets", command=self.seeAssetsClicked)
        seeAssets.grid(columnspan=2)

        seeContractsAvailable = Button(self.sessionMenu, text="See Contracts Available",
                                       command=self.seeContractsAvailableClicked)
        seeContractsAvailable.grid(columnspan=2)

        sellContract = Button(self.sessionMenu, text="Sell Contract", command=self.sellContractClicked)
        sellContract.grid(columnspan=2)

        buyContract = Button(self.sessionMenu, text="Buy Contract", command=self.buyContractClicked)
        buyContract.grid(columnspan=2)

        addPlafond = Button(self.sessionMenu, text="Add Plafond", command=self.addPlafondClicked)
        addPlafond.grid(columnspan=2)

        logout = Button(self.sessionMenu, text="Logout", command=self.logoutClicked)
        logout.grid(columnspan=2)


    def run(self):
        self.myThread = DBUpdater(self.user, self)
        self.loadTable(self.myThread)
        self.myThread.join()

    def createTable(self):
        tv = Treeview()
        tv['columns'] = ('asset', 'lastPrice', 'change', 'changePercentage',
                         'volume', 'type', 'numberOfAssets','sellPrice', 'buyPrice', 'endOfContract',
                         'takeProfit', 'stopLoss')
        tv.heading('#0', text='Estado')
        tv.column('#0', anchor='w', width=90)
        tv.heading('asset', text='Ativo')
        tv.column('asset', anchor='center', width=100)
        tv.heading('lastPrice', text='Ultimo Preco')
        tv.column('lastPrice', anchor='center', width=90)
        tv.heading('change', text='Alteração')
        tv.column('change', anchor='center', width=90)
        tv.heading('changePercentage', text='Alteração (%)')
        tv.column('changePercentage', anchor='center', width=90)
        tv.heading('volume', text='Volume')
        tv.column('volume', anchor='center', width=90)
        tv.heading('type', text='Tipo')
        tv.column('type', anchor='center', width=80)
        tv.heading('numberOfAssets', text='Número de Ativos')
        tv.column('numberOfAssets', anchor='center', width=80)
        tv.heading('sellPrice', text='Preço de Venda')
        tv.column('sellPrice', anchor='center', width=90)
        tv.heading('buyPrice', text='Preço de Compra')
        tv.column('buyPrice', anchor='center', width=90)
        tv.heading('endOfContract', text='Fim do Contrato')
        tv.column('endOfContract', anchor='center', width=110)
        tv.heading('takeProfit', text='Take Profit')
        tv.column('takeProfit', anchor='center', width=100)
        tv.heading('stopLoss', text='Stop Loss')
        tv.column('stopLoss', anchor='center', width=100)
        tv.grid(sticky=(N, S, W, E))
        self.treeview = tv
        tv.grid_rowconfigure(0, weight=1)
        tv.grid_columnconfigure(0, weight=1)
        tv.grid()

    def loadTable(self, t):
        firstRun = True
        while(True):
            contracts = self.tableContents
            self.treeview.delete(*self.treeview.get_children())
            for c in contracts:
                self.treeview.insert('', 'end', text=self.getStatus(c), values=(self.getAssetName(c),
                                                                       self.getLastPrice(c), self.getChange(c),
                                                                       self.getChangePercentage(c), self.getVolume(c),
                                                                       self.getType(c), self.getNumberOfAssets(c),
                                                                       self.getSellPrice(c), self.getBuyPrice(c),
                                                                       self.getEndOfContract(c), self.getTakeProfit(c),
                                                                       self.getStopLoss(c)))
            self.sessionMenu.update_idletasks()
            self.sessionMenu.update()
            if firstRun is True:
                t.start()
                firstRun = False
            time.sleep(5)

    def observerUpdate(self):
        self.tableContents = Trader.getContracts(self, self.user)

    def getStatus(self, idContrato):
        return Contract.getStatus(self, idContrato)

    def getAssetId(self, idContrato):
        assetId = Contract.getAssetId(self, idContrato)
        return assetId

    def getAssetType(self, idAtivo):
        return Asset.getAssetType(self, idAtivo)

    def getAssetName(self, idContrato):
        assetId = self.getAssetId(idContrato)
        assetTypeId = self.getAssetType(assetId)
        return AssetType.getTypeName(self, assetTypeId)

    def getLastPrice(self, idContrato):
        assetId = self.getAssetId(idContrato)
        return Asset.getLastPrice(self, assetId)

    def getChange(self, idContrato):
        assetId = self.getAssetId(idContrato)
        return Asset.getChange(self, assetId)

    def getChangePercentage(self, idContrato):
        assetId = self.getAssetId(idContrato)
        return Asset.getChangePercentage(self, assetId)

    def getVolume(self, idContrato):
        assetId = self.getAssetId(idContrato)
        return Asset.getVolume(self, assetId)

    def getType(self, idContrato):
        sellerId = Contract.getSellerId(self, idContrato)
        buyerId = Contract.getBuyerId(self, idContrato)
        if(("'"+self.user+"'")==sellerId):
            return "Sell"
        if(("'"+self.user+"'")==buyerId):
            return "Buy"
        else:
            return "-"

    def getNumberOfAssets(self, idContrato):
        return Contract.getNumberOfAssets(self, idContrato)

    def getSellPrice(self, idContrato):
        return Contract.getSellPrice(self, idContrato)

    def getBuyPrice(self, idContrato):
        return Contract.getBuyPrice(self, idContrato)

    def getEndOfContract(self, idContrato):
        return Contract.getEndOfContract(self, idContrato)

    def getTakeProfit(self, idContrato):
        return Contract.getTakeProfit(self, idContrato)

    def getStopLoss(self, idContrato):
        return Contract.getStopLoss(self, idContrato)

    def seeAssetsClicked(self):
        self.sessionMenu.destroy()
        seeAssetsTable = SeeAssetsMenu(self.user)
        seeAssetsTable.run()

    def seeContractsAvailableClicked(self):
        self.sessionMenu.destroy()
        seeContractsAvailable = SeeContractsMenu(self.user)
        seeContractsAvailable.run()


    def sellContractClicked(self):
        self.sessionMenu.destroy()
        sellContract = SellContractMenu(self.user)
        
    def buyContractClicked(self):
        self.sessionMenu.destroy()
        buyContract = BuyContractMenu(self.user)
        
    def addPlafondClicked(self):
        self.sessionMenu.destroy()
        AddPlafondMenu(self.user)
        
    def logoutClicked(self):
        self.sessionMenu.destroy()
        logout = LogoutMenu()
        logout.run()




