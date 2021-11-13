#pragma once

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <map>
#include <vector>
#include <algorithm>
#include <regex>

bool comp(std::pair<int, std::string> a, std::pair<int, std::string> b);

std::map <std::string, int> count_words(std::ifstream& in);
void print(std::map <std::string, int>& dict, std::ofstream& out);
