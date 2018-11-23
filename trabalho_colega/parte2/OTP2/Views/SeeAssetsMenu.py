import time
import Controllers
from Controllers.Asset import Asset
from Utils.DBUpdater import DBUpdater

try:
    import tkinter
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *

class SeeAssetsMenu():

    def __init__(self, email):
        self.user = email
        self.assetsTable = tkinter.Tk()

        self.W = Label(self.assetsTable, text="OTP")
        self.L = Label(self.assetsTable, text="Assets")
        self.W.grid(row=1, columnspan=2)
        self.L.grid(row=2, columnspan=2)

        self.createTableAssets()

        getBack = Button(self.assetsTable, text="Back to Menu", command=self.getBackClicked)
        getBack.grid(columnspan=2)


    def run(self):
        self.myThread = DBUpdater(self.user, self)
        self.loadAssetsTable(self.myThread)
        self.myThread.join()

    def createTableAssets(self):
        tv = Treeview()
        tv['columns'] = ('lastPrice', 'change', 'changePercentage', 'volume')
        tv.heading('#0', text='Ativo')
        tv.column('#0', anchor='w', width=90)
        tv.heading('lastPrice', text='Ultimo Preco')
        tv.column('lastPrice', anchor='center', width=90)
        tv.heading('change', text='Alteração')
        tv.column('change', anchor='center', width=90)
        tv.heading('changePercentage', text='Alteração (%)')
        tv.column('changePercentage', anchor='center', width=90)
        tv.heading('volume', text='Volume')
        tv.column('volume', anchor='center', width=90)
        tv.grid(sticky=(N, S, W, E))
        self.treeview = tv
        tv.grid_rowconfigure(0, weight=1)
        tv.grid_columnconfigure(0, weight=1)
        tv.grid()


    def loadAssetsTable(self, t):
        firstRun = True
        while(True):
            self.treeview.delete(*self.treeview.get_children())
            for x in range(1, 8):
                self.treeview.insert('', 'end', text=self.getAssetName(x), values=(self.getLastPrice(x),
                                                                                    self.getChange(x),
                                                                                    self.getChangePercentage(x),
                                                                                    self.getVolume(x)))
            self.assetsTable.update_idletasks()
            self.assetsTable.update()
            if firstRun is True:
                t.start()
                firstRun = False
            time.sleep(5)

    def observerUpdate(self):
        pass

    def getAssetType(self, idAtivo):
        return Asset.getAssetType(self, idAtivo)

    def getAssetName(self, idAtivo):
        from Controllers.AssetType import AssetType
        assetTypeId = self.getAssetType(idAtivo)
        return AssetType.getTypeName(self, assetTypeId)

    def getLastPrice(self, idAtivo):
        return Asset.getLastPrice(self, idAtivo)

    def getChange(self, idAtivo):
        return Asset.getChange(self, idAtivo)

    def getChangePercentage(self, idAtivo):
        return Asset.getChangePercentage(self, idAtivo)

    def getVolume(self, idAtivo):
        return Asset.getVolume(self, idAtivo)

    def getBackClicked(self):
        from Views.LoginMenu import LoginMenu
        self.assetsTable.destroy()
        loginMenu = LoginMenu(self.user)
        loginMenu.run()
