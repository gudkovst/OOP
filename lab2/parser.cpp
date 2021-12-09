#include "parser.h"

Parser::Parser(const std::string& filename) {
	fin.open(filename);
	ReadFactory = new WorkerReadFactory;
	WriteFactory = new WorkerWriteFactory;
	GrepFactory = new WorkerGrepFactory;
	SortFactory = new WorkerSortFactory;
	ReplaceFactory = new WorkerReplaceFactory;
	DumpFactory = new WorkerDumpFactory;
}

std::vector<int> Parser::getSequence() {
	return commandSequence;
}

std::map<int, Worker*> Parser::getBlocks() {
	return blocks;
}

std::vector<std::string> Parser::parseBlock(const std::string& buffer) {
	std::vector<std::string> res;
	int i = 0;
	for (; buffer[i] == ' ' && i < buffer.size(); i++);
	if (i == buffer.size()) throw DescriptionBlockError();
	res[0].clear();
	for (; std::isdigit(buffer[i]) && i < buffer.size(); i++)
		res[0] += buffer[i];
	if (i < buffer.size() - 3 && buffer[i] == ' ' && buffer[i + 1] == '='
		&& buffer[i + 2] == '=') i += 3;
	else throw DescriptionBlockError();
	res[1].clear();
	for (; buffer[i] != ' ' && i < buffer.size(); i++)
		res[1] += buffer[i];
	for (; buffer[i] == ' ' && i < buffer.size(); i++);
	res[2] = buffer.substr(i);
	return res;
}

std::vector<int> Parser::parseSequence(const std::string& buffer) {
	std::vector<int> res;
	int i = 0;
	bool prevOut = false;
	for (; buffer[i] == ' ' && i < buffer.size(); i++);
	if (i == buffer.size()) throw DescriptionSequenceError();
	while (i < buffer.size()) {
		if (!std::isdigit(buffer[i])) throw DescriptionSequenceError();
		int blockNum = 0;
		for (; std::isdigit(buffer[i]) && i < buffer.size(); i++)
			blockNum = blockNum * 10 + buffer[i] - '0';
		if (blocks[blockNum]->isInput() == prevOut)
			prevOut = blocks[blockNum]->isOutput();
		else throw DescriptionSequenceError();
		res.push_back(blockNum);
		if (i < buffer.size() - 4 && buffer[i] == ' ' && buffer[i + 1] == '-'
			&& buffer[i + 2] == '>' && buffer[i + 3] == ' ') i += 4;
		else throw DescriptionSequenceError();
	}
	return res;
}

void Parser::parse() {
	ParserState state = dream;
	while (!fin.eof()) {
		std::getline(fin, buffer);
		if (state == dream) {
			if (buffer == "desc") {
				state = open;
				continue;
			}
			else continue;
		}
		if (state == open) {
			if (buffer == "csed") {
				state = close;
				continue;
			}
			else {
				std::vector<std::string> block = parseBlock(buffer);
				int number = std::stoi(block[0]);
				if (block[1] == "readfile")
					blocks[number] = makeWorker(ReadFactory, block[2]);
				if (block[1] == "writefile")
					blocks[number] = makeWorker(WriteFactory, block[2]);
				if (block[1] == "grep")
					blocks[number] = makeWorker(GrepFactory, block[2]);
				if (block[1] == "sort")
					blocks[number] = makeWorker(SortFactory, block[2]);
				if (block[1] == "replace")
					blocks[number] = makeWorker(ReplaceFactory, block[2]);
				if (block[1] == "dump")
					blocks[number] = makeWorker(DumpFactory, block[2]);
				else throw UnknownWorker();
			}
		}
		if (state == close) {
			state = end;
			commandSequence = parseSequence(buffer);
		}
	}
	if (state != end) throw DelimiterError();
	fin.close();
}

