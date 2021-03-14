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
    QByteArrayData data[21];
    char stringdata0[193];
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
QT_MOC_LITERAL(5, 20, 22), // "resetBuracoSelecionado"
QT_MOC_LITERAL(6, 43, 4), // "int*"
QT_MOC_LITERAL(7, 48, 18), // "buraco_selecionado"
QT_MOC_LITERAL(8, 67, 9), // "showAbout"
QT_MOC_LITERAL(9, 77, 10), // "showVenceu"
QT_MOC_LITERAL(10, 88, 1), // "i"
QT_MOC_LITERAL(11, 90, 14), // "verificaVenceu"
QT_MOC_LITERAL(12, 105, 6), // "venceu"
QT_MOC_LITERAL(13, 112, 3), // "cor"
QT_MOC_LITERAL(14, 116, 12), // "movimentacao"
QT_MOC_LITERAL(15, 129, 5), // "index"
QT_MOC_LITERAL(16, 135, 15), // "mudaPecaDeLugar"
QT_MOC_LITERAL(17, 151, 15), // "restauraSelecao"
QT_MOC_LITERAL(18, 167, 5), // "Phase"
QT_MOC_LITERAL(19, 173, 9), // "DropPhase"
QT_MOC_LITERAL(20, 183, 9) // "MovePhase"

    },
    "Achi\0play\0\0id\0reset\0resetBuracoSelecionado\0"
    "int*\0buraco_selecionado\0showAbout\0"
    "showVenceu\0i\0verificaVenceu\0venceu\0"
    "cor\0movimentacao\0index\0mudaPecaDeLugar\0"
    "restauraSelecao\0Phase\0DropPhase\0"
    "MovePhase"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_Achi[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
      10,   14, // methods
       0,    0, // properties
       1,   98, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    1,   64,    2, 0x08 /* Private */,
       4,    0,   67,    2, 0x08 /* Private */,
       5,    1,   68,    2, 0x08 /* Private */,
       8,    0,   71,    2, 0x08 /* Private */,
       9,    1,   72,    2, 0x08 /* Private */,
      11,    2,   75,    2, 0x08 /* Private */,
      12,    2,   80,    2, 0x08 /* Private */,
      14,    2,   85,    2, 0x08 /* Private */,
      16,    2,   90,    2, 0x08 /* Private */,
      17,    1,   95,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void, QMetaType::Int,    3,
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 6,    7,
    QMetaType::Void,
    QMetaType::Void, QMetaType::Int,   10,
    QMetaType::Void, QMetaType::Int, 0x80000000 | 6,   10,    7,
    QMetaType::Void, QMetaType::Int, 0x80000000 | 6,   13,    7,
    QMetaType::Void, QMetaType::Int, 0x80000000 | 6,   15,    7,
    QMetaType::Void, QMetaType::Int, 0x80000000 | 6,   15,    7,
    QMetaType::Void, 0x80000000 | 6,    7,

 // enums: name, alias, flags, count, data
      18,   18, 0x0,    2,  103,

 // enum data: key, value
      19, uint(Achi::DropPhase),
      20, uint(Achi::MovePhase),

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
        case 2: _t->resetBuracoSelecionado((*reinterpret_cast< int*(*)>(_a[1]))); break;
        case 3: _t->showAbout(); break;
        case 4: _t->showVenceu((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 5: _t->verificaVenceu((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< int*(*)>(_a[2]))); break;
        case 6: _t->venceu((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< int*(*)>(_a[2]))); break;
        case 7: _t->movimentacao((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< int*(*)>(_a[2]))); break;
        case 8: _t->mudaPecaDeLugar((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< int*(*)>(_a[2]))); break;
        case 9: _t->restauraSelecao((*reinterpret_cast< int*(*)>(_a[1]))); break;
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
        if (_id < 10)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 10;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 10)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 10;
    }
    return _id;
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
