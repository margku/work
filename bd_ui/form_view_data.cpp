#include "form_view_data.h"
#include "ui_form_view_data.h"

form_view_data::form_view_data(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::form_view_data)
{
    QPixmap t(":/ress/table.png");
    this->setWindowIcon(t);
    QPixmap icon(":/img/table.png");
    this->setWindowIcon(icon);

    ui->setupUi(this);

    ui->comboBox->addItem("Olimpiad");        ui->comboBox->addItem("Game");
    ui->comboBox->addItem("Olimpiad symbol"); ui->comboBox->addItem("Discipline");
    ui->comboBox->addItem("Dest country");    ui->comboBox->addItem("Sportsmen");
    ui->comboBox->addItem("Dest city");       ui->comboBox->addItem("Referee");
    ui->comboBox->addItem("Object");          ui->comboBox->addItem("Participating country");
    ui->comboBox->addItem("Medal");


    /*db = QSqlDatabase::addDatabase("QODBC");//, "LENOVO-PC\\danii"
        db.setDatabaseName(QString("DRIVER={SQL Server};SERVER=LENOVO-PC;DATABASE=my_base;"));
        db.setUserName("admin");
        db.setPassword("admin");*/
}

form_view_data::~form_view_data()
{
    delete ui;

}

void form_view_data::on_but_view_clicked()
{
    QString val;
    val = ui->comboBox->currentText();

    model = new QSqlTableModel();
    if (val == "Game"){
        model->setTable("game");
        model->select();
    }

    if (val == "Medal"){
        model->setTable("medal");
        model->select();
    }

    if (val == "Objects"){
        model->setTable("objectss");
        model->select();
    }

    if (val == "Participating country"){
        model->setTable("part_country");
        model->select();
    }

    if (val == "Referee"){
        model->setTable("referee");
        model->select();
    }

    if (val == "Sportsmen"){
        model->setTable("sportsmen");
        model->select();
    }

    if (val == "Dest country"){
        model->setTable("dest_country");
        model->select();
    }

    if (val == "Discipline"){
        model->setTable("discipline");
        model->select();
    }

    if (val == "Dest city"){
        model->setTable("dest_city");
        model->select();
    }

    if (val == "Olimpiad symbol"){
        model->setTable("attribute");
        model->select();
    }

    if (val == "Olimpiad"){
        model->setTable("olymp_games");
        model->select();
    }

    /*model->setTable("game");
    model->select();*/
    ui->tableView->setModel(model);
    ui->tableView->resizeColumnsToContents();
    ui->tableView->show();

}
