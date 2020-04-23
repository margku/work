#ifndef FORM_DEL_H
#define FORM_DEL_H

#include <QDialog>

#include <QDebug>
#include <QtSql/QSqlError>
#include <QtSql/QSqlRecord>

namespace Ui {
class form_del;
}

class form_del : public QDialog
{
    Q_OBJECT

public:
    explicit form_del(QWidget *parent = nullptr);
    ~form_del();

private slots:
    void on_but_del_clicked();

    void on_but_can_2_clicked();

private:
    Ui::form_del *ui;
};

#endif // FORM_DEL_H
