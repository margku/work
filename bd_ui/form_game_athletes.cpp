#include "form_game_athletes.h"
#include "ui_form_game_athletes.h"
#include "mainwindow.h"

form_game_athletes::form_game_athletes(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_game_athletes),
    valid_str(QRegExp("^[a-z]{1,10}$")),
    valid_id(QRegExp("^[0-9]{1,7}"))
{
    ui->setupUi(this);

    QPixmap icon(":/img/athlete.png");
    //параметры окна
    this->setMaximumWidth(259);
    this->setMinimumWidth(259);
    this->setMaximumHeight(227);
    this->setMinimumHeight(227);
    this->setWindowIcon(icon);

    //устанавливаем подсказки и критерии ввода

    ui->lineEdit_2->setPlaceholderText("String"); ui->lineEdit_2->setValidator(&valid_str);
    ui->lineEdit_3->setPlaceholderText("Seven-digit number"); ui->lineEdit_3->setValidator(&valid_id);
    ui->lineEdit_5->setPlaceholderText("Seven-digit number"); ui->lineEdit_5->setValidator(&valid_id);
    ui->statusLabel->setVisible(false);//делаем sttusLabel невидимым,что бы могли отображать его
                                       //только, когда нужно вывести информацию о корректности
                                       //вводимых данных
}

form_game_athletes::~form_game_athletes()
{
    delete ui;
}

void form_game_athletes::on_but_save_clicked()//обработка кнопки save
{

    bool check = cathEmpty();//создаём флаг для отслеживания наличия пустых строк
    if (check == false){//если их нет, то записываем их значения в базу и очищаем поля
        //запись в базу данных

        QSqlQuery q = QSqlQuery();
        QString a = ui->lineEdit_2->text();
        int a1 = ui->lineEdit_3->text().toInt();
        int a2 = ui->lineEdit_5->text().toInt();
        q.exec("INSERT INTO sportsmen(sportsmen_name, id_discipline,id_part_country) VALUES ('"+a+"', "+QString::number(a1)+","+QString::number(a2)+")");

        ui->lineEdit_2->clear();
        ui->lineEdit_3->clear();

        ui->lineEdit_5->clear();

        this->setMaximumHeight(255);
        this->setMinimumHeight(255);
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

bool form_game_athletes::cathEmpty(){
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



    if (ui->lineEdit_5->text().isEmpty()){
        ui->lineEdit_5->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_5->setStyleSheet("border: 1px solid green;");

    if (k != 0){//если счетчик увеличился => есть пустые строк, тогда выводим соответствующую надпись и возвращаем true

        this->setMaximumHeight(255);
        this->setMinimumHeight(255);
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

        ui->lineEdit_5->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        return false;
    }
}

void form_game_athletes::on_but_can_clicked()//кнопка выхода с формы
{
    this->close();
    this->~form_game_athletes();
}
