#ifndef FORM_OLYMP_SYMBOL_H
#define FORM_OLYMP_SYMBOL_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_olymp_symbol;
}

class form_olymp_symbol : public QDialog
{
    Q_OBJECT

public:
    explicit form_olymp_symbol(QWidget *parent = nullptr);
    ~form_olymp_symbol();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_olymp_symbol *ui;
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_OLYMP_SYMBOL_H
