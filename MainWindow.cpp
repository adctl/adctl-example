#include "MainWindow.h"
#include "ui_MainWindow.h"
#include "adctl/adctl.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow),
    m_AdCtl(new AdCtl(this))
{
    ui->setupUi(this);

    m_AdCtl->setAdMobId("ca-app-pub-7485900711629006/8288667458");
    m_AdCtl->setAdMobBannerEnabled(true);
    m_AdCtl->init();

    //m_AdCtl->signInGPGS();
    //m_AdCtl->showLeaderboardGPGS();
}

MainWindow::~MainWindow()
{
    delete ui;
    delete m_AdCtl;
}
