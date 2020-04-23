#ifndef FORM_GAME_GAME_H
#define FORM_GAME_GAME_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_game_game;
}

class form_game_game : public QDialog
{
    Q_OBJECT

public:
    explicit form_game_game(QWidget *parent = nullptr);
    ~form_game_game();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_game_game *ui;
    QRegExpValidator valid_time;//валидатор временных полей
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_GAME_GAME_H
