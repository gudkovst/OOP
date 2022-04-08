//
// Created by User on 16.01.2022.
//
#include "field.h"


FieldRow::FieldRow(){
    fldr.resize(10, empty);
}

Mark &FieldRow::operator[](size_t index) {
    return fldr[index];
}

Field::Field(){
    fld.resize(10, FieldRow());
}

FieldRow &Field::operator[](size_t index) {
    return fld[index];
}
