//
// Created by User on 16.01.2022.
//

#pragma once

#include <iostream>
#include <vector>

enum Mark{
    ship, aroundShip, hit, miss, empty, kill, end
};

class FieldRow{
private:
    std::vector<Mark> fldr;
public:
    FieldRow();
    Mark& operator[](size_t index);
};

class Field{
private:
    std::vector<FieldRow> fld;
public:
    Field();
    FieldRow& operator[](size_t index);
};
