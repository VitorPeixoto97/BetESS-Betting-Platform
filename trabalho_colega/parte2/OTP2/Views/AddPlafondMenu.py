from Controllers.Trader import Trader

try:
    import tkinter
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *

class AddPlafondMenu:
    def __init__(self, email):
        self.user = email
        self.addPlafond = tkinter.Tk()

        self.W = Label(self.addPlafond, text="OTP")
        self.L = Label(self.addPlafond, text="Add plafond:")
        self.W.grid(row=1, columnspan=2)
        self.L.grid(row=2, columnspan=2)

        self.L1 = Label(self.addPlafond, text="How much you want to add? ")
        self.E1 = Entry(self.addPlafond)

        self.L1.grid(row=3, sticky="E")
        self.E1.grid(row=3, column=1)

        addButton = Button(self.addPlafond, text="Add", command=self.addButtonClicked)
        addButton.grid(columnspan=2)

        getBack = Button(self.buyContract, text="Back to Menu", command=self.getBackClicked)
        getBack.grid(columnspan=2)

    def addButtonClicked(self):
        plafond = self.E1.get()
        result = Trader.addPlafond(self, self.user, plafond)
        if (result):
           messagebox.showinfo("Add info", "Plafond added successfully")
        else:
            messagebox.showerror("Add error", "Not possible to add plafond")

    def getBackClicked(self):
        from Views.LoginMenu import LoginMenu
        self.buyContract.destroy()
        loginMenu = LoginMenu(self.user)
        loginMenu.run()