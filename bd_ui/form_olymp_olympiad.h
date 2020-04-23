#ifndef FORM_OLYMP_OLYMPIAD_H
#define FORM_OLYMP_OLYMPIAD_H

#include <QDialog>
#include <mainwindow.h>
#include <QRegExpValidator>

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlQuery>
#include <QtSql/QSqlTableModel>

namespace Ui {
class form_olymp_olympiad;
}

class form_olymp_olympiad : public QDialog
{
    Q_OBJECT

public:
    explicit form_olymp_olympiad(QWidget *parent = nullptr);
    ~form_olymp_olympiad();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_olymp_olympiad *ui;
    QRegExpValidator valid_data;//валидатор для поля даты
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_medal;//валидатор для полей медали
    QRegExpValidator valid_id;//валидатор для поля id
    QRegExpValidator valid_type;//валидатор для поля type
    QRegExpValidator valid_year;//валидатор для поля year

    //QSqlDatabase db1;
};

#endif // FORM_OLYMP_OLYMPIAD_H
