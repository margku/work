#ifndef FORM_OLYMP_OBJECT_H
#define FORM_OLYMP_OBJECT_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_olymp_object;
}

class form_olymp_object : public QDialog
{
    Q_OBJECT

public:
    explicit form_olymp_object(QWidget *parent = nullptr);
    ~form_olymp_object();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_olymp_object *ui;
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_OLYMP_OBJECT_H
