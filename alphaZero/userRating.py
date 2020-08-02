import tkinter as tk
import tkinter.ttk as ttk

from models import *
import peewee

from provider import Provider


class PersonRating(tk.Frame):
    def __init__(self, parent=None):
        super().__init__(parent)

        table = ttk.Treeview(self, show="headings", selectmode="browse")
        headings = ('id', 'Name', 'Rating, %')
        table["columns"] = headings
        table["displaycolumns"] = headings

        for head in headings:
            table.heading(head, text=head, anchor=tk.CENTER)
            table.column(head, anchor=tk.CENTER)

        cursor = Provider.personRating()
        # cpt = 0  # Counter representing the ID of your code.
        for row in cursor:
            # print(row.id, row.fileName)
            # tree.insert('', 'end', text=str(cpt), values=(row.id, row.fileName, row.training))
            table.insert('', 'end', values=(row.id, row.name, round(row.rating * 100)))
        scrolltable = tk.Scrollbar(self, command=table.yview)
        table.configure(yscrollcommand=scrolltable.set)
        scrolltable.pack(side=tk.RIGHT, fill=tk.Y)
        table.pack(expand=tk.YES, fill=tk.BOTH)
        # root.mainloop()


def getPersonRating():
    data = []
    root = tk.Tk()
    root.title('Person Rating')
    table = PersonRating(root)
    table.pack(expand=tk.YES, fill=tk.BOTH)
    root.mainloop()


if __name__ == '__main__':
    getPersonRating()
