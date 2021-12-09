#include <iostream>
#include <vector>
#include <string>

#include "parser.h"

int main(int argc, char** argv) {
	std::vector<std::string> text;
	std::string file = "workflow.txt";
	Parser p(file);
	try {
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
