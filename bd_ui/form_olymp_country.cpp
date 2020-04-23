#include "form_olymp_country.h"
#include "ui_form_olymp_country.h"
#include "mainwindow.h"

#include <QDebug>

form_olymp_country::form_olymp_country(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_olymp_country),
    valid_str(QRegExp("^[a-z]{1,10}$")),
    valid_id(QRegExp("^[0-9]{1,7}"))

{
    ui->setupUi(this);

    //параметры окна
    this->setMaximumWidth(290);
    this->setMinimumWidth(290);
    this->setMaximumHeight(213);
    this->setMinimumHeight(213);
    QPixmap icon(":/img/country.png");
    this->setWindowIcon(icon);



    //устанавливаем подсказки и критерии ввода
    ui->lineEdit_2->setPlaceholderText("String"); ui->lineEdit_2->setValidator(&valid_str);
    ui->statusLabel->setVisible(false);//делаем sttusLabel невидимым,что бы могли отображать его
                                       //только, когда нужно вывести информацию о корректности
                                       //вводимых данных
}

form_olymp_country::~form_olymp_country()
{
    delete ui;
}

void form_olymp_country::on_but_save_clicked()//обработка кнопки save
{

    bool check = cathEmpty();//создаём флаг для отслеживания наличия пустых строк
    if (check == false){//если их нет, то записываем их значения в базу и очищаем поля
        //запись в базу данных

        QSqlQuery q = QSqlQuery();
        QString a = ui->lineEdit_2->text();
        q.exec("INSERT INTO dest_country(dest_country_name) VALUES ('"+a+"')");
        ui->lineEdit_2->clear();

        this->setMaximumHeight(234);
        this->setMinimumHeight(234);
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

bool form_olymp_country::cathEmpty(){
    int k = 0;//счётчик для посчета пустых строк
    //если данные введены верно, то окрашиваем border в зеленый
    //если поле пустое, то окрашиваем в красный и инкременируем счетчик

    if (ui->lineEdit_2->text().isEmpty()){
        ui->lineEdit_2->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_2->setStyleSheet("border: 1px solid green;");



    if (k != 0){//если счетчик увеличился => есть пустые строк, тогда выводим соответствующую надпись и возвращаем true

        this->setMaximumHeight(234);
        this->setMinimumHeight(234);
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

        return false;
    }
}

void form_olymp_country::on_but_can_clicked()//кнопка выхода с формы
{
    this->close();
    this->~form_olymp_country();
}

