#include "mainwindow.h"
#include <iostream>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
{
    resize(800, 480);

    imageLabel = new QLabel;
    imageLabel->setBackgroundRole(QPalette::Base);

    QImage image(this->width(), this->height(), QImage::Format::Format_RGB32);
    imageLabel->setPixmap(QPixmap::fromImage(image));

    setCentralWidget(imageLabel);
}

MainWindow::~MainWindow()
{
}

void MainWindow::InitWindowElements()
{
}
