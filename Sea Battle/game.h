//
// Created by User on 16.01.2022.
//
#pragma once

#include <iostream>
#include <string>

#include "gamer.h"

class GameView{
protected:
    Gamer* fst;
    Gamer* snd;
    int num;
public:
    virtual int gaming() = 0;
};

class ConsoleView : public GameView{
protected:
    static std::string printing(Mark c);
public:
    ConsoleView(const std::string& typeFirst, const std::string& typeSecond);
    ~ConsoleView();
    int gaming() override;
};