import tkinter
from tkinter import messagebox
from tkinter import *

from Controllers.Trader import Trader
from Views.LoginMenu import LoginMenu


class RegisterMenu():

    def __init__(self):
        self.register = tkinter.Tk()
        self.W = Label(self.register, text="OTP")
        self.L = Label(self.register, text="Register")
        self.L1 = Label(self.register, text="Nome: ")
        self.E1 = Entry(self.register)
        self.L2 = Label(self.register, text="Email: ")
        self.E2 = Entry(self.register)
        self.L3 = Label(self.register, text="Password: ")
        self.E3 = Entry(self.register)
        self.L4 = Label(self.register, text="Plafond: ")
        self.E4 = Entry(self.register)

    def run(self):
        self.W.grid(row=0, columnspan=2)
        self.L.grid(row=1, columnspan=2)

        self.L1.grid(row=2, sticky="E")
        self.L2.grid(row=3, sticky="E")
        self.L3.grid(row=4, sticky="E")
        self.L4.grid(row=5, sticky="E")

        self.E1.grid(row=2, column=1)
        self.E2.grid(row=3, column=1)
        self.E3.grid(row=4, column=1)
        self.E4.grid(row=5, column=1)

        registerButton = Button(self.register, text="Register", command=self.registerButtonClicked)
        registerButton.grid(columnspan=2)

        self.register.mainloop()

    def registerButtonClicked(self):
        name = self.E1.get()
        email = self.E2.get()
        password = self.E3.get()
        plafond = self.E4.get()
        if (Trader.isRegisterValid(self, name, email, password, plafond)):
            messagebox.showinfo("Register info", "You're now registered. Welcome")
            self.register.destroy()
            loginMenu = LoginMenu(email)
            loginMenu.run()

        else:
            messagebox.showerror("Register error", "Invalid account")

