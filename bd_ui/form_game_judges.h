#ifndef FORM_GAME_JUDGES_H
#define FORM_GAME_JUDGES_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_game_judges;
}

class form_game_judges : public QDialog
{
    Q_OBJECT

public:
    explicit form_game_judges(QWidget *parent = nullptr);
    ~form_game_judges();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_game_judges *ui;
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_GAME_JUDGES_H
