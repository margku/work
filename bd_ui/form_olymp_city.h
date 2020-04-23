#ifndef FORM_OLYMP_CITY_H
#define FORM_OLYMP_CITY_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_olymp_city;
}

class form_olymp_city : public QDialog
{
    Q_OBJECT

public:
    explicit form_olymp_city(QWidget *parent = nullptr);
    ~form_olymp_city();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_olymp_city *ui;
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_OLYMP_CITY_H
