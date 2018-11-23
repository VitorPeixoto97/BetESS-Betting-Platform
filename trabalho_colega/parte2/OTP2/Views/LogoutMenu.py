try:
    import tkinter
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter import messagebox
    from tkinter.ttk import *

class LogoutMenu:
    def __init__(self):
        self.logout = tkinter.Tk()
        self.W = Label(self.logout, text="OTP")
        self.L = Label(self.logout, text="You logged out successfully")
        self.W.grid(row=0, columnspan=2)
        self.L.grid(row=2, columnspan=2)


    def run(self):

        loginButton = Button(self.logout, text="Close", command=self.mainMenuButtonClicked)
        loginButton.grid(columnspan=2)

        self.logout.mainloop()

    def mainMenuButtonClicked(self):
        self.logout.destroy()
