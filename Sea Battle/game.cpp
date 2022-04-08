//
// Created by User on 16.01.2022.
//
#include "game.h"

ConsoleView::ConsoleView(const std::string& typeFirst, const std::string& typeSecond){
    num = 0;
    if (typeFirst == "console")
        fst = new ConsoleGamer;
    else if (typeFirst == "random")
        fst = new RandomGamer;
    else if (typeFirst == "optimal")
        fst = new OptimalGamer;
    if (typeSecond == "console")
        snd = new ConsoleGamer(fst);
    else if (typeSecond == "random")
        snd = new RandomGamer(fst);
    else if (typeSecond == "optimal")
        snd = new OptimalGamer(fst);
    fst->setShips();
    snd->setShips();
}

ConsoleView::~ConsoleView(){
    delete fst;
    delete snd;
}

std::string ConsoleView::printing(Mark c){
    if (c == miss) return "miss";
    if (c == hit) return "hit";
    if (c == kill) return "kill";
    if (c == end) return "kill";
    return "";
}

int ConsoleView::gaming(){
    Gamer* act;
    std::string actGamer;
    if (num % 2){
        act = snd;
        actGamer = "Second";
    }
    else {
        act = fst;
        actGamer = "First";
    }
    Mark res = act->shot();
    if (res == miss)
        num++;
    std::cout << actGamer << " " << printing(res) << "\n";
    if (res == end){
        std::cout << actGamer << " win!\n";
        std::cout << "Winners field\n";
        act->lookField(false);
        return num % 2 + 1;
    }
    return 0;
}
