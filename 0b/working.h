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

map <string, int> count_words(ifstream in);
void print(map <string, int> dict, ofstream out);
