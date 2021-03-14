/****************************************************************************
** Meta object code from reading C++ file 'Achi.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.12.8)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../Achi/Achi.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'Achi.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.12.8. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_Achi_t {
    QByteArrayData data[20];
    char stringdata0[176];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_Achi_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_Achi_t qt_meta_stringdata_Achi = {
    {
QT_MOC_LITERAL(0, 0, 4), // "Achi"
QT_MOC_LITERAL(1, 5, 4), // "play"
QT_MOC_LITERAL(2, 10, 0), // ""
QT_MOC_LITERAL(3, 11, 2), // "id"
QT_MOC_LITERAL(4, 14, 5), // "reset"
QT_MOC_LITERAL(5, 20, 9), // "showAbout"
QT_MOC_LITERAL(6, 30, 10), // "showVenceu"
QT_MOC_LITERAL(7, 41, 1), // "i"
QT_MOC_LITERAL(8, 43, 14), // "verificaVenceu"
QT_MOC_LITERAL(9, 58, 4), // "int*"
QT_MOC_LITERAL(10, 63, 18), // "buraco_selecionado"
QT_MOC_LITERAL(11, 82, 8), // "Hole*[9]"
QT_MOC_LITERAL(12, 91, 7), // "m_holes"
QT_MOC_LITERAL(13, 99, 12), // "movimentacao"
QT_MOC_LITERAL(14, 112, 5), // "index"
QT_MOC_LITERAL(15, 118, 15), // "mudaPecaDeLugar"
QT_MOC_LITERAL(16, 134, 15), // "restauraSelecao"
QT_MOC_LITERAL(17, 150, 5), // "Phase"
QT_MOC_LITERAL(18, 156, 9), // "DropPhase"
QT_MOC_LITERAL(19, 166, 9) // "MovePhase"

    },
    "Achi\0play\0\0id\0reset\0showAbout\0showVenceu\0"
    "i\0verificaVenceu\0int*\0buraco_selecionado\0"
    "Hole*[9]\0m_holes\0movimentacao\0index\0"
    "mudaPecaDeLugar\0restauraSelecao\0Phase\0"
    "DropPhase\0MovePhase"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_Achi[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       8,   14, // methods
       0,    0, // properties
       1,   76, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    1,   54,    2, 0x08 /* Private */,
       4,    0,   57,    2, 0x08 /* Private */,
       5,    0,   58,    2, 0x08 /* Private */,
       6,    1,   59,    2, 0x08 /* Private */,
       8,    3,   62,    2, 0x08 /* Private */,
      13,    1,   69,    2, 0x08 /* Private */,
      15,    1,   72,    2, 0x08 /* Private */,
      16,    0,   75,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void, QMetaType::Int,    3,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::Int,    7,
    QMetaType::Void, QMetaType::Int, 0x80000000 | 9, 0x80000000 | 11,    7,   10,   12,
    QMetaType::Void, QMetaType::Int,   14,
    QMetaType::Void, QMetaType::Int,   14,
    QMetaType::Void,

 // enums: name, alias, flags, count, data
      17,   17, 0x0,    2,   81,

 // enum data: key, value
      18, uint(Achi::DropPhase),
      19, uint(Achi::MovePhase),

       0        // eod
};

void Achi::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<Achi *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->play((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 1: _t->reset(); break;
        case 2: _t->showAbout(); break;
        case 3: _t->showVenceu((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 4: _t->verificaVenceu((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< int*(*)>(_a[2])),(*reinterpret_cast< Hole*(*)[9]>(_a[3]))); break;
        case 5: _t->movimentacao((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 6: _t->mudaPecaDeLugar((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 7: _t->restauraSelecao(); break;
        default: ;
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject Achi::staticMetaObject = { {
    &QMainWindow::staticMetaObject,
    qt_meta_stringdata_Achi.data,
    qt_meta_data_Achi,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *Achi::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *Achi::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_Achi.stringdata0))
        return static_cast<void*>(this);
    return QMainWindow::qt_metacast(_clname);
}

int Achi::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 8)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 8;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 8)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 8;
    }
    return _id;
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
