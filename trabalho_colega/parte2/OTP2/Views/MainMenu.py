import tkinter
from tkinter import *
from tkinter import messagebox
import time
from Controllers.Trader import Trader
from Views.LoginMenu import LoginMenu
from Views.RegisterMenu import RegisterMenu


class MainMenu:
    top = tkinter.Tk()
    W = Label(top, text="OTP")
    L = Label(top, text="Login")
    L1 = Label(top, text="Email: ")
    E1 = Entry(top)
    L2 = Label(top, text="Password: ")
    E2 = Entry(top)

    def run(self):
        self.W.grid(row=0, columnspan=2)
        self.L.grid(row=1, columnspan=2)

        self.L1.grid(row=2, sticky="E")
        self.L2.grid(row=3, sticky="E")
        self.E1.grid(row=2, column=1)
        self.E2.grid(row=3, column=1)

        loginButton = Button(self.top, text="Login", command=self.loginButtonClicked)
        loginButton.grid(columnspan=2)

        registerButton = Button(self.top, text="Don't have an account? Register", command=self.registerButtonClicked)
        registerButton.grid(columnspan=2)

        self.top.mainloop()

    def loginButtonClicked(self):
        email = self.E1.get()
        password = self.E2.get()
        if(Trader.isLoginValid(self, email, password)):
            self.top.destroy()
            loginMenu = LoginMenu(email)
            loginMenu.run()
        else:
            messagebox.showerror("Login error", "Incorrect email or password")


    def registerButtonClicked(self):
        registerMenu = RegisterMenu()
        self.top.destroy()
        registerMenu.run()



