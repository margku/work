QT       += core gui sql


greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++11

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    form_autf.cpp \
    form_del.cpp \
    form_game_athletes.cpp \
    form_game_discipline.cpp \
    form_game_game.cpp \
    form_game_judges.cpp \
    form_game_part_country.cpp \
    form_olymp_city.cpp \
    form_olymp_country.cpp \
    form_olymp_medal.cpp \
    form_olymp_object.cpp \
    form_olymp_olympiad.cpp \
    form_olymp_symbol.cpp \
    form_view_data.cpp \
    main.cpp \
    mainwindow.cpp

HEADERS += \
    form_autf.h \
    form_del.h \
    form_game_athletes.h \
    form_game_discipline.h \
    form_game_game.h \
    form_game_judges.h \
    form_game_part_country.h \
    form_olymp_city.h \
    form_olymp_country.h \
    form_olymp_medal.h \
    form_olymp_object.h \
    form_olymp_olympiad.h \
    form_olymp_symbol.h \
    form_view_data.h \
    mainwindow.h

FORMS += \
    form_autf.ui \
    form_del.ui \
    form_game_athletes.ui \
    form_game_discipline.ui \
    form_game_game.ui \
    form_game_judges.ui \
    form_game_part_country.ui \
    form_olymp_city.ui \
    form_olymp_country.ui \
    form_olymp_medal.ui \
    form_olymp_object.ui \
    form_olymp_olympiad.ui \
    form_olymp_symbol.ui \
    form_view_data.ui \
    mainwindow.ui

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

RESOURCES += \
    rec.qrc

DISTFILES +=
