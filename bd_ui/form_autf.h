#ifndef FORM_AUTF_H
#define FORM_AUTF_H

#include <QDialog>

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlQuery>
#include <QtSql/QSqlTableModel>

#include <QMessageBox>

namespace Ui {
class form_autf;
}

class form_autf : public QDialog
{
    Q_OBJECT

public:
    explicit form_autf(QWidget *parent = nullptr);
    ~form_autf();

    QMessageBox *mess;

private slots:
    void on_pushButton_clicked();

private:
    Ui::form_autf *ui;
    QSqlDatabase db;
};

#endif // FORM_AUTF_H
