import tkinter

from Controllers.Asset import Asset
from Controllers.Contract import Contract

try:
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *

class BuyContractMenu:
    def __init__(self, email):
        self.user = email
        self.buyContract = tkinter.Tk()

        self.W = Label(self.buyContract, text="OTP")
        self.L = Label(self.buyContract, text="Buy Contract:")
        self.W.grid(row=1, columnspan=2)
        self.L.grid(row=2, columnspan=2)

        self.L1 = Label(self.buyContract, text="Contract Id: ")
        self.E1 = Entry(self.buyContract)

        self.L1.grid(row=3, sticky="E")
        self.E1.grid(row=3, column=1)

        buyButton = Button(self.buyContract, text="Buy", command=self.buyButtonClicked)
        buyButton.grid(columnspan=2)

        getBack = Button(self.buyContract, text="Back to Menu", command=self.getBackClicked)
        getBack.grid(columnspan=2)

    def buyButtonClicked(self):
        contract = self.E1.get()
        buyerId = Contract.getBuyerId(self, contract)
        assetId = Contract.getAssetId(self, contract)
        buyPrice = Asset.getLastPrice(self, assetId)
        if (buyerId == 0):
            value = Contract.addBuyer(self, contract, self.user, buyPrice)
            if(not value):
                messagebox.showerror("Buy error", value)
            else:
                messagebox.showinfo("Buy info", "You bought the contract")
        else:
            messagebox.showerror("Buy error", "Contract already has a buyer")

    def getBackClicked(self):
        from Views.LoginMenu import LoginMenu
        self.buyContract.destroy()
        loginMenu = LoginMenu(self.user)
        loginMenu.run()