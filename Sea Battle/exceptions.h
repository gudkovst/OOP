//
// Created by User on 19.01.2022.
//
#pragma once

#include <stdexcept>
#include <string>

bool playerTypeIsCorrect(const std::string& type);
void proverka(int n, const std::string& typeFirst, const std::string& typeSecond);

class TypePlayerError : public std::exception{
private:
    std::string meserr;
public:
    TypePlayerError(const std::string& msg);
    const char* what() const noexcept override;
};

class NumberError : public std::exception{
private:
    std::string meserr;
public:
    NumberError();
    const char* what() const noexcept override;
};
