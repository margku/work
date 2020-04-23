#ifndef FORM_VIEW_DATA_H
#define FORM_VIEW_DATA_H

#include <QDialog>

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlQuery>
#include <QtSql/QSqlTableModel>

namespace Ui {
class form_view_data;
}

class form_view_data : public QDialog
{
    Q_OBJECT

public:
    explicit form_view_data(QWidget *parent = nullptr);
    ~form_view_data();

private slots:
    void on_but_view_clicked();

private:
    Ui::form_view_data *ui;
    QSqlTableModel *model;
    //QSqlDatabase db;
};

#endif // FORM_VIEW_DATA_H
