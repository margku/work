    #include "form_autf.h"
#include "ui_form_autf.h"

#include <QDebug>
#include <QtSql/QSqlError>
#include <QtSql/QSqlRecord>

form_autf::form_autf(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_autf)
{
    ui->setupUi(this);

    QPixmap icon(":/img/connect.png");
    this->setWindowIcon(icon);
    this->setMaximumWidth(202);
    this->setMinimumWidth(202);
    this->setMaximumHeight(300);
    this->setMinimumHeight(300);

    mess = new QMessageBox;
    mess->setWindowTitle("Warning");
    QPixmap icon1(":/img/warning.png");
    mess->setWindowIcon(icon1);
    mess->setStyleSheet("font-family: 'Montserrat', sans-serif;"
                        "font-size: 14px;"
                        "color: rgb(71, 74, 81);"
                        "background-color: white;");
    ui->line_pass->setEchoMode(QLineEdit::Password);


}

form_autf::~form_autf()
{
    delete ui;
}

void form_autf::on_pushButton_clicked()
{
    db = QSqlDatabase::addDatabase("QODBC");//, "LENOVO-PC\\danii"
    db.setDatabaseName(QString("DRIVER={SQL Server};SERVER="+ui->line_serv->text()+";DATABASE="+ui->line_base->text()+";"));
    db.setUserName(ui->line_login->text());
    db.setPassword(ui->line_pass->text());
   /* db = QSqlDatabase::addDatabase("QODBC");//, "LENOVO-PC\\danii"
    db.setDatabaseName(QString("DRIVER={SQL Server};SERVER=LENOVO-PC;DATABASE=my_base;"));
    db.setUserName("admin");
    db.setPassword("admin");*/

    if (!db.open()){
        mess->setText("NO CONNECTION");
        //qDebug() << db.lastError().text();

    } else {
        mess->setText("CONNECTION");
        this->close();
        this->~form_autf();
        //qDebug() << "Success!";
    }
    mess->show();
}
