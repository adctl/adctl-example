#-------------------------------------------------
#
# Project created by QtCreator 2015-10-12T12:20:46
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = AdCtlApp
TEMPLATE = app

include(adctl/AdCtl.pri)

SOURCES += main.cpp\
        MainWindow.cpp

HEADERS  += MainWindow.h

FORMS    += MainWindow.ui

CONFIG += mobility
MOBILITY = 

QTADCTL_LIB_DIR = $$PWD/adctl

android {
  ANDROID_PACKAGE_SOURCE_DIR = $$PWD/platform/android
  android:QT += androidextras gui-private
  android:DISTFILES += $$ANDROID_PACKAGE_SOURCE_DIR/src/com/google/example/games/basegameutils/BaseGameUtils.java \
                       $$ANDROID_PACKAGE_SOURCE_DIR/src/com/google/example/games/basegameutils/GameHelper.java \
                       $$ANDROID_PACKAGE_SOURCE_DIR/src/com/google/example/games/basegameutils/GameHelperUtils.java \
                       $$ANDROID_PACKAGE_SOURCE_DIR/src/org/dreamdev/QtAdMob/QtAdMobActivity.java \
                       $$ANDROID_PACKAGE_SOURCE_DIR/src/ru/forsk/AdCtl/AdCtlActivity.java \
                       $$ANDROID_PACKAGE_SOURCE_DIR/res/values/strings.xml \
                       $$ANDROID_PACKAGE_SOURCE_DIR/AndroidManifest.xml \
                       $$ANDROID_PACKAGE_SOURCE_DIR/project.properties \
                       $$ANDROID_PACKAGE_SOURCE_DIR/proguard-project.txt \
                       $$ANDROID_PACKAGE_SOURCE_DIR/libs/*
}

ios {
  ios:QMAKE_CXXFLAGS += -fobjc-arc
  ios:QMAKE_LFLAGS += -ObjC
  ios:QT += gui_private
  ios:LIBS += -F $$PWD/QtAdMob/platform/ios/GoogleMobileAds \
    -framework GoogleMobileAds \
    -framework AVFoundation \
    -framework AudioToolbox \
    -framework CoreTelephony \
    -framework MessageUI \
    -framework SystemConfiguration \
    -framework CoreGraphics \
    -framework AdSupport \
    -framework StoreKit \
    -framework EventKit \
    -framework EventKitUI \
    -framework CoreMedia
}
