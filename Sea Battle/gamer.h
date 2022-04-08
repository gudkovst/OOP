//
// Created by User on 16.01.2022.
//
#pragma once

#include <iostream>
#include <vector>
#include <cstdlib>

#include "field.h"


class Gamer{
protected:
    Gamer* enemy;
    Field MyField;
    Field EnemyField;
    int CountShips;

    void buryShip(); //корабль убит(похоронить)
    void makeBorder(int x, int y); //сделать окружение убитого корабля
    bool putShip(int shiplen, int x, int y, int orient); //поставить данный корабль
    void print(Mark c);
public:
    void appointEnemy(Gamer* protiv); //назначить противника
    Mark resShot(int x, int y); //выстрел по его клетке (x,y)
    void lookField(bool regime); //показать поле
    virtual void setShips() = 0; //расставить корабли
    virtual Mark shot() = 0; //выстрелить
    int getCountShips() const;
};

class ConsoleGamer : public Gamer{
public:
    ConsoleGamer(Gamer* protiv);
    ConsoleGamer();
    void setShips() override;
    Mark shot() override;
};

class ComputerGamer : public Gamer{
protected:
    bool woundedShip; //наличие раненого корабля у противника
    std::pair<int, int> lastShot;

    std::pair<Mark, std::pair<int, int>> killShip(std::pair<int, int> koord); //добить корабль
    std::vector<std::pair<int, int>> freeCells(int shiplen, int orient); //свободные клетки
    std::vector<std::pair<int, int>> emptyCells(); //клетки, куда ещё не стреляли
    std::vector<std::pair<int, int>> environment(std::pair<int, int> wound); //свободные клетки вокруг wound
};

class RandomGamer : public ComputerGamer{
public:
    RandomGamer();
    RandomGamer(Gamer* protiv);
    void setShips() override;
    Mark shot() override;
};

class OptimalGamer : public ComputerGamer{
public:
    OptimalGamer();
    OptimalGamer(Gamer* protiv);
    void setShips() override;
    Mark shot() override;
};
