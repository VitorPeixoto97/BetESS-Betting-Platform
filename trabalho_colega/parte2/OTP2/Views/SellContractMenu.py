import tkinter

from Controllers.Contract import Contract
from Controllers.AssetType import AssetType
from Controllers.Asset import Asset

try:
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *

class SellContractMenu:
    def __init__(self, email):
        self.user = email
        self.sellContract = tkinter.Tk()

        self.W = Label(self.sellContract, text="OTP")
        self.L = Label(self.sellContract, text="Sell Contract:")
        self.W.grid(row=1, columnspan=2)
        self.L.grid(row=2, columnspan=2)

        self.L1 = Label(self.sellContract, text="Asset: ")
        self.E1 = Entry(self.sellContract)
        self.L2 = Label(self.sellContract, text="End of Contract (yyyy-mm-dd): ")
        self.E2 = Entry(self.sellContract)
        self.L3 = Label(self.sellContract, text="Take Profit: ")
        self.E3 = Entry(self.sellContract)
        self.L4 = Label(self.sellContract, text="Stop Loss: ")
        self.E4 = Entry(self.sellContract)
        self.L5 = Label(self.sellContract, text="Number of Assets: ")
        self.E5 = Entry(self.sellContract)

        self.L1.grid(row=3, sticky="E")
        self.L2.grid(row=4, sticky="E")
        self.L3.grid(row=5, sticky="E")
        self.L4.grid(row=6, sticky="E")
        self.L5.grid(row=7, sticky="E")

        self.E1.grid(row=3, column=1)
        self.E2.grid(row=4, column=1)
        self.E3.grid(row=5, column=1)
        self.E4.grid(row=6, column=1)
        self.E5.grid(row=7, column=1)

        sellButton = Button(self.sellContract, text="Sell", command=self.sellButtonClicked)
        sellButton.grid(columnspan=2)

        getBack = Button(self.sellContract, text="Back to Menu", command=self.getBackClicked)
        getBack.grid(columnspan=2)

    def sellButtonClicked(self):
        asset = self.E1.get()
        endOfContract = self.E2.get()
        takeProfit = self.E3.get()
        stopLoss = self.E4.get()
        numberOfContracts = self.E5.get()
        result = Contract.isValidContract(self, asset, endOfContract, takeProfit, stopLoss, numberOfContracts)
        if(result==True):
            assetId = AssetType.getTypeId(self, asset)
            sellPrice = Asset.getLastPrice(self, assetId)
            Contract.createContract(self, assetId, self.user, endOfContract, sellPrice, takeProfit, stopLoss, numberOfContracts)
            messagebox.showinfo("sell info", "You're selling the contract")
        else:
            messagebox.showerror("Sell error", result)

    def getBackClicked(self):
        from Views.LoginMenu import LoginMenu
        self.sellContract.destroy()
        loginMenu = LoginMenu(self.user)
        loginMenu.run()