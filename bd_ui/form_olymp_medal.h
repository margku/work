#ifndef FORM_OLYMP_MEDAL_H
#define FORM_OLYMP_MEDAL_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_olymp_medal;
}

class form_olymp_medal : public QDialog
{
    Q_OBJECT

public:
    explicit form_olymp_medal(QWidget *parent = nullptr);
    ~form_olymp_medal();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_olymp_medal *ui;
    QRegExpValidator valid_medal;//валидатор для полей медали
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_OLYMP_MEDAL_H
