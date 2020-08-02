import tkinter as tk
import tkinter.ttk as ttk


from models import *
import peewee

from provider import Provider
class NetRating(tk.Frame):
    def __init__(self, parent=None):
        super().__init__(parent)

        table = ttk.Treeview(self, show="headings", selectmode="browse")
        headings = ('id', 'Name', 'Date')
        table["columns"] = headings
        table["displaycolumns"] = headings

        for head in headings:
            table.heading(head, text=head, anchor=tk.CENTER)
            table.column(head, anchor=tk.CENTER)
       
        cursor = Provider.allNet()
        # cpt = 0  # Counter representing the ID of your code.
        for row in cursor:
            table.insert('', 'end', values=(row.id, row.fileName, row.date))
        scrolltable = tk.Scrollbar(self, command=table.yview)
        table.configure(yscrollcommand=scrolltable.set)
        scrolltable.pack(side=tk.RIGHT, fill=tk.Y)
        table.pack(expand=tk.YES, fill=tk.BOTH)
def getNetRating():
    data = []
    root = tk.Tk()
    root.title('All nets')
    table = NetRating(root)
    table.pack(expand=tk.YES, fill=tk.BOTH)
    root.mainloop()
if __name__ == '__main__':
    # gameName = input('введите название игры tictactoe or tictactoe3d or reverse \n')
    getNetRating()
