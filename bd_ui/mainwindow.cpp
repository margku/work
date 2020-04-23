#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QStackedWidget>

#include "form_game_athletes.h"
#include "form_game_discipline.h"
#include "form_game_game.h"
#include "form_game_judges.h"
#include "form_game_part_country.h"
#include "form_olymp_city.h"
#include "form_olymp_country.h"
#include "form_olymp_object.h"
#include "form_olymp_olympiad.h"
#include "form_olymp_symbol.h"
#include "form_olymp_medal.h"
#include "form_del.h"
#include "form_view_data.h"
#include "form_autf.h"

#include <QDebug>
#include <QtSql/QSqlError>
#include <QtSql/QSqlRecord>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{

    ui->setupUi(this);
    ui->label->setWordWrap(true);
    ui->label_2->setWordWrap(true);
    ui->label->setAlignment(Qt::AlignCenter);
    ui->label_2->setAlignment(Qt::AlignCenter);

    //Создание меню действий для кнопки "ADD" раздела олимпиады
    QMenu *menu_olymp_add = new QMenu;
    QAction *act_olymp_add_1 = new QAction("Olympiad");
    QAction *act_olymp_add_2 = new QAction("Olympiad symbol");
    QAction *act_olymp_add_3 = new QAction("Dest country");
    QAction *act_olymp_add_4 = new QAction("Dest city");
    QAction *act_olymp_add_5 = new QAction("Dest object");
    QAction *act_olymp_add_6 = new QAction("Medal");
    menu_olymp_add->addAction(act_olymp_add_1);
    menu_olymp_add->addAction(act_olymp_add_2);
    menu_olymp_add->addAction(act_olymp_add_3);
    menu_olymp_add->addAction(act_olymp_add_4);
    menu_olymp_add->addAction(act_olymp_add_5);
    menu_olymp_add->addAction(act_olymp_add_6);
    ui->but_olymp_add->setMenu(menu_olymp_add);

    //Создание меню действий для кнопки "ADD" раздела игры
    QMenu *menu_game_add = new QMenu;
    QAction *act_game_add_1 = new QAction("Game");
    QAction *act_game_add_2 = new QAction("Discipline");
    QAction *act_game_add_3 = new QAction("Athletes");
    QAction *act_game_add_4 = new QAction("Judges");
    QAction *act_game_add_5 = new QAction("Participating country");
    menu_game_add->addAction(act_game_add_1);
    menu_game_add->addAction(act_game_add_2);
    menu_game_add->addAction(act_game_add_3);
    menu_game_add->addAction(act_game_add_4);
    menu_game_add->addAction(act_game_add_5);
    ui->but_game_add->setMenu(menu_game_add);

    /*//Создание меню действий для кнопки "Del" раздела олимпиады
    QMenu *menu_olymp_del = new QMenu;
    QAction *act_olymp_del_1 = new QAction("Olympiad");
    QAction *act_olymp_del_2 = new QAction("Olympiad symbol");
    QAction *act_olymp_del_3 = new QAction("Dest country");
    QAction *act_olymp_del_4 = new QAction("Dest city");
    QAction *act_olymp_del_5 = new QAction("Dest object");
    menu_olymp_del->addAction(act_olymp_del_1);
    menu_olymp_del->addAction(act_olymp_del_2);
    menu_olymp_del->addAction(act_olymp_del_3);
    menu_olymp_del->addAction(act_olymp_del_4);
    menu_olymp_del->addAction(act_olymp_del_5);
    ui->but_olymp_add->setMenu(menu_olymp_add);

    //Создание меню действий для кнопки "Del" раздела игры
    QMenu *menu_game_del = new QMenu;
    QAction *act_game_del_1 = new QAction("Olympiad");
    QAction *act_game_del_2 = new QAction("Olympiad symbol");
    QAction *act_game_del_3 = new QAction("Dest country");
    QAction *act_game_del_4 = new QAction("Dest city");
    QAction *act_game_del_5 = new QAction("Dest object");
    menu_game_del->addAction(act_game_del_1);
    menu_game_del->addAction(act_game_del_2);
    menu_game_del->addAction(act_game_del_3);
    menu_game_del->addAction(act_game_del_4);
    menu_game_del->addAction(act_game_del_5);
    ui->but_olymp_add->setMenu(menu_olymp_add);*/

    //обработка сигналов для "ADD" олимпиады
    connect(act_olymp_add_1, SIGNAL(triggered()), this, SLOT(trig_act_olymp_add_1()));
    connect(act_olymp_add_2, SIGNAL(triggered()), this, SLOT(trig_act_olymp_add_2()));
    connect(act_olymp_add_3, SIGNAL(triggered()), this, SLOT(trig_act_olymp_add_3()));
    connect(act_olymp_add_4, SIGNAL(triggered()), this, SLOT(trig_act_olymp_add_4()));
    connect(act_olymp_add_5, SIGNAL(triggered()), this, SLOT(trig_act_olymp_add_5()));
    connect(act_olymp_add_6, SIGNAL(triggered()), this, SLOT(trig_act_olymp_add_6()));

    //обработка сигналов для "ADD" игры
    connect(act_game_add_1, SIGNAL(triggered()), this, SLOT(trig_act_game_add_1()));
    connect(act_game_add_2, SIGNAL(triggered()), this, SLOT(trig_act_game_add_2()));
    connect(act_game_add_3, SIGNAL(triggered()), this, SLOT(trig_act_game_add_3()));
    connect(act_game_add_4, SIGNAL(triggered()), this, SLOT(trig_act_game_add_4()));
    connect(act_game_add_5, SIGNAL(triggered()), this, SLOT(trig_act_game_add_5()));


    //окно подлючения к бд

    //Работа с sql
    form_autf *login = new form_autf;
    login->setWindowTitle("Connection to server");
    login->setModal(true);
    login->show();

   /* db = QSqlDatabase::addDatabase("QODBC");//, "LENOVO-PC\\danii"
    db.setDatabaseName(QString("DRIVER={SQL Server};SERVER=LENOVO-PC;DATABASE=my_base;"));
    db.setUserName("admin");
    db.setPassword("admin");

    if (!db.open()){
        qDebug() << db.lastError().text();
    } else{
        qDebug() << "Success!";
    }*/



    //тест с бд(успешный кста)
    //QSqlQuery q = QSqlQuery(db);
    /*if (!q.exec("INSERT INTO olymp VALUES (1,2, 'Красный ягуар', 'Красный ягуар', 3, 4, 5, 6)")){
        qDebug() << q.lastError().databaseText();
    }*/
    /*while (q.next()) {
        qDebug << q.record();
    }*/
   /* q.prepare("INSERT INTO olymp VALUES (40,10,'usa', 'winter', 1, 2, 3, 6)");
   // q.bindValue("")
    if (!q.exec()){
        qDebug() << q.lastError().databaseText();
    }
    if (!q.exec("SELECT * FROM olymp")){
        qDebug() << q.lastError().databaseText();
    }
    while (q.next()) {
        qDebug() << q.record();
    }*/
}

MainWindow::~MainWindow()
{
    delete ui;

}

//Имплементация слотов действий "ADD" олимпиады
void MainWindow::trig_act_olymp_add_1(){
    form_olymp_olympiad *form_act_1 = new form_olymp_olympiad;
    form_act_1->setWindowTitle("Olympiad");
    form_act_1->setModal(true);
    form_act_1->show();
}

void MainWindow::trig_act_olymp_add_2(){
    form_olymp_symbol *form_act_2 = new form_olymp_symbol;
    form_act_2->setWindowTitle("Symbol");
    form_act_2->setModal(true);
    form_act_2->show();
}

void MainWindow::trig_act_olymp_add_3(){
    form_olymp_country *form_act_3 = new form_olymp_country;
    form_act_3->setWindowTitle("Country");
    form_act_3->setModal(true);
    form_act_3->show();
}

void MainWindow::trig_act_olymp_add_4(){
    form_olymp_city *form_act_4 = new form_olymp_city;
    form_act_4->setWindowTitle("City");
    form_act_4->setModal(true);
    form_act_4->show();
}

void MainWindow::trig_act_olymp_add_5(){
    form_olymp_object *form_act_5 = new form_olymp_object;
    form_act_5->setWindowTitle("Object");
    form_act_5->setModal(true);
    form_act_5->show();
}

void MainWindow::trig_act_olymp_add_6(){
    form_olymp_medal *form_act_6 = new form_olymp_medal;
    form_act_6->setWindowTitle("Medal");
    form_act_6->setModal(true);
    form_act_6->show();
}

//Имплементация слотов действий "ADD" игры
void MainWindow::trig_act_game_add_1(){
    form_game_game *form_act_1 = new form_game_game;
    form_act_1->setWindowTitle("Game");
    form_act_1->setModal(true);
    form_act_1->show();

}

void MainWindow::trig_act_game_add_2(){
    form_game_discipline *form_act_2 = new form_game_discipline;
    form_act_2->setWindowTitle("Discipline");
    form_act_2->setModal(true);
    form_act_2->show();
}

void MainWindow::trig_act_game_add_3(){
    form_game_athletes *form_act_3 = new form_game_athletes;
    form_act_3->setWindowTitle("Athletes");
    form_act_3->setModal(true);
    form_act_3->show();
}

void MainWindow::trig_act_game_add_4(){
    form_game_judges *form_act_4 = new form_game_judges;
    form_act_4->setWindowTitle("Judges");
    form_act_4->setModal(true);
    form_act_4->show();
}

void MainWindow::trig_act_game_add_5(){
    form_game_part_country *form_act_5 = new form_game_part_country;
    form_act_5->setWindowTitle("Participating country");
    form_act_5->setModal(true);
    form_act_5->show();
}







void MainWindow::on_but_del_clicked()
{
    form_del *fm = new form_del;
    fm->setWindowTitle("Delete table");
    fm->setModal(true);
    fm->show();
}

void MainWindow::on_but_view_clicked()
{
    form_view_data *fvd = new form_view_data;
    fvd->setWindowTitle("View DATA");
    fvd->setModal(true);
    fvd->show();
}
