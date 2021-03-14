#ifndef ACHI_H
#define ACHI_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui {
    class Achi;
}
QT_END_NAMESPACE

class Hole;

class Achi : public QMainWindow {
    Q_OBJECT

public:
    Achi(QWidget *parent = nullptr);
    virtual ~Achi();

    enum Phase {
        DropPhase,
        MovePhase
    };
    Q_ENUM(Phase)

private:
    Ui::Achi *ui;
    Hole* m_holes[9];
    Hole* anterior;
    Phase m_phase;
    Hole* m_selected;
    int indice_anterior;

    int qtclicks;
    int anterior_estado;
    int anterior_indice = 0;
    //int buraco_selecionado[9];

private slots:
    void play(int id);
    //void reset(Hole* m_holes[9]);
    void reset();
    void resetBuracoSelecionado(int* buraco_selecionado);
    void showAbout();
    void showVenceu(int i);
    void verificaVenceu(int i, int* buraco_selecionado);
    void venceu(int cor, int* buraco_selecionado);
    void movimentacao(int index, int* buraco_selecionado);
    void mudaPecaDeLugar(int index, int* buraco_selecionado);
    void restauraSelecao(int* buraco_selecionado);

};

#endif // ACHI_H
