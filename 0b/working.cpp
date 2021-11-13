#include "working.h"

using namespace std;

bool comp(pair<int, string> a, pair<int, string> b) {
	return a.first > b.first;
}

map <string, int> count_words(ifstream& in){
	map <string, int> dict;
	while (!in.eof()) {
		string str;
		getline(in, str);
		stringstream strin(str);
		string substr;
		while (strin >> substr) {
			cmatch word;
			regex regular("^[a-zA-Zа-яёА-ЯЁ]+");
			if (regex_search(substr.c_str(), word, regular))
				dict[word.str()]++;
		}
	}
	return dict;
}

void print(map <string, int>& dict, ofstream& out){
	double n = 0;
	vector <pair<int, string>> res;
	for (const auto& i: dict) {
		res.push_back({ i.second, i.first });
		n += i.second;
	}
	sort(res.begin(), res.end(), comp);
	for (const auto& i: res)
		out << i.second << "," << i.first << "," << i.first / n * 100 << "%\n";
}

