#include "working.h"

using namespace std;

bool comp(pair<int, string> a, pair<int, string> b) {
	return a.first > b.first;
}

map <string, int> count_words(ifstream& in) {
	map <string, int> dict;
	while (!in.eof()) {
		cmatch word;
		regex regular("^[a-zA-Zа-яёА-ЯЁ]+");
		string str;
		getline(in, str);
		while (size(str)) {
			if (regex_search(str.c_str(), word, regular)) {
				dict[word.str()]++;
				str.erase(0, size(word.str()));
			}
			else 
				str.erase(0, 1);
		}
	}
	return dict;
}

void print(map <string, int>& dict, ofstream& out) {
	double n = 0;
	vector <pair<int, string>> res;
	for (const auto& i : dict) {
		res.push_back({ i.second, i.first });
		n += i.second;
	}
	sort(res.begin(), res.end(), comp);
	for (const auto& i : res)
		out << i.second << "," << i.first << "," << i.first / n * 100 << "%\n";
}
