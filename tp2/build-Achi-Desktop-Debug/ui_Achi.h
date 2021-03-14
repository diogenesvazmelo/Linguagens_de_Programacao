/********************************************************************************
** Form generated from reading UI file 'Achi.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ACHI_H
#define UI_ACHI_H

#include <Hole.h>
#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Achi
{
public:
    QAction *actionNew;
    QAction *actionQuit;
    QAction *actionAbout;
    QWidget *centralwidget;
    QGridLayout *gridLayout;
    Hole *hole00;
    Hole *hole01;
    Hole *hole02;
    Hole *hole10;
    Hole *hole11;
    Hole *hole12;
    Hole *hole20;
    Hole *hole21;
    Hole *hole22;
    QMenuBar *menubar;
    QMenu *menuJogo;
    QMenu *menuAjuda;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Achi)
    {
        if (Achi->objectName().isEmpty())
            Achi->setObjectName(QString::fromUtf8("Achi"));
        Achi->resize(300, 349);
        actionNew = new QAction(Achi);
        actionNew->setObjectName(QString::fromUtf8("actionNew"));
        actionQuit = new QAction(Achi);
        actionQuit->setObjectName(QString::fromUtf8("actionQuit"));
        actionAbout = new QAction(Achi);
        actionAbout->setObjectName(QString::fromUtf8("actionAbout"));
        centralwidget = new QWidget(Achi);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        centralwidget->setStyleSheet(QString::fromUtf8("#centralwidget { background: url(':/grid'); }"));
        gridLayout = new QGridLayout(centralwidget);
        gridLayout->setSpacing(0);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        gridLayout->setContentsMargins(0, 0, 0, 0);
        hole00 = new Hole(centralwidget);
        hole00->setObjectName(QString::fromUtf8("hole00"));
        hole00->setMinimumSize(QSize(100, 100));
        hole00->setMaximumSize(QSize(100, 100));
        hole00->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole00->setIconSize(QSize(50, 50));
        hole00->setFlat(true);
        hole00->setProperty("row", QVariant(0));
        hole00->setProperty("col", QVariant(0));

        gridLayout->addWidget(hole00, 0, 0, 1, 1);

        hole01 = new Hole(centralwidget);
        hole01->setObjectName(QString::fromUtf8("hole01"));
        hole01->setMinimumSize(QSize(100, 100));
        hole01->setMaximumSize(QSize(100, 100));
        hole01->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole01->setIconSize(QSize(50, 50));
        hole01->setFlat(true);
        hole01->setProperty("row", QVariant(0));
        hole01->setProperty("col", QVariant(1));

        gridLayout->addWidget(hole01, 0, 1, 1, 1);

        hole02 = new Hole(centralwidget);
        hole02->setObjectName(QString::fromUtf8("hole02"));
        hole02->setMinimumSize(QSize(100, 100));
        hole02->setMaximumSize(QSize(100, 100));
        hole02->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole02->setIconSize(QSize(50, 50));
        hole02->setFlat(true);
        hole02->setProperty("row", QVariant(0));
        hole02->setProperty("col", QVariant(2));

        gridLayout->addWidget(hole02, 0, 2, 1, 1);

        hole10 = new Hole(centralwidget);
        hole10->setObjectName(QString::fromUtf8("hole10"));
        hole10->setMinimumSize(QSize(100, 100));
        hole10->setMaximumSize(QSize(100, 100));
        hole10->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole10->setIconSize(QSize(50, 50));
        hole10->setFlat(true);
        hole10->setProperty("row", QVariant(1));
        hole10->setProperty("col", QVariant(0));

        gridLayout->addWidget(hole10, 1, 0, 1, 1);

        hole11 = new Hole(centralwidget);
        hole11->setObjectName(QString::fromUtf8("hole11"));
        hole11->setMinimumSize(QSize(100, 100));
        hole11->setMaximumSize(QSize(100, 100));
        hole11->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole11->setIconSize(QSize(50, 50));
        hole11->setFlat(true);
        hole11->setProperty("row", QVariant(1));
        hole11->setProperty("col", QVariant(1));

        gridLayout->addWidget(hole11, 1, 1, 1, 1);

        hole12 = new Hole(centralwidget);
        hole12->setObjectName(QString::fromUtf8("hole12"));
        hole12->setMinimumSize(QSize(100, 100));
        hole12->setMaximumSize(QSize(100, 100));
        hole12->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole12->setIconSize(QSize(50, 50));
        hole12->setFlat(true);
        hole12->setProperty("row", QVariant(1));
        hole12->setProperty("col", QVariant(2));

        gridLayout->addWidget(hole12, 1, 2, 1, 1);

        hole20 = new Hole(centralwidget);
        hole20->setObjectName(QString::fromUtf8("hole20"));
        hole20->setMinimumSize(QSize(100, 100));
        hole20->setMaximumSize(QSize(100, 100));
        hole20->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole20->setIconSize(QSize(50, 50));
        hole20->setFlat(true);
        hole20->setProperty("row", QVariant(2));
        hole20->setProperty("col", QVariant(0));

        gridLayout->addWidget(hole20, 2, 0, 1, 1);

        hole21 = new Hole(centralwidget);
        hole21->setObjectName(QString::fromUtf8("hole21"));
        hole21->setMinimumSize(QSize(100, 100));
        hole21->setMaximumSize(QSize(100, 100));
        hole21->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole21->setIconSize(QSize(50, 50));
        hole21->setFlat(true);
        hole21->setProperty("row", QVariant(2));
        hole21->setProperty("col", QVariant(1));

        gridLayout->addWidget(hole21, 2, 1, 1, 1);

        hole22 = new Hole(centralwidget);
        hole22->setObjectName(QString::fromUtf8("hole22"));
        hole22->setMinimumSize(QSize(100, 100));
        hole22->setMaximumSize(QSize(100, 100));
        hole22->setStyleSheet(QString::fromUtf8("QPushButton {\n"
"    border: none;\n"
"    outline: none;\n"
"}"));
        hole22->setIconSize(QSize(50, 50));
        hole22->setFlat(true);
        hole22->setProperty("row", QVariant(2));
        hole22->setProperty("col", QVariant(2));

        gridLayout->addWidget(hole22, 2, 2, 1, 1);

        Achi->setCentralWidget(centralwidget);
        menubar = new QMenuBar(Achi);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 300, 22));
        menuJogo = new QMenu(menubar);
        menuJogo->setObjectName(QString::fromUtf8("menuJogo"));
        menuAjuda = new QMenu(menubar);
        menuAjuda->setObjectName(QString::fromUtf8("menuAjuda"));
        Achi->setMenuBar(menubar);
        statusbar = new QStatusBar(Achi);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        Achi->setStatusBar(statusbar);

        menubar->addAction(menuJogo->menuAction());
        menubar->addAction(menuAjuda->menuAction());
        menuJogo->addAction(actionNew);
        menuJogo->addAction(actionQuit);
        menuAjuda->addAction(actionAbout);

        retranslateUi(Achi);

        QMetaObject::connectSlotsByName(Achi);
    } // setupUi

    void retranslateUi(QMainWindow *Achi)
    {
        Achi->setWindowTitle(QApplication::translate("Achi", "Achi", nullptr));
        actionNew->setText(QApplication::translate("Achi", "Novo", nullptr));
        actionQuit->setText(QApplication::translate("Achi", "Sair", nullptr));
        actionAbout->setText(QApplication::translate("Achi", "Sobre", nullptr));
        hole00->setText(QString());
        hole01->setText(QString());
        hole02->setText(QString());
        hole10->setText(QString());
        hole11->setText(QString());
        hole12->setText(QString());
        hole20->setText(QString());
        hole21->setText(QString());
        hole22->setText(QString());
        menuJogo->setTitle(QApplication::translate("Achi", "Jogo", nullptr));
        menuAjuda->setTitle(QApplication::translate("Achi", "Ajuda", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Achi: public Ui_Achi {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ACHI_H
