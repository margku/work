#include "mainwindow.h"
#include <QApplication>
#include "ui_mainwindow.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.setMaximumWidth(357);
    w.setMinimumWidth(357);
    w.setMaximumHeight(347);
    w.setMinimumHeight(347);
    QPixmap icon(":/img/main_icon_new.png");
    w.setWindowIcon(icon);
    w.show();

    return a.exec();
}
