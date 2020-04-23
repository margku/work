#include "form_olymp_medal.h"
#include "ui_form_olymp_medal.h"
#include "mainwindow.h"

form_olymp_medal::form_olymp_medal(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_olymp_medal),
    valid_medal(QRegExp("^[0-9]{0,3}$")),
    valid_id(QRegExp("^[0-9]{1,7}"))

{
    ui->setupUi(this);
    QPixmap icon(":/img/medal.png");
    this->setWindowIcon(icon);

    //устанавливаем подсказки и критерии ввода
    ui->lineEdit->setPlaceholderText("Seven-digit number")  ; ui->lineEdit->setValidator(&valid_id);
    ui->lineEdit_2->setPlaceholderText("Three-digit number") ; ui->lineEdit_2->setValidator(&valid_medal);
    ui->lineEdit_3->setPlaceholderText("Three-digit number")  ; ui->lineEdit_3->setValidator(&valid_medal);
    ui->lineEdit_4->setPlaceholderText("Three-digit number"); ui->lineEdit_4->setValidator(&valid_medal);
    ui->lineEdit_5->setPlaceholderText("Three-digit number"); ui->lineEdit_5->setValidator(&valid_medal);
    ui->lineEdit_6->setPlaceholderText("Seven-digit number"); ui->lineEdit_6->setValidator(&valid_id);
    ui->statusLabel->setVisible(false);//делаем sttusLabel невидимым,что бы могли отображать его
                                       //только, когда нужно вывести информацию о корректности
                                       //вводимых данных

}

form_olymp_medal::~form_olymp_medal()
{
    delete ui;
}

void form_olymp_medal::on_but_save_clicked()//обработка кнопки save
{

    bool check = cathEmpty();//создаём флаг для отслеживания наличия пустых строк
    if (check == false){//если их нет, то записываем их значения в базу и очищаем поля
        //запись в базу данных
        //MainWindow::db ;
        //MainWindow *t = new MainWindow;
       // QSqlQuery q = QSqlQuery(t->db);
        //q.exec("INSERT INTO olymp VALUES (1,2, 'Красный ягуар', 'Красный ягуар', 3, 4, 5, 6)")){


        /*db1 = QSqlDatabase::addDatabase("QODBC");//, "LENOVO-PC\\danii"
        db1.setDatabaseName(QString("DRIVER={SQL Server};SERVER=LENOVO-PC;DATABASE=my_base;"));
        db1.setUserName("admin");
        db1.setPassword("admin");*/
        QSqlQuery q = QSqlQuery();
        int a1 = ui->lineEdit->text().toInt();
        int a2 = ui->lineEdit_2->text().toInt();
        int a3 = ui->lineEdit_3->text().toInt();
        int a4 = ui->lineEdit_4->text().toInt();
        int a5 = ui->lineEdit_5->text().toInt();
        int a6 = ui->lineEdit_6->text().toInt();
        q.exec("INSERT INTO medal(id_part_country, gold, silver, bronze, count_medal, id_olymp) VALUES ("+QString::number(a1)+", "+QString::number(a2)+", "+QString::number(a3)+", "+QString::number(a4)+", "+QString::number(a5)+", "+QString::number(a6)+")");

        ui->lineEdit->clear();
        ui->lineEdit_2->clear();
        ui->lineEdit_3->clear();
        ui->lineEdit_4->clear();
        ui->lineEdit_5->clear();
        ui->lineEdit_6->clear();

        this->setMaximumHeight(309);
        this->setMinimumHeight(309);
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

bool form_olymp_medal::cathEmpty(){
    int k = 0;//счётчик для посчета пустых строк
    //если данные введены верно, то окрашиваем border в зеленый
    //если поле пустое, то окрашиваем в красный и инкременируем счетчик
    if (ui->lineEdit->text().isEmpty()){
        ui->lineEdit->setStyleSheet("border: 1px solid red;");
        k++;
    } else ui->lineEdit->setStyleSheet("border: 1px solid green;");

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


    if (k != 0){//если счетчик увеличился => есть пустые строк, тогда выводим соответствующую надпись и возвращаем true

        this->setMaximumHeight(309);
        this->setMinimumHeight(309);
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
        ui->lineEdit->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_2->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_3->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_4->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_5->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        ui->lineEdit_6->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
        return false;
    }
}

void form_olymp_medal::on_but_can_clicked()//кнопка выхода с формы
{
    this->close();
    this->~form_olymp_medal();
}
