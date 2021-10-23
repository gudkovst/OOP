#include "working.h"

using namespace std;

bool comp(pair<int, string> a, pair<int, string> b) {
	return a.first > b.first;
}

map <string, int> count_words(ifstream in){
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

void print(map <string, int> dict, ofstream out){
	double n = 0;
	vector <pair<int, string>> res;
	map <string, int> ::iterator it = dict.begin();
	for (; it != dict.end(); it++) {
		res.push_back({ it->second, it->first });
		n += it->second;
	}
	sort(res.begin(), res.end(), comp);
	vector <pair<int, string>> ::iterator itt = res.begin();
	for (; itt != res.end(); itt++)
		out << itt->second << "," << itt->first << "," << itt->first / n * 100 << "%\n";
}
