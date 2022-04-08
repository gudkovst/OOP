//
// Created by User on 19.01.2022.
//
#include "exceptions.h"

bool playerTypeIsCorrect(const std::string& type){
    return (type == "console" || type == "random" || type == "optimal");
}

void proverka(int n, const std::string& typeFirst, const std::string& typeSecond){
    if (n <= 0) throw NumberError();
    if (!playerTypeIsCorrect(typeFirst)) throw TypePlayerError("first");
    if (!playerTypeIsCorrect(typeSecond)) throw TypePlayerError("second");
}

TypePlayerError::TypePlayerError(const std::string& msg){
    meserr = "incorrect type of " + msg + " player\n";
}

const char* TypePlayerError::what() const noexcept {
    return meserr.c_str();
}

NumberError::NumberError() {
    meserr = "incorrect number of rounds\n";
}

const char* NumberError::what() const noexcept {
    return meserr.c_str();
}