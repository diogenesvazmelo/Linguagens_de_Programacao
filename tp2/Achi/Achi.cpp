#include "Achi.h"
#include "ui_Achi.h"

#include <QMessageBox>
#include <QSignalMapper>

#include <QDebug>

Achi::Achi(QWidget *parent)
    : QMainWindow(parent),
      ui(new Ui::Achi),
      anterior(nullptr),
      m_phase(Achi::DropPhase),
      m_selected(nullptr),
      indice_anterior(),

      qtclicks(0),
      anterior_estado(0),
      anterior_indice(0){
      //buraco_selecionado(){

    ui->setupUi(this);

    QObject::connect(ui->actionNew, SIGNAL(triggered(bool)), this, SLOT(reset()));
    QObject::connect(ui->actionQuit, SIGNAL(triggered(bool)), qApp, SLOT(quit()));
    QObject::connect(ui->actionAbout, SIGNAL(triggered(bool)), this, SLOT(showAbout()));

    // Load the pieces.
    QSignalMapper* map = new QSignalMapper(this);
    for (int id = 0; id < 9; ++id) {
        int r = id / 3;
        int c = id % 3;

        Hole* hole = this->findChild<Hole*>(QString("hole%1%2").arg(r).arg(c));
        Q_ASSERT(hole != nullptr);

        m_holes[id] = hole;
        map->setMapping(hole, id);
        QObject::connect(hole, SIGNAL(clicked()), map, SLOT(map()));
    }
    QObject::connect(map, SIGNAL(mappedInt(int)), this, SLOT(play(int)));

    // Compact the layout of the widgets.
    this->adjustSize();

    // Set a fixed window size.
    this->setFixedSize(this->size());
}

Achi::~Achi() {
    delete ui;
}

void Achi::movimentacao(int index, int* buraco_selecionado){
    Hole* hole = m_holes[index];
    Q_ASSERT(hole != nullptr);

    anterior = hole;
    anterior_estado = buraco_selecionado[index];
    anterior_indice = index;

    for (int i = 0; i < 9; i++){
        switch (index){
        case 0:
            if (buraco_selecionado[i] == 0 && (i == 1 || i == 3 || i == 4)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 1:
            if (buraco_selecionado[i] == 0 && (i == 0 || i == 4 || i == 2)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 2:
            if (buraco_selecionado[i] == 0 && (i == 1 || i == 4 || i == 5)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 3:
            if (buraco_selecionado[i] == 0 && (i == 0 || i == 4 || i == 6)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 4:
            if (buraco_selecionado[i] == 0){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 5:
            if (buraco_selecionado[i] == 0 && (i == 2 || i == 4 || i == 8)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 6:
            if (buraco_selecionado[i] == 0 && (i == 3 || i == 4 || i == 7)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 7:
            if (buraco_selecionado[i] == 0 && (i == 6 || i == 4 || i == 8)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 8:
            if (buraco_selecionado[i] == 0 && (i == 4 || i == 5 || i == 7)){
                hole = m_holes[i];
                buraco_selecionado[i] = 10;
                hole->setState(Hole::SelectableState);
            }
            break;
        default:
            break;
        }
    }
}

void Achi::mudaPecaDeLugar(int index, int* buraco_selecionado){
    buraco_selecionado[index] = anterior_estado;
    anterior_estado = 0;
    anterior->setState(Hole::EmptyState);
    buraco_selecionado[anterior_indice] = 0;
    verificaVenceu(buraco_selecionado[index], buraco_selecionado);
    qtclicks++;
}

void Achi::play(int index) {
////////////////////////////////////////////////////////////////////////////////////////////
///
///   "buraco_selecionado" é um vetor que guarda o estado de cada um dos 9 buracos (holes),
///   de forma que:
///                 0- buraco vazio
///                 1- buraco ocupado por uma peça vermelha
///                 2- buraco ocupado por uma peça azul
///                10- buraco selecionado
///
///   "qtcliks" conta a quantidade de rodadas. As rodadas pares são do jogador vermelho,
///   e as ímpares do jogador azul.
///
///   O "index" identifica qual buraco é, indo de 0 a 8.
///
///   "anterior_estado" guarda o estado do buraco selecionado anteriormente (0, 1, 2 ou 10)
///
///   "anterior_indice" guarda qual foi o buraco selecionado anteriormente (de 0 a 8)
///
////////////////////////////////////////////////////////////////////////////////////////////


//    static int qtclicks = 0;
//    static int anterior_estado = 0;
//    static int anterior_indice = 0;
    static int buraco_selecionado[9];

    qDebug() << QString("qtclicks: (%1)").arg(qtclicks);
    if (qtclicks == 0){
        for (int i = 0; i < 9; i++)                                             // Inicializa todos como zero
        {
            buraco_selecionado[i] = 0;
        }
    }

    Hole* hole = m_holes[index];
    Q_ASSERT(hole != nullptr); // pode ser preciso retirar


    qDebug() << QString("indice: (%1)").arg(index);
    qDebug() << QString("qtclicks: (%1)").arg(qtclicks);                          // Já estava aqui, printa infos no terminal
    qDebug() << QString("hole: (%1, %2)").arg(hole->row()).arg(hole->col());    // Já estava aqui, printa infos no terminal

    /// FASE DE COLOCAR AS PEÇAS NO TABULEIRO
    if (qtclicks <= 5){                                                          // Quando os jogadores ainda estão colocando suas 3 peças no tabuleiro
        if ((qtclicks%2 == 0) && (buraco_selecionado[index] == 0)){
            hole->setState(Hole::RedState);
            buraco_selecionado[index] = 1;
            qDebug() << QString("buraco_selecionado: (%1)").arg(buraco_selecionado[index]);
            verificaVenceu(buraco_selecionado[index], buraco_selecionado);
            qtclicks++;
        }
        else if ((qtclicks%2 != 0) && (buraco_selecionado[index] == 0)){
            hole->setState(Hole::BlueState);
            buraco_selecionado[index] = 2;
            qDebug() << QString("buraco_selecionado: (%1)").arg(buraco_selecionado[index]);
            verificaVenceu(buraco_selecionado[index], buraco_selecionado);
            qtclicks++;
        }
    }
    /// FASE DE MOVIMENTAR AS PEÇAS
    else{
        for (int i = 0; i < 9; i++){
            qDebug() << QString("buraco_selecionado pos. (%1): (%2)").arg(i).arg(buraco_selecionado[index]);
        }
        if (anterior_estado == 0){                                                    // Seleção da peça a ser movida
            if ((qtclicks%2 == 0) && (buraco_selecionado[index] == 1)){               // Se a peça for vermelha
                movimentacao(index, buraco_selecionado);
            }
            else if ((qtclicks%2 != 0) && (buraco_selecionado[index] == 2)){          // Se a peça for azul
                movimentacao(index, buraco_selecionado);
            }
        }
        else{                                                                   // Local para o qual a peça será movida
            if ((index != anterior_indice) && (buraco_selecionado[index] == 10)){
                if ((qtclicks%2 == 0) && (anterior_estado == 1)){                               // Se for a vez do vermelho e a peça anteriormente selecioanda for vermelha
                    hole->setState(Hole::RedState);
                    mudaPecaDeLugar(index, buraco_selecionado);
                }
                else if ((qtclicks%2 != 0) && (anterior_estado == 2)){                          // Se for a vez do azul e a peça anteriormente selecioanda for azul
                    hole->setState(Hole::BlueState);
                    mudaPecaDeLugar(index, buraco_selecionado);
                }
            }
            restauraSelecao(buraco_selecionado);
        }
    }
}

void Achi::restauraSelecao(int* buraco_selecionado){
    Hole* hole;

    anterior_estado = 0;                                                      // Restaura a seleção da peça
    for (int i = 0; i < 9; i++){                                        // Restaura a seleção da peça
        if (buraco_selecionado[i] == 10){
            hole = m_holes[i];
            buraco_selecionado[i] = 0;
            hole->setState(Hole::EmptyState);
        }
    }
}

//void Achi::reset(Hole* m_holes[9]) {
void Achi::reset(int* buraco_selecionado) {
    qtclicks = -1;
    anterior_estado = 0;
    anterior_indice = 0;

//    Hole* hole = m_holes[0];
//    Q_ASSERT(hole != nullptr); // pode ser preciso retirar

    for (int id = 0; id < 9; ++id) {
//        hole = m_holes[id];
        //Q_ASSERT(hole != nullptr);

        m_holes[id]->reset();
//        hole->setState(Hole::EmptyState);
        buraco_selecionado[id] = 0;
    }
}

void Achi::showAbout() {
    QMessageBox::information(this, tr("Sobre"), tr("Achi"));
}

void Achi::showVenceu(int i) {
    QMessageBox msgBox;
    if (i == 1)
        msgBox.setText("Parabéns, o jogador Vermelho venceu!");
    else
        msgBox.setText("Parabéns, o jogador Azul venceu!");
    msgBox.exec();
}

void Achi::verificaVenceu(int cor, int* buraco_selecionado) {
    for (int i = 0; i < 9; i = i + 3){                                                                  // Procura nas linhas horizontais
        if (buraco_selecionado[i] == cor && buraco_selecionado[i+1] == cor && buraco_selecionado[i+2] == cor){
            showVenceu(cor);
            reset(buraco_selecionado);
        }
    }
    if (buraco_selecionado[6] == cor && buraco_selecionado[4] == cor && buraco_selecionado[2] == cor){      // Procura na diagonal
        showVenceu(cor);
        reset(buraco_selecionado);
    }
    else if (buraco_selecionado[0] == cor && buraco_selecionado[4] == cor && buraco_selecionado[8] == cor){      // Procura na diagonal
        showVenceu(cor);
        reset(buraco_selecionado);
    }

    else if (buraco_selecionado[0] == cor && buraco_selecionado[3] == cor && buraco_selecionado[6] == cor){         // Procura na vertical
        showVenceu(cor);
        reset(buraco_selecionado);
    }
    else if (buraco_selecionado[1] == cor && buraco_selecionado[4] == cor && buraco_selecionado[7] == cor){         // Procura na vertical
        showVenceu(cor);
        reset(buraco_selecionado);
    }
    else if (buraco_selecionado[2] == cor && buraco_selecionado[5] == cor && buraco_selecionado[8] == cor){         // Procura na vertical
        showVenceu(cor);
        reset(buraco_selecionado);
    }
}
