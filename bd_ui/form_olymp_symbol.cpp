#include "form_olymp_symbol.h"
#include "ui_form_olymp_symbol.h"
#include "mainwindow.h"

#include <QDebug>

form_olymp_symbol::form_olymp_symbol(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_olymp_symbol),
    valid_str(QRegExp("^[a-z]{1,10}$")),
    valid_id(QRegExp("^[0-9]{1,7}"))
{
    ui->setupUi(this);

    //параметры окна
    this->setMaximumWidth(211);
    this->setMinimumWidth(211);
    this->setMaximumHeight(190);
    this->setMinimumHeight(190);
    QPixmap icon(":/img/symbol.png");
    this->setWindowIcon(icon);


    //устанавливаем подсказки и критерии ввода
    ui->lineEdit_4->setPlaceholderText("String"); ui->lineEdit_4->setValidator(&valid_str);
    ui->statusLabel->setVisible(false);//делаем sttusLabel невидимым,что бы могли отображать его
                                       //только, когда нужно вывести информацию о корректности
                                       //вводимых данных
}

form_olymp_symbol::~form_olymp_symbol()
{
    delete ui;
}

void form_olymp_symbol::on_but_save_clicked()//обработка кнопки save
{

    bool check = cathEmpty();//создаём флаг для отслеживания наличия пустых строк
    if (check == false){//если их нет, то записываем их значения в базу и очищаем поля
        //запись в базу данных

        QSqlQuery q = QSqlQuery();
        QString a1 = ui->lineEdit_4->text();
        if (!q.exec("INSERT INTO attribute(attribute_name) VALUES ('"+a1+"')")) qDebug() << "trabl";

        ui->lineEdit_4->clear();

        this->setMaximumHeight(233);
        this->setMinimumHeight(233);
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

bool form_olymp_symbol::cathEmpty(){
    int k = 0;//счётчик для посчета пустых строк
    //если данные введены верно, то окрашиваем border в зеленый
    //если поле пустое, то окрашиваем в красный и инкременируем счетчик

    if (ui->lineEdit_4->text().isEmpty()){
        ui->lineEdit_4->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit_4->setStyleSheet("border: 1px solid green;");


    if (k != 0){//если счетчик увеличился => есть пустые строк, тогда выводим соответствующую надпись и возвращаем true
        ui->statusLabel->setVisible(true);
        ui->statusLabel->setWordWrap(true);
        this->setMaximumHeight(233);
        this->setMinimumHeight(233);
        ui->statusLabel->setAlignment(Qt::AlignLeft);
        ui->statusLabel->setStyleSheet("border:3px solid rgb(214, 60, 60);"
                                       "border-top: none;"
                                       "border-bottom: none;"
                                       "color: rgb(214, 60, 60);");
        ui->statusLabel->setText("Please fill in the fields marked in RED");
        return true;
    }
    else {//иначе окрашиваем все border  в исходный нейтральный цвет
        ui->lineEdit_4->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        return false;
    }
}

void form_olymp_symbol::on_but_can_clicked()//кнопка выхода с формы
{
    this->close();
    this->~form_olymp_symbol();
}
