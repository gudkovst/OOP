#include <iostream>
#include <vector>
#include <string>

#include "parser.h"

int main(int argc, char** argv) {
	std::vector<std::string> text;
	try {
		Parser p("C:\\Users\\s.gudkov\\source\\repos\\lab2\\Debug\\workflow.txt");
		p.parse();
		auto sequence = p.getSequence();
		auto workers = p.getBlocks();
		for (auto com : sequence)
			workers[com]->execute(text);
	}
	catch (std::exception& exp) {
		std::cerr << exp.what();
	}
	return 0;
}
