#include "Achi.h"
#include "ui_Achi.h"

#include <QMessageBox>
#include <QSignalMapper>

#include <QDebug>

#define BURACO_VAZIO 0
#define BURACO_VERMELHO 1
#define BURACO_AZUL 2
#define BURACO_SELECIONADO 10

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
    char versao = QT_VERSION_STR[0];                                        // Adiciona compatibilidade entre as versões 5 e 6 do Qt
    int intversao = versao - '0';
    if (versao == '6'){
        QObject::connect(map, SIGNAL(mappedInt(int)), this, SLOT(play(int)));
    }
    else{
        QObject::connect(map, SIGNAL(mapped(int)), this, SLOT(play(int)));
    }


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
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 1 || i == 3 || i == 4)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 1:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 0 || i == 4 || i == 2)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 2:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 1 || i == 4 || i == 5)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 3:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 0 || i == 4 || i == 6)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 4:
            if (buraco_selecionado[i] == BURACO_VAZIO){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 5:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 2 || i == 4 || i == 8)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 6:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 3 || i == 4 || i == 7)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 7:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 6 || i == 4 || i == 8)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
                hole->setState(Hole::SelectableState);
            }
            break;
        case 8:
            if (buraco_selecionado[i] == BURACO_VAZIO && (i == 4 || i == 5 || i == 7)){
                hole = m_holes[i];
                buraco_selecionado[i] = BURACO_SELECIONADO;
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
    anterior_estado = BURACO_VAZIO;
    anterior->setState(Hole::EmptyState);
    buraco_selecionado[anterior_indice] = BURACO_VAZIO;
    qtclicks++;
    verificaVenceu(buraco_selecionado[index], buraco_selecionado);
    if (buraco_selecionado[index] == BURACO_VERMELHO)
        ui->label->setText("   Fase de mover: vez do jogador azul");
    else if (buraco_selecionado[index] == BURACO_AZUL)
        ui->label->setText("   Fase de mover: vez do jogador vermelho");
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
            buraco_selecionado[i] = BURACO_VAZIO;
        }
    }

    Hole* hole = m_holes[index];
    Q_ASSERT(hole != nullptr); // pode ser preciso retirar


    qDebug() << QString("indice: (%1)").arg(index);
    qDebug() << QString("qtclicks: (%1)").arg(qtclicks);                          // Já estava aqui, printa infos no terminal
    qDebug() << QString("hole: (%1, %2)").arg(hole->row()).arg(hole->col());    // Já estava aqui, printa infos no terminal

    /// FASE DE COLOCAR AS PEÇAS NO TABULEIRO
    if (qtclicks <= 5){                                                          // Quando os jogadores ainda estão colocando suas 3 peças no tabuleiro
        if ((qtclicks%2 == 0) && (buraco_selecionado[index] == BURACO_VAZIO)){
            hole->setState(Hole::RedState);
            buraco_selecionado[index] = BURACO_VERMELHO;
            qDebug() << QString("buraco_selecionado: (%1)").arg(buraco_selecionado[index]);
            qtclicks++;
            verificaVenceu(buraco_selecionado[index], buraco_selecionado);
            ui->label->setText("   Fase de colocar: vez do jogador azul");
        }
        else if ((qtclicks%2 != 0) && (buraco_selecionado[index] == BURACO_VAZIO)){
            hole->setState(Hole::BlueState);
            buraco_selecionado[index] = BURACO_AZUL;
            qDebug() << QString("buraco_selecionado: (%1)").arg(buraco_selecionado[index]);
            qtclicks++;
            verificaVenceu(buraco_selecionado[index], buraco_selecionado);
            if (qtclicks >= 6)
                ui->label->setText("   Fase de mover: vez do jogador vermelho");
            else
                ui->label->setText("   Fase de colocar: vez do jogador vermelho");
        }
    }
    /// FASE DE MOVIMENTAR AS PEÇAS
    else{
        if (anterior_estado == BURACO_VAZIO){                                                    // Seleção da peça a ser movida
            if ((qtclicks%2 == 0) && (buraco_selecionado[index] == BURACO_VERMELHO)){               // Se a peça for vermelha
                movimentacao(index, buraco_selecionado);
            }
            else if ((qtclicks%2 != 0) && (buraco_selecionado[index] == BURACO_AZUL)){          // Se a peça for azul
                movimentacao(index, buraco_selecionado);
            }
        }
        else{                                                                   // Local para o qual a peça será movida
            if ((index != anterior_indice) && (buraco_selecionado[index] == BURACO_SELECIONADO)){
                if ((qtclicks%2 == 0) && (anterior_estado == BURACO_VERMELHO)){                               // Se for a vez do vermelho e a peça anteriormente selecioanda for vermelha
                    hole->setState(Hole::RedState);
                    mudaPecaDeLugar(index, buraco_selecionado);
                }
                else if ((qtclicks%2 != 0) && (anterior_estado == BURACO_AZUL)){                          // Se for a vez do azul e a peça anteriormente selecioanda for azul
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

    anterior_estado = BURACO_VAZIO;                                                      // Restaura a seleção da peça
    for (int i = 0; i < 9; i++){                                                        // Restaura a seleção da peça
        if (buraco_selecionado[i] == BURACO_SELECIONADO){
            hole = m_holes[i];
            buraco_selecionado[i] = BURACO_VAZIO;
            hole->setState(Hole::EmptyState);
        }
    }
}

//void Achi::reset(Hole* m_holes[9]) {
void Achi::reset() {
    qtclicks = 0;
    anterior_estado = 0;
    anterior_indice = 0;

//    Hole* hole = m_holes[0];
//    Q_ASSERT(hole != nullptr); // pode ser preciso retirar

    for (int id = 0; id < 9; ++id) {
//        hole = m_holes[id];
        //Q_ASSERT(hole != nullptr);

        m_holes[id]->reset();
//        hole->setState(Hole::EmptyState);
    }
}

void Achi::resetBuracoSelecionado(int* buraco_selecionado) {
    for (int id = 0; id < 9; ++id) {
        buraco_selecionado[id] = BURACO_VAZIO;
    }
}

void Achi::showAbout() {
    QMessageBox::information(this, tr("Sobre"), tr("Achi rules\n"
                                                   "- Cada jogador possui 3 peças\n"
                                                   "- O jogador vermelho é o primeiro a jogar\n"
                                                   "- O objetivo do jogo é montar uma linha com as 3 peças\n"
                                                   "- Apos inserir as peças é possível mover uma peça para um espaço vazio"));
}

void Achi::showVenceu(int i) {
    QMessageBox msgBox;
    if (i == BURACO_VERMELHO)
        msgBox.setText("Parabéns, o jogador Vermelho venceu!");
    else
        msgBox.setText("Parabéns, o jogador Azul venceu!");
    msgBox.exec();
}

void Achi::verificaVenceu(int cor, int* buraco_selecionado) {
    for (int i = 0; i < 9; i = i + 3){                                                                  // Procura nas linhas horizontais
        if (buraco_selecionado[i] == cor && buraco_selecionado[i+1] == cor && buraco_selecionado[i+2] == cor){
            venceu(cor, buraco_selecionado);
        }
    }
    if (buraco_selecionado[6] == cor && buraco_selecionado[4] == cor && buraco_selecionado[2] == cor){      // Procura na diagonal
        venceu(cor, buraco_selecionado);
    }
    else if (buraco_selecionado[0] == cor && buraco_selecionado[4] == cor && buraco_selecionado[8] == cor){      // Procura na diagonal
        venceu(cor, buraco_selecionado);
    }

    else if (buraco_selecionado[0] == cor && buraco_selecionado[3] == cor && buraco_selecionado[6] == cor){         // Procura na vertical
        venceu(cor, buraco_selecionado);
    }
    else if (buraco_selecionado[1] == cor && buraco_selecionado[4] == cor && buraco_selecionado[7] == cor){         // Procura na vertical
        venceu(cor, buraco_selecionado);
    }
    else if (buraco_selecionado[2] == cor && buraco_selecionado[5] == cor && buraco_selecionado[8] == cor){         // Procura na vertical
        venceu(cor, buraco_selecionado);
    }
}

void Achi::venceu(int cor, int* buraco_selecionado) {
    showVenceu(cor);
    reset();
    resetBuracoSelecionado(buraco_selecionado);
}
