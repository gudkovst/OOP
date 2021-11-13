#include <iostream>
#include <fstream>
#include <map>
#include "working.h"


int main(int argc, char** argv) {
	using namespace std;

	ifstream in(argv[1]);
	ofstream out(argv[2]);

	if (!in) {
		cout << "No input file\n";
		return 0;
	}
	if (!out) {
		cout << "No output file\n";
		return 0;
	}

	map <string, int> dict = count_words(in);
	print(dict, out);

	in.close();
	out.close();
	return 0;
}
