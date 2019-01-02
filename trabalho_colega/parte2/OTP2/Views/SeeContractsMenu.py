import tkinter

import time

from Controllers.Asset import Asset
from Controllers.AssetType import AssetType
from Controllers.Contract import Contract
from Utils.DBUpdater import DBUpdater
from Views.Observer import Observer

try:
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *


class SeeContractsMenu(Observer):

    def __init__(self, email):
        self.user = email
        self.contractsTable = tkinter.Tk()
        self.W = Label(self.contractsTable, text="OTP")
        self.L = Label(self.contractsTable, text="Contracts Available:")
        self.W.grid(row=1, columnspan=2)
        self.L.grid(row=2, columnspan=2)

        self.createTableContractsAvailable()
        self.tableContents = Contract.getAvailableContracts(self)

        getBack = Button(self.contractsTable, text="Back to Menu", command=self.getBackClicked)
        getBack.grid(columnspan=2)

    def run(self):
        self.myThread = DBUpdater(self.user, self)
        self.loadTable(self.myThread)
        self.myThread.join()

    def createTableContractsAvailable(self):
        tv = Treeview()
        tv['columns'] = ('asset', 'lastPrice', 'change', 'changePercentage',
                         'volume', 'type', 'numberOfAssets','sellPrice', 'buyPrice', 'endOfContract',
                         'takeProfit', 'stopLoss')
        tv.heading('#0', text='Id Contrato')
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
                self.treeview.insert('', 'end', text=str(c), values=(self.getAssetName(c),
                                                                       self.getLastPrice(c), self.getChange(c),
                                                                       self.getChangePercentage(c), self.getVolume(c),
                                                                       self.getType(c), self.getNumberOfAssets(c),
                                                                       self.getSellPrice(c), self.getBuyPrice(c),
                                                                       self.getEndOfContract(c), self.getTakeProfit(c),
                                                                       self.getStopLoss(c)))
            self.contractsTable.update_idletasks()
            self.contractsTable.update()
            if firstRun is True:
                t.start()
                firstRun = False
            time.sleep(5)

    def observerUpdate(self):
        self.tableContents = Contract.getAvailableContracts(self)

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
        if(sellerId=="-"):
            return "Available to Sell"
        if(buyerId=="-"):
            return "Available to Buy"
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

    def getBackClicked(self):
        from Views.LoginMenu import LoginMenu
        self.contractsTable.destroy()
        loginMenu = LoginMenu(self.user)
        loginMenu.run()
