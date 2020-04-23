#include "form_game_game.h"
#include "ui_form_game_game.h"
#include "mainwindow.h"

form_game_game::form_game_game(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_game_game),
     valid_time(QRegExp("^[0-9]{2}\\:[0-9]{2}$")),//задаём условия ввода
     valid_str(QRegExp("^[a-z]d{1,10}$")),
     valid_id(QRegExp("^[0-9]{1,7}$"))
{
    ui->setupUi(this);

    //параметры окна
    this->setMaximumWidth(222);
    this->setMinimumWidth(222);
    this->setMaximumHeight(340);
    this->setMinimumHeight(340);
    QPixmap icon(":/img/game.png");
    this->setWindowIcon(icon);

    //устанавливаем подсказки и критерии ввода
    ui->lineEdit_2->setPlaceholderText("Seven-digit number"); ui->lineEdit_2->setValidator(&valid_id);
    ui->lineEdit_3->setPlaceholderText("Seven-digit number"); ui->lineEdit_3->setValidator(&valid_id);
    ui->lineEdit_4->setPlaceholderText("Seven-digit number"); ui->lineEdit_4->setValidator(&valid_id);
    ui->lineEdit_5->setPlaceholderText("Seven-digit number"); ui->lineEdit_5->setValidator(&valid_id);
    ui->lineEdit_6->setPlaceholderText("Seven-digit number"); ui->lineEdit_6->setValidator(&valid_id);
    ui->lineEdit_7->setPlaceholderText("Seven-digit number"); ui->lineEdit_7->setValidator(&valid_id);
    ui->lineEdit_8->setPlaceholderText("Seven-digit number"); ui->lineEdit_8->setValidator(&valid_id);
    ui->lineEdit_9->setPlaceholderText("Seven-digit number"); ui->lineEdit_9->setValidator(&valid_id);
    ui->statusLabel->setVisible(false);//делаем sttusLabel невидимым,что бы могли отображать его
                                       //только, когда нужно вывести информацию о корректности
                                       //вводимых данных
}

form_game_game::~form_game_game()
{
    delete ui;
}

void form_game_game::on_but_save_clicked()//обработка кнопки save
{

    bool check = cathEmpty();//создаём флаг для отслеживания наличия пустых строк
    if (check == false){//если их нет, то записываем их значения в базу и очищаем поля
        //запись в базу данных

        QSqlQuery q = QSqlQuery();

        int a2 = ui->lineEdit_2->text().toInt(); int a6 = ui->lineEdit_6->text().toInt();
        int a3 = ui->lineEdit_3->text().toInt(); int a7 = ui->lineEdit_7->text().toInt();
        int a4 = ui->lineEdit_4->text().toInt(); int a8 = ui->lineEdit_8->text().toInt();
        int a5 = ui->lineEdit_5->text().toInt(); int a9 = ui->lineEdit_9->text().toInt();
        q.exec("INSERT INTO game(id_object, id_discipline, id_sportsmen, id_referee, id_win_country, id_2_country, id_3_country, id_olymp) VALUES ("+QString::number(a2)+", "+QString::number(a3)+", "+QString::number(a4)+", "+QString::number(a5)+", "+QString::number(a6)+", "+QString::number(a7)+", "+QString::number(a8)+","+QString::number(a9)+")");


        ui->lineEdit_2->clear();
        ui->lineEdit_3->clear();
        ui->lineEdit_4->clear();
        ui->lineEdit_5->clear();
        ui->lineEdit_6->clear();
        ui->lineEdit_7->clear();
        ui->lineEdit_8->clear();
        ui->lineEdit_9->clear();

        this->setMaximumHeight(360);
        this->setMinimumHeight(360);
        ui->statusLabel->setVisible(true);
        //окрашиваем в исходный цвет border полей ввода
        ui->statusLabel->setStyleSheet("border:3px solid rgb(43, 124, 186);"
                                       "border-top: none;"
                                       "border-bottom: none;"
                                       "color: rgb(43, 124, 186);");
        ui->statusLabel->setAlignment(Qt::AlignCenter);
        ui->statusLabel->setText("Data recorded");
    }
}

bool form_game_game::cathEmpty(){
    int k = 0;//счётчик для посчета пустых строк
    //если данные введены верно, то окрашиваем border в зеленый
    //если поле пустое, то окрашиваем в красный и инкременируем счетчик


    if (ui->lineEdit_2->text().isEmpty()){
        ui->lineEdit_2->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_2->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_3->text().isEmpty()){
        ui->lineEdit_3->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_3->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_4->text().isEmpty()){
        ui->lineEdit_4->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_4->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_5->text().isEmpty()){
        ui->lineEdit_5->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_5->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_6->text().isEmpty()){
        ui->lineEdit_6->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_6->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_7->text().isEmpty()){
        ui->lineEdit_7->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_7->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_8->text().isEmpty()){
        ui->lineEdit_8->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_8->setStyleSheet("border: 1px solid green;");

    if (ui->lineEdit_9->text().isEmpty()){
        ui->lineEdit_9->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_9->setStyleSheet("border: 1px solid green;");

    if (k != 0){//если счетчик увеличился => есть пустые строк, тогда выводим соответствующую надпись и возвращаем true

        this->setMaximumHeight(260);
        this->setMinimumHeight(260);
        ui->statusLabel->setVisible(true);
        ui->statusLabel->setWordWrap(true);
        ui->statusLabel->setAlignment(Qt::AlignLeft);
        ui->statusLabel->setStyleSheet("border:3px solid rgb(214, 60, 60);"
                                       "border-top: none;"
                                       "border-bottom: none;"
                                       "color: rgb(214, 60, 60);");
        ui->statusLabel->setText("Please fill in the fields marked in RED");
        return true;
    }
    else {//иначе окрашиваем все border  в исходный нейтральный цвет

        ui->lineEdit_2->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_3->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_4->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_5->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_6->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_7->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_8->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_9->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        return false;
    }
}

void form_game_game::on_but_can_clicked()//кнопка выхода с формы
{
    this->close();
    this->~form_game_game();
}
