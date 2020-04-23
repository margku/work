#ifndef FORM_GAME_PART_COUNTRY_H
#define FORM_GAME_PART_COUNTRY_H

#include <QDialog>
#include <QRegExpValidator>

namespace Ui {
class form_game_part_country;
}

class form_game_part_country : public QDialog
{
    Q_OBJECT

public:
    explicit form_game_part_country(QWidget *parent = nullptr);
    ~form_game_part_country();
    bool cathEmpty();//функция отслеживания пустых строк
                                        //true - есть пустые строки
                                        //false - нет пустых строк

private slots:
    void on_but_save_clicked();

    void on_but_can_clicked();

private:
    Ui::form_game_part_country *ui;
    QRegExpValidator valid_str;//валидатор строковых полей
    QRegExpValidator valid_id;//валидатор для поля id
};

#endif // FORM_GAME_PART_COUNTRY_H
