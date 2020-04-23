#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlQuery>
#include <QtSql/QSqlTableModel>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    //QSqlQuery q = QSqlQuery(db);




private slots:

    //Слоты для действий "ADD" олимпиады
    void trig_act_olymp_add_1();
    void trig_act_olymp_add_2();
    void trig_act_olymp_add_3();
    void trig_act_olymp_add_4();
    void trig_act_olymp_add_5();
    void trig_act_olymp_add_6();

    //Слоты для действий "ADD" игры
    void trig_act_game_add_1();
    void trig_act_game_add_2();
    void trig_act_game_add_3();
    void trig_act_game_add_4();
    void trig_act_game_add_5();

    //слот для действия "DEL"


    void on_but_del_clicked();

    void on_but_view_clicked();

private:
    Ui::MainWindow *ui;
    //QSqlDatabase db;

};
#endif // MAINWINDOW_H
