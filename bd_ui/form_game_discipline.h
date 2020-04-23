#ifndef FORM_GAME_DISCIPLINE_H
#define FORM_GAME_DISCIPLINE_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_game_discipline;
}

class form_game_discipline : public QDialog
{
    Q_OBJECT

public:
    explicit form_game_discipline(QWidget *parent = nullptr);
    ~form_game_discipline();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_game_discipline *ui;
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
    QRegExpValidator valid_type;//валидатор для поля type
};

#endif // FORM_GAME_DISCIPLINE_H
