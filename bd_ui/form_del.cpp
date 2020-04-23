#include "form_del.h"
#include "ui_form_del.h"
#include "mainwindow.h"

#include <QDebug>



form_del::form_del(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_del)
{
    ui->setupUi(this);

    this->setMaximumWidth(249);
    this->setMinimumWidth(249);
    this->setMaximumHeight(170);
    this->setMinimumHeight(170);
    QPixmap icon(":/img/delete.png");
    this->setWindowIcon(icon);


    ui->comboBox->addItem("Olimpiad");        ui->comboBox->addItem("Game");
    ui->comboBox->addItem("Olimpiad symbol"); ui->comboBox->addItem("Discipline");
    ui->comboBox->addItem("Dest country");    ui->comboBox->addItem("Athletes");
    ui->comboBox->addItem("Dest city");       ui->comboBox->addItem("Judges");
    ui->comboBox->addItem("Dest object");     ui->comboBox->addItem("Participating country");
    ui->comboBox->addItem("Medal");

    ui->lineEdit->setText("");
    ui->statusLabel->setVisible(false);


}

form_del::~form_del()
{
    delete ui;
}

void form_del::on_but_del_clicked()
{
    if (ui->lineEdit->text() == ""){
        ui->lineEdit->setStyleSheet("border-color: red");
        ui->statusLabel->setVisible(true);
        ui->statusLabel->setWordWrap(true);
        ui->statusLabel->setAlignment(Qt::AlignCenter);
        ui->statusLabel->setStyleSheet("border:3px solid rgb(214, 60, 60);"
                                       "border-top: none;"
                                       "border-bottom: none;"
                                       "color: rgb(214, 60, 60);");
        ui->statusLabel->setText("Please fill in the ID field");
        return;
    };
    ui->lineEdit->setStyleSheet("border: 1px solid rgb(0, 188, 188);");
    ui->statusLabel->setVisible(true);
    //окрашиваем в исходный цвет border полей ввода
    ui->statusLabel->setStyleSheet("border:3px solid rgb(43, 124, 186);"
                                   "border-top: none;"
                                   "border-bottom: none;"
                                   "color: rgb(43, 124, 186);");
    ui->statusLabel->setAlignment(Qt::AlignCenter);
    ui->statusLabel->setText("Data deleted");

    QString tableName = ui->comboBox->currentText();
    //удаление таблица с ID из linaEdit

    QSqlQuery q = QSqlQuery();
    int a = ui->lineEdit->text().toInt();
    QString name_table = ui->comboBox->currentText();
    if (!q.exec("DELETE FROM "+name_table+" WHERE idolymp_games = "+QString::number(a)+"")) qDebug() << "trabl";

    //если такого ID нет, то сообщить пользователю
    if (name_table == "Olimpiad symbol")       q.exec("DELETE FROM attribute WHERE id_attribute = "+QString::number(a)+"");
    if (name_table == "Olimpiad")              q.exec("DELETE FROM olymp_games WHERE idolymp_games = "+QString::number(a)+"");
    if (name_table == "Dest city")             q.exec("DELETE FROM dest_city WHERE iddest_city = "+QString::number(a)+"");
    if (name_table == "Discipline")            q.exec("DELETE FROM discipline WHERE iddiscipline = "+QString::number(a)+"");
    if (name_table == "Dest country")          q.exec("DELETE FROM dest_country WHERE iddest_country = "+QString::number(a)+"");
    if (name_table == "Game")                  q.exec("DELETE FROM game WHERE idgame = "+QString::number(a)+"");
    if (name_table == "Medal")                 q.exec("DELETE FROM medal WHERE idmedal = "+QString::number(a)+"");
    if (name_table == "Dest object")           q.exec("DELETE FROM objectss WHERE idobjects = "+QString::number(a)+"");
    if (name_table == "Participating country") q.exec("DELETE FROM part_country WHERE idpart_country = "+QString::number(a)+"");
    if (name_table == "Judges")                q.exec("DELETE FROM referee WHERE idreferee = "+QString::number(a)+"");
    if (name_table == "Athletes")              q.exec("DELETE FROM sportsmen WHERE idsportsmen = "+QString::number(a)+"");

}

void form_del::on_but_can_2_clicked()
{
    this->close();
    this->~form_del();
}
