#include "form_olymp_city.h"
#include "ui_form_olymp_city.h"
#include "mainwindow.h"

form_olymp_city::form_olymp_city(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_olymp_city),
    valid_str(QRegExp("^[a-z]{1,10}$")),
    valid_id(QRegExp("^[0-9]{1,7}"))
{
    ui->setupUi(this);

    //параметры окна
    this->setMaximumWidth(266);
    this->setMinimumWidth(266);
    this->setMinimumHeight(175);
    this->setMaximumHeight(175);
    QPixmap icon(":/img/city.png");
    this->setWindowIcon(icon);


    //устанавливаем подсказки и критерии ввода
    ui->lineEdit_2->setPlaceholderText("String"); ui->lineEdit_2->setValidator(&valid_str);
    ui->lineEdit_3->setPlaceholderText("Seven-digit number"); ui->lineEdit_3->setValidator(&valid_id);
    ui->statusLabel->setVisible(false);//делаем sttusLabel невидимым,что бы могли отображать его
                                       //только, когда нужно вывести информацию о корректности
                                       //вводимых данных
}

form_olymp_city::~form_olymp_city()
{
    delete ui;
}

void form_olymp_city::on_but_save_clicked()//обработка кнопки save
{

    bool check = cathEmpty();//создаём флаг для отслеживания наличия пустых строк
    if (check == false){//если их нет, то записываем их значения в базу и очищаем поля
        //запись в базу данных

        QSqlQuery q = QSqlQuery();
        QString a = ui->lineEdit_2->text();
        int a1 = ui->lineEdit_3->text().toInt();
        q.exec("INSERT INTO dest_city(dest_city_name, id_dest_country) VALUES ('"+a+"', "+QString::number(a1)+")");

        ui->lineEdit_2->clear();
        ui->lineEdit_3->clear();

        this->setMaximumHeight(196);
        this->setMinimumHeight(196);
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

bool form_olymp_city::cathEmpty(){
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

    if (k != 0){//если счетчик увеличился => есть пустые строк, тогда выводим соответствующую надпись и возвращаем true

        this->setMaximumHeight(196);
        this->setMinimumHeight(196);
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
        return false;
    }
}

void form_olymp_city::on_but_can_clicked()//кнопка выхода с формы
{
    this->close();
    this->~form_olymp_city();
}
